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

    public IronHammer() {
        super(ToolMaterials.IRON, 4, - 3.1F, 0.9F);
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
