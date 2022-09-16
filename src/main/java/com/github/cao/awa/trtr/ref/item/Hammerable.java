package com.github.cao.awa.trtr.ref.item;

import com.github.zhuaidadaya.rikaishinikui.handler.rage.*;
import com.github.zhuaidadaya.rikaishinikui.handler.rage.table.*;
import net.minecraft.item.*;

import java.util.*;

public interface Hammerable {
    RageTable<Item, NumberRage<Item>> products();
    Set<Item> prototypes();
}
