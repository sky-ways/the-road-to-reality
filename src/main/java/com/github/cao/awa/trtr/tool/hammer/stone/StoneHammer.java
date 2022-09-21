package com.github.cao.awa.trtr.tool.hammer.stone;

import com.github.cao.awa.trtr.tool.hammer.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class StoneHammer extends Hammer {
    public static final Identifier IDENTIFIER = new Identifier("trtr:stone_hammer");

    public StoneHammer() {
        super(ToolMaterials.STONE, 3, - 3.2F);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
