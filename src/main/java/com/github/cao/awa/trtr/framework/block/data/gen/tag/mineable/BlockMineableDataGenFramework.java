package com.github.cao.awa.trtr.framework.block.data.gen.tag.mineable;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.mine.repo.MineableAnnotations;
import com.github.cao.awa.trtr.framework.accessor.block.item.BlockItemAccessor;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BlockMineableDataGenFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("BlockMineableDataGenFramework");
    private final BlockFramework blockFramework;
    private final Map<Block, Collection<Annotation>> mineableFactories = ApricotCollectionFactor.hashMap();

    public BlockMineableDataGenFramework(BlockFramework blockFramework) {
        this.blockFramework = blockFramework;
    }

    public void mineable(FabricDataGenerator generator) {
        this.blockFramework.dumpBlocks()
                           .stream()
                           .filter(this :: match)
                           .filter(this :: verify)
                           .forEach(this :: build);
        done(generator);
    }

    private void build(Block block) {
        this.mineableFactories.compute(block,
                                       (k, v) -> {
                                           if (v == null) {
                                               v = ApricotCollectionFactor.arrayList();
                                           }
                                           v.addAll(MineableAnnotations.getMineableAnnotation(List.of(block.getClass()
                                                                                                           .getAnnotations())));
                                           return v;
                                       }
        );
    }

    private boolean match(Block block) {
        // Get the class of block.
        Class<? extends Block> clazz = block.getClass();

        // Framework will not process the unsupported class.
        boolean unsupported = unsupported(clazz);
        boolean dev = dev(clazz);

        // Notice the unsupported class.
        if (unsupported) {
            LOGGER.warn("Class '{}' is unsupported, ignored it",
                        clazz.getName()
            );
        }

        // Notice development class.
        if (dev && ! TrtrMod.DEV_MODE) {
            LOGGER.warn("Class '{}' is only available in development environment, ignored it",
                        clazz.getName()
            );
        }

        // Combine conditions.
        return
                // Block should have at least one mineable annotation.
                MineableAnnotations.getMineableAnnotation(List.of(block.getClass()
                                                                       .getAnnotations()))
                                   .size() > 0 &&
                        // Ignored dev check when dev mode enabled.
                        (TrtrMod.DEV_MODE || ! dev) &&
                        // Unsupported class will not be proxy.
                        ! unsupported;
    }

    private void done(FabricDataGenerator generator) {
        generator.createPack()
                 .addProvider((o, f) -> new FrameworkMineableTagProvider(o,
                                                                         f,
                                                                         this.mineableFactories
                 ));
    }

    private boolean verify(Block block) {
        final List<String> missing = ApricotCollectionFactor.arrayList();

        // Check indispensable fields, cannot register if missing.
        if (! BlockItemAccessor.ACCESSOR.has(block)) {
            missing.add("ITEM or BLOCK_ITEM");
        }
        return checkFields(block.getClass()
                                .getName(),
                           missing
        );
    }
}
