package com.github.cao.awa.trtr.framework.block.data.gen;

import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.loot.BlockLootDataGenFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.model.BlockModelDataGenFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/BlockDataGenFramework");
    private final BlockFramework blockFramework;
    private final BlockLootDataGenFramework lootFramework;
    private final BlockModelDataGenFramework modelFramework;

    public BlockDataGenFramework(BlockFramework blockFramework) {
        this.blockFramework = blockFramework;
        this.lootFramework = new BlockLootDataGenFramework(blockFramework);
        this.modelFramework = new BlockModelDataGenFramework(blockFramework);
    }

    public void loot(FabricDataGenerator generator) {
        this.lootFramework.loot(generator);
    }

    public void model(FabricDataGenerator generator) {
        this.modelFramework.model(generator);
    }
}
