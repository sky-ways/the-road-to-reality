package com.github.cao.awa.trtr.util;

import com.github.cao.awa.trtr.mixin.server.*;
import net.minecraft.server.*;

public class LevelUtil {
    public static String getServerLevelPath(MinecraftServer server) {
        return (server.isDedicated() ? "" : "saves/") + getServerLevelNamePath(server);
    }

    public static String getServerLevelNamePath(MinecraftServer server) {
        return getServerLevelName(server) + "/";
    }

    public static String getServerLevelName(MinecraftServer server) {
        return ((MinecraftServerInterface) server).getSession().getDirectoryName();
    }
}
