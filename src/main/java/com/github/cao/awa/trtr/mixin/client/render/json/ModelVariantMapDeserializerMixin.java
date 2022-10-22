package com.github.cao.awa.trtr.mixin.client.render.json;

import com.google.common.collect.*;
import com.google.gson.*;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.lang.reflect.*;
import java.util.*;

@Mixin(ModelVariantMap.Deserializer.class)
public abstract class ModelVariantMapDeserializerMixin {
    @Shadow
    @Nullable
    protected abstract MultipartUnbakedModel multipartFromJson(JsonDeserializationContext context, JsonObject object);

    @Inject(method = "deserialize(Lcom/google/gson/JsonElement;Ljava/lang/reflect/Type;Lcom/google/gson/JsonDeserializationContext;)Lnet/minecraft/client/render/model/json/ModelVariantMap;", at = @At("HEAD"), cancellable = true)
    public void deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext, CallbackInfoReturnable<ModelVariantMap> cir) throws JsonParseException {
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        if (jsonObject.has("relational")) {
//            Map<String, WeightedUnbakedModel> map = this.relationalFromJson(
//                    jsonDeserializationContext,
//                    jsonObject
//            );
//            cir.setReturnValue(new ModelVariantMap(
//                    map,
//                    null
//            ));
//        }
    }

    protected Map<String, WeightedUnbakedModel> relationalFromJson(JsonDeserializationContext context, JsonObject object) {
        Map<String, WeightedUnbakedModel> map = Maps.newHashMap();

        JsonObject relational = object.getAsJsonObject("relational");

        JsonObject switching = relational.getAsJsonObject("switch");

        Map<String, JsonObject> conditions = new LinkedHashMap<>();

        for (String key : switching.keySet()) {
            JsonObject values = switching.getAsJsonObject(key);
            for (String conditionValues : values.keySet()) {
                conditions.put(key + "=" + conditionValues, values.getAsJsonObject(conditionValues));
            }
        }

        return map;
    }
}
