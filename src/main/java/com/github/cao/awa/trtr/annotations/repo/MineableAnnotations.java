package com.github.cao.awa.trtr.annotations.repo;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotations.mining.*;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MineableAnnotations {
    public static final List<Class<? extends Annotation>> annotations = ApricotCollectionFactor.newArrayList();
    public static final Map<Class<? extends Annotation>, TagKey<Block>> toolsKey = ApricotCollectionFactor.newHashMap();
    public static final Map<Integer, TagKey<Block>> levelsKey = ApricotCollectionFactor.newHashMap();

    public static void register(Class<? extends Annotation> annotation, TagKey<Block> key) {
        annotations.add(annotation
        );
        toolsKey.put(annotation,
                     key
        );
    }

    public static void register(int level, TagKey<Block> key) {
        levelsKey.put(level,
                      key
        );
    }

    public static boolean contains(Class<? extends Annotation> annotation) {
        return annotations.contains(annotation);
    }

    public static Collection<Class<? extends Annotation>> getMineableAnnotationClass(Collection<Class<? extends Annotation>> annotations) {
        Set<Class<? extends Annotation>> result = ApricotCollectionFactor.newHashSet();
        for (Class<? extends Annotation> annotation : annotations) {
            if (contains(annotation)) {
                result.add(annotation);
            }
        }
        return result;
    }

    public static Collection<Annotation> getMineableAnnotation(Collection<Annotation> annotations) {
        Set<Annotation> result = ApricotCollectionFactor.newHashSet();
        for (Annotation annotation : annotations) {
            if (contains(annotation.annotationType())) {
                result.add(annotation);
            }
        }
        return result;
    }

    public static TagKey<Block> getToolKey(Annotation annotation) {
        return toolsKey.get(annotation.annotationType());
    }

    public static TagKey<Block> getLevelKey(int level) {
        return levelsKey.get(level);
    }

    public static int getLevel(Annotation annotation) {
        if (annotation instanceof PickaxeMining pickaxe) {
            return pickaxe.value();
        }
        return - 1;
    }

    public static void putDefaults() {
        register(AxeMining.class,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "mineable/axe"
                           )
                 )
        );
        register(HoeMining.class,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "mineable/hoe"
                           )
                 )
        );
        register(PickaxeMining.class,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "mineable/pickaxe"
                           )
                 )
        );
        register(ShearsMining.class,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "mineable/shear"
                           )
                 )
        );
        register(ShovelMining.class,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "mineable/shovel"
                           )
                 )
        );
        register(SwordMining.class,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "mineable/sword"
                           )
                 )
        );

        register(MiningLevels.WOOD,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "needs_wood_tool"
                           )
                 )
        );
        register(MiningLevels.STONE,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "needs_stone_tool"
                           )
                 )
        );
        register(MiningLevels.IRON,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "needs_iron_tool"
                           )
                 )
        );
        register(MiningLevels.DIAMOND,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "needs_diamond_tool"
                           )
                 )
        );
        register(MiningLevels.NETHERITE,
                 TagKey.of(Registries.BLOCK.getKey(),
                           Identifier.of("minecraft",
                                         "needs_netherite_tool"
                           )
                 )
        );
    }
}
