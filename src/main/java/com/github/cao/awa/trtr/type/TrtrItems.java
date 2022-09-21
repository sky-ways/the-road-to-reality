package com.github.cao.awa.trtr.type;

import com.github.cao.awa.trtr.container.bowl.wooden.water.*;
import com.github.cao.awa.trtr.container.bucket.wooden.*;
import com.github.cao.awa.trtr.debuger.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.crushed.*;
import com.github.cao.awa.trtr.ore.aluminum.bauxite.powder.*;
import com.github.cao.awa.trtr.ore.carbon.coal.crushed.*;
import com.github.cao.awa.trtr.ore.carbon.coal.powder.*;
import com.github.cao.awa.trtr.ore.copper.chalcopyrite.crushed.*;
import com.github.cao.awa.trtr.ore.copper.chalcopyrite.powder.*;
import com.github.cao.awa.trtr.ore.copper.cuprite.crushed.*;
import com.github.cao.awa.trtr.ore.copper.cuprite.powder.*;
import com.github.cao.awa.trtr.ore.iron.hematite.crushed.*;
import com.github.cao.awa.trtr.ore.iron.hematite.powder.*;
import com.github.cao.awa.trtr.ore.lead.galena.crushed.*;
import com.github.cao.awa.trtr.ore.lead.galena.powder.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.crushed.*;
import com.github.cao.awa.trtr.ore.silver.acanthite.powder.*;
import com.github.cao.awa.trtr.ore.stone.crushed.*;
import com.github.cao.awa.trtr.ore.stone.powder.*;
import com.github.cao.awa.trtr.plant.fibres.*;
import com.github.cao.awa.trtr.power.storage.battery.*;
import com.github.cao.awa.trtr.tool.hammer.iron.*;
import com.github.cao.awa.trtr.tool.hammer.stone.*;
import com.github.cao.awa.trtr.tool.hammer.wooden.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;

public class TrtrItems {
    public static final ObjectArrayList<Item> items = new ObjectArrayList<>();
    public static final Item BATTERY = new Battery();
    public static final Item THERMOMETER = new HandHoldThermometer();
    public static final Item IRON_HAMMER = new IronHammer();
    public static final Item STONE_HAMMER = new StoneHammer();
    public static final Item WOODEN_HAMMER = new WoodenHammer();

    public static final Item WOODEN_BOWL_WATERED = new WoodenBowlWateredItem();

    public static final Item WOODEN_BUCKET = WoodenBucket.register(WoodenBucket.IDENTIFIER, Fluids.EMPTY);
    public static final Item WATER_WOODEN_BUCKET = WoodenBucket.register(WoodenBucket.WATER_IDENTIFIER, Fluids.WATER);

    public static final Item PLANT_FIBER = new PlantFibre();

    public static final Item BAUXITE_POWDER = new BauxitePowder();
    public static final Item ACANTHITE_POWDER = new AcanthitePowder();
    public static final Item GALENA_POWDER = new GalenaPowder();
    public static final Item COAL_POWDER = new CoalPowder();
    public static final Item HEMATITE_POWDER = new HematitePowder();
    public static final Item DEEPSLATE_POWDER = new DeepslatePowder();
    public static final Item STONE_POWDER = new StonePowder();
    public static final Item CHALCOPYRITE_POWDER = new ChalcopyritePowder();
    public static final Item CUPRITE_POWDER = new CupritePowder();

    public static final Item CRUSHED_BAUXITE = new CrushedBauxite();
    public static final Item CRUSHED_ACANTHITE = new CrushedAcanthite();
    public static final Item CRUSHED_GALENA = new CrushedGalena();
    public static final Item CRUSHED_COAL = new CrushedCoal();
    public static final Item CRUSHED_HEMATITE = new CrushedHematite();
    public static final Item CRUSHED_CHALCOPYRITE = new CrushedChalcopyrite();
    public static final Item CRUSHED_CUPRITE = new CrushedCuprite();

    public static final Item CRUSHED_DEEPSLATE_BAUXITE = new CrushedDeepslateBauxite();
    public static final Item CRUSHED_DEEPSLATE_ACANTHITE = new CrushedDeepslateAcanthite();
    public static final Item CRUSHED_DEEPSLATE_GALENA = new CrushedDeepslateGalena();
    public static final Item CRUSHED_DEEPSLATE_COAL = new CrushedDeepslateCoal();
    public static final Item CRUSHED_DEEPSLATE_HEMATITE = new CrushedDeepslateHematite();
    public static final Item CRUSHED_DEEPSLATE_CHALCOPYRITE = new CrushedDeepslateChalcopyrite();
    public static final Item CRUSHED_DEEPSLATE_CUPRITE = new CrushedDeepslateCuprite();

    public static final Item CRUSHED_DEEPSLATE = new CrushedDeepslate();
    public static final Item CRUSHED_STONE = new CrushedStone();

    public static void pre() {
    }
}
