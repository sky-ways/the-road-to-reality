package com.github.cao.awa.trtr.ref.block.trtr;

import com.github.cao.awa.trtr.heat.conductor.*;
import com.github.cao.awa.trtr.ore.generic.*;
import com.github.cao.awa.trtr.ref.*;
import com.github.cao.awa.trtr.ref.block.*;
import com.github.cao.awa.trtr.register.*;
import com.github.cao.awa.trtr.tool.hammer.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public abstract class TrtrBasedBlock extends BlockWithEntity implements HeatConductiveBlock, ElectricConductive, BlockEntityProvider, RefRegister {
    private TrtrBlockRegister register;
    public final Random random = new Random();

    public TrtrBasedBlock(Settings settings) {
        super(settings);
        this.register = new TrtrBlockRegister().registerBlock(true).registerItem(true).block(this);
        register();
    }

    public TrtrBasedBlock(Settings settings, TrtrBlockRegister register) {
        super(settings);
        this.register = register.block(this);
        register();
    }

    @Override
    public HeatConductor getConductor() {
        return null;
    }

    @Override
    public int thermalConductivity() {
        return 0;
    }

    @Override
    public void setConductor(HeatConductor conductor) {

    }

    public void register() {
        register.register();
    }

    @Override
    public int resistance() {
        return 114;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        boolean mainHand = player.getMainHandStack().getItem() instanceof Hammer;
        thump(world, pos, state, mainHand ? player.getMainHandStack() : player.getOffHandStack(), player);
    }

    public void thump(World world, BlockPos pos, BlockState state, ItemStack tool, PlayerEntity player) {

    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
