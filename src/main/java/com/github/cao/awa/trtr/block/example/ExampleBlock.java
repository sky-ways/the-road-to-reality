package com.github.cao.awa.trtr.block.example;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.annotations.DataGen;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.block.example.loot.ExampleLoot;
import com.github.cao.awa.trtr.block.example.model.ExampleModel;
import com.github.cao.awa.trtr.framework.data.gen.loot.LootFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

@Auto
public class ExampleBlock extends TrtrBlock {
    // Identifier.
    @Auto
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "example"
    );

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
    // Direct item with class.
    public static final Class<ExampleModel> TYPE_MODEL = ExampleModel.class;

    // Constructor...
    @Auto
    public ExampleBlock(Settings settings) {
        super(settings);
    }
}
