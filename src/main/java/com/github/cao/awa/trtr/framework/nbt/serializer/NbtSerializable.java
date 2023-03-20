package com.github.cao.awa.trtr.framework.nbt.serializer;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingConsumer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.function.Consumer;

@Auto
public interface NbtSerializable {
    @Auto
    NbtElement toNbt();

    @Auto
    void fromNbt(NbtElement element);

    default NbtCompound compound(ExceptingConsumer<NbtCompound> action) {
        return EntrustEnvironment.operation(new NbtCompound(),
                                            action
        );
    }

    default <Y extends NbtElement> void as(NbtElement element, Class<Y> type, Consumer<Y> function) {
        if (type.isAssignableFrom(element.getClass())) {
            function.accept(type.cast(element));
        }
    }
}
