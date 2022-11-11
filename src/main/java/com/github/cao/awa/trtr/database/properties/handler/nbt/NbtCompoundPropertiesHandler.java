package com.github.cao.awa.trtr.database.properties.handler.nbt;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.database.properties.type.*;
import com.github.cao.awa.trtr.math.*;
import net.minecraft.nbt.*;
import org.json.*;

// TODO
public class NbtCompoundPropertiesHandler extends PropertiesHandler<NbtCompound> {
    public NbtCompoundPropertiesHandler() {
        super(
                "[Nbt[C",
                NbtCompound.class
        );
    }

    @Override
    public NbtCompound getFromJSON(String key, JSONObject json) {
        //        return Numerical.parseByte(json.get(key).toString());
        return NbtCompoundSerializer.deserialize(json.get(key)
                                                     .toString());
    }

    @Override
    public void putToJSON(String key, NbtCompound target, JSONObject json) {
        json.put(
                key,
                NbtCompoundSerializer.serialize(target)
        );
    }
}
