package t;

import com.github.cao.awa.trtr.debuger.performance.tracker.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.util.math.*;

import java.util.*;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        double dx = Math.abs(1.2);
        double dy = Math.abs(1.2);
        double angle = Math.toRadians(Math.round(Math.asin(Math.sqrt(dy / (dx * dx + dy * dy))) / Math.PI * 180));
        double l = Math.tan(angle);
        System.out.println(angle);
        System.out.println(l);
    }
}
