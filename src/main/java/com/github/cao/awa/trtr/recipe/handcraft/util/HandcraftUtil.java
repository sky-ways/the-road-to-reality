package com.github.cao.awa.trtr.recipe.handcraft.util;

import com.github.cao.awa.trtr.recipe.handcraft.HandcraftingRecipe;
import com.github.cao.awa.trtr.recipe.handcraft.inventory.HandcraftingInventory;
import com.github.cao.awa.trtr.recipe.type.TrtrRecipeType;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class HandcraftUtil {
    @SuppressWarnings("unchecked")
    public static Optional<RecipeEntry<HandcraftingRecipe>> getMaxCrafting(RecipeManager recipeManager, HandcraftingInventory inventory, World world) {
        try {
            Method method = recipeManager.getClass()
                                         .getDeclaredMethod("getAllOfType",
                                                            RecipeType.class
                                         );
            method.setAccessible(true);

            return ((Map<Identifier, RecipeEntry<HandcraftingRecipe>>) method.invoke(recipeManager,
                                                                                     TrtrRecipeType.HAND_CRAFTING
            ))
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
            return Optional.empty();
        }
    }
}
