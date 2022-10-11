package com.github.cao.awa.trtr.dev.dump.mixin.screen.hud.debug;

import bot.inker.inkrender.*;
import com.github.zhuaidadaya.rikaishinikui.handler.option.*;

import java.util.*;

public class DebugHudMixinDev {
    public static void appendDebugRightText(List<String> elements) {
        elements.add("");
        elements.add("Inker Renderer " + InkerRender.VERSION);
        elements.add("Allocated: " + MemoryResourcePack.INSTANCE.allocated());
        elements.add("Locators: " + InkerRender.resourceService().registered());
        BiOption<Boolean> option = MemoryResourcePack.INSTANCE.asyncOption();
        elements.add("Memory type: " + (option.first() ? "ASYNC" : (option.second() ? "SYNC" : "UNSAFE")));

        //        elements.add("");
        //        elements.add("The road to reality (" + TrtrMod.VERSION + ")");
        //        elements.add();
    }
}
