package com.github.cao.awa.trtr;

import com.github.cao.awa.modmdo.annotations.platform.*;
import net.fabricmc.api.*;
import org.apache.logging.log4j.*;

import java.io.*;

@Client
@Server
public class TrtrMod implements ModInitializer {
    @Override
    public void onInitialize() {
        initializeConfig();

    }

    public static void initializeConfig() {
    }
}
