package t;

import com.github.cao.awa.trtr.database.file.storage.independent.db.leveldb.cached.*;
import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;

import java.lang.management.*;

import static com.github.cao.awa.trtr.TrtrMod.LOGGER;
import static com.github.cao.awa.trtr.TrtrMod.timeTracker;

public class Test {
    public static void main(String[] args) {
        mem();
        testPureLevelDb();
        System.gc();
        mem();
    }

    public static void testPureLevelDb() {
        try {
            LevelDbStorage db = new LevelDbStorage("C:\\normal\\Codes\\Code-Java\\the-road-to-reality\\leveldb_demo\\db");
            timeTracker.start("Insert");
            for (int i = 0; i < 1000000; i++) {
                db.write(
                        "az" + i,
                        properties().toJSONObject()
                                    .toString()
                );
            }
            timeTracker.done("Insert");

            timeTracker.start("Query");
            System.out.println(db.read("az114"));
            timeTracker.done("Query");

            db.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InstanceProperties properties() {
        InstanceProperties properties = new InstanceProperties();
        ChemicalElementProperties elementProperties = new ChemicalElementProperties();
        for (ChemicalElement value : ChemicalElements.NAME_TO_ELEMENTS.values()) {
            elementProperties.put(
                    value,
                    new ChemicalContent(
                            value,
                            1.14514,
                            1919810
                    )
            );
            properties.put(
                    "TestingElements",
                    elementProperties
            );
        }
        return properties;
    }

    public static void mem() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

        long totalMemorySize = memoryUsage.getInit();
        long maxMemorySize = memoryUsage.getMax();
        long usedMemorySize = memoryUsage.getUsed();

        LOGGER.info(
                "TotalMemory: {}",
                totalMemorySize / (1024 * 1024) + "M"
        );
        LOGGER.info(
                "FreeMemory: {}",
                (totalMemorySize - usedMemorySize) / (1024 * 1024) + "M"
        );
        LOGGER.info(
                "MaxMemory: {}",
                maxMemorySize / (1024 * 1024) + "M"
        );
        LOGGER.info(
                "UsedMemory: {}",
                usedMemorySize / (1024 * 1024) + "M"
        );
    }
}