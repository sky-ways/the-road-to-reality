package com.github.cao.awa.trtr.mixin.world.manager;

import net.minecraft.server.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin(ServerChunkManager.class)
public interface ServerChunkManagerInterface {
//    @Invoker("getChunkHolder")
//    ChunkHolder getChunkHolder(long pos);
}
