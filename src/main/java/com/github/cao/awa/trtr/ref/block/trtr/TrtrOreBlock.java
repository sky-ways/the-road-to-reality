package com.github.cao.awa.trtr.ref.block.trtr;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.intprovider.*;

public class TrtrOreBlock extends TrtrBasedBlock {
    private final IntProvider experienceDropped;

    public TrtrOreBlock(Settings settings) {
        this(settings, ConstantIntProvider.create(0));
    }

    public TrtrOreBlock(Settings settings, IntProvider experience) {
        super(settings);
        this.experienceDropped = experience;
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, stack, dropExperience);
        if (dropExperience) {
            this.dropExperienceWhenMined(world, pos, stack, this.experienceDropped);
        }
    }
}
