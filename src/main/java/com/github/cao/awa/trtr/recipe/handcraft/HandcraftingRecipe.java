package com.github.cao.awa.trtr.recipe.handcraft;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.inaction.DoNotCall;
import com.github.cao.awa.trtr.codec.TrtrCodecs;
import com.github.cao.awa.trtr.math.Mathematics;
import com.github.cao.awa.trtr.math.range.IntegerRange;
import com.github.cao.awa.trtr.pair.ingredient.IngredientPair;
import com.github.cao.awa.trtr.pair.integer.IntegerPair;
import com.github.cao.awa.trtr.recipe.handcraft.inventory.HandcraftingInventory;
import com.github.cao.awa.trtr.recipe.serializer.TrtrRecipeSerializer;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeCodecs;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class HandcraftingRecipe implements Recipe<HandcraftingInventory> {
    private final boolean stopOnUsage;
    private final List<ItemStack> result;
    private final IngredientPair input;
    private final IntegerRange range;
    private final IntegerPair doConsume;
    private final boolean anyHand;
    private final Map<Item, Integer> consumeMap = ApricotCollectionFactor.hashMap();

    public HandcraftingRecipe(IngredientPair input, List<ItemStack> result, boolean stopOnUsage, IntegerRange range, IntegerPair doConsume, boolean anyHand) {
        this.input = input;
        this.result = result;
        this.stopOnUsage = stopOnUsage;
        this.range = range;
        this.doConsume = doConsume;
        this.anyHand = anyHand;
    }

    @Override
    public boolean matches(HandcraftingInventory inventory, World world) {
        if (inventory.remainingTicks() == 0 && ! stopOnUsage()) {
            return false;
        }

        boolean match = Mathematics.inRange(inventory.usedTicks(),
                                            this.range
        );

        if (anyHand()) {
            boolean isMainMatch = this.input.main()
                                            .test(inventory.mainStack());
            isMainMatch &= inventory.mainStack()
                                    .getCount() >= doMainConsume();
            if (isMainMatch) {
                match &= this.input.off()
                                   .test(inventory.offStack());
                match &= inventory.offStack()
                                  .getCount() >= doOffConsume();
            } else {
                match &= this.input.main()
                                   .test(inventory.offStack());
                match &= inventory.mainStack()
                                  .getCount() >= doOffConsume();

                match &= this.input.off()
                                   .test(inventory.mainStack());
                match &= inventory.offStack()
                                  .getCount() >= doMainConsume();

            }
        } else {
            match &= this.input.main()
                               .test(inventory.mainStack());
            match &= this.input.off()
                               .test(inventory.offStack());
            match &= inventory.mainStack()
                              .getCount() >= doMainConsume();
            match &= inventory.offStack()
                              .getCount() >= doOffConsume();
        }

        return match;
    }

    public void consume(ItemStack mainStack, ItemStack offStack) {
        if (this.input.main()
                      .test(mainStack)) {
            if (this.input.off()
                          .test(offStack)) {
                mainStack.decrement(doMainConsume());
                offStack.decrement(doOffConsume());
            }
        } else {
            if (this.input.main()
                          .test(offStack)) {
                if (this.input.off()
                              .test(mainStack)) {
                    mainStack.decrement(doOffConsume());
                    offStack.decrement(doMainConsume());
                }
            }
        }
    }

    public boolean ingredientMatches(HandcraftingInventory inventory, World world) {
        if (anyHand()) {
            boolean match;
            boolean isMainMatch = this.input.main()
                                            .test(inventory.mainStack());
            isMainMatch &= inventory.mainStack()
                                    .getCount() >= doMainConsume();
            if (isMainMatch) {
                match = this.input.off()
                                  .test(inventory.offStack());
                match &= inventory.offStack()
                                  .getCount() >= doOffConsume();
            } else {
                match = this.input.main()
                                  .test(inventory.offStack());
                match &= inventory.mainStack()
                                  .getCount() >= doOffConsume();

                match &= this.input.off()
                                   .test(inventory.mainStack());
                match &= inventory.offStack()
                                  .getCount() >= doMainConsume();

            }

            return match;
        } else {
            return this.input.main()
                             .test(inventory.mainStack())
                    && this.input.off()
                                 .test(inventory.offStack())
                    && (inventory.mainStack()
                                 .getCount() >= this.doConsume.main()
                    && inventory.offStack()
                                .getCount() >= this.doConsume.off()
            );
        }
    }

    @Override
    @DoNotCall
    public ItemStack craft(HandcraftingInventory inventory, DynamicRegistryManager registryManager) {
        throw new UnsupportedOperationException();
    }

    public List<ItemStack> craft(HandcraftingInventory inventory) {
        List<ItemStack> result = ApricotCollectionFactor.arrayList();
        for (ItemStack stack : this.result) {
            result.add(stack.copy());
        }
        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.result.size() > 0 ? this.result.get(0) : null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TrtrRecipeSerializer.HANDCRAFTING;
    }

    public boolean stopOnUsage() {
        return this.stopOnUsage;
    }

    public List<ItemStack> result() {
        return this.result;
    }

    public IngredientPair input() {
        return this.input;
    }

    public IntegerRange range() {
        return this.range;
    }

    public IntegerPair doConsume() {
        return this.doConsume;
    }

    public int doMainConsume() {
        return this.doConsume.main();
    }

    public int doOffConsume() {
        return this.doConsume.off();
    }

    public boolean anyHand() {
        return this.anyHand;
    }

    @Override
    public RecipeType<?> getType() {
        return TrtrRecipeType.HAND_CRAFTING;
    }

    public static class Serializer implements RecipeSerializer<HandcraftingRecipe> {
        private static final Codec<HandcraftingRecipe> CODEC = RecordCodecBuilder.create((instance) -> {
            return instance.group(
                                   TrtrCodecs.INGREDIENT_PAIR.fieldOf("input")
                                                             .forGetter(HandcraftingRecipe :: input),
                                   RecipeCodecs.CRAFTING_RESULT.listOf()
                                                               .fieldOf("result")
                                                               .forGetter(HandcraftingRecipe :: result),
                                   Codec.BOOL.fieldOf("stop_on_usage")
                                             .forGetter(HandcraftingRecipe :: stopOnUsage),
                                   TrtrCodecs.INTEGER_RANGE.fieldOf("range")
                                                           .forGetter(HandcraftingRecipe :: range),
                                   TrtrCodecs.INTEGER_PAIR.fieldOf("do_consume")
                                                          .forGetter(HandcraftingRecipe :: doConsume),
                                   Codec.BOOL.fieldOf("any_hand")
                                             .orElse(false)
                                             .forGetter(HandcraftingRecipe :: anyHand)
                           )
                           .apply(instance,
                                  HandcraftingRecipe :: new
                           );
        });

        @Override
        public Codec<HandcraftingRecipe> codec() {
            return CODEC;
        }

        @Override
        public HandcraftingRecipe read(PacketByteBuf buf) {
            // Read input.
            IngredientPair input = IngredientPair.create(buf);

            // Read result.
            int capcity = buf.readInt();
            List<ItemStack> result = ApricotCollectionFactor.arrayList(capcity);
            for (int i = 0; i < capcity; i++) {
                ItemStack stack = buf.readItemStack();
                result.add(stack);
            }

            // Read stop on usage.
            boolean stopOnUsage = buf.readBoolean();

            // Read scaled crafting range.
            IntegerRange range = IntegerRange.create(buf);

            // Read item do consume.
            IntegerPair doConsume = IntegerPair.create(buf);

            // Read any hand flag.
            boolean anyHand = buf.readBoolean();

            // Create recipe.
            return new HandcraftingRecipe(input,
                                          result,
                                          stopOnUsage,
                                          range,
                                          doConsume,
                                          anyHand
            );
        }

        @Override
        public void write(PacketByteBuf buf, HandcraftingRecipe recipe) {
            // Write input.
            recipe.input()
                  .write(buf);

            // Write result.
            buf.writeInt(recipe.result()
                               .size());
            for (ItemStack stack : recipe.result()) {
                buf.writeItemStack(stack);
            }

            // Write stop on usage.
            buf.writeBoolean(recipe.stopOnUsage());

            // Write scaled crafting range.
            recipe.range()
                  .write(buf);

            // Write item do consume.
            recipe.doConsume()
                  .write(buf);

            // Write any hand flag.
            buf.writeBoolean(recipe.anyHand());
        }
    }
}
