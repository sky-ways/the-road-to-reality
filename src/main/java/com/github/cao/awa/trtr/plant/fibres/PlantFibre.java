package com.github.cao.awa.trtr.plant.fibres;

import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class PlantFibre extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:plant_fiber");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }
}
