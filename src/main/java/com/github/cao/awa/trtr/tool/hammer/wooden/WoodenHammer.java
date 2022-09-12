package com.github.cao.awa.trtr.tool.hammer.wooden;

import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.cao.awa.trtr.tool.hammer.iron.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class WoodenHammer extends Hammer {
    public static final Identifier IDENTIFIER = new Identifier("trtr:wooden_hammer");

    public WoodenHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static Item register() {
        Settings settings = defaultSettings();
        WoodenHammer item = new WoodenHammer(ToolMaterials.WOOD, 2, - 3.3F, settings);
        Registry.register(Registry.ITEM, IDENTIFIER, item);
        return item;
    }
}
