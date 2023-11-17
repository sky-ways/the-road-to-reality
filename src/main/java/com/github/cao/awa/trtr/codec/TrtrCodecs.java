package com.github.cao.awa.trtr.codec;

import com.github.cao.awa.trtr.math.range.IntegerRange;
import com.github.cao.awa.trtr.pair.bool.BooleanPair;
import com.github.cao.awa.trtr.pair.ingredient.IngredientPair;
import com.github.cao.awa.trtr.pair.integer.IntegerPair;
import com.github.cao.awa.trtr.pair.item.ItemStackPair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeCodecs;

public class TrtrCodecs {
    public static final Codec<IntegerRange> INTEGER_RANGE = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("min")
                                       .forGetter(IntegerRange :: min),
                              Codec.INT.fieldOf("max")
                                       .forGetter(IntegerRange :: max)
                       )
                       .apply(instance,
                              IntegerRange :: new
                       );
    });

    public static final Codec<IntegerPair> INTEGER_PAIR = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.INT.fieldOf("main")
                                       .orElse(0)
                                       .forGetter(IntegerPair :: main),
                              Codec.INT.fieldOf("off")
                                       .orElse(0)
                                       .forGetter(IntegerPair :: off)
                       )
                       .apply(instance,
                              IntegerPair :: new
                       );
    });
    public static final Codec<BooleanPair> BOOLEAN_PAIR = RecordCodecBuilder.create(instance -> {
        return instance.group(Codec.BOOL.fieldOf("main")
                                        .orElse(false)
                                        .forGetter(BooleanPair :: main),
                              Codec.BOOL.fieldOf("off")
                                        .orElse(false)
                                        .forGetter(BooleanPair :: off)
                       )
                       .apply(instance,
                              BooleanPair :: new
                       );
    });
    public static final Codec<ItemStackPair> ITEM_STACK_PAIR = RecordCodecBuilder.create(instance -> {
        return instance.group(RecipeCodecs.CRAFTING_RESULT.fieldOf("main")
                                                          .forGetter(ItemStackPair :: main),
                              RecipeCodecs.CRAFTING_RESULT.fieldOf("off")
                                                          .forGetter(ItemStackPair :: off)
                       )
                       .apply(instance,
                              ItemStackPair :: new
                       );
    });

    public static final Codec<IngredientPair> INGREDIENT_PAIR = RecordCodecBuilder.create(instance -> {
        return instance.group(Ingredient.ALLOW_EMPTY_CODEC.fieldOf("main")
                                                          .orElse(Ingredient.empty())
                                                          .forGetter(IngredientPair :: main),
                              Ingredient.ALLOW_EMPTY_CODEC.fieldOf("off")
                                                          .forGetter(IngredientPair :: off)
                       )
                       .apply(instance,
                              IngredientPair :: new
                       );
    });
}
