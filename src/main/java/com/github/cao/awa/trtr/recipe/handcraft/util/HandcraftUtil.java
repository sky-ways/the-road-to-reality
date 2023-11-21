package com.github.cao.awa.trtr.recipe.handcraft.util;

import com.github.cao.awa.trtr.mixin.recipe.RecipeManagerInvoker;
import com.github.cao.awa.trtr.recipe.handcraft.HandcraftingRecipe;
import com.github.cao.awa.trtr.recipe.handcraft.inventory.HandcraftingInventory;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class HandcraftUtil {
    public static Optional<RecipeEntry<HandcraftingRecipe>> getMaxCrafting(RecipeManager recipeManager, HandcraftingInventory inventory, World world) {
        try {
            // TODO
            return getMaxCrafting(recipeManager)
                    .values()
                    .stream()
                    .filter((recipe) -> recipe.value()
                                              .ingredientMatches(inventory,
                                                                 world
                                              )
                    )
                    .max(Comparator.comparingInt(x -> x.value()
                                                       .range()
                                                       .max())
                    );
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private static Map<Identifier, RecipeEntry<HandcraftingRecipe>> getMaxCrafting(RecipeManager recipeManager) {
        return ((RecipeManagerInvoker) recipeManager).getAllOfType(TrtrRecipeType.HAND_CRAFTING);
    }
}
