package com.github.cao.awa.trtr.element.chemical;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public interface ChemicalElemental<T> {
    default void generateElements(World world, BlockPos pos, InstanceProperties<T> properties) {
        ChemicalElementProperties elementProperties = properties.get("chemical_elements");
        if (elementProperties == null) {
            elementProperties = new ChemicalElementProperties();
            properties.put("chemical_elements", elementProperties);
        }
        generateElements(world, pos, elementProperties);
    }

    void generateElements(World world, BlockPos pos, ChemicalElementProperties properties);
}
