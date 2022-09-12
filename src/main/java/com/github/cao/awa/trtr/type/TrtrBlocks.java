package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.ore.aluminum.alunite.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.deepslate.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.deepslate.*;
import com.github.cao.awa.trtr.power.photovoltaic.panels.*;
import com.github.cao.awa.trtr.power.thermoelectric.fire.burner.*;
import net.minecraft.block.*;

public class TrtrBlocks {
    public static final Block PHOTOVOLTAIC_PANELS = PhotovoltaicPanels.register();
    public static final Block BURNER = BurnerBlock.register();

    public static final Block BAUXITE_BLOCK = BauxiteBlock.register();
    public static final Block DEEPSLATE_BAUXITE_BLOCK = DeepslateBauxiteBlock.register();

    public static final Block ALUNITE_BLOCK = AluniteBlock.register();

    public static final Block ACANTHITE_BLOCK = AcanthiteBlock.register();
    public static final Block DEEPSLATE_ACANTHITE_BLOCK = DeepslateAcanthiteBlock.register();

    public static void pre() {

    }
}
