package com.github.cao.awa.trtr.block.grave;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.mine.ShovelMining;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

@Auto
@ShovelMining(value = MiningLevels.WOOD)
public class NoFlintGravelBlock extends GravelBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:no_flint_gravel");

    @Auto
    public static final Settings SETTINGS = FabricBlockSettings.create()
                                                               .strength(0.6f)
                                                               .sounds(BlockSoundGroup.GRAVEL)
                                                               .mapColor(MapColor.STONE_GRAY)
                                                               .instrument(Instrument.SNARE);

    @Auto
    public static BlockItem ITEM;

    @Auto
    public NoFlintGravelBlock(Settings settings) {
        super(settings);
    }
}
