package com.github.cao.awa.trtr.debuger;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.ref.item.trtr.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;

import static com.github.cao.awa.trtr.TrtrMod.heatHandler;

public class HandHoldThermometer extends TrtrItem {
    public static final Identifier IDENTIFIER = new Identifier("trtr:hand_hold_thermometer");

    public HandHoldThermometer() {
        super(new Settings().maxCount(1));
    }

    @Override
    public Identifier identifier() {
        return IDENTIFIER;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.ANY);
        HeatConductor conductor = heatHandler.getConductor(world, hitResult.getBlockPos());
        if (conductor != null) {
            user.sendMessage(Text.of("Temperature of block " + hitResult.getBlockPos() + " is: " + conductor.getTemperature()), true);
        } else {
            user.sendMessage(Text.of("The block " + hitResult.getBlockPos() + " is haven not a temperature register"), true);
        }
        return super.use(world, user, hand);
    }
}
