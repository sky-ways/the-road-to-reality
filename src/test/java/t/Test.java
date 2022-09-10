package t;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Bl bl1 = new Bl(180);
//        Bl bl2 = new Bl(180);
//        Bl bl3 = new Bl(180);
//        Bl bl4 = new Bl(100);
//        Bl bl5 = new Bl(100);
//        Bl bl6 = new Bl(100);

        Bl a1 = new Bl(0);

        for (int i = 100; i >0;i--) {
            a1.collect(bl1);
//            a1.collect(bl2);
//            a1.collect(bl3);
//            a1.collect(bl4);
//            a1.collect(bl5);
//            a1.collect(bl6);

            a1.heat();
        }
    }

    public static class Bl {
        public double temperature;
        public ArrayList<Bl> heated = new ArrayList<>();

        public Bl(int temperature) {
            this.temperature = temperature;
        }

        public void heat() {
            double cons = 0;
            for (Bl bl : heated) {
                if (bl.temperature > temperature) {
                    double tc = bl.temperature - temperature;
                    double v = Math.ceil((tc * 0.01));
                    cons += v;
                    bl.temperature -= v;

                    System.out.println("V: " + v);
                }
            }

            this.temperature += cons;

            System.out.println(temperature);

            heated.clear();
        }

        public void collect(Bl bl) {
            heated.add(bl);
        }
    }
}
