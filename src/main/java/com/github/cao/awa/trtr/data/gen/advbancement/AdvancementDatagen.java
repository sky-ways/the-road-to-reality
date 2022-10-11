package com.github.cao.awa.trtr.data.gen.advbancement;

import com.github.cao.awa.trtr.data.gen.advbancement.nitrate.*;
import com.google.common.collect.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.advancement.*;
import net.minecraft.util.*;

import java.util.*;
import java.util.function.*;

public class AdvancementDatagen extends FabricAdvancementProvider {
    private static final List<Consumer<Consumer<Advancement>>> generators = Util.make(Lists.newArrayList(), list -> {
        list.add(new NitrateAdvancementGen());
    });

    public AdvancementDatagen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        generators.forEach(i -> i.accept(consumer));
    }
}
