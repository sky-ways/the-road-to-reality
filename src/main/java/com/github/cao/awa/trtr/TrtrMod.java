package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.annotation.mine.repo.MineableAnnotations;
import com.github.cao.awa.trtr.command.GasCommand;
import com.github.cao.awa.trtr.command.GetAllTrtrBlockCommand;
import com.github.cao.awa.trtr.criteria.TrtrCriteria;
import com.github.cao.awa.trtr.database.provider.DatabaseProviders;
import com.github.cao.awa.trtr.feature.pebble.PebbleFeature;
import com.github.cao.awa.trtr.feature.pebble.PebbleFeatureConfig;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.item.ItemFramework;
import com.github.cao.awa.trtr.framework.model.provider.ModelFramework;
import com.github.cao.awa.trtr.framework.nbt.NbtSerializeFramework;
import com.github.cao.awa.trtr.framework.scanner.AnnotationScannerFramework;
import com.github.cao.awa.trtr.gas.WorldGasManager;
import com.github.cao.awa.trtr.mixin.server.MinecraftServerSessionAccessor;
import com.github.cao.awa.trtr.recipe.serializer.TrtrRecipeSerializer;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import com.github.cao.awa.trtr.tag.item.TrtrItemTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

@Client
@Server
public class TrtrMod implements ModInitializer {
    public static final BlockFramework BLOCK_FRAMEWORK = new BlockFramework();
    public static final NbtSerializeFramework NBT_SERIALIZE_FRAMEWORK = new NbtSerializeFramework();
    public static final AnnotationScannerFramework SCANNER_FRAMEWORK = new AnnotationScannerFramework();
    public static final ItemFramework ITEM_FRAMEWORK = new ItemFramework();
    public static final ModelFramework MODEL_FRAMEWORK = new ModelFramework();
    public static final boolean DEV_MODE = true;

    @Override
    public void onInitialize() {
        initializeConfig();

        // Register mineable annotations for data generator.
        MineableAnnotations.register();

        TrtrRecipeType.initialize();
        TrtrRecipeSerializer.initialize();

        TrtrItemTags.initialize();

        TrtrCriteria.initialize();

        // Scan the annotations.
        SCANNER_FRAMEWORK.work();

        // Startup frameworks.
        ITEM_FRAMEWORK.work();
        BLOCK_FRAMEWORK.work();

        ServerLifecycleEvents.SERVER_STARTING.register(GetAllTrtrBlockCommand :: register);
        ServerLifecycleEvents.SERVER_STARTING.register(GasCommand :: register);
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            WorldGasManager.GAS_MANAGER.setWorld(server.getWorld(World.OVERWORLD));

            String path = ((MinecraftServerSessionAccessor) server).getSession()
                                                                   .getWorldDirectory(World.OVERWORLD) + "/gas";

            try {
                WorldGasManager.GAS_MANAGER.setDatabase(DatabaseProviders.bytes(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            WorldGasManager.GAS_MANAGER.close();
        });

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
