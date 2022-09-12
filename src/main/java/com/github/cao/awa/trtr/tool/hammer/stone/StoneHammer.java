package com.github.cao.awa.trtr.tool.hammer.stone;

import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.cao.awa.trtr.tool.hammer.iron.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class StoneHammer extends Hammer {
    public static final Identifier IDENTIFIER = new Identifier("trtr:stone_hammer");

    public StoneHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static Item register() {
        Settings settings = defaultSettings();
        StoneHammer item = new StoneHammer(ToolMaterials.STONE, 3, - 3.2F, settings);
        Registry.register(Registry.ITEM, IDENTIFIER, item);
        return item;
    }
}
