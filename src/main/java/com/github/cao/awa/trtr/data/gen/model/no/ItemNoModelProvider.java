package com.github.cao.awa.trtr.data.gen.model.no;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.framework.accessor.data.gen.model.TrtrItemModelProvider;
import com.github.cao.awa.trtr.framework.model.provider.FrameworkModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.client.ItemModelGenerator;

/**
 * Do not generate texture for items.
 *
 * @author 草二号机
 * @see FrameworkModelProvider
 * @since 1.0.0
 */
@Auto
public class ItemNoModelProvider extends TrtrItemModelProvider {
    @Auto
    public ItemNoModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    }
}
