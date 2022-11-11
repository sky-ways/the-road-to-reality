package com.github.cao.awa.trtr.mixin.block.campfire;

import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import net.fabricmc.fabric.mixin.client.rendering.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.recipe.*;
import net.minecraft.util.*;
import net.minecraft.util.collection.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;
import org.checkerframework.checker.units.qual.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

// TODO
@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
//    private int tick = 100;
//
//    @Inject(method = "litServerTick", at = @At("HEAD"))
//    private static void litServerTick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
//        CampfireBlockEntityMixin camp = EntrustEnvironment.cast(campfire);
//        if (camp.tick == 0) {
//            camp.tick = 100;
//        }
//
//        camp.tick--;
//
//        if (camp.tick == 0) {
//            world.setBlockState(pos, state.with(CampfireBlock.LIT, false), 2);
//        }
//    }
//
//    @Inject(method = "writeNbt", at = @At("HEAD"))
//    private void writeNbt(NbtCompound nbt, CallbackInfo ci) {
//        nbt.putInt("tick", tick);
//    }
//
//    @Inject(method = "readNbt", at = @At("HEAD"))
//    private void readNbt(NbtCompound nbt, CallbackInfo ci) {
//        this.tick = nbt.getInt("tick");
//    }
}
