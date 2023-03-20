package com.github.cao.awa.trtr.framework.serializer;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.ExceptingConsumer;
import net.minecraft.nbt.NbtCompound;

@Auto
public interface NbtSerializer {
    @Auto
    NbtCompound toNbt();

    @Auto
    void fromNbt(NbtCompound compound);

    default NbtCompound nbt(ExceptingConsumer<NbtCompound> action) {
        return EntrustEnvironment.operation(new NbtCompound(),
                                            action
        );
    }
}
