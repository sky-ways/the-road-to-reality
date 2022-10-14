package com.github.zhuaidadaya.rikaishinikui.handler.universal.runnable;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class FutureTaskOrder {
    private final List<FutureTask> tasks = new ObjectArrayList<>();
    private final List<FutureTask> parallelTasks = new ObjectArrayList<>();

    public void submit(Temporary action, int waitTicks) {
        submit(action, waitTicks, false);
    }

    public void submit(Temporary action, int waitTicks, boolean parallel) {
        if (waitTicks < 1) {
            submit(action, parallel);
            return;
        }
        if (parallel) {
            parallelTasks.add(new FutureTask(action, waitTicks));
        } else {
            tasks.add(new FutureTask(action, waitTicks));
        }
    }

    public void submit(Temporary action, boolean parallel) {
        if (parallel) {
            CompletableFuture.runAsync(action::apply);
        } else {
            action.apply();
        }
    }

    public void submit(Temporary action) {
        action.apply();
    }

    public void tick() {
        if (tasks.size() > 0) {
            tasks.removeAll(tasks.stream().filter(FutureTask::tick).toList());
        }
        if (parallelTasks.size() > 0) {
            tasks.removeAll(parallelTasks.parallelStream().filter(FutureTask::tick).toList());
        }
    }

    private static class FutureTask {
        private final Temporary action;
        private int tick;

        public FutureTask(Temporary action, int ticks) {
            this.action = action;
            this.tick = ticks;
        }

        public boolean tick() {
            if (--tick == 0) {
                action.apply();
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        FutureTaskOrder order = new FutureTaskOrder();
        order.submit(() -> {
            System.out.println("???");
        }, 1);

        order.submit(() -> {
            System.out.println("???");
        }, 1);

        order.submit(() -> {
            order.submit(() -> {
                System.out.println("???");
            }, 1);
        }, 1);

        order.submit(() -> {
            order.submit(() -> {
                System.out.println("???");
            }, 1);
        }, 1);

        order.tick();
        order.tick();
        order.tick();

        System.out.println(order.tasks);

    }
}
