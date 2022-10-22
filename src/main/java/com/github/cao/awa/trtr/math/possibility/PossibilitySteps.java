package com.github.cao.awa.trtr.math.possibility;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class PossibilitySteps {
    private static final Random random = new Random();
    private final Map<Integer, List<Integer>> possibility = new Object2ObjectOpenHashMap<>();

    public void append(Integer key, Integer... values) {
        this.possibility.put(key,
                             List.of(values)
        );
    }

    public int select(int key) {
        return EntrustParser.select(possibility.get(key), random);
    }
}
