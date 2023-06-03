package com.github.cao.awa.trtr.annotation.data.gen;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Stable;
import com.github.cao.awa.trtr.block.example.full.ExampleBlock;
import com.github.cao.awa.trtr.block.example.simple.SimpleExampleBlock;
import com.github.cao.awa.trtr.framework.block.data.gen.BlockDataGenFramework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * An annotation mark a type or field should auto process by datagen framework.
 * </p>
 * <p>
 * Mainly used in minecraft blocks, items, entities and other need process by data generator framework types.
 * </p>
 * <br>
 * <p>
 * This annotation can also use in other data generator processor marks in other framework.
 * </p>
 * <br>
 * <p>
 * Can only annotate at field, type, method location.
 * </p>
 *
 * @author cao_awa
 * @see NoModel
 * @see BlockDataGenFramework The datagen framework
 * @see SimpleExampleBlock Simple uses example
 * @see ExampleBlock Uses example
 * @since 1.0.0
 */
@Auto
@Stable
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface DataGen {
}
