package com.github.cao.awa.trtr.recipe.handing;

import com.github.cao.awa.trtr.codec.TrtrCodecs;
import com.github.cao.awa.trtr.math.Mathematics;
import com.github.cao.awa.trtr.math.range.IntegerRange;
import com.github.cao.awa.trtr.pair.bool.BooleanPair;
import com.github.cao.awa.trtr.pair.item.ItemStackPair;
import com.github.cao.awa.trtr.recipe.handing.inventory.HandCraftingInventory;
import com.github.cao.awa.trtr.recipe.serializer.TrtrRecipeSerializer;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeCodecs;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

public class HandCraftingRecipe implements Recipe<HandCraftingInventory> {
    private final boolean stopOnUsage;
    private final ItemStack result;
    private final ItemStackPair input;
    private final IntegerRange range;
    private final BooleanPair doConsume;

    public HandCraftingRecipe(ItemStackPair input, ItemStack result, boolean stopOnUsage, IntegerRange range, BooleanPair doConsume) {
        this.input = input;
        this.result = result;
        this.stopOnUsage = stopOnUsage;
        this.range = range;
        this.doConsume = doConsume;
    }

    @Override
    public boolean matches(HandCraftingInventory inventory, World world) {
        System.out.println(inventory.mainStack()
                                    .getItem() + ":" + this.input.main()
                                                                 .getItem());
        System.out.println(inventory.offStack()
                                    .getItem() + ":" + this.input.off()
                                                                 .getItem());
        System.out.println(inventory.usedTicks() + ":" + Mathematics.inRange(inventory.usedTicks(),
                                                                             this.range
        ));
        if (inventory.remainingTicks() == 0 && ! stopOnUsage()) {
            return false;
        }
        return inventory.mainStack()
                        .getItem() == this.input.main()
                                                .getItem()
                && inventory.offStack()
                            .getItem() == this.input.off()
                                                    .getItem()
                && Mathematics.inRange(inventory.usedTicks(),
                                       this.range
        );
    }

    @Override
    public ItemStack craft(HandCraftingInventory inventory, DynamicRegistryManager registryManager) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        System.out.println(width + ":" + height);
        return false;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TrtrRecipeSerializer.HAND_CRAFTING;
    }

    public boolean stopOnUsage() {
        return this.stopOnUsage;
    }

    public ItemStack result() {
        return this.result;
    }

    public ItemStackPair input() {
        return this.input;
    }

    public IntegerRange range() {
        return this.range;
    }

    public BooleanPair doConsume() {
        return this.doConsume;
    }

    public boolean doMainConsume() {
        return this.doConsume.main();
    }

    public boolean doOffConsume() {
        return this.doConsume.off();
    }

    @Override
    public RecipeType<?> getType() {
        return TrtrRecipeType.HAND_CRAFTING;
    }

    public static class Serializer implements RecipeSerializer<HandCraftingRecipe> {
        private static final Codec<HandCraftingRecipe> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(
                                   TrtrCodecs.ITEM_STACK_PAIR.fieldOf("input")
                                                             .forGetter(HandCraftingRecipe :: input),
                                   RecipeCodecs.CRAFTING_RESULT.fieldOf("result")
                                                               .forGetter(HandCraftingRecipe :: result),
                                   Codec.BOOL.fieldOf("stop_on_usage")
                                             .forGetter(HandCraftingRecipe :: stopOnUsage),
                                   TrtrCodecs.INTEGER_RANGE.fieldOf("range")
                                                           .forGetter(HandCraftingRecipe :: range),
                                   TrtrCodecs.BOOLEAN_PAIR.fieldOf("do_consume")
                                                          .forGetter(HandCraftingRecipe :: doConsume)
                           )
                           .apply(instance,
                                  HandCraftingRecipe :: new
                           );
        });

        @Override
        public Codec<HandCraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public HandCraftingRecipe read(PacketByteBuf buf) {
            // Read input.
            ItemStackPair input = ItemStackPair.create(buf);

            // Read result.
            ItemStack result = buf.readItemStack();

            // Read stop on usage.
            boolean stopOnUsage = buf.readBoolean();

            // Read scaled crafting range.
            IntegerRange range = IntegerRange.create(buf);

            // Read item do consume.
            BooleanPair doConsume = BooleanPair.create(buf);

            // Create recipe.
            return new HandCraftingRecipe(input,
                                          result,
                                          stopOnUsage,
                                          range,
                                          doConsume
            );
        }

        @Override
        public void write(PacketByteBuf buf, HandCraftingRecipe recipe) {
            // Write input.
            recipe.input()
                  .write(buf);

            // Write result.
            buf.writeItemStack(recipe.result());

            // Write stop on usage.
            buf.writeBoolean(recipe.stopOnUsage());

            // Write scaled crafting range.
            recipe.range()
                  .write(buf);

            // Write item do consume.
            recipe.doConsume()
                  .write(buf);
        }
    }
}
