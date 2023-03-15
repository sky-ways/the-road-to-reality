package com.github.cao.awa.trtr.block.example.model;

import com.github.cao.awa.trtr.block.TrtrBlocks;
import com.github.cao.awa.trtr.block.example.ExampleBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class ExampleModel extends FabricModelProvider {
    public ExampleModel(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        System.out.println("Custom model generator");
        blockStateModelGenerator.registerSimpleCubeAll(TrtrBlocks.get(ExampleBlock.IDENTIFIER));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        Item item = TrtrBlocks.get(ExampleBlock.IDENTIFIER).asItem();
        if (item != null) {
            itemModelGenerator.register(item,
                                        Models.GENERATED
            );
        }
    }
}
