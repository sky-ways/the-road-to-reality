package com.github.cao.awa.trtr.block.ore.aluminum.alunite;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.annotations.DataGen;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.block.ore.aluminum.alunite.loot.AluniteLoot;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;

@Auto
public class Alunite extends TrtrBlock {
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "alunite_ore"
    );

    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.STONE,
                                                                              MapColor.GRAY
                                                                          )
                                                                          .requiresTool()
                                                                          .strength(1.5F,
                                                                                    6.0F
                                                                          );

    public static AluniteOreItem ITEM;

    @DataGen
    public static AluniteLoot LOOT;

    public Alunite(Settings settings) {
        super(settings);
    }
}
