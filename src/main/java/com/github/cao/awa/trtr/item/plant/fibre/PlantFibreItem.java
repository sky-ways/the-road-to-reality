package com.github.cao.awa.trtr.item.plant.fibre;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.item.craft.CraftingItem;
import net.minecraft.util.Identifier;

@Auto
public class PlantFibreItem extends CraftingItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:plant_fibre");

    @Auto
    public PlantFibreItem(Settings settings) {
        super(settings);
    }
}
