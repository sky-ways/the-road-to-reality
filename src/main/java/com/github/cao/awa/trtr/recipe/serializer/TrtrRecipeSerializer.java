package com.github.cao.awa.trtr.recipe.serializer;

import com.github.cao.awa.trtr.recipe.handcraft.HandcraftingRecipe;
import net.minecraft.recipe.RecipeSerializer;

public class TrtrRecipeSerializer {
    public static final RecipeSerializer<HandcraftingRecipe> HANDCRAFTING = RecipeSerializer.register("trtr:handcrafting",
                                                                                                      new HandcraftingRecipe.Serializer()
    );

    public static void initialize() {

    }
}
