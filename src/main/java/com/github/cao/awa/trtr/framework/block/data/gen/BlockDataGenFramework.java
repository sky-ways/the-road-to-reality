package com.github.cao.awa.trtr.framework.block.data.gen;

import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.loot.BlockLootDataGenFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.model.BlockModelDataGenFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.tag.BlockTagDataGenFramework;
import com.github.cao.awa.trtr.framework.block.data.gen.tag.mineable.BlockMineableDataGenFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("BlockDataGenFramework");
    private final BlockLootDataGenFramework lootFramework;
    private final BlockModelDataGenFramework modelFramework;
    private final BlockMineableDataGenFramework mineableFramework;
    private final BlockTagDataGenFramework tagFramework;

    public BlockDataGenFramework(BlockFramework blockFramework) {
        this.lootFramework = new BlockLootDataGenFramework(blockFramework);
        this.modelFramework = new BlockModelDataGenFramework(blockFramework);
        this.mineableFramework = new BlockMineableDataGenFramework(blockFramework);
        this.tagFramework = new BlockTagDataGenFramework(blockFramework);
    }

    public void tag(FabricDataGenerator generator) {
        this.tagFramework.tag(generator);
    }

    public void mineable(FabricDataGenerator generator) {
        this.mineableFramework.mineable(generator);
    }

    public void loot(FabricDataGenerator generator) {
        this.lootFramework.loot(generator);
    }

    public void model(FabricDataGenerator generator) {
        this.modelFramework.model(generator);
    }
}
