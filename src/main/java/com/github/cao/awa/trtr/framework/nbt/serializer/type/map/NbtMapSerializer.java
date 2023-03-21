package com.github.cao.awa.trtr.framework.nbt.serializer.type.map;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import net.minecraft.nbt.NbtElement;

import java.util.Map;

public class NbtMapSerializer implements NbtSerializer<Map<?, ?>> {
    @Override
    public NbtElement serialize(Map<?, ?> objects) {
        return null;
    }

    @Override
    public Map<?, ?> deserialize(NbtElement element) {
        return null;
    }

    @Override
    public Map<?, ?> initializer() {
        return ApricotCollectionFactor.newHashMap();
    }
}
