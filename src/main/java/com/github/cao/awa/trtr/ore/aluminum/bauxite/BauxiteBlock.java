package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.elements.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class BauxiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:bauxite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
        properties.put(
                ChemicalElements.ELEMENT_ALUMINUM,
                new ChemicalContent(
                        ChemicalElements.ELEMENT_ALUMINUM,
                        random.nextDouble(70, 85),
                        0
                )
        );

        properties.put(
                ChemicalElements.ELEMENT_CARBON,
                new ChemicalContent(
                        ChemicalElements.ELEMENT_CARBON,
                        random.nextDouble(0, 5),
                        0
                )
        );
    }
}
