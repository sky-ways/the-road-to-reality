package t;

import java.util.*;

public final class TestChemicalElement {
    private String name;
    private int count;

    public TestChemicalElement(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public TestChemicalElement comp(TestChemicalElement element) {
        if (element.name.equals(name)) {
            this.count += element.count;
            element.count = 0;
        }
        return this;
    }

    public String name() {
        return name;
    }

    public int count() {
        return count;
    }

}
