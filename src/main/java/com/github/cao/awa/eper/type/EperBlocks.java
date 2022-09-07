package com.github.cao.awa.eper.type;

import com.github.cao.awa.eper.power.photovoltaic.panels.*;
import com.github.cao.awa.eper.power.thermoelectric.fire.burner.*;
import net.minecraft.block.*;

public class EperBlocks {
    public static final Block PHOTOVOLTAIC_PANELS = PhotovoltaicPanels.register();
    public static final Block BURNER = Burner.register();

    public static void pre() {

    }
}
