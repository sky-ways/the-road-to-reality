package t;

import com.github.cao.awa.modmdo.security.*;
import com.github.cao.awa.trtr.database.file.storage.system.*;
import com.github.cao.awa.trtr.database.file.storage.system.sector.*;
import com.github.cao.awa.trtr.database.file.storage.system.writer.*;
import com.github.cao.awa.trtr.math.base.*;
import com.github.cao.awa.trtr.util.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import com.mojang.logging.*;
import it.unimi.dsi.fastutil.longs.*;
import it.unimi.dsi.fastutil.objects.*;
import org.apache.commons.codec.binary.*;
import org.apache.logging.log4j.*;

import java.io.*;
import java.lang.management.*;
import java.nio.charset.*;
import java.util.*;

public class Test {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            File file = new File("C:\\normal\\Codes\\Code-Java\\the-road-to-reality\\test\\partition.dump");

            Partition partition = new Partition(file);

            byte[] s1 = "Sector1 here is".getBytes(StandardCharsets.UTF_8);
            byte[] s2 = "Sector2 here is".getBytes(StandardCharsets.UTF_8);
//
//            partition.write(1, s1);
//            partition.write(2, s2);
//
            byte[] reading1 = new byte[s1.length];
            byte[] reading2 = new byte[s2.length];
//
//            LOGGER.info(StringUtils.newStringUtf8(partition.read(1, reading1)));
//            LOGGER.info(StringUtils.newStringUtf8(partition.read(2, reading2)));
//
//            LOGGER.info("Swapping sector 1 to sector 2");
//            partition.swap(1, 2);

            LOGGER.info(StringUtils.newStringUtf8(partition.read(1, reading1)));
            LOGGER.info(StringUtils.newStringUtf8(partition.read(2, reading2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
