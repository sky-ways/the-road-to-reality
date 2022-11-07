package com.github.zhuaidadaya.rikaishinikui.handler.universal.runnable;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.*;
import java.util.concurrent.*;

public final class FutureTaskOrder {
    private final List<FutureTask> tasks = new ObjectArrayList<>();
    private final List<FutureTask> parallelTasks = new ObjectArrayList<>();

    public void submit(Temporary action, int waitTicks) {
        submit(
                action,
                false,
                waitTicks
        );
    }

    public void submit(Temporary action, boolean parallel, int waitTicks) {
        if (waitTicks < 1) {
            submit(
                    action,
                    parallel
            );
        } else {
            if (parallel) {
                this.parallelTasks.add(new FutureTask(
                        action,
                        waitTicks
                ));
            } else {
                this.tasks.add(new FutureTask(
                        action,
                        waitTicks
                ));
            }
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
        if (this.tasks.size() > 0) {
            this.tasks.removeAll(this.tasks.stream()
                                           .filter(FutureTask::tick)
                                           .toList());
        }
        if (this.parallelTasks.size() > 0) {
            this.parallelTasks.removeAll(this.parallelTasks.parallelStream()
                                                           .filter(FutureTask::tick)
                                                           .toList());
        }
    }

    private static final class FutureTask {
        private final Temporary action;
        private int tick;

        public FutureTask(Temporary action, int ticks) {
            this.action = action;
            this.tick = ticks;
        }

        public boolean tick() {
            if (-- this.tick == 0) {
                this.action.apply();
                return true;
            }
            return false;
        }
    }
}
