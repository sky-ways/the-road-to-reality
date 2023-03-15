package com.github.cao.awa.trtr.data.gen.model;

import com.github.cao.awa.apricot.anntations.Auto;
import com.github.cao.awa.trtr.framework.data.gen.model.TrtrItemModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

@Auto
public class GenericItemModelProvider extends TrtrItemModelProvider {
    private final Item item;

    @Auto
    public GenericItemModelProvider(FabricDataOutput output, Item item) {
        super(output);
        this.item = item;
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(this.item,
                                    Models.GENERATED
        );
    }
}
