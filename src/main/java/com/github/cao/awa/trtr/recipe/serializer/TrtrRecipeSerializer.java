package com.github.cao.awa.trtr.recipe.serializer;

import com.github.cao.awa.trtr.recipe.handing.HandCraftingRecipe;
import net.minecraft.recipe.RecipeSerializer;

public class TrtrRecipeSerializer {
    public static final RecipeSerializer<HandCraftingRecipe> HAND_CRAFTING = RecipeSerializer.register("trtr:hand_crafting",
                                                                                                       new HandCraftingRecipe.Serializer()
    );

    public static void initialize() {

    }
}
