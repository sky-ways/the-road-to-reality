package com.github.cao.awa.trtr.block.ore.iron.magnetite;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.mine.PickaxeMining;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.data.gen.loot.GenericBlockLootProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

@Auto
@PickaxeMining(MiningLevels.STONE)
public class DeepslateMagnetite extends TrtrBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "deepslate_magnetite"
    );
    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.STONE,
                                                                              DyeColor.BLACK
                                                                          )
                                                                          .requiresTool()
                                                                          .strength(15F,
                                                                                    6F
                                                                          );

    @Auto
    public static BlockItem ITEM;

    @Auto
    @DataGen
    public static GenericBlockLootProvider LOOT;

    public DeepslateMagnetite(Settings settings) {
        super(settings);
    }
}
