package com.github.cao.awa.trtr.mixin.server;

import net.minecraft.server.*;
import net.minecraft.world.level.storage.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

/**
 * Reference <a herf="https://github.com/Szum123321/textile_backup/blob/2.x/src/main/java/net/szum123321/textile_backup/mixin/MinecraftServerSessionAccessor.java">TextileBackup</a>
 */
@Mixin(MinecraftServer.class)
public interface MinecraftServerInterface {
    @Accessor
    LevelStorage.Session getSession();
}