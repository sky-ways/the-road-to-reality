package com.github.cao.awa.trtr.block.example.simple;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import com.github.cao.awa.trtr.annotation.mine.AxeMining;
import com.github.cao.awa.trtr.annotation.mine.PickaxeMining;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.TrtrBlockWithEntity;
import com.github.cao.awa.trtr.block.example.simple.entity.SimpleExampleBlockEntity;
import com.github.cao.awa.trtr.block.example.simple.item.SimpleExampleBlockItem;
import com.github.cao.awa.trtr.block.example.simple.loot.SimpleExampleLoot;
import com.github.cao.awa.trtr.block.example.simple.model.SimpleExampleModel;
import com.github.cao.awa.trtr.block.example.simple.tag.SimpleExampleBlockTag;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.MapColor;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
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
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.create()
                                                                          .mapColor(MapColor.WHITE)
                                                                          .breakInstantly();

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

    // Auto properties to state builder
    @Auto
    @AutoProperty
    public static final DirectionProperty DIRECTION = Properties.FACING;

    // This property will not append to state builder, will auto ignored because field type is not 'net.minecraft.state.property.Property'.
    @Auto
    @AutoProperty
    public static final String WRONG_PROPERTY = "Test wrong property";

    // Constructor...
    @Auto
    public SimpleExampleBlock(Settings settings) {
        super(settings);
    }
}

