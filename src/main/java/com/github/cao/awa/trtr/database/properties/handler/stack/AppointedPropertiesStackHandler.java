package com.github.cao.awa.trtr.database.properties.handler.stack;

import com.github.cao.awa.trtr.database.properties.handler.*;
import com.github.cao.awa.trtr.database.properties.stack.*;
import com.github.cao.awa.trtr.database.properties.type.*;
import net.minecraft.nbt.*;
import org.json.*;

public class AppointedPropertiesStackHandler extends PropertiesHandler<AppointedPropertiesStack<?>> {
    public AppointedPropertiesStackHandler() {
        super(
                "*STACK",
                (Class<AppointedPropertiesStack<?>>) new AppointedPropertiesStack<>().getClass()
        );
    }

    @Override
    public AppointedPropertiesStack<?> getFromJSON(String key, JSONObject json) {
        //        return Numerical.parseByte(json.get(key).toString());
        return AppointedPropertiesStack.deserialize(json.get(key)
                                                        .toString());
    }

    @Override
    public void putToJSON(String key, AppointedPropertiesStack<?> target, JSONObject json) {
        json.put(
                key,
                target.serialize()
        );
    }
}
