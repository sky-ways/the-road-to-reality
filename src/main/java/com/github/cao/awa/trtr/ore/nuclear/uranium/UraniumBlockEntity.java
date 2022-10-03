package com.github.cao.awa.trtr.ore.nuclear.uranium;

import com.github.cao.awa.trtr.ref.nuclear.*;
import com.github.cao.awa.trtr.type.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class UraniumBlockEntity extends BlockEntity implements NuclearDiffusible {
    public UraniumBlockEntity(BlockPos pos, BlockState state) {
        super(TrtrBlockEntityType.URANIUM, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        Set<Entity> entities = track(world, pos);

//        for (Entity entity : entities) {
//            if (entity instanceof LivingEntity item) {
//                entity.damage(new DamageSource("trtr.nuclear.disease"), 0.2F);
//            }
//        }
    }

    @Override
    public double radiation() {
        return 0;
    }

    @Override
    public double radioactivity() {
        return 0;
    }
}
