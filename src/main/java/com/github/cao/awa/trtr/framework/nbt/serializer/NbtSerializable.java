package com.github.cao.awa.trtr.framework.nbt.serializer;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingConsumer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.function.Consumer;

@Auto
public interface NbtSerializable {
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

    @Auto
    default NbtElement toNbt() {
        return compound(compound -> TrtrMod.BLOCK_FRAMEWORK.nbtSerializeFramework()
                                                           .writeNbt(this,
                                                                     compound
                                                           ));
    }

    @Auto
    default void fromNbt(NbtElement element) {
        as(element,
           NbtCompound.class,
           compound -> TrtrMod.BLOCK_FRAMEWORK.nbtSerializeFramework()
                                              .readNbt(this,
                                                       compound
                                              )
        );
    }
}
