package com.github.cao.awa.trtr.ore.marble;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.content.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.element.chemical.substance.*;
import com.github.cao.awa.trtr.ref.block.trtr.*;
import net.minecraft.client.item.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.github.cao.awa.trtr.TrtrMod.propertiesDatabase;

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
                        random.nextDouble(
                                70,
                                85
                        )
                )
        );

        properties.put(ChemicalSubstances.CARBON_DIOXIDE,
                       new ChemicalContent(
                               ChemicalSubstances.CARBON_DIOXIDE,
                               random.nextDouble(
                                       10,
                                       15
                               )
                       )
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.of("Chemical elements"));
        InstanceProperties properties = propertiesDatabase.get(stack.getOrCreateNbt().getString("acs"));

        ChemicalElementProperties elementProperties = properties.get("elements");

        if (elementProperties != null) {
            elementProperties.forEach((element, content) -> {
                tooltip.add(Text.of(element.getName() + ": " + content.getPercent() + "%"));
            });
        }
    }
}
