package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.annotation.mine.repo.MineableAnnotations;
import com.github.cao.awa.trtr.command.GetAllTrtrBlockCommand;
import com.github.cao.awa.trtr.feature.pebble.PebbleFeature;
import com.github.cao.awa.trtr.feature.pebble.PebbleFeatureConfig;
import com.github.cao.awa.trtr.fluid.awa.AwaFluid;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.item.ItemFramework;
import com.github.cao.awa.trtr.framework.model.provider.ModelFramework;
import com.github.cao.awa.trtr.framework.scanner.AnnotationScannerFramework;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();
    public static final AnnotationScannerFramework SCANNER_FRAMEWORK = new AnnotationScannerFramework();
    public static final ItemFramework ITEM_FRAMEWORK = new ItemFramework();
    public static final ModelFramework MODEL_FRAMEWORK = new ModelFramework();
    public static final boolean DEV_MODE = true;

    //TODO
    // THIS IS TEST WILL BE DELETE
    public static final FlowableFluid FLOWING_AWA = register("flowing_awa",
                                                             new AwaFluid.Flowing()
    );

    public static final FlowableFluid AWA = register("awa",
                                                     new AwaFluid.Still()
    );

    private static <T extends Fluid> T register(String id, T value) {
        return Registry.register(Registries.FLUID,
                                 id,
                                 value
        );
    }
    //TODO END

    @Override
    public void onInitialize() {
        initializeConfig();

        // Register mineable annotations for data generator.
        MineableAnnotations.register();

        // Scan the annotations.
        SCANNER_FRAMEWORK.work();

        // Startup frameworks.
        ITEM_FRAMEWORK.work();
        BLOCK_FRAMEWORK.work();

        ServerLifecycleEvents.SERVER_STARTING.register(GetAllTrtrBlockCommand :: register);

        Registry.register(Registries.FEATURE,
                          Identifier.tryParse("trtr:surface_pebble"),
                          new PebbleFeature(PebbleFeatureConfig.CODEC)
        );

        RegistryKey<PlacedFeature> key = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                                                        new Identifier("trtr",
                                                                       "surface_pebble"
                                                        )
        );

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                                      GenerationStep.Feature.VEGETAL_DECORATION,
                                      key
        );
    }

    public static void initializeConfig() {
    }
}
