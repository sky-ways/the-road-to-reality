package com.github.cao.awa.trtr.mixin.recipe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
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

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Shadow
    private boolean errored;

    @Shadow
    protected static RecipeEntry<?> deserialize(Identifier id, JsonObject json) {
        return null;
    }

    @Shadow
    private Map<RecipeType<?>, Map<Identifier, RecipeEntry<?>>> recipes;

    @Shadow
    private Map<Identifier, RecipeEntry<?>> recipesById;

    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"), cancellable = true)
    private void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        this.errored = false;
        Map<RecipeType<?>, ImmutableMap.Builder<Identifier, RecipeEntry<?>>> map2 = Maps.newHashMap();
        ImmutableMap.Builder<Identifier, RecipeEntry<?>> builder = ImmutableMap.builder();

        for (Map.Entry<Identifier, JsonElement> identifierJsonElementEntry : map.entrySet()) {
            Identifier identifier = identifierJsonElementEntry.getKey();

            try {
                JsonObject jsonObject = JsonHelper.asObject(identifierJsonElementEntry.getValue(),
                                                            "top element"
                );

                String type = JsonHelper.getString(jsonObject,
                                                   "type"
                );
                if (type.equals("minecraft:crafting_shaped") || type.equals("minecraft:crafting_shapeless")) {
                    continue;
                }

                RecipeEntry<?> recipeEntry = deserialize(identifier,
                                                         jsonObject
                );
                map2.computeIfAbsent(recipeEntry.value()
                                                .getType(),
                                     (recipeType) -> ImmutableMap.builder()
                    )
                    .put(identifier,
                         recipeEntry
                    );
                builder.put(identifier,
                            recipeEntry
                );
            } catch (IllegalArgumentException | JsonParseException var10) {
                LOGGER.error("Parsing error loading recipe {}",
                             identifier,
                             var10
                );
            }
        }

        this.recipes = map2.entrySet()
                           .stream()
                           .collect(ImmutableMap.toImmutableMap(Map.Entry :: getKey,
                                                                (entry) -> entry.getValue()
                                                                                .build()
                           ));
        this.recipesById = builder.build();
        LOGGER.info("Loaded {} recipes",
                    map2.size()
        );

        ci.cancel();
    }
}
