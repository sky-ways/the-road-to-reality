package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.transmission.gearwheel.*;
import com.github.cao.awa.trtr.transmission.gearwheel.test.*;
import com.google.common.collect.ImmutableSet;
import com.mojang.logging.LogUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class TrtrEntityType<T extends Entity> extends EntityType<T> {

    //    public static final EntityType<ElectricWire> ELECTRIC_WIRE;
    public static final EntityType<GearWheelEntity>         GEARWHEEL = register("trtr:gearwheel", EntityType.Builder.create(GearWheelEntity::new, SpawnGroup.MISC).setDimensions(0.35F, 0.6F).maxTrackingRange(8).trackingTickInterval(2));

    public TrtrEntityType(EntityFactory<T> factory, SpawnGroup spawnGroup, boolean saveable, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> canSpawnInside, EntityDimensions dimensions, int maxTrackDistance, int trackTickInterval) {
        super(factory, spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, canSpawnInside, dimensions, maxTrackDistance, trackTickInterval);
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, id, type.build(id));
    }

    public static void pre() {

    }
}

