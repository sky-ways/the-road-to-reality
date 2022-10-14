package com.github.cao.awa.trtr.debuger.performance.tracker;

import com.github.cao.awa.trtr.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import de.javagl.obj.*;

import java.util.*;
import java.util.concurrent.*;

public class Counter {
    private final Map<Object, Double> counts = new ConcurrentHashMap<>();

    public void count(Object obj) {
        count(obj, 1L);
    }

    public void count(Object obj, Number value) {
        counts.put(obj, counts.getOrDefault(obj, 0D) + value.doubleValue());
    }

    public void start(Object obj) {
        counts.put(obj, 0D);
    }

    public void done(Object obj) {
        submit(obj, counts.getOrDefault(obj, 0D));
        counts.remove(obj);
    }

    public void submit(Object where, double counts) {
        TrtrMod.LOGGER.info(where + " counter done, counted " + counts + " tasks");
    }
}
