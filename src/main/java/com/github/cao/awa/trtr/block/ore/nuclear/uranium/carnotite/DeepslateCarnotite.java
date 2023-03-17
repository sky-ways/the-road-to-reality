package com.github.cao.awa.trtr.block.ore.nuclear.uranium.carnotite;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.DataGen;
import com.github.cao.awa.trtr.annotation.mine.PickaxeMining;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.data.gen.loot.GenericBlockLootProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

@Auto
@PickaxeMining(MiningLevels.STONE)
public class DeepslateCarnotite extends TrtrBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "deepslate_carnotite"
    );

    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.STONE,
                                                                              MapColor.GRAY
                                                                          )
                                                                          .requiresTool()
                                                                          .strength(1.5F,
                                                                                    6.0F
                                                                          );

    @Auto
    public static BlockItem ITEM;

    @DataGen
    public static GenericBlockLootProvider LOOT;

    @Auto
    public DeepslateCarnotite(Settings settings) {
        super(settings);
    }
}
