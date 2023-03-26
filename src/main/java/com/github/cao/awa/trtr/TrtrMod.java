package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.annotation.mine.repo.MineableAnnotations;
import com.github.cao.awa.trtr.command.GetAllTrtrBlockCommand;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();
    public static final boolean DEV_MODE = false;

    @Override
    public void onInitialize() {
        initializeConfig();

        // Register mineable annotations for data generator.
        MineableAnnotations.putDefaults();

        // Startup block framework.
        BLOCK_FRAMEWORK.work();

        ServerLifecycleEvents.SERVER_STARTING.register(l -> {
            GetAllTrtrBlockCommand.register(l);
        });

        // Test ore feature.
        RegistryKey<PlacedFeature> marbleKey = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                                                              new Identifier("trtr",
                                                                             "ore_xxx"
                                                              )
        );

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                                      GenerationStep.Feature.UNDERGROUND_ORES,
                                      marbleKey
        );
    }

    public static void initializeConfig() {
    }
}
