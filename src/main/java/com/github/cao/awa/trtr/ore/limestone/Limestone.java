package com.github.cao.awa.trtr.ore.limestone;

import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class Limestone extends TrtrOreBlock {
    public static final Identifier IDENTIFIER = new Identifier("trtr:limestone");

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
        properties.put(
                ChemicalElements.CALCIUM_CARBONATE,
                new ChemicalContent(
                        ChemicalElements.CALCIUM_CARBONATE,
                        random.nextDouble(70, 85),
                        0
                )
        );
    }
}
