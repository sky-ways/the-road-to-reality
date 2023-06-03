package com.github.cao.awa.trtr.annotation.property;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Stable;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import net.minecraft.state.property.Property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * An annotation mark a field should auto process by framework.
 * </p>
 * <p>
 * Mainly used in minecraft block state property  {@link  Property net.minecraft.state.property.Property} and impls.
 * </p>
 * <br>
 * <p>
 * This annotation can also use in other property auto processor marks in other framework.
 * </p>
 * <p>
 * The minecraft block state property framework will ignored not {@link  Property net.minecraft.state.property.Property} field.
 * </p>
 * <br>
 * <p>
 * Can only annotate at field location.
 * </p>
 *
 * @author 草二号机
 * @author cao_awa
 * @see Property Block state property
 * @see BlockFramework The block framework
 * @since 1.0.0
 */
@Auto
@Stable
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoProperty {
}
