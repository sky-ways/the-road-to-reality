package com.github.cao.awa.trtr.ore.aluminum.bauxite;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;

import java.util.*;

public class BauxiteBlock extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:bauxite");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
        properties.put(
                ChemicalElements.ALUMINUM,
                new ChemicalContent(
                        ChemicalElements.ALUMINUM,
                        new Random().nextDouble(70, 85),
                        0
                )
        );

        properties.put(
                ChemicalElements.CARBON,
                new ChemicalContent(
                        ChemicalElements.CARBON,
                        new Random().nextDouble(0, 5),
                        0
                )
        );
    }
}
