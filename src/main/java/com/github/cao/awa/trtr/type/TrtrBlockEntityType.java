package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.power.photovoltaic.panels.*;
import com.github.cao.awa.trtr.power.thermoelectric.fire.burner.*;
import com.github.cao.awa.trtr.ref.block.iron.*;
import com.google.common.collect.*;
import com.mojang.datafixers.types.*;
import com.mojang.logging.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.datafixer.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import org.slf4j.*;

import java.util.*;

public class TrtrBlockEntityType<T extends BlockEntity> extends BlockEntityType<T> {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final BlockEntityType.BlockEntityFactory<? extends T> factory;
    private final Set<Block> blocks;
    public static final BlockEntityType<PhotovoltaicPanelsBlockEntity> PHOTOVOLTAIC_PANELS = create("trtr:photovoltaic_panels", Builder.create(PhotovoltaicPanelsBlockEntity::new, TrtrBlocks.PHOTOVOLTAIC_PANELS));
    public static final BlockEntityType<BurnerBlockEntity> BURNER = create("trtr:burner", Builder.create(BurnerBlockEntity::new, TrtrBlocks.BURNER));

    public TrtrBlockEntityType(BlockEntityType.BlockEntityFactory<? extends T> factory, Set<Block> blocks, Type<?> type) {
        super(factory, blocks, type);
        this.factory = factory;
        this.blocks = blocks;
    }

    public static void pre() {

    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id, Builder<T> builder) {
        if (builder.blocks().isEmpty()) {
            LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", id);
        }

        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, builder.build(type));
    }

    @Nullable
    public T instantiate(BlockPos pos, BlockState state) {
        return this.factory.create(pos, state);
    }    public static final BlockEntityType<IronBlockEntity> IRON_BLOCK = create("minecraft:iron", Builder.create(IronBlockEntity::new, Blocks.IRON_BLOCK));

    public boolean supports(BlockState state) {
        return this.blocks.contains(state.getBlock());
    }

    @Nullable
    public T get(BlockView world, BlockPos pos) {
        T blockEntity = (T) world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.getType() == this ? blockEntity : null;
    }

    @FunctionalInterface
    private interface BlockEntityFactory<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }

    public record Builder<T extends BlockEntity>(BlockEntityType.BlockEntityFactory<? extends T> factory, Set<Block> blocks) {
        public static <T extends BlockEntity> Builder<T> create(BlockEntityType.BlockEntityFactory<? extends T> factory, Block... blocks) {
            return new Builder<>(factory, ImmutableSet.copyOf(blocks));
        }

        public BlockEntityType<T> build(Type<?> type) {
            return new BlockEntityType<>(this.factory, this.blocks, type);
        }
    }
}

