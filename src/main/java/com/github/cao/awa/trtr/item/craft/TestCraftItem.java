package com.github.cao.awa.trtr.item.craft;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Auto
public class TestCraftItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:test_crafting");

    @Auto
    public TestCraftItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> craft(World world, PlayerEntity user, ItemStack craftingStack, ItemStack targetStack) {
        System.out.println("Crafting: " + craftingStack + "/" + targetStack);
        user.giveItemStack(new ItemStack(Items.BEDROCK,
                                         1
        ));
        return TypedActionResult.success(targetStack);
    }
}
