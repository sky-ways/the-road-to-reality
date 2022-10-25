package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
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
                ChemicalElements.ALUMINUM_ELEMENT,
                new ChemicalContent(
                        ChemicalElements.ALUMINUM_ELEMENT,
                        random.nextDouble(70, 85),
                        0
                )
        );

        properties.put(
                ChemicalElements.ALUMINUM_ELEMENT,
                new ChemicalContent(
                        ChemicalElements.ALUMINUM_ELEMENT,
                        random.nextDouble(0, 5),
                        0
                )
        );
    }
}
