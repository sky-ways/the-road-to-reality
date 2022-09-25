package com.github.cao.awa.trtr.tool.hammer.wooden;

import com.github.cao.awa.trtr.tool.hammer.*;
import com.github.cao.awa.trtr.tool.hammer.iron.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class WoodenHammer extends Hammer {
    public static final Identifier IDENTIFIER = new Identifier("trtr:wooden_hammer");

    public WoodenHammer() {
        super(ToolMaterials.WOOD, 2, - 3.3F, 0.6F);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
