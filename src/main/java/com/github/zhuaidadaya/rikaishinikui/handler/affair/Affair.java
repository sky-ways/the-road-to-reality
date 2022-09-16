package com.github.zhuaidadaya.rikaishinikui.handler.affair;

import com.github.cao.awa.modmdo.annotations.*;
import it.unimi.dsi.fastutil.objects.*;

import java.util.function.*;

@Disposable
public class Affair {
    private final ObjectLinkedOpenHashSet<AffairTask> actions = new ObjectLinkedOpenHashSet<>();

    public static Affair of(Runnable action) {
        return new Affair().participate(action);
    }

    public static Affair empty() {
        return new Affair();
    }

    public Affair participate(Runnable action) {
        actions.add(new AffairTask(() -> true, action));
        return this;
    }

    public Affair participateIf(Supplier<Boolean> predicate, Runnable action) {
        if (predicate.get()) {
            actions.add(new AffairTask(() -> true, action));
        }
        return this;
    }

    public Affair addIf(Supplier<Boolean> predicate, Runnable action) {
        actions.add(new AffairTask(predicate, action));
        return this;
    }

    public void done() {
        for (AffairTask runnable : actions) {
            runnable.run();
        }
    }

    public record AffairTask(Supplier<Boolean> predicate, Runnable action) {
        public void run() {
            if (predicate.get()) {
                action.run();
            }
        }
    }
}
