package com.github.cao.awa.trtr.ref.item;

import com.github.zhuaidadaya.rikaishinikui.handler.range.*;
import com.github.zhuaidadaya.rikaishinikui.handler.range.table.*;
import net.minecraft.item.*;

import java.util.*;

public interface Hammerable {
    RangeTable<Item, NumberRange<Item>> products();
    Set<Item> prototypes();
}
