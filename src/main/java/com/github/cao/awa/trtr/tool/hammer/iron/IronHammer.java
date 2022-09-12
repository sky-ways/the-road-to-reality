package com.github.cao.awa.trtr.tool.hammer.iron;

import com.github.cao.awa.trtr.tool.hammer.*;
import net.minecraft.client.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class IronHammer extends Hammer {
    public static final Identifier IDENTIFIER = new Identifier("trtr:iron_hammer");

    public IronHammer(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static Item register() {
        Settings settings = defaultSettings();
        IronHammer item = new IronHammer(ToolMaterials.IRON, 4, - 3.1F, settings);
        Registry.register(Registry.ITEM, IDENTIFIER, item);
        return item;
    }
}
