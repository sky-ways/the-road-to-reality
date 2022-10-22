package com.github.cao.awa.trtr.client.render.relational;

import com.google.common.collect.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.client.render.model.json.*;
import org.json.*;

import java.util.*;

public class SwitchRelational {
    public static void main(String[] args) {
        Map<String, WeightedUnbakedModel> map = Maps.newHashMap();

        JSONObject object = new JSONObject("""
                                                   {
                                                       "relational": {
                                                           "switch": {
                                                               "key1": {
                                                                   "value1":{
                                                                       "y": 90
                                                                   } ,
                                                                   "value2": {
                                                                       "y": 180
                                                                   }
                                                               },
                                                               "key2": {
                                                                  
                                                               },
                                                               "facing": {
                                                                   "south": {
                                                                       "y": 114
                                                                   }
                                                               }
                                                           }
                                                       }
                                                   }
                                                   """);

        JSONObject relational = object.getJSONObject("relational");

        JSONObject switching = relational.getJSONObject("switch");

        Map<String, List<Condition>> kvs = new LinkedHashMap<>();

        for (String key : switching.keySet()) {
            JSONObject values = switching.getJSONObject(key);
            for (String conditionValue : values.keySet()) {
                List<Condition> v = kvs.get(key);
                if (v == null) {
                    v = new ObjectArrayList<>();
                    kvs.put(
                            key,
                            v
                    );
                }
                v.add(new Condition(
                        conditionValue,
                        values.getJSONObject(conditionValue)
                ));
            }
        }

        Map<String, StringBuilder> builders = new LinkedHashMap<>();

        kvs.forEach((k, v) -> {
            StringBuilder builder;
            if (! builders.containsKey(k)) {
                builder = new StringBuilder();
                builders.put(
                        k,
                        builder
                );
            } else {
                builder = builders.get(k);
            }


        });
    }

    private record Condition(String value, JSONObject apply) {
    }
}
