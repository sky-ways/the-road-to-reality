package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface ChemicalElemental<T> {
    default void generateElements(World world, BlockPos pos, InstanceProperties properties) {
        ChemicalElementProperties elementProperties = properties.get("elements");
        if (elementProperties == null) {
            elementProperties = new ChemicalElementProperties();
        }
        generateElements(world, pos, elementProperties);
        properties.put("elements", elementProperties);
    }

    void generateElements(World world, BlockPos pos, ChemicalElementProperties properties);

    default void adapterElements(World world, BlockPos pos, InstanceProperties properties) {
        if (!properties.contains("elements")) {
            generateElements(world, pos, properties);
        }
    }
}
