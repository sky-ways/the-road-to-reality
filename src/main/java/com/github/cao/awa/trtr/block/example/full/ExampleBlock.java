package com.github.cao.awa.trtr.block.example.full;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.DataGen;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import com.github.cao.awa.trtr.annotation.mine.AxeMining;
import com.github.cao.awa.trtr.annotation.mine.PickaxeMining;
import com.github.cao.awa.trtr.annotation.property.AutoProperty;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.block.example.full.item.ExampleBlockItem;
import com.github.cao.awa.trtr.block.example.full.loot.ExampleLoot;
import com.github.cao.awa.trtr.block.example.full.model.ExampleModel;
import com.github.cao.awa.trtr.block.example.full.tag.ExampleBlockTag;
import com.github.cao.awa.trtr.block.example.simple.entity.SimpleExampleBlockEntity;
import com.github.cao.awa.trtr.framework.accessor.data.gen.loot.LootFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Material;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

@Auto
@DevOnly
@PickaxeMining(MiningLevels.DIAMOND)
@AxeMining(MiningLevels.IRON)
public class ExampleBlock extends TrtrBlock {
    // Identifier.
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:example");

    // Settings.
    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.AIR,
                                                                              DyeColor.WHITE
    );

    // Block item.
    // Direct item.
    @Auto
    public static ExampleBlockItem ITEM;

    // Here one block item is invalid, the name must be "ITEM" or "BLOCK_ITEM", framework will ignore it automatically.
    // Direct item with class.
    public static final Class<ExampleBlockItem> TYPE_ITEM = ExampleBlockItem.class;

    // Loots.
    // Direct loot provider.
    @Auto
    @DataGen
    public static ExampleLoot LOOT;

    // Here two loot provider is invalid, the name must be "LOOT" or "LOOT_PROVIDER", framework will ignore it automatically.
    // Direct loot with class provider.
    public static final Class<ExampleLoot> TYPE_LOOT = ExampleLoot.class;
    // Factory loot.
    public static final LootFactory<ExampleLoot> FACTORY_LOOT = ExampleLoot::new;

    // Models.
    // Direct model provider
    @Auto
    @DataGen
    public static ExampleModel MODEL;

    // Here one block model provider is invalid, the name must be "MODEL" or "MODEL_PROVIDER", framework will ignore it automatically.
    // Direct model with class.
    public static final Class<ExampleModel> TYPE_MODEL = ExampleModel.class;

    // Tags.
    // Direct block tag provider
    @Auto
    @DataGen
    public static ExampleBlockTag TAG;

    // Here one block tag provider is invalid, the name must be "TAG" or "TAG_PROVIDER", framework will ignore it automatically.
    // Direct block tag with class.
    public static final Class<ExampleBlockTag> TYPE_TAG = ExampleBlockTag.class;

    // Block entity.
    @Auto
    public static SimpleExampleBlockEntity ENTITY;

    // Here one block entity provider is invalid, the name must be "ENTITY" or "BLOCK_ENTITY", framework will ignore it automatically.
    // Direct block tag with class.
    public static final Class<ExampleBlockTag> TYPE_ENTITY = ExampleBlockTag.class;

    // Auto properties to state builder
    @AutoProperty
    public static final DirectionProperty DIRECTION = Properties.FACING;

    // This property will not append to state builder, will auto ignored because field type is not 'net.minecraft.state.property.Property'.
    @AutoProperty
    public static final String WRONG_PROPERTY = "Test wrong property";

    // Constructor...
    @Auto
    public ExampleBlock(Settings settings) {
        super(settings);
    }
}
