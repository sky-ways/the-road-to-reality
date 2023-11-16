package com.github.cao.awa.trtr.pair.ingredient;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;

public record IngredientPair(Ingredient main, Ingredient off) {
    public void write(PacketByteBuf buf) {
        main().write(buf);
        off().write(buf);
    }

    public static IngredientPair create(PacketByteBuf buf) {
        Ingredient mainIngredient = Ingredient.fromPacket(buf);
        Ingredient offIngredient = Ingredient.fromPacket(buf);

        return new IngredientPair(
                mainIngredient,
                offIngredient
        );
    }
}