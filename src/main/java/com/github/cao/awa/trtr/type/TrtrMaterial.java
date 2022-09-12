package com.github.cao.awa.trtr.type;

import net.minecraft.block.*;
import net.minecraft.block.piston.*;
import net.minecraft.item.*;
import net.minecraft.recipe.*;

public class TrtrMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
