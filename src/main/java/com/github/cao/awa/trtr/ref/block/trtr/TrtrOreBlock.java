package com.github.cao.awa.trtr.ref.block.trtr;

import com.github.cao.awa.trtr.ore.generic.*;
import com.github.cao.awa.trtr.ref.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.util.registry.*;
import org.jetbrains.annotations.*;

public abstract class TrtrOreBlock extends TrtrBasedBlock implements OreRegister {
    private final IntProvider experienceDropped;

    public TrtrOreBlock(Settings settings) {
        this(settings, ConstantIntProvider.create(0));
        register();
    }

    public TrtrOreBlock() {
        this(defaultSettings());
    }

    public TrtrOreBlock(Settings settings, IntProvider experience) {
        super(settings);
        this.experienceDropped = experience;
    }

    public void register() {
        Registry.register(Registry.BLOCK, identifier(), this);
        new TrtrOreBlockItem(this).register();
    }

    public static Settings defaultSettings() {
        return Settings.of(Material.METAL).hardness(4F).requiresTool();
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, stack, dropExperience);
        if (dropExperience) {
            this.dropExperienceWhenMined(world, pos, stack, this.experienceDropped);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
