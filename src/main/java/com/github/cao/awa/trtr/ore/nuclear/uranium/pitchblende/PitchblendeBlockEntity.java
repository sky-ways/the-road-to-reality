package com.github.cao.awa.trtr.ore.nuclear.uranium.pitchblende;

import com.github.cao.awa.trtr.ref.nuclear.*;
import com.github.cao.awa.trtr.type.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.entity.effect.*;
import net.minecraft.nbt.*;
import net.minecraft.server.network.*;
import net.minecraft.tag.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class PitchblendeBlockEntity extends BlockEntity implements NuclearDiffusible {
    public PitchblendeBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.PITCHBLENDE, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        Set<Entity> entities = simple(world, pos);

        for (Entity entity : entities) {
            if (entity instanceof ItemEntity item) {
                continue;
            }
            entity.damage(new DamageSource("trtr.nuclear.disease"), 0.2F);
        }
    }
}
