package com.github.cao.awa.trtr.block.turbine;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.annotation.data.gen.NoModel;
import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.gas.GasPassable;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Auto
@NoModel
public class SteamTurbineBlock extends TrtrBlock implements GasPassable {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:steam_turbine");
    @Auto
    public static final Settings SETTINGS = FabricBlockSettings.create();
    @Auto
    public static BlockItem ITEM;

    @Auto
    public SteamTurbineBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canGasPass(BlockState state, Direction direction) {
        return true;
    }

    @Override
    public void onGasPass(World world, BlockPos pos, BlockState state, Direction from) {

    }
}
