package com.github.cao.awa.trtr.framework.accessor.data.gen.model;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.VariantSetting;
import net.minecraft.data.client.When;
import net.minecraft.state.property.Property;

import java.util.Map;

public abstract class TrtrBlockModelProvider extends FabricModelProvider {
    private final Map<VariantSetting<?>, ?> variants = ApricotCollectionFactor.newHashMap();

    public TrtrBlockModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public final void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    public When.PropertyCondition when() {
        return When.create();
    }

    public <T extends Comparable<T>> When.PropertyCondition when(Property<T> property, T value) {
        return When.create()
                   .set(property,
                        value
                   );
    }

    public BlockStateVariant variant() {
        return EntrustEnvironment.operation(BlockStateVariant.create(),
                                            variant -> this.variants.forEach((k, v) -> variant.put(k,
                                                                                                   EntrustEnvironment.cast(v)
                                            ))
        );
    }

    public <T> BlockStateVariant variant(VariantSetting<T> key, T value) {
        return variant().put(key,
                             value
        );
    }

    public <T> BlockStateVariant variants() {
        return variant();
    }

    public <T> Map<VariantSetting<T>, T> variants(VariantSetting<T> key, T value) {
        this.variants.put(key,
                          EntrustEnvironment.cast(value)
        );
        return EntrustEnvironment.cast(this.variants);
    }
}
