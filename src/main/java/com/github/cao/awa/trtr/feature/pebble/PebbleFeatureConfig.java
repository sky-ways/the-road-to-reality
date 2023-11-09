package com.github.cao.awa.trtr.feature.pebble;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;
import java.util.stream.Collectors;

public final class PebbleFeatureConfig implements FeatureConfig {
    public static Codec<PebbleFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance ->
                    instance.group(
                                    Codec.STRING.listOf()
                                                .fieldOf("surface_placeable")
                                                .forGetter(PebbleFeatureConfig :: surfacePlaceableSource),
                                    Codec.BOOL.fieldOf("default_state")
                                              .orElse(false)
                                              .forGetter(PebbleFeatureConfig :: isDefaultState)
                            )
                            .apply(instance,
                                   PebbleFeatureConfig :: new
                            ));
    private final List<String> placeable;
    private final boolean defaultState;

    public PebbleFeatureConfig(List<String> placeable, boolean defaultState) {
        this.placeable = placeable;
        this.defaultState = defaultState;
    }

    private List<String> surfacePlaceableSource() {
        return this.placeable;
    }

    public List<Identifier> surfacePlaceable() {
        return this.placeable.stream()
                             .map(Identifier :: tryParse)
                             .collect(Collectors.toList());
    }

    public boolean isDefaultState() {
        return this.defaultState;
    }
}
