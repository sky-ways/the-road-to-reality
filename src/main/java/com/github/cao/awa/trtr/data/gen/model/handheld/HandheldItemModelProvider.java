package com.github.cao.awa.trtr.data.gen.model.handheld;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrItemModelProvider;
import com.github.cao.awa.trtr.framework.model.provider.FrameworkModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

/**
 * Generate the handheld model for items.
 *
 * @author cao_awa
 * @see FrameworkModelProvider
 * @since 1.0.0
 */
@Auto
public class HandheldItemModelProvider extends TrtrItemModelProvider {
    private final Item item;

    @Auto
    public HandheldItemModelProvider(FabricDataOutput output, Item item) {
        super(output);
        this.item = item;
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Usually an item is flat texture.
        itemModelGenerator.register(this.item,
                                    Models.HANDHELD
        );
    }
}
