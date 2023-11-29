package com.github.cao.awa.trtr.block.cobbled;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.mine.PickaxeMining;
import com.github.cao.awa.trtr.block.TrtrBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

@Auto
@PickaxeMining(MiningLevels.WOOD)
public class CobbledDiorite extends TrtrBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:cobbled_diorite");

    @Auto
    public static final Settings SETTINGS = FabricBlockSettings.create()
                                                               .mapColor(MapColor.GRAY)
                                                               .requiresTool()
                                                               .strength(1.5F,
                                                                         6.0F
                                                               );

    @Auto
    public static BlockItem ITEM;

    @Auto
    public CobbledDiorite(Settings settings) {
        super(settings);
    }
}
