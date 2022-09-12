package com.github.cao.awa.trtr.type;

import net.minecraft.entity.attribute.*;
import net.minecraft.util.registry.*;

public class TrtrEntityAttributes extends EntityAttributes {
    public static final EntityAttribute THUMP_EFFICIENCY = register("trtr:thump_efficiency", (new ClampedEntityAttribute("attribute.name.trtr.thump_efficiency", 0.0D, 0.0D, 1024.0D)).setTracked(true));

    private static EntityAttribute register(String id, EntityAttribute attribute) {
        return Registry.register(Registry.ATTRIBUTE, id, attribute);
    }
}
