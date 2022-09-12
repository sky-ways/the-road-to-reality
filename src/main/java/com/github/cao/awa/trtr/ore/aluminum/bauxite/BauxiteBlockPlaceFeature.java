package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import com.github.cao.awa.trtr.ref.place.*;
import com.github.cao.awa.trtr.type.*;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.*;

public class BauxiteBlockPlaceFeature extends TrtrOrePlacedFeatures {
    public static final Identifier IDENTIFIER = new Identifier("trtr:bauxite_ore");

    public static void register() {
        List<OreFeatureConfig.Target> targets = List.of(
                OreFeatureConfig.createTarget(
                        OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                        TrtrBlocks.BAUXITE_BLOCK.getDefaultState()
                ),
                OreFeatureConfig.createTarget(
                        OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                        TrtrBlocks.DEEPSLATE_BAUXITE_BLOCK.getDefaultState()
                )
        );

        ConfiguredFeature<?, ?> config = new ConfiguredFeature<>(
                Feature.ORE,
                new OreFeatureConfig(
                        targets,
                        16
                )
        );
        PlacedFeature feature = new PlacedFeature(
                RegistryEntry.of(config),
                Arrays.asList(
                        CountPlacementModifier.of(5),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(
                                YOffset.fixed(50),
                                YOffset.fixed(80)
                        )
                )
        );
        Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                IDENTIFIER,
                config
        );
        Registry.register(
                BuiltinRegistries.PLACED_FEATURE,
                IDENTIFIER,
                feature
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(
                        Registry.PLACED_FEATURE_KEY,
                        IDENTIFIER
                )
        );
    }
}