package com.github.cao.awa.trtr.debuger.performance.tracker;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;
import java.util.concurrent.*;

public class SubmitTimeTracker {
    private final Map<Object, Long> times =  new ConcurrentHashMap<>();
    private final Map<Object, Long> counts = new ConcurrentHashMap<>();

    public void count(Object obj) {
        counts.put(obj, counts.getOrDefault(obj, 0L) + 1L);
    }

    public void start(Object obj) {
        times.put(obj, TimeUtil.nano());
    }

    public void done(Object obj) {
        submit(obj, TimeUtil.processNano(times.getOrDefault(obj, 0L)), counts.getOrDefault(obj, 0L));
        times.remove(obj);
        counts.remove(obj);
    }

    public void submit(Object where, long nanos, long counts) {
//        System.out.println(where + " used " + nanos + "ns(" + ((nanos / 1000000) + "ms")+ ") for done, counted " + counts + " tasks");
    }
}
