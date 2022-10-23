package com.github.cao.awa.trtr.pressure.convert;

import com.github.cao.awa.trtr.pressure.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class PressuresConvertRates {
    public static final Map<String, Map<String, Double>> MULTIPLIER = new Object2ObjectOpenHashMap<>();

    public static void pre() {
        register(
                "kPa",
                "Pa",
                1.0E-03
        );

        register(
                "Pa",
                "kPa",
                1.0E03
        );
    }

    public static void register(String pressure, String toPressure, double multiplier) {
        Map<String, Double> map = MULTIPLIER.get(pressure);
        if (map == null) {
            map = new Object2ObjectOpenHashMap<>();
            MULTIPLIER.put(
                    pressure,
                    map
            );
        }
        map.put(
                toPressure,
                multiplier
        );
    }

    public static Double get(String target, String self) {
        return MULTIPLIER.get(self).get(target);
    }

    public static Double get(Pressure target, Pressure self) {
        return get(target.getName(), self.getName());
    }
}
