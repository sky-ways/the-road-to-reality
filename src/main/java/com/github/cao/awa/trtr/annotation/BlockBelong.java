package com.github.cao.awa.trtr.annotation;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.apricot.anntation.Stable;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * An annotation mark a item type is belong to a block.
 * </p>
 * <p>
 * Mainly used in minecraft item that belong to minecraft block.
 * </p>
 * <br>
 * <p>
 * This annotation is skip mark, so can also use in other cases, the framework will not process that.
 * </p>
 * <br>
 * <p>
 * Can only annotate at type location.
 * </p>
 *
 * @author cao_awa
 * @author 草二号机
 * @see Item The item
 * @see Block The block
 * @see BlockItem BlockItem (Most used in)
 * @see BlockFramework The block framework
 * @since 1.0.0
 */
@Auto
@Stable
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BlockBelong {
}
