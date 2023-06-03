package com.github.cao.awa.trtr.annotation.mine.repo;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.mine.*;
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

    public static void register(Class<? extends Annotation> annotation, Identifier key) {
        annotations.add(annotation);
        toolsKey.put(annotation,
                     TagKey.of(Registries.BLOCK.getKey(),
                               key
                     )
        );
    }

    public static void register(int level, Identifier key) {
        levelsKey.put(level,
                      TagKey.of(Registries.BLOCK.getKey(),
                                key
                      )
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
        if (annotation instanceof AxeMining axe) {
            return axe.value();
        } else if (annotation instanceof HoeMining hoe) {
            return hoe.value();
        } else if (annotation instanceof PickaxeMining pickaxe) {
            return pickaxe.value();
        } else if (annotation instanceof ShearsMining shears) {
            return shears.value();
        } else if (annotation instanceof ShovelMining shovel) {
            return shovel.value();
        } else if (annotation instanceof SwordMining sword) {
            return sword.value();
        }
        return - 1;
    }

    public static void register() {
        register(AxeMining.class,
                 Identifier.tryParse("minecraft:mineable/axe")
        );
        register(HoeMining.class,
                 Identifier.tryParse("minecraft:mineable/hoe")
        );
        register(PickaxeMining.class,
                 Identifier.tryParse("minecraft:mineable/pickaxe")
        );
        register(ShearsMining.class,
                 Identifier.tryParse("minecraft:mineable/shear")
        );
        register(ShovelMining.class,
                 Identifier.tryParse("minecraft:mineable/shovel")
        );
        register(SwordMining.class,
                 Identifier.tryParse("minecraft:mineable/sword")
        );

        register(MiningLevels.WOOD,
                 Identifier.tryParse("minecraft:needs_wood_tool")
        );
        register(MiningLevels.STONE,
                 Identifier.tryParse("minecraft:needs_stone_tool")
        );
        register(MiningLevels.IRON,
                 Identifier.tryParse("minecraft:needs_iron_tool")
        );
        register(MiningLevels.DIAMOND,
                 Identifier.tryParse("minecraft:needs_diamond_tool")
        );
        register(MiningLevels.NETHERITE,
                 Identifier.tryParse("minecraft:needs_netherite_tool")
        );
    }
}
