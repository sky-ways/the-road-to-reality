package com.github.cao.awa.trtr.power.storage.battery;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.times.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;

public class Battery extends Item {
    public static final Identifier IDENTIFIER = new Identifier("trtr:battery");

    public Battery(Settings settings) {
        super(settings);
    }

    public static Item register() {
        Settings settings = new Settings();
        Battery battery = new Battery(settings);
        Registry.register(Registry.ITEM, IDENTIFIER, battery);
        return battery;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.getCount() == 1) {
            if (!stack.hasNbt()) {
                NbtCompound nbt = stack.getOrCreateNbt();
                nbt.putDouble("lifespan", 1);
                nbt.putDouble("storage", 114);
                nbt.putDouble("voltage", 114);
                nbt.putDouble("resistance", 114);
                nbt.putLong("D", TimeUtil.nano());
            }
        }
    }
}
