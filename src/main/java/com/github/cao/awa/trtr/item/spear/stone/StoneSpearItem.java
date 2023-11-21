package com.github.cao.awa.trtr.item.spear.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.data.gen.model.handheld.HandheldItemModelProvider;
import com.github.cao.awa.trtr.item.spear.SpearItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

@Auto
public class StoneSpearItem extends SpearItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_spear");
    @Auto
    public static final FabricItemSettings SETTINGS = new FabricItemSettings();
    @Auto
    public static HandheldItemModelProvider MODEL;

    @Auto
    public StoneSpearItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 100;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }
}
