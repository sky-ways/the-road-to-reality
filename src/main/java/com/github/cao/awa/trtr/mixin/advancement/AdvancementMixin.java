package com.github.cao.awa.trtr.mixin.advancement;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.*;
import net.minecraft.loot.LootManager;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ServerAdvancementLoader.class)
public class AdvancementMixin {
    @Shadow
    private AdvancementManager manager;

    @Shadow
    private Map<Identifier, AdvancementEntry> advancements;

    @Shadow
    @Final
    private LootManager conditionManager;

    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"), cancellable = true)
    private void fromJSON(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ImmutableMap.Builder<Identifier, AdvancementEntry> builder = ImmutableMap.builder();
        map.forEach((id, json) -> {
            try {
                JsonObject jsonObject = JsonHelper.asObject(json,
                                                            "advancement"
                );

                // We do not want load recipe advancement.
                if (jsonObject.has("parent")) {
                    if (JsonHelper.getString(jsonObject,
                                             "parent"
                                  )
                                  .equals("minecraft:recipes/root")) {
                        return;
                    }
                }

                Advancement advancement = Advancement.fromJson(jsonObject,
                                                               new AdvancementEntityPredicateDeserializer(id,
                                                                                                          this.conditionManager
                                                               )
                );
                builder.put(id,
                            new AdvancementEntry(id,
                                                 advancement
                            )
                );
            } catch (Exception var6) {
                LOGGER.error("Parsing error loading custom advancement {}: {}",
                             id,
                             var6.getMessage()
                );
            }

        });
        this.advancements = builder.buildOrThrow();
        AdvancementManager advancementManager = new AdvancementManager();
        advancementManager.addAll(this.advancements.values());

        for (PlacedAdvancement placedAdvancement : advancementManager.getRoots()) {
            if (placedAdvancement.getAdvancementEntry()
                                 .value()
                                 .display()
                                 .isPresent()) {
                AdvancementPositioner.arrangeForTree(placedAdvancement);
            }
        }

        this.manager = advancementManager;

        ci.cancel();
    }
}
