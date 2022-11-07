package t;

import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public class Test {
    public static void main(String[] args) {
    }

    public static class ElectricPower extends Electric {
        private boolean checking = false;
        private boolean powering = false;

        public ElectricPower(String name) {
            super(name);
        }

        @Override
        public boolean canPower() {
            if (! checking) {
                return super.canPower() && electrics.stream()
                                                    .allMatch(Electric::canPower);
            } else {
                checking = false;
                return true;
            }
        }

        @Override
        public void electric() {
            if (! powering) {
                System.out.println("Powering");
                powering = true;
                electrics.forEach(Electric::electric);
            } else {
                System.out.println("Loop composition");
                powering = false;
            }
        }

        @Override
        public boolean connected() {
            checking = true;
            return super.canPower() && electrics.stream()
                                                .allMatch(Electric::canPower);
        }
    }

    public static class ElectricLight extends Electric {
        public ElectricLight(String name) {
            super(name);
        }

        @Override
        public boolean canPower() {
            return super.canPower() && electrics.stream()
                                                .allMatch(Electric::canPower);
        }
    }

    public static class ElectricLine extends Electric {
        public ElectricLine(String name) {
            super(name);
        }

        @Override
        public boolean canPower() {
            return super.canPower() && electrics.stream()
                                                .allMatch(Electric::canPower);
        }
    }

    public static abstract class Electric {
        public final List<Electric> electrics = new ObjectArrayList<>();
        public final String name;

        public Electric(String name) {
            this.name = name;
        }

        public void join(Electric electric) {
            electrics.add(electric);
        }

        public boolean canPower() {
            return electrics.size() > 0;
        }

        public void electric() {
            System.out.println("Electric to: " + name);
            electrics.forEach(Electric::electric);
        }

        public boolean connected() {
            return canPower() && electrics.stream()
                                          .allMatch(Electric::connected);
        }
    }
}