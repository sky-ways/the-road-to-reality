package com.github.cao.awa.trtr.properties.stack;

import it.unimi.dsi.fastutil.objects.*;

import java.util.*;

public abstract class PropertiesStack<T> {
    public final List<T> list;
    private final int capacity;
    private final boolean preStack;

    public PropertiesStack(List<T> list) {
        this.list = list;
        this.capacity = -1;
        this.preStack = true;
    }

    public PropertiesStack(List<T> list, int capacity) {
        this.list = list;
        this.capacity = capacity;
        this.preStack = false;
    }

    public boolean stack(T t) {
        if (this.stackable()) {
            this.list.add(t);
            return true;
        }
        return false;
    }

    public T pop() {
        if (this.stackable() && this.size() > 0) {
            T t = this.list.get(this.size() - 1);
            this.list.remove(t);
            return t;
        }
        return null;
    }

    public List<T> pops() {
        if (this.stackable()) {
            List<T> list = new ObjectArrayList<>();
            if (this.size() > 0) {
                list.addAll(this.list);
                list.clear();
                return list;
            }
        }
        return Collections.emptyList();
    }

    public int size() {
        return this.list.size();
    }

    public boolean stackable(){
        return this.preStack || this.size() < this.capacity;
    }
}
