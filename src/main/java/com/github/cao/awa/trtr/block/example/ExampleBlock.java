package com.github.cao.awa.trtr.block.example;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.annotations.DataGen;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.block.example.loot.ExampleLoot;
import com.github.cao.awa.trtr.framework.data.gen.loot.LootFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

@Auto
public class ExampleBlock extends TrtrBlock {
    // Identifier.
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "example"
    );

    // Settings.
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.AIR,
                                                                              DyeColor.WHITE
    );

    // Loots.
    // Direct loot provider.
    @DataGen
    public static ExampleLoot LOOT;

    // Here two loot provider is invalid, the name must be "LOOT" or "LOOT_PROVIDER", framework will ignore it automatically.
    // Direct loot with class provider.
    public static final Class<ExampleLoot> TYPE_LOOT = ExampleLoot.class;
    // Factory loot.
    public static final LootFactory<ExampleLoot> FACTORY_LOOT = ExampleLoot::new;

    // Block item.
    // Direct item.
    public static ExampleBlockItem ITEM;

    // Here one block item is invalid, the name must be "ITEM" or "BLOCK_ITEM", framework will ignore it automatically.
    // Direct item with class.
    public static final Class<ExampleBlockItem> TYPE_ITEM = ExampleBlockItem.class;

    // Constructor...
    public ExampleBlock(Settings settings) {
        super(settings);
    }
}