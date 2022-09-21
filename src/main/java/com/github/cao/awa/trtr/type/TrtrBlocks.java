package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.air.*;
import com.github.cao.awa.trtr.ore.aluminum.alunite.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.deepslate.*;
import com.github.cao.awa.trtr.ore.copper.chalcopyrite.*;
import com.github.cao.awa.trtr.ore.copper.chalcopyrite.deepslate.*;
import com.github.cao.awa.trtr.ore.copper.cuprite.*;
import com.github.cao.awa.trtr.ore.copper.cuprite.deepslate.*;
import com.github.cao.awa.trtr.ore.copper.malachite.*;
import com.github.cao.awa.trtr.ore.feldspar.albite.*;
import com.github.cao.awa.trtr.ore.feldspar.orthoclase.*;
import com.github.cao.awa.trtr.ore.lead.galena.*;
import com.github.cao.awa.trtr.ore.lead.galena.deepslate.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.deepslate.*;
import com.github.cao.awa.trtr.power.photovoltaic.panels.*;
import com.github.cao.awa.trtr.power.thermoelectric.fire.burner.*;
import com.github.cao.awa.trtr.transmission.gearwheel.*;
import net.minecraft.block.*;

public class TrtrBlocks {
    public static final Block PHOTOVOLTAIC_PANELS = new PhotovoltaicPanels();
    public static final Block BURNER = new BurnerBlock();

    public static final Block BAUXITE_BLOCK = new BauxiteBlock();
    public static final Block DEEPSLATE_BAUXITE_BLOCK = new DeepslateBauxiteBlock();

    public static final Block ALUNITE_BLOCK = new AluniteBlock();

    public static final Block ACANTHITE_BLOCK = new AcanthiteBlock();
    public static final Block DEEPSLATE_ACANTHITE_BLOCK = new DeepslateAcanthiteBlock();

    public static final Block ALBITE_BLOCK = new AlbiteBlock();

    public static final Block GALENA_BLOCK = new GalenaBlock();
    public static final Block DEEPSLATE_GALENA_BLOCK = new DeepslateGalenaBlock();

    public static final Block ORTHOCLASE_BLOCK = new OrthoclaseBlock();

    public static final Block CHALCOPYRITE_BLOCK = new Chalcopyrite();
    public static final Block CUPRITE_BLOCK = new Cuprite();
    public static final Block DEEPSLATE_CHALCOPYRITE_BLOCK = new DeepslateChalcopyrite();
    public static final Block DEEPSLATE_CUPRITE_BLOCk = new DeepslateCuprite();

    public static final Block MALACHITE_BLOCK = new Malachite();

    public static final Block WATER_VAPOR = new WaterVapor();

    public static final Block GEAR_WHEEL = new GearWheel();

    public static void pre() {

    }
}
