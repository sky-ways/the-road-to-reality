package com.github.cao.awa.trtr.food.egg;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.util.*;

import java.util.function.*;

public class FriedEgg extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:fried_egg");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
