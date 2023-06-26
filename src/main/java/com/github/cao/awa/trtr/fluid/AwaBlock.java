package com.github.cao.awa.trtr.fluid;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.annotation.dev.DevOnly;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Auto
@DevOnly
public class AwaBlock extends FluidBlock {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:awa");

    @Auto
    public static final AbstractBlock.Settings SETTINGS = AbstractBlock.Settings.create()
                                                                                .mapColor(MapColor.WATER_BLUE)
                                                                                .replaceable()
                                                                                .noCollision()
                                                                                .strength(100.0F)
                                                                                .pistonBehavior(PistonBehavior.DESTROY)
                                                                                .dropsNothing()
                                                                                .liquid()
                                                                                .sounds(BlockSoundGroup.field_44608);

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity living) {
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,
                                                            1,
                                                            2
            ));
        }
    }

    public AwaBlock(Settings settings) {
        super(TrtrMod.AWA,
              settings
        );
    }
}
