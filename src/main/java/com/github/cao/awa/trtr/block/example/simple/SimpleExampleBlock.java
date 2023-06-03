package com.github.cao.awa.trtr.block.example.simple;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import com.github.cao.awa.trtr.annotation.mine.AxeMining;
import com.github.cao.awa.trtr.annotation.mine.PickaxeMining;
import com.github.cao.awa.trtr.block.TrtrBlockWithEntity;
import com.github.cao.awa.trtr.block.example.simple.entity.SimpleExampleBlockEntity;
import com.github.cao.awa.trtr.block.example.simple.item.SimpleExampleBlockItem;
import com.github.cao.awa.trtr.block.example.simple.loot.SimpleExampleLoot;
import com.github.cao.awa.trtr.block.example.simple.model.SimpleExampleModel;
import com.github.cao.awa.trtr.block.example.simple.tag.SimpleExampleBlockTag;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Material;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

@Auto
@DevOnly
@PickaxeMining(MiningLevels.DIAMOND)
@AxeMining(MiningLevels.IRON)
public class SimpleExampleBlock extends TrtrBlockWithEntity {
    // Identifier.
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:example_simple");

    // Settings.
    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.AIR,
                                                                              DyeColor.WHITE
    );

    // Block item.
    @Auto
    public static SimpleExampleBlockItem ITEM;

    // Loots.
    @Auto
    @DataGen
    public static SimpleExampleLoot LOOT;

    // Models.
    @Auto
    @DataGen
    public static SimpleExampleModel MODEL;

    // Tags.
    @Auto
    @DataGen
    public static SimpleExampleBlockTag TAG;

    // Block entity.
    @Auto
    public static SimpleExampleBlockEntity ENTITY;

    // Constructor...
    @Auto
    public SimpleExampleBlock(Settings settings) {
        super(settings);
    }
}

