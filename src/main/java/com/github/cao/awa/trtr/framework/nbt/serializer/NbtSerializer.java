package com.github.cao.awa.trtr.framework.nbt.serializer;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingConsumer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@Auto
public interface NbtSerializer<T> {
    @Auto
    NbtElement serialize(T t);

    @Auto
    T deserialize(NbtElement element);

    T initializer();

    default NbtCompound compound(ExceptingConsumer<NbtCompound> action) {
        return EntrustEnvironment.operation(new NbtCompound(),
                                            action
        );
    }

    default <Y extends NbtElement> T as(@Nullable NbtElement element, Class<Y> type, Function<Y, T> function) {
        if (element == null) {
            return null;
        }
        if (type.isAssignableFrom(element.getClass())) {
            T result = function.apply(type.cast(element));
            if (result != null) {
                return result;
            }
        }
        return initializer();
    }

    default <Y extends NbtElement, Z> Z is(@Nullable NbtElement element, Class<Y> type, Function<Y, Z> function) {
        if (element == null) {
            return null;
        }
        if (type.isAssignableFrom(element.getClass())) {
            return function.apply(type.cast(element));
        }
        return null;
    }
}
