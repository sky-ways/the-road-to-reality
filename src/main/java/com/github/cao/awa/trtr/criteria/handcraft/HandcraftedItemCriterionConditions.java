package com.github.cao.awa.trtr.criteria.handcraft;

import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class HandcraftedItemCriterionConditions extends AbstractCriterionConditions {
    private final ItemPredicate mainStackPredicate;
    private final ItemPredicate offStackPredicate;
    private final List<ItemPredicate> resultStacksPredicate;

    public HandcraftedItemCriterionConditions(Optional<LootContextPredicate> playerPredicate, @Nullable ItemPredicate mainStackPredicate, @Nullable ItemPredicate offStackPredicate, @NotNull List<ItemPredicate> resultStacksPredicate) {
        super(playerPredicate);
        this.mainStackPredicate = mainStackPredicate;
        this.offStackPredicate = offStackPredicate;
        this.resultStacksPredicate = resultStacksPredicate;
    }

    public boolean matches(ItemStack mainStack, ItemStack offStack, List<ItemStack> resultStacks) {
        boolean match = true;

        if (this.mainStackPredicate != null) {
            match = this.mainStackPredicate.test(mainStack);
        }
        if (this.offStackPredicate != null) {
            match &= this.offStackPredicate.test(offStack);
        }

        for (ItemPredicate resultPredicate : this.resultStacksPredicate) {
            boolean mm = false;
            for (ItemStack stack : resultStacks) {
                mm |= resultPredicate.test(stack);
            }
            match &= mm;
        }

        return match;
    }
}
