package com.github.cao.awa.trtr.criteria.handcraft;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Optional;

public class HandcraftedItemCriterion extends AbstractCriterion<HandcraftedItemCriterionConditions> {
    @Override
    protected HandcraftedItemCriterionConditions conditionsFromJson(JsonObject jsonObject, Optional<LootContextPredicate> predicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        ItemPredicate mainStackPredicate = ItemPredicate.fromJson(jsonObject.get("main"))
                                                        .orElse(null);
        ItemPredicate offStackPredicate = ItemPredicate.fromJson(jsonObject.get("off"))
                                                       .orElse(null);

        List<ItemPredicate> resultStacksPredicate = ApricotCollectionFactor.arrayList();
        if (jsonObject.has("results")) {
            for (JsonElement results : jsonObject.getAsJsonArray("results")) {
                ItemPredicate.fromJson(results)
                             .ifPresent(resultStacksPredicate :: add);
            }
        }

        return new HandcraftedItemCriterionConditions(predicate,
                                                      mainStackPredicate,
                                                      offStackPredicate,
                                                      resultStacksPredicate
        );
    }

    public void trigger(ServerPlayerEntity player, ItemStack mainStack, ItemStack offStack, List<ItemStack> resultStacks) {
        trigger(player,
                (conditions) -> {
                    return conditions.matches(mainStack,
                                              offStack,
                                              resultStacks
                    );
                }
        );
    }
}
