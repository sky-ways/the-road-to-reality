package com.github.cao.awa.trtr.database.file.storage.leisurely;

import com.github.cao.awa.trtr.database.file.storage.*;
import com.github.cao.awa.trtr.database.file.storage.independent.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import it.unimi.dsi.fastutil.*;
import it.unimi.dsi.fastutil.objects.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static com.github.cao.awa.trtr.TrtrMod.timeTracker;

public class LeisurelyStorage extends DatabaseStorage {
    private final boolean preventSudden;
    private final DatabaseStorage storage;
    private final IndependentStorage dump;
    private final LeisurelyStorageTask processor = new LeisurelyStorageTask(this);
    private final Set<String> lockingDump = new ConcurrentSkipListSet<>();
    private final Set<String> lockingStorage = new ConcurrentSkipListSet<>();
    private boolean shutdown;

    public LeisurelyStorage(DatabaseStorage storage, boolean preventSudden) {
        super(storage.getPath());
        this.storage = storage;
        this.dump = new IndependentStorage(storage.getPath() + "/leisurely_dump", "dump", 128);
        this.preventSudden = preventSudden;
    }

    public LeisurelyStorage(DatabaseStorage storage) {
        super(storage.getPath());
        this.storage = storage;
        this.dump = new IndependentStorage(storage.getPath() + "/leisurely_dump", "dump", 128);
        this.preventSudden = true;
    }

    @Override
    public long idleTime() {
        return processor.idleTime();
    }

    public void shutdown() {
        waiting: while (!processor.isShutdown()) {
            switch (processor.getStage()) {
                case SHUTDOWN, WAITING -> {
                    break waiting;
                }
            }
            TimeUtil.coma(100);
        }
        this.shutdown = true;
    }

    @Override
    public void entrustWrite(String key, Supplier<String> action) {
        try {
            if (shutdown) {
                storage.write(
                        key,
                        action.get()
                );
                return;
            }
            processor.entrust(() -> new LeisurelyStorageInformation(
                    key,
                    action.get(),
                    preventSudden
            ));
        } catch (Exception e) {

        }
    }

    @Override
    public void write(String key, String information) throws IOException {
        if (shutdown) {
            storage.write(key, information);
            return;
        }
        processor.submit(new LeisurelyStorageInformation(
                key,
                information,
                preventSudden
        ));
    }

    @Override
    public String read(String key) throws IOException {
        String information;
        if (preventSudden) {
            if (lockingDump.contains(key)) {
                lockingStorage.add(key);
                information = storage.read(key);
                lockingStorage.remove(key);
            } else {
                lockingDump.add(key);
                information = dump.read(key);
                lockingDump.remove(key);
            }
        } else {
            information = storage.read(key);
        }
        return information;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    private record LeisurelyStorageInformation(String key, String information, boolean preventSudden) {

    }

    private static class LeisurelyStorageTask {
        private final Thread thread;
        private final ConcurrentLinkedQueue<Supplier<LeisurelyStorageInformation>> entrusts = new ConcurrentLinkedQueue<>();
        private final ConcurrentLinkedQueue<LeisurelyStorageInformation> waiting = new ConcurrentLinkedQueue<>();
        private final Map<String, Long> skipped = new Object2ObjectOpenHashMap<>();
        private LeisurelyStorageTaskStage stage;
        private String current;
        private boolean requestSkip;
        private boolean shutdown;
        private Pair<Boolean, Long> idling = new ObjectObjectImmutablePair<>(true, TimeUtil.nano());

        public long idleTime() {
            return idling.left() ? TimeUtil.processNano(idling.right()) : 0;
        }

        public LeisurelyStorageTask(LeisurelyStorage storage) {
            this.stage = LeisurelyStorageTaskStage.WAITING;

            thread = new Thread(() -> {
                try {
                    while (! storage.isShutdown()) {
                        synchronized (this.waiting) {
                            synchronized (this.waiting) {
                                if (entrusts.size() > 0) {
                                    this.stage = LeisurelyStorageTaskStage.RESOLVING;
                                    for (Supplier<LeisurelyStorageInformation> entrust : entrusts) {
                                        waiting.add(entrust.get());
                                        entrusts.remove(entrust);
                                    }
                                }
                            }
                            if (waiting.size() > 0) {
                                this.stage = LeisurelyStorageTaskStage.RESOLVING;
                                idling = new ObjectObjectImmutablePair<>(false, TimeUtil.nano());
                                for (LeisurelyStorageInformation information : waiting) {
                                    current = information.key();
                                    while (storage.lockingDump.contains(information.key())) {
                                        TimeUtil.coma(35);
                                    }
                                    storage.lockingDump.add(information.key());
                                    stage = LeisurelyStorageTaskStage.DUMPING;
                                    if (information.preventSudden()) {
                                        storage.dump.write(
                                                information.key(),
                                                information.information()
                                        );
                                    }
                                    storage.lockingDump.remove(information.key());

                                    if (requestSkip && skipped.getOrDefault(
                                            information.key(),
                                            0L
                                    ) < 5) {
                                        waiting.remove(information);
                                        skipped.put(
                                                information.key(),
                                                skipped.getOrDefault(
                                                        information.key(),
                                                        0L
                                                ) + 1L
                                        );
                                        requestSkip = false;
                                        continue;
                                    }

                                    skipped.remove(information.key());

                                    if (stage == LeisurelyStorageTaskStage.DUMPING) {
                                        while (storage.lockingStorage.contains(information.key())) {
                                            TimeUtil.coma(35);
                                        }
                                        storage.lockingStorage.add(information.key());
                                        stage = LeisurelyStorageTaskStage.WRITING;
                                        storage.storage.write(
                                                information.key(),
                                                information.information()
                                        );
                                        storage.lockingStorage.remove(information.key());
                                    }

                                    waiting.remove(information);
                                }

                                idling = new ObjectObjectImmutablePair<>(true, TimeUtil.nano());
                                stage = LeisurelyStorageTaskStage.WAITING;
                            } else {
                                TimeUtil.coma(30);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = LeisurelyStorageTaskStage.SHUTDOWN;
                shutdown = true;
            });
            thread.start();
        }

        public boolean isShutdown() {
            return shutdown;
        }

        public void submit(LeisurelyStorageInformation information) {
            this.stage = LeisurelyStorageTaskStage.RESOLVING;
            if (Objects.equals(
                        information.key(),
                        current
                )) {
                    requestSkip = true;
                }
                waiting.add(information);
        }

        public void entrust(Supplier<LeisurelyStorageInformation> information) {
            this.stage = LeisurelyStorageTaskStage.RESOLVING;
            entrusts.add(information);
        }

        public LeisurelyStorageTaskStage getStage() {
            return stage;
        }

        private enum LeisurelyStorageTaskStage {
            DUMPING, WRITING, WAITING, RESOLVING, SHUTDOWN
        }
    }
}
