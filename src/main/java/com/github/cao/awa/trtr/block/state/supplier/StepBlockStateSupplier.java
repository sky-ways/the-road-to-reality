package com.github.cao.awa.trtr.block.state.supplier;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.VariantSetting;
import net.minecraft.state.property.Property;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class StepBlockStateSupplier implements BlockStateSupplier {
    private final Block block;
    private final List<Property<?>> properties = ApricotCollectionFactor.arrayList();
    private final Map<VariantSetting<?>, ?> variants = ApricotCollectionFactor.hashMap();
    private final List<Function<Map<Property<?>, Property.Value<?>>, Boolean>> variantChangers = ApricotCollectionFactor.arrayList();

    public StepBlockStateSupplier(Block block) {
        this.block = block;
    }

    public static StepBlockStateSupplier create(Block block) {
        return new StepBlockStateSupplier(block);
    }

    @Override
    public Block getBlock() {
        return this.block;
    }

    public StepBlockStateSupplier property(Property<?> property) {
        this.properties.add(property);
        return this;
    }

    public <T> StepBlockStateSupplier variant(VariantSetting<T> setting, T value) {
        this.variants.put(setting,
                          EntrustEnvironment.cast(value)
        );
        return this;
    }

    @Override
    public JsonElement get() {
        JsonObject json = new JsonObject();
        JsonObject variantsObject = new JsonObject();

        List<Map<Property<?>, Property.Value<?>>> variants = ApricotCollectionFactor.arrayList();

        // Create first level.
        Property<?> firstProperty = this.properties.get(0);
        firstProperty.stream()
                     .forEach(value -> {
                         Map<Property<?>, Property.Value<?>> m = ApricotCollectionFactor.hashMap();
                         m.put(firstProperty,
                               value
                         );
                         variants.add(m);
                     });

        // Creates all variant map.
        for (int i = 1; i < this.properties.size(); i++) {
            Property<?> property = this.properties.get(i);

            Stream<Map<Property<?>, Property.Value<?>>> stream = variants.stream()
                                                                         .toList()
                                                                         .stream();

            variants.clear();

            stream.forEach(variant -> {
                property.stream()
                        .forEach(value -> {
                            Map<Property<?>, Property.Value<?>> m = ApricotCollectionFactor.hashMap();
                            m.put(property,
                                  value
                            );
                            m.putAll(variant);
                            variants.add(m);
                        });
            });
        }

        // Create to json.
        for (Map<Property<?>, Property.Value<?>> variant : variants) {
            for (Function<Map<Property<?>, Property.Value<?>>, Boolean> variantChanger : this.variantChangers) {
                if (variantChanger.apply(variant)) {
                    variantsObject.add(variantName(variant),
                                       variantContent()
                    );
                }
            }
        }

        json.add("variants",
                 variantsObject
        );
        return json;
    }

    public StepBlockStateSupplier changer(Function<Map<Property<?>, Property.Value<?>>, Boolean> changer) {
        this.variantChangers.add(changer);
        return this;
    }

    public String variantName(Map<Property<?>, Property.Value<?>> properties) {
        StringBuilder builder = new StringBuilder();
        properties.forEach((key, value) -> {
            builder.append(key.getName())
                   .append("=")
                   .append(value.value())
                   .append(",");
        });
        builder.delete(builder.length() - 1,
                       builder.length()
        );
        return builder.toString();
    }

    public JsonObject variantContent() {
        JsonObject json = new JsonObject();
        this.variants.forEach((key, value) -> {
            key.evaluate(EntrustEnvironment.cast(value))
               .writeTo(json);
        });
        return json;
    }
}
