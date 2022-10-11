package com.github.cao.awa.trtr.ref.block.trtr;

import com.github.cao.awa.trtr.database.properties.*;
import com.github.cao.awa.trtr.element.chemical.*;
import com.github.cao.awa.trtr.element.chemical.properties.*;
import com.github.cao.awa.trtr.ore.generic.*;
import com.github.cao.awa.trtr.type.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public abstract class TrtrOreBlock extends TrtrBlockWithEntity<TrtrOreBlockEntity> implements ChemicalElemental<TrtrOreBlockEntity> {
    private final IntProvider experienceDropped;
    public static final List<TrtrOreBlock> ORES = new ObjectArrayList<>();

    public TrtrOreBlock(Settings settings) {
        this(settings, ConstantIntProvider.create(0));
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
        new TrtrGenBlockItem(this);
        ORES.add(this);
    }

    @Override
    public BlockEntityType<TrtrOreBlockEntity> blockEntityType() {
        return TrtrBlockEntityType.ORE;
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
        return new TrtrOreBlockEntity(pos, state, this);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, TrtrOreBlockEntity blockEntity) {

    }

    public void generateElements(World world, BlockPos pos, ChemicalElementProperties properties) {
//        properties.put();
    }
}
