package com.github.cao.awa.trtr.pebble;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.util.*;

public class Pebble extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:pebble");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
