package com.github.cao.awa.trtr.framework.block.data.gen.tag.mineable;

import com.github.cao.awa.trtr.annotations.repo.MineableAnnotations;
import com.github.cao.awa.trtr.framework.accessor.identifier.IdentifierAccessor;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FrameworkMineableTagProvider extends FabricTagProvider<Block> {
    private final Map<Block, Collection<Annotation>> mineableFactories;

    public FrameworkMineableTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, Map<Block, Collection<Annotation>> mineableFactories) {
        super(output,
              Registries.BLOCK.getKey(),
              registriesFuture
        );
        this.mineableFactories = mineableFactories;
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        this.mineableFactories.forEach((block, mineableList) -> {
            for (Annotation mineable : mineableList) {
                Identifier identifier = IdentifierAccessor.ACCESSOR.get(block);
                getTagBuilder(MineableAnnotations.getLevelKey(MineableAnnotations.getLevel(mineable))).add(identifier);
                getTagBuilder(MineableAnnotations.getToolKey(mineable)).add(identifier);
            }
        });
    }

    @Override
    public String getName() {
        return "minecraft:block/mineable";
    }
}