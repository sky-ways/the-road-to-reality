package com.github.cao.awa.trtr.config;

import it.unimi.dsi.fastutil.objects.*;
import org.apache.logging.log4j.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

/**
 * Configuration readonly, changes will temporally apply.
 * Will not rewrite to file.
 *
 * @author cao_awa
 */
public class Configure {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, Map<String, String>> warning = new Object2ObjectOpenHashMap<>();
    private final Map<String, String> configs = new Object2ObjectOpenHashMap<>();
    private final Supplier<String> loader;

    /**
     * Setting basic prepares.
     *
     * @param loader
     *         Support loading config information
     */
    public Configure(Supplier<String> loader) {
        this.loader = loader;
    }

    /**
     * Reload configurations
     */
    public void reload() {
        load(loader.get());
    }

    /**
     * Load config from cold data
     *
     * @param configInformation
     *         Config deltas
     */
    private void load(String configInformation) {
        BufferedReader reader = new BufferedReader(new StringReader(configInformation));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                line = line.trim()
                           .strip();
                if (line.startsWith("#")) {
                    // Note here
                } else {
                    int note = line.indexOf("#");
                    if (note != - 1) {
                        line = line.substring(
                                0,
                                note
                        );
                        //  LOGGER.warn("The # should not be appear in properties line, ignored it");
                    }
                    int dividing = line.indexOf("=");
                    String key = line.substring(
                                             0,
                                             dividing
                                     )
                                     .strip()
                                     .trim();
                    String value = line.substring(dividing + 1)
                                       .strip()
                                       .trim();

                    if (key.equals("")) {
                        LOGGER.warn(value.equals("") ?
                                    "The key and value not found in configuration line" :
                                    "The key not found in configuration line");
                        continue;
                    } else {
                        if (value.equals("")) {
                            LOGGER.warn("The value not found in configuration line");
                            continue;
                        }
                    }

                    LOGGER.info("Processing config: " + key + " = " + value);

                    set(
                            key,
                            value
                    );

                    Map<String, String> warning = this.warning.get(key);
                    if (warning != null) {
                        String info = warning.get(value);
                        if (info != null) {
                            LOGGER.warn(info);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Unable to load the rest of the configuration");
        }
    }

    /**
     * Set config.
     *
     * @param key
     *         Config key
     * @param value
     *         Config value
     */
    public void set(String key, String value) {
        this.configs.put(
                key,
                value
        );
    }

    /**
     * Get config.
     *
     * @param key
     *         Config key
     * @return Config value
     */
    @NotNull
    public String get(String key) {
        return this.configs.getOrDefault(
                key,
                ""
        );
    }

    public void load() {
        load(loader.get());
    }

    /**
     * Prepare warning for readied value match to expect values.
     *
     * @param key
     *         Config key
     * @param values
     *         Config values
     * @param info
     *         Warning when config match to target
     */
    public void warningWhen(String key, List<String> values, String info) {
        values.forEach(value -> warningWhen(
                key,
                value,
                info
        ));
    }

    /**
     * Prepare warning for readied value match to expect value.
     *
     * @param key
     *         Config key
     * @param value
     *         Config value
     * @param info
     *         Warning when config match to target
     */
    public void warningWhen(String key, String value, String info) {
        Map<String, String> map = this.warning.get(key);
        if (map == null) {
            map = new Object2ObjectOpenHashMap<>();
            this.warning.put(
                    key,
                    map
            );
        }
        map.put(
                value,
                info
        );
    }
}
