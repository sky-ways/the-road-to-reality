package com.github.cao.awa.trtr.plant.fibres;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class PlantFibre extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:plant_fiber");

    public PlantFibre(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings plantFibres = new Settings();
        PlantFibre fibres = new PlantFibre(plantFibres);
        Registry.register(Registry.ITEM, IDENTIFIER, fibres);
//        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
//            if (
//                    source.isBuiltin() &&
//                    Blocks.GRASS.getLootTableId().equals(id) ||
//                    Blocks.TALL_GRASS.getLootTableId().equals(id) ||
//                    Blocks.FERN.getLootTableId().equals(id) ||
//                    Blocks.SEAGRASS.getLootTableId().equals(id)
//            ) {
//                LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(fibres));
//                tableBuilder.pool(poolBuilder);
//            }
//        });
        return fibres;
    }
}
