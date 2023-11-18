package com.github.cao.awa.trtr.constant.trtr;

import com.github.cao.awa.modmdo.annotation.platform.Client;
import com.github.cao.awa.modmdo.annotation.platform.Server;
import com.github.cao.awa.trtr.framework.side.LoadingSide;

public class TrtrConstants {
    public static boolean isServer = false;

    public static LoadingSide getLoadingSide(Class<?> clazz) {
        boolean isServer = clazz.isAnnotationPresent(Server.class);
        boolean isClient = clazz.isAnnotationPresent(Client.class);

        if (isServer && isClient) {
            return LoadingSide.BOTH;
        } else if (isServer) {
            return LoadingSide.SERVER;
        } else if (isClient) {
            return LoadingSide.CLIENT;
        } else {
            return LoadingSide.BOTH;
        }
    }
}
