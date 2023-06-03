package com.github.cao.awa.trtr.annotation.serializer;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Stable;
import com.github.cao.awa.trtr.block.example.full.entity.ExampleBlockEntity;
import com.github.cao.awa.trtr.block.example.simple.entity.SimpleExampleBlockEntity;
import com.github.cao.awa.trtr.framework.nbt.NbtSerializeFramework;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * An annotation mark a field should auto process serialize and deserialize to the nbt by framework.
 * </p>
 * <p>
 * Mainly used in block entity data and other need nbt serialize types.
 * </p>
 * <br>
 * <p>
 * This annotation can also use in other nbt auto processor marks in other framework.
 * </p>
 * <br>
 * <p>
 * Can only annotate at field location.
 * </p>
 *
 * @author cao_awa
 * @author 草二号机
 * @see NbtCompound The nbt
 * @see NbtElement The nbt base
 * @see NbtSerializeFramework The nbt framework
 * @see NbtSerializer The nbt serializer
 * @see NbtSerializable The nbt serializable (Direct serialilzer)
 * @see SimpleExampleBlockEntity Simple uses example
 * @see ExampleBlockEntity Uses example
 * @since 1.0.0
 */
@Auto
@Stable
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoNbt {
    String value() default "";
}
