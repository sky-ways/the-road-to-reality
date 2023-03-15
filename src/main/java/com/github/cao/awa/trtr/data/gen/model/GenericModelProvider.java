package com.github.cao.awa.trtr.data.gen.model;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class GenericModelProvider extends FabricModelProvider {
    private final Block block;

    public GenericModelProvider(FabricDataOutput output, Block block) {
        super(output);
        this.block = block;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(this.block);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Item item = (this.block.asItem());
        if (item != null) {
            itemModelGenerator.register(item,
                                        Models.GENERATED
            );
        }
    }
}
