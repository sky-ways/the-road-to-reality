package com.github.cao.awa.trtr.block.ore.aluminum.alunite;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.block.TrtrBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;

@Auto
public class TestOre extends TrtrBlock {
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "test_ore"
    );

    public static final Class<AluniteOreItem> ITEM = AluniteOreItem.class;

    public TestOre(Settings settings) {
        super(settings);
    }
}
