package com.github.cao.awa.trtr.data.gen.advbancement.nitrate;

import com.github.cao.awa.trtr.type.*;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

import java.util.function.*;

public class NitrateAdvancementGen implements Consumer<Consumer<Advancement>>{
    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement parentAdvancement = Advancement.Builder.create()
                .display(TrtrBlocks.NITER_BLOCK, Text.translatable("advancements.nitrate.title"),
                        Text.translatable("advancements.nitrate.description"),
                        new Identifier("textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false)
                .criterion("inventory_changed", InventoryChangedCriterion.Conditions.items(Items.CRAFTING_TABLE))
                .build(consumer, "nitrate/root");

        Advancement exampleBlockAdvancement = Advancement.Builder.create()
                .display(
                        TrtrItems.NITER_POWDER,
                        Text.translatable("advancements.niter_block.title"),
                        Text.translatable("advancements.niter_block.description"),
                        new Identifier("textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .parent(parentAdvancement)
                .criterion("inventory_changed", InventoryChangedCriterion.Conditions.items(TrtrBlocks.NITER_BLOCK))
                .build(consumer, "nitrate/nitrate_block_in_inventory");
    }
}
