package com.github.cao.awa.trtr.mixin.world.chunk;

import com.github.cao.awa.trtr.element.generator.*;
import com.github.cao.awa.trtr.ref.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin {
    @Shadow public abstract World getWorld();

    @Shadow @Final
    World world;

    @Inject(method = "addBlockEntity", at = @At("HEAD"))
    public void addBlockEntity(BlockEntity blockEntity, CallbackInfo ci) {
        blockEntity.setWorld(world);
        if (blockEntity instanceof HeatConductiveBlockEntity heatConductive) {
            heatConductive.init(world);
        }
        if (blockEntity instanceof ChemicalElementGenerator generator) {
            generator.generateElement();
        }
    }
}
