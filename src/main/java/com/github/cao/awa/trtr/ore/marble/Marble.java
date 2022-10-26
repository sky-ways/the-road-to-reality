package com.github.cao.awa.trtr.ore.marble;

import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.element.chemical.substance.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class Marble extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:marble");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
        properties.put(
                ChemicalSubstances.CALCIUM_CARBONATE,
                new ChemicalContent(
                        ChemicalSubstances.CALCIUM_CARBONATE,
                        random.nextDouble(70, 85),
                        0
                )
        );
    }
}
