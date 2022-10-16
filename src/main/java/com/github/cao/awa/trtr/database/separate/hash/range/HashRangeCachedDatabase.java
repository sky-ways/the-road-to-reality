package com.github.cao.awa.trtr.database.separate.hash.range;

import com.github.cao.awa.trtr.database.*;
import com.github.cao.awa.trtr.database.file.line.*;
import com.github.cao.awa.trtr.database.file.storage.independent.*;
import com.github.cao.awa.trtr.database.file.storage.leisurely.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.math.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import org.json.*;

import java.util.*;
import java.util.concurrent.*;

public class HashRangeCachedDatabase extends InstancePropertiesDatabase {
    private final Map<Integer, Map<String, InstanceProperties<?>>> properties = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> modifies = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> uses = new ConcurrentHashMap<>();
    private static final int PAGE_SIZE = 10000000;

    public HashRangeCachedDatabase(String path) {
        super(new LeisurelyStorage(
                new IndependentStorage(
                        path,
                        ".page",
                        0
                ),
                false
        ));
    }

    @Override
    public void export() {

    }

    @Override
    public void put(String key, InstanceProperties<?> properties) {
        int page = page(key);
        Map<String, InstanceProperties<?>> map = this.properties.get(page);
        if (map == null) {
            map = new ConcurrentHashMap<>();
            this.properties.put(
                    page,
                    map
            );
        }
        map.put(
                key,
                properties
        );
        modifies.put(
                page,
                modifies.getOrDefault(
                        page,
                        0
                ) + 1
        );
        try {
            if (modifies.get(page) > 5000) {
                save(page);
                modifies.put(
                        page,
                        0
                );
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void shutdown() {
        getStorage().shutdown();
        this.properties.keySet()
                       .parallelStream()
                       .forEach(this::save);

        while (! getStorage().isShutdown()) {
            TimeUtil.coma(10);
        }
    }

    @Override
    public InstanceProperties<?> get(String key) {
        int page = page(key);
        Map<String, InstanceProperties<?>> cache = this.properties.get(page);
        if (cache == null) {
            cache = new ConcurrentHashMap<>();
            this.properties.put(page, cache);
        }
        InstanceProperties<?> properties = cache.get(key);
        try {
            if (properties == null) {
                load(page);
                cache = this.properties.get(page);
                properties = cache.get(key);
                if (properties == null) {
                    return new InstanceProperties<>(null);
                }
            }
            return properties;
        } catch (Exception e) {
            return new InstanceProperties<>(null);
        }
    }

    public static int page(String key) {
        return Mathematics.truncation(
                                  hash(key),
                                  PAGE_SIZE
                          )
                          .intValue();
    }

    public static int hash(String key) {
        return Mathematics.absHash(key.hashCode());
    }

    private void save(int page) {
        getStorage().entrustWrite(
                String.valueOf(page),
                () -> this.pageToString(page)
        );
    }

    private void load(int page) {
        try {
            LinesData lines = new LinesData(getStorage().read(String.valueOf(page)));
            Map<String, InstanceProperties<?>> propertiesMap = this.properties.get(page);
            if (propertiesMap == null) {
                propertiesMap = new ConcurrentHashMap<>();
                this.properties.put(page, propertiesMap);
            }
            for (String key : lines.keySet()) {
                if (propertiesMap.containsKey(key)) {
                    continue;
                }
                InstanceProperties<?> properties = new InstanceProperties<>(null);
                properties.readJSONObject(new JSONObject(lines.get(key)));
                propertiesMap.put(
                        key,
                        properties
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String pageToString(int page) {
        load(page);
        Map<String, InstanceProperties<?>> properties = this.properties.get(page);
        LinesData lines = new LinesData();
        properties.keySet()
                  .parallelStream()
                  .forEach(key -> {
                      lines.put(
                              key,
                              properties.get(key)
                                        .toJSONObject().toString()
                      );
                  });
        return lines.toString();
    }
}
