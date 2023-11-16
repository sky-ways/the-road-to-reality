package com.github.cao.awa.trtr.mixin.item;

import com.github.cao.awa.trtr.item.flint.FlintItem;
import com.github.cao.awa.trtr.item.stick.StickItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.item.trim.ArmorTrimPatterns;
import net.minecraft.registry.tag.BannerPatternTags;
import net.minecraft.registry.tag.InstrumentTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Mutable
    @Shadow
    @Final
    public static Item AIR;
    @Mutable
    @Shadow
    @Final
    public static Item STONE;
    @Mutable
    @Shadow
    @Final
    public static Item GRANITE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_GRANITE;
    @Mutable
    @Shadow
    @Final
    public static Item DIORITE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DIORITE;
    @Mutable
    @Shadow
    @Final
    public static Item ANDESITE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_ANDESITE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLED_DEEPSLATE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DEEPSLATE;
    @Mutable
    @Shadow
    @Final
    public static Item CALCITE;
    @Mutable
    @Shadow
    @Final
    public static Item TUFF;
    @Mutable
    @Shadow
    @Final
    public static Item DRIPSTONE_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item GRASS_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item DIRT;
    @Mutable
    @Shadow
    @Final
    public static Item COARSE_DIRT;
    @Mutable
    @Shadow
    @Final
    public static Item PODZOL;
    @Mutable
    @Shadow
    @Final
    public static Item ROOTED_DIRT;
    @Mutable
    @Shadow
    @Final
    public static Item MUD;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_NYLIUM;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_NYLIUM;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLESTONE;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_PLANKS;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_MOSAIC;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_SAPLING;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_PROPAGULE;
    @Mutable
    @Shadow
    @Final
    public static Item BEDROCK;
    @Mutable
    @Shadow
    @Final
    public static Item SAND;
    @Mutable
    @Shadow
    @Final
    public static Item SUSPICIOUS_SAND;
    @Mutable
    @Shadow
    @Final
    public static Item SUSPICIOUS_GRAVEL;
    @Mutable
    @Shadow
    @Final
    public static Item RED_SAND;
    @Mutable
    @Shadow
    @Final
    public static Item GRAVEL;
    @Mutable
    @Shadow
    @Final
    public static Item COAL_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_COAL_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_IRON_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item COPPER_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_COPPER_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item GOLD_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_GOLD_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item REDSTONE_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_REDSTONE_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item EMERALD_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_EMERALD_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item LAPIS_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_LAPIS_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_DIAMOND_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_GOLD_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_QUARTZ_ORE;
    @Mutable
    @Shadow
    @Final
    public static Item ANCIENT_DEBRIS;
    @Mutable
    @Shadow
    @Final
    public static Item COAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item RAW_IRON_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item RAW_COPPER_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item RAW_GOLD_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item AMETHYST_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item BUDDING_AMETHYST;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item COPPER_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item GOLD_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item EXPOSED_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WEATHERED_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item OXIDIZED_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item EXPOSED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WEATHERED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item OXIDIZED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item EXPOSED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WEATHERED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item OXIDIZED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item EXPOSED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item WEATHERED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item OXIDIZED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_COPPER_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_EXPOSED_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_WEATHERED_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_OXIDIZED_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_EXPOSED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_WEATHERED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_OXIDIZED_CUT_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_EXPOSED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_WEATHERED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_OXIDIZED_CUT_COPPER_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_EXPOSED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_WEATHERED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item WAXED_OXIDIZED_CUT_COPPER_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_ROOTS;
    @Mutable
    @Shadow
    @Final
    public static Item MUDDY_MANGROVE_ROOTS;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_STEM;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_STEM;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_OAK_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_SPRUCE_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_BIRCH_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_JUNGLE_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_ACACIA_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_CHERRY_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_DARK_OAK_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_MANGROVE_LOG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_CRIMSON_STEM;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_WARPED_STEM;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_OAK_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_SPRUCE_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_BIRCH_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_JUNGLE_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_ACACIA_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_CHERRY_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_DARK_OAK_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_MANGROVE_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_CRIMSON_HYPHAE;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_WARPED_HYPHAE;
    @Mutable
    @Shadow
    @Final
    public static Item STRIPPED_BAMBOO_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_WOOD;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_HYPHAE;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_HYPHAE;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item AZALEA_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item FLOWERING_AZALEA_LEAVES;
    @Mutable
    @Shadow
    @Final
    public static Item SPONGE;
    @Mutable
    @Shadow
    @Final
    public static Item WET_SPONGE;
    @Mutable
    @Shadow
    @Final
    public static Item GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item TINTED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item LAPIS_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item COBWEB;
    @Mutable
    @Shadow
    @Final
    public static Item GRASS;
    @Mutable
    @Shadow
    @Final
    public static Item FERN;
    @Mutable
    @Shadow
    @Final
    public static Item AZALEA;
    @Mutable
    @Shadow
    @Final
    public static Item FLOWERING_AZALEA;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BUSH;
    @Mutable
    @Shadow
    @Final
    public static Item SEAGRASS;
    @Mutable
    @Shadow
    @Final
    public static Item SEA_PICKLE;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item RED_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_WOOL;
    @Mutable
    @Shadow
    @Final
    public static Item DANDELION;
    @Mutable
    @Shadow
    @Final
    public static Item POPPY;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_ORCHID;
    @Mutable
    @Shadow
    @Final
    public static Item ALLIUM;
    @Mutable
    @Shadow
    @Final
    public static Item AZURE_BLUET;
    @Mutable
    @Shadow
    @Final
    public static Item RED_TULIP;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_TULIP;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_TULIP;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_TULIP;
    @Mutable
    @Shadow
    @Final
    public static Item OXEYE_DAISY;
    @Mutable
    @Shadow
    @Final
    public static Item CORNFLOWER;
    @Mutable
    @Shadow
    @Final
    public static Item LILY_OF_THE_VALLEY;
    @Mutable
    @Shadow
    @Final
    public static Item WITHER_ROSE;
    @Mutable
    @Shadow
    @Final
    public static Item TORCHFLOWER;
    @Mutable
    @Shadow
    @Final
    public static Item PITCHER_PLANT;
    @Mutable
    @Shadow
    @Final
    public static Item SPORE_BLOSSOM;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_MUSHROOM;
    @Mutable
    @Shadow
    @Final
    public static Item RED_MUSHROOM;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_FUNGUS;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_FUNGUS;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_ROOTS;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_ROOTS;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_SPROUTS;
    @Mutable
    @Shadow
    @Final
    public static Item WEEPING_VINES;
    @Mutable
    @Shadow
    @Final
    public static Item TWISTING_VINES;
    @Mutable
    @Shadow
    @Final
    public static Item SUGAR_CANE;
    @Mutable
    @Shadow
    @Final
    public static Item KELP;
    @Mutable
    @Shadow
    @Final
    public static Item MOSS_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_PETALS;
    @Mutable
    @Shadow
    @Final
    public static Item MOSS_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item HANGING_ROOTS;
    @Mutable
    @Shadow
    @Final
    public static Item BIG_DRIPLEAF;
    @Mutable
    @Shadow
    @Final
    public static Item SMALL_DRIPLEAF;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_MOSAIC_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_STONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SANDSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_SANDSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item PETRIFIED_OAK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLESTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item MUD_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item QUARTZ_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item RED_SANDSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_RED_SANDSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item PURPUR_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_PRISMARINE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_QUARTZ;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_RED_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_STONE;
    @Mutable
    @Shadow
    @Final
    public static Item BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item BOOKSHELF;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_BOOKSHELF;
    @Mutable
    @Shadow
    @Final
    public static Item DECORATED_POT;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_COBBLESTONE;
    @Mutable
    @Shadow
    @Final
    public static Item OBSIDIAN;
    @Mutable
    @Shadow
    @Final
    public static Item TORCH;
    @Mutable
    @Shadow
    @Final
    public static Item END_ROD;
    @Mutable
    @Shadow
    @Final
    public static Item CHORUS_PLANT;
    @Mutable
    @Shadow
    @Final
    public static Item CHORUS_FLOWER;
    @Mutable
    @Shadow
    @Final
    public static Item PURPUR_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item PURPUR_PILLAR;
    @Mutable
    @Shadow
    @Final
    public static Item PURPUR_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SPAWNER;
    @Mutable
    @Shadow
    @Final
    public static Item CHEST;
    @Mutable
    @Shadow
    @Final
    public static Item CRAFTING_TABLE;
    @Mutable
    @Shadow
    @Final
    public static Item FARMLAND;
    @Mutable
    @Shadow
    @Final
    public static Item FURNACE;
    @Mutable
    @Shadow
    @Final
    public static Item LADDER;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLESTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SNOW;
    @Mutable
    @Shadow
    @Final
    public static Item ICE;
    @Mutable
    @Shadow
    @Final
    public static Item SNOW_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item CACTUS;
    @Mutable
    @Shadow
    @Final
    public static Item CLAY;
    @Mutable
    @Shadow
    @Final
    public static Item JUKEBOX;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item PUMPKIN;
    @Mutable
    @Shadow
    @Final
    public static Item CARVED_PUMPKIN;
    @Mutable
    @Shadow
    @Final
    public static Item JACK_O_LANTERN;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERRACK;
    @Mutable
    @Shadow
    @Final
    public static Item SOUL_SAND;
    @Mutable
    @Shadow
    @Final
    public static Item SOUL_SOIL;
    @Mutable
    @Shadow
    @Final
    public static Item BASALT;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BASALT;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_BASALT;
    @Mutable
    @Shadow
    @Final
    public static Item SOUL_TORCH;
    @Mutable
    @Shadow
    @Final
    public static Item GLOWSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_STONE;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_COBBLESTONE;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_MOSSY_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_CRACKED_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_CHISELED_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item INFESTED_DEEPSLATE;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item CRACKED_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item PACKED_MUD;
    @Mutable
    @Shadow
    @Final
    public static Item MUD_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item CRACKED_DEEPSLATE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_TILES;
    @Mutable
    @Shadow
    @Final
    public static Item CRACKED_DEEPSLATE_TILES;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_DEEPSLATE;
    @Mutable
    @Shadow
    @Final
    public static Item REINFORCED_DEEPSLATE;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_MUSHROOM_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item RED_MUSHROOM_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item MUSHROOM_STEM;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_BARS;
    @Mutable
    @Shadow
    @Final
    public static Item CHAIN;
    @Mutable
    @Shadow
    @Final
    public static Item GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item MELON;
    @Mutable
    @Shadow
    @Final
    public static Item VINE;
    @Mutable
    @Shadow
    @Final
    public static Item GLOW_LICHEN;
    @Mutable
    @Shadow
    @Final
    public static Item BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item MUD_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item MYCELIUM;
    @Mutable
    @Shadow
    @Final
    public static Item LILY_PAD;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item CRACKED_NETHER_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_NETHER_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_BRICK_FENCE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SCULK;
    @Mutable
    @Shadow
    @Final
    public static Item SCULK_VEIN;
    @Mutable
    @Shadow
    @Final
    public static Item SCULK_CATALYST;
    @Mutable
    @Shadow
    @Final
    public static Item SCULK_SHRIEKER;
    @Mutable
    @Shadow
    @Final
    public static Item ENCHANTING_TABLE;
    @Mutable
    @Shadow
    @Final
    public static Item END_PORTAL_FRAME;
    @Mutable
    @Shadow
    @Final
    public static Item END_STONE;
    @Mutable
    @Shadow
    @Final
    public static Item END_STONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item DRAGON_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SANDSTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item ENDER_CHEST;
    @Mutable
    @Shadow
    @Final
    public static Item EMERALD_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_MOSAIC_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item COMMAND_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item BEACON;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLESTONE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_COBBLESTONE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item RED_SANDSTONE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_STONE_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item GRANITE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item MUD_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item ANDESITE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item RED_NETHER_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item SANDSTONE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item END_STONE_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item DIORITE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item BLACKSTONE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLED_DEEPSLATE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DEEPSLATE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_BRICK_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_TILE_WALL;
    @Mutable
    @Shadow
    @Final
    public static Item ANVIL;
    @Mutable
    @Shadow
    @Final
    public static Item CHIPPED_ANVIL;
    @Mutable
    @Shadow
    @Final
    public static Item DAMAGED_ANVIL;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_QUARTZ_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item QUARTZ_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item QUARTZ_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item QUARTZ_PILLAR;
    @Mutable
    @Shadow
    @Final
    public static Item QUARTZ_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item RED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BARRIER;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT;
    @Mutable
    @Shadow
    @Final
    public static Item HAY_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item RED_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_CARPET;
    @Mutable
    @Shadow
    @Final
    public static Item TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item PACKED_ICE;
    @Mutable
    @Shadow
    @Final
    public static Item DIRT_PATH;
    @Mutable
    @Shadow
    @Final
    public static Item SUNFLOWER;
    @Mutable
    @Shadow
    @Final
    public static Item LILAC;
    @Mutable
    @Shadow
    @Final
    public static Item ROSE_BUSH;
    @Mutable
    @Shadow
    @Final
    public static Item PEONY;
    @Mutable
    @Shadow
    @Final
    public static Item TALL_GRASS;
    @Mutable
    @Shadow
    @Final
    public static Item LARGE_FERN;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item RED_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_STAINED_GLASS;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item RED_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_STAINED_GLASS_PANE;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_PRISMARINE;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_PRISMARINE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SEA_LANTERN;
    @Mutable
    @Shadow
    @Final
    public static Item RED_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_RED_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item CUT_RED_SANDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item RED_SANDSTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item REPEATING_COMMAND_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item CHAIN_COMMAND_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item MAGMA_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_WART_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_WART_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item RED_NETHER_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item BONE_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item STRUCTURE_VOID;
    @Mutable
    @Shadow
    @Final
    public static Item SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item RED_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_SHULKER_BOX;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item RED_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_GLAZED_TERRACOTTA;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item RED_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_CONCRETE;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item RED_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_CONCRETE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item TURTLE_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SNIFFER_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_TUBE_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BRAIN_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BUBBLE_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_FIRE_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_HORN_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item TUBE_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item BRAIN_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item BUBBLE_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item FIRE_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item HORN_CORAL_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item TUBE_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item BRAIN_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item BUBBLE_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item FIRE_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item HORN_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BRAIN_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BUBBLE_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_FIRE_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_HORN_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_TUBE_CORAL;
    @Mutable
    @Shadow
    @Final
    public static Item TUBE_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item BRAIN_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item BUBBLE_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item FIRE_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item HORN_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_TUBE_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BRAIN_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_BUBBLE_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_FIRE_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item DEAD_HORN_CORAL_FAN;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_ICE;
    @Mutable
    @Shadow
    @Final
    public static Item CONDUIT;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_GRANITE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_RED_SANDSTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_STONE_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DIORITE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_COBBLESTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item END_STONE_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_SANDSTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_QUARTZ_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item GRANITE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item ANDESITE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item RED_NETHER_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_ANDESITE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item DIORITE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLED_DEEPSLATE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DEEPSLATE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_TILE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_GRANITE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_RED_SANDSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_STONE_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DIORITE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item MOSSY_COBBLESTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item END_STONE_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_SANDSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SMOOTH_QUARTZ_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item GRANITE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item ANDESITE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item RED_NETHER_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_ANDESITE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item DIORITE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item COBBLED_DEEPSLATE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_DEEPSLATE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item DEEPSLATE_TILE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item SCAFFOLDING;
    @Mutable
    @Shadow
    @Final
    public static Item REDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item REDSTONE_TORCH;
    @Mutable
    @Shadow
    @Final
    public static Item REDSTONE_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item REPEATER;
    @Mutable
    @Shadow
    @Final
    public static Item COMPARATOR;
    @Mutable
    @Shadow
    @Final
    public static Item PISTON;
    @Mutable
    @Shadow
    @Final
    public static Item STICKY_PISTON;
    @Mutable
    @Shadow
    @Final
    public static Item SLIME_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item HONEY_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item OBSERVER;
    @Mutable
    @Shadow
    @Final
    public static Item HOPPER;
    @Mutable
    @Shadow
    @Final
    public static Item DISPENSER;
    @Mutable
    @Shadow
    @Final
    public static Item DROPPER;
    @Mutable
    @Shadow
    @Final
    public static Item LECTERN;
    @Mutable
    @Shadow
    @Final
    public static Item TARGET;
    @Mutable
    @Shadow
    @Final
    public static Item LEVER;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHTNING_ROD;
    @Mutable
    @Shadow
    @Final
    public static Item DAYLIGHT_DETECTOR;
    @Mutable
    @Shadow
    @Final
    public static Item SCULK_SENSOR;
    @Mutable
    @Shadow
    @Final
    public static Item CALIBRATED_SCULK_SENSOR;
    @Mutable
    @Shadow
    @Final
    public static Item TRIPWIRE_HOOK;
    @Mutable
    @Shadow
    @Final
    public static Item TRAPPED_CHEST;
    @Mutable
    @Shadow
    @Final
    public static Item TNT;
    @Mutable
    @Shadow
    @Final
    public static Item REDSTONE_LAMP;
    @Mutable
    @Shadow
    @Final
    public static Item NOTE_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_BUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_WEIGHTED_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item HEAVY_WEIGHTED_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_PRESSURE_PLATE;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_DOOR;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_TRAPDOOR;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_FENCE_GATE;
    @Mutable
    @Shadow
    @Final
    public static Item POWERED_RAIL;
    @Mutable
    @Shadow
    @Final
    public static Item DETECTOR_RAIL;
    @Mutable
    @Shadow
    @Final
    public static Item RAIL;
    @Mutable
    @Shadow
    @Final
    public static Item ACTIVATOR_RAIL;
    @Mutable
    @Shadow
    @Final
    public static Item SADDLE;
    @Mutable
    @Shadow
    @Final
    public static Item MINECART;
    @Mutable
    @Shadow
    @Final
    public static Item CHEST_MINECART;
    @Mutable
    @Shadow
    @Final
    public static Item FURNACE_MINECART;
    @Mutable
    @Shadow
    @Final
    public static Item TNT_MINECART;
    @Mutable
    @Shadow
    @Final
    public static Item HOPPER_MINECART;
    @Mutable
    @Shadow
    @Final
    public static Item CARROT_ON_A_STICK;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_FUNGUS_ON_A_STICK;
    @Mutable
    @Shadow
    @Final
    public static Item ELYTRA;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_CHEST_BOAT;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_RAFT;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_CHEST_RAFT;
    @Mutable
    @Shadow
    @Final
    public static Item STRUCTURE_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item JIGSAW;
    @Mutable
    @Shadow
    @Final
    public static Item TURTLE_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item SCUTE;
    @Mutable
    @Shadow
    @Final
    public static Item FLINT_AND_STEEL;
    @Mutable
    @Shadow
    @Final
    public static Item APPLE;
    @Mutable
    @Shadow
    @Final
    public static Item BOW;
    @Mutable
    @Shadow
    @Final
    public static Item ARROW;
    @Mutable
    @Shadow
    @Final
    public static Item COAL;
    @Mutable
    @Shadow
    @Final
    public static Item CHARCOAL;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND;
    @Mutable
    @Shadow
    @Final
    public static Item EMERALD;
    @Mutable
    @Shadow
    @Final
    public static Item LAPIS_LAZULI;
    @Mutable
    @Shadow
    @Final
    public static Item QUARTZ;
    @Mutable
    @Shadow
    @Final
    public static Item AMETHYST_SHARD;
    @Mutable
    @Shadow
    @Final
    public static Item RAW_IRON;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_INGOT;
    @Mutable
    @Shadow
    @Final
    public static Item RAW_COPPER;
    @Mutable
    @Shadow
    @Final
    public static Item COPPER_INGOT;
    @Mutable
    @Shadow
    @Final
    public static Item RAW_GOLD;
    @Mutable
    @Shadow
    @Final
    public static Item GOLD_INGOT;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_INGOT;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_SCRAP;
    @Mutable
    @Shadow
    @Final
    public static Item WOODEN_SWORD;
    @Mutable
    @Shadow
    @Final
    public static Item WOODEN_SHOVEL;
    @Mutable
    @Shadow
    @Final
    public static Item WOODEN_PICKAXE;
    @Mutable
    @Shadow
    @Final
    public static Item WOODEN_AXE;
    @Mutable
    @Shadow
    @Final
    public static Item WOODEN_HOE;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_SWORD;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_SHOVEL;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_PICKAXE;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_AXE;
    @Mutable
    @Shadow
    @Final
    public static Item STONE_HOE;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_SWORD;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_SHOVEL;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_PICKAXE;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_AXE;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_HOE;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_SWORD;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_SHOVEL;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_PICKAXE;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_AXE;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_HOE;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_SWORD;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_SHOVEL;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_PICKAXE;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_AXE;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_HOE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_SWORD;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_SHOVEL;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_PICKAXE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_AXE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_HOE;
    @Mutable
    @Shadow
    @Final
    public static Item STICK;
    @Mutable
    @Shadow
    @Final
    public static Item BOWL;
    @Mutable
    @Shadow
    @Final
    public static Item MUSHROOM_STEW;
    @Mutable
    @Shadow
    @Final
    public static Item STRING;
    @Mutable
    @Shadow
    @Final
    public static Item FEATHER;
    @Mutable
    @Shadow
    @Final
    public static Item GUNPOWDER;
    @Mutable
    @Shadow
    @Final
    public static Item WHEAT_SEEDS;
    @Mutable
    @Shadow
    @Final
    public static Item WHEAT;
    @Mutable
    @Shadow
    @Final
    public static Item BREAD;
    @Mutable
    @Shadow
    @Final
    public static Item LEATHER_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item LEATHER_CHESTPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item LEATHER_LEGGINGS;
    @Mutable
    @Shadow
    @Final
    public static Item LEATHER_BOOTS;
    @Mutable
    @Shadow
    @Final
    public static Item CHAINMAIL_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item CHAINMAIL_CHESTPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item CHAINMAIL_LEGGINGS;
    @Mutable
    @Shadow
    @Final
    public static Item CHAINMAIL_BOOTS;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_CHESTPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_LEGGINGS;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_BOOTS;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_CHESTPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_LEGGINGS;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_BOOTS;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_CHESTPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_LEGGINGS;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_BOOTS;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_HELMET;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_CHESTPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_LEGGINGS;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_BOOTS;
    @Mutable
    @Shadow
    @Final
    public static Item FLINT;
    @Mutable
    @Shadow
    @Final
    public static Item PORKCHOP;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_PORKCHOP;
    @Mutable
    @Shadow
    @Final
    public static Item PAINTING;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_APPLE;
    @Mutable
    @Shadow
    @Final
    public static Item ENCHANTED_GOLDEN_APPLE;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item OAK_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item SPRUCE_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item BIRCH_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item JUNGLE_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item ACACIA_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item CHERRY_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item DARK_OAK_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item MANGROVE_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item BAMBOO_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item CRIMSON_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item WARPED_HANGING_SIGN;
    @Mutable
    @Shadow
    @Final
    public static Item BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item WATER_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item LAVA_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item POWDER_SNOW_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item SNOWBALL;
    @Mutable
    @Shadow
    @Final
    public static Item LEATHER;
    @Mutable
    @Shadow
    @Final
    public static Item MILK_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item PUFFERFISH_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item SALMON_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item COD_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item TROPICAL_FISH_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item AXOLOTL_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item TADPOLE_BUCKET;
    @Mutable
    @Shadow
    @Final
    public static Item BRICK;
    @Mutable
    @Shadow
    @Final
    public static Item CLAY_BALL;
    @Mutable
    @Shadow
    @Final
    public static Item DRIED_KELP_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item PAPER;
    @Mutable
    @Shadow
    @Final
    public static Item BOOK;
    @Mutable
    @Shadow
    @Final
    public static Item SLIME_BALL;
    @Mutable
    @Shadow
    @Final
    public static Item EGG;
    @Mutable
    @Shadow
    @Final
    public static Item COMPASS;
    @Mutable
    @Shadow
    @Final
    public static Item RECOVERY_COMPASS;
    @Mutable
    @Shadow
    @Final
    public static Item BUNDLE;
    @Mutable
    @Shadow
    @Final
    public static Item FISHING_ROD;
    @Mutable
    @Shadow
    @Final
    public static Item CLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item SPYGLASS;
    @Mutable
    @Shadow
    @Final
    public static Item GLOWSTONE_DUST;
    @Mutable
    @Shadow
    @Final
    public static Item COD;
    @Mutable
    @Shadow
    @Final
    public static Item SALMON;
    @Mutable
    @Shadow
    @Final
    public static Item TROPICAL_FISH;
    @Mutable
    @Shadow
    @Final
    public static Item PUFFERFISH;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_COD;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_SALMON;
    @Mutable
    @Shadow
    @Final
    public static Item INK_SAC;
    @Mutable
    @Shadow
    @Final
    public static Item GLOW_INK_SAC;
    @Mutable
    @Shadow
    @Final
    public static Item COCOA_BEANS;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item RED_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_DYE;
    @Mutable
    @Shadow
    @Final
    public static Item BONE_MEAL;
    @Mutable
    @Shadow
    @Final
    public static Item BONE;
    @Mutable
    @Shadow
    @Final
    public static Item SUGAR;
    @Mutable
    @Shadow
    @Final
    public static Item CAKE;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_BED;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_BED;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_BED;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_BED;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_BED;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_BED;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_BED;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_BED;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_BED;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_BED;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_BED;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_BED;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_BED;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_BED;
    @Mutable
    @Shadow
    @Final
    public static Item RED_BED;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_BED;
    @Mutable
    @Shadow
    @Final
    public static Item COOKIE;
    @Mutable
    @Shadow
    @Final
    public static Item FILLED_MAP;
    @Mutable
    @Shadow
    @Final
    public static Item SHEARS;
    @Mutable
    @Shadow
    @Final
    public static Item MELON_SLICE;
    @Mutable
    @Shadow
    @Final
    public static Item DRIED_KELP;
    @Mutable
    @Shadow
    @Final
    public static Item PUMPKIN_SEEDS;
    @Mutable
    @Shadow
    @Final
    public static Item MELON_SEEDS;
    @Mutable
    @Shadow
    @Final
    public static Item BEEF;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_BEEF;
    @Mutable
    @Shadow
    @Final
    public static Item CHICKEN;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_CHICKEN;
    @Mutable
    @Shadow
    @Final
    public static Item ROTTEN_FLESH;
    @Mutable
    @Shadow
    @Final
    public static Item ENDER_PEARL;
    @Mutable
    @Shadow
    @Final
    public static Item BLAZE_ROD;
    @Mutable
    @Shadow
    @Final
    public static Item GHAST_TEAR;
    @Mutable
    @Shadow
    @Final
    public static Item GOLD_NUGGET;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_WART;
    @Mutable
    @Shadow
    @Final
    public static Item POTION;
    @Mutable
    @Shadow
    @Final
    public static Item GLASS_BOTTLE;
    @Mutable
    @Shadow
    @Final
    public static Item SPIDER_EYE;
    @Mutable
    @Shadow
    @Final
    public static Item FERMENTED_SPIDER_EYE;
    @Mutable
    @Shadow
    @Final
    public static Item BLAZE_POWDER;
    @Mutable
    @Shadow
    @Final
    public static Item MAGMA_CREAM;
    @Mutable
    @Shadow
    @Final
    public static Item BREWING_STAND;
    @Mutable
    @Shadow
    @Final
    public static Item CAULDRON;
    @Mutable
    @Shadow
    @Final
    public static Item ENDER_EYE;
    @Mutable
    @Shadow
    @Final
    public static Item GLISTERING_MELON_SLICE;
    @Mutable
    @Shadow
    @Final
    public static Item ALLAY_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item AXOLOTL_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item BAT_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item BEE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item BLAZE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item CAT_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item CAMEL_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item CAVE_SPIDER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item CHICKEN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item COD_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item COW_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item CREEPER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item DOLPHIN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item DONKEY_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item DROWNED_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ELDER_GUARDIAN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ENDER_DRAGON_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ENDERMAN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ENDERMITE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item EVOKER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item FOX_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item FROG_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item GHAST_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item GLOW_SQUID_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item GOAT_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item GUARDIAN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item HOGLIN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item HORSE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item HUSK_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_GOLEM_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item LLAMA_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item MAGMA_CUBE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item MOOSHROOM_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item MULE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item OCELOT_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PANDA_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PARROT_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PHANTOM_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PIG_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PIGLIN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PIGLIN_BRUTE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PILLAGER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item POLAR_BEAR_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item PUFFERFISH_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item RABBIT_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item RAVAGER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SALMON_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SHEEP_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SHULKER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SILVERFISH_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SKELETON_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SKELETON_HORSE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SLIME_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SNIFFER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SNOW_GOLEM_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SPIDER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item SQUID_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item STRAY_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item STRIDER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item TADPOLE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item TRADER_LLAMA_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item TROPICAL_FISH_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item TURTLE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item VEX_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item VILLAGER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item VINDICATOR_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item WANDERING_TRADER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item WARDEN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item WITCH_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item WITHER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item WITHER_SKELETON_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item WOLF_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ZOGLIN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ZOMBIE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ZOMBIE_HORSE_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ZOMBIE_VILLAGER_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item ZOMBIFIED_PIGLIN_SPAWN_EGG;
    @Mutable
    @Shadow
    @Final
    public static Item EXPERIENCE_BOTTLE;
    @Mutable
    @Shadow
    @Final
    public static Item FIRE_CHARGE;
    @Mutable
    @Shadow
    @Final
    public static Item WRITABLE_BOOK;
    @Mutable
    @Shadow
    @Final
    public static Item WRITTEN_BOOK;
    @Mutable
    @Shadow
    @Final
    public static Item ITEM_FRAME;
    @Mutable
    @Shadow
    @Final
    public static Item GLOW_ITEM_FRAME;
    @Mutable
    @Shadow
    @Final
    public static Item FLOWER_POT;
    @Mutable
    @Shadow
    @Final
    public static Item CARROT;
    @Mutable
    @Shadow
    @Final
    public static Item POTATO;
    @Mutable
    @Shadow
    @Final
    public static Item BAKED_POTATO;
    @Mutable
    @Shadow
    @Final
    public static Item POISONOUS_POTATO;
    @Mutable
    @Shadow
    @Final
    public static Item MAP;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_CARROT;
    @Mutable
    @Shadow
    @Final
    public static Item SKELETON_SKULL;
    @Mutable
    @Shadow
    @Final
    public static Item WITHER_SKELETON_SKULL;
    @Mutable
    @Shadow
    @Final
    public static Item PLAYER_HEAD;
    @Mutable
    @Shadow
    @Final
    public static Item ZOMBIE_HEAD;
    @Mutable
    @Shadow
    @Final
    public static Item CREEPER_HEAD;
    @Mutable
    @Shadow
    @Final
    public static Item DRAGON_HEAD;
    @Mutable
    @Shadow
    @Final
    public static Item PIGLIN_HEAD;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_STAR;
    @Mutable
    @Shadow
    @Final
    public static Item PUMPKIN_PIE;
    @Mutable
    @Shadow
    @Final
    public static Item FIREWORK_ROCKET;
    @Mutable
    @Shadow
    @Final
    public static Item FIREWORK_STAR;
    @Mutable
    @Shadow
    @Final
    public static Item ENCHANTED_BOOK;
    @Mutable
    @Shadow
    @Final
    public static Item NETHER_BRICK;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_SHARD;
    @Mutable
    @Shadow
    @Final
    public static Item PRISMARINE_CRYSTALS;
    @Mutable
    @Shadow
    @Final
    public static Item RABBIT;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_RABBIT;
    @Mutable
    @Shadow
    @Final
    public static Item RABBIT_STEW;
    @Mutable
    @Shadow
    @Final
    public static Item RABBIT_FOOT;
    @Mutable
    @Shadow
    @Final
    public static Item RABBIT_HIDE;
    @Mutable
    @Shadow
    @Final
    public static Item ARMOR_STAND;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_HORSE_ARMOR;
    @Mutable
    @Shadow
    @Final
    public static Item GOLDEN_HORSE_ARMOR;
    @Mutable
    @Shadow
    @Final
    public static Item DIAMOND_HORSE_ARMOR;
    @Mutable
    @Shadow
    @Final
    public static Item LEATHER_HORSE_ARMOR;
    @Mutable
    @Shadow
    @Final
    public static Item LEAD;
    @Mutable
    @Shadow
    @Final
    public static Item NAME_TAG;
    @Mutable
    @Shadow
    @Final
    public static Item COMMAND_BLOCK_MINECART;
    @Mutable
    @Shadow
    @Final
    public static Item MUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item COOKED_MUTTON;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item RED_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_BANNER;
    @Mutable
    @Shadow
    @Final
    public static Item END_CRYSTAL;
    @Mutable
    @Shadow
    @Final
    public static Item CHORUS_FRUIT;
    @Mutable
    @Shadow
    @Final
    public static Item POPPED_CHORUS_FRUIT;
    @Mutable
    @Shadow
    @Final
    public static Item TORCHFLOWER_SEEDS;
    @Mutable
    @Shadow
    @Final
    public static Item PITCHER_POD;
    @Mutable
    @Shadow
    @Final
    public static Item BEETROOT;
    @Mutable
    @Shadow
    @Final
    public static Item BEETROOT_SEEDS;
    @Mutable
    @Shadow
    @Final
    public static Item BEETROOT_SOUP;
    @Mutable
    @Shadow
    @Final
    public static Item DRAGON_BREATH;
    @Mutable
    @Shadow
    @Final
    public static Item SPLASH_POTION;
    @Mutable
    @Shadow
    @Final
    public static Item SPECTRAL_ARROW;
    @Mutable
    @Shadow
    @Final
    public static Item TIPPED_ARROW;
    @Mutable
    @Shadow
    @Final
    public static Item LINGERING_POTION;
    @Mutable
    @Shadow
    @Final
    public static Item SHIELD;
    @Mutable
    @Shadow
    @Final
    public static Item TOTEM_OF_UNDYING;
    @Mutable
    @Shadow
    @Final
    public static Item SHULKER_SHELL;
    @Mutable
    @Shadow
    @Final
    public static Item IRON_NUGGET;
    @Mutable
    @Shadow
    @Final
    public static Item KNOWLEDGE_BOOK;
    @Mutable
    @Shadow
    @Final
    public static Item DEBUG_STICK;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_13;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_CAT;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_BLOCKS;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_CHIRP;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_FAR;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_MALL;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_MELLOHI;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_STAL;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_STRAD;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_WARD;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_11;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_WAIT;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_OTHERSIDE;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_RELIC;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_5;
    @Mutable
    @Shadow
    @Final
    public static Item MUSIC_DISC_PIGSTEP;
    @Mutable
    @Shadow
    @Final
    public static Item DISC_FRAGMENT_5;
    @Mutable
    @Shadow
    @Final
    public static Item TRIDENT;
    @Mutable
    @Shadow
    @Final
    public static Item PHANTOM_MEMBRANE;
    @Mutable
    @Shadow
    @Final
    public static Item NAUTILUS_SHELL;
    @Mutable
    @Shadow
    @Final
    public static Item HEART_OF_THE_SEA;
    @Mutable
    @Shadow
    @Final
    public static Item CROSSBOW;
    @Mutable
    @Shadow
    @Final
    public static Item SUSPICIOUS_STEW;
    @Mutable
    @Shadow
    @Final
    public static Item LOOM;
    @Mutable
    @Shadow
    @Final
    public static Item FLOWER_BANNER_PATTERN;
    @Mutable
    @Shadow
    @Final
    public static Item CREEPER_BANNER_PATTERN;
    @Mutable
    @Shadow
    @Final
    public static Item SKULL_BANNER_PATTERN;
    @Mutable
    @Shadow
    @Final
    public static Item MOJANG_BANNER_PATTERN;
    @Mutable
    @Shadow
    @Final
    public static Item GLOBE_BANNER_PATTERN;
    @Mutable
    @Shadow
    @Final
    public static Item PIGLIN_BANNER_PATTERN;
    @Mutable
    @Shadow
    @Final
    public static Item GOAT_HORN;
    @Mutable
    @Shadow
    @Final
    public static Item COMPOSTER;
    @Mutable
    @Shadow
    @Final
    public static Item BARREL;
    @Mutable
    @Shadow
    @Final
    public static Item SMOKER;
    @Mutable
    @Shadow
    @Final
    public static Item BLAST_FURNACE;
    @Mutable
    @Shadow
    @Final
    public static Item CARTOGRAPHY_TABLE;
    @Mutable
    @Shadow
    @Final
    public static Item FLETCHING_TABLE;
    @Mutable
    @Shadow
    @Final
    public static Item GRINDSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item SMITHING_TABLE;
    @Mutable
    @Shadow
    @Final
    public static Item STONECUTTER;
    @Mutable
    @Shadow
    @Final
    public static Item BELL;
    @Mutable
    @Shadow
    @Final
    public static Item LANTERN;
    @Mutable
    @Shadow
    @Final
    public static Item SOUL_LANTERN;
    @Mutable
    @Shadow
    @Final
    public static Item SWEET_BERRIES;
    @Mutable
    @Shadow
    @Final
    public static Item GLOW_BERRIES;
    @Mutable
    @Shadow
    @Final
    public static Item CAMPFIRE;
    @Mutable
    @Shadow
    @Final
    public static Item SOUL_CAMPFIRE;
    @Mutable
    @Shadow
    @Final
    public static Item SHROOMLIGHT;
    @Mutable
    @Shadow
    @Final
    public static Item HONEYCOMB;
    @Mutable
    @Shadow
    @Final
    public static Item BEE_NEST;
    @Mutable
    @Shadow
    @Final
    public static Item BEEHIVE;
    @Mutable
    @Shadow
    @Final
    public static Item HONEY_BOTTLE;
    @Mutable
    @Shadow
    @Final
    public static Item HONEYCOMB_BLOCK;
    @Mutable
    @Shadow
    @Final
    public static Item LODESTONE;
    @Mutable
    @Shadow
    @Final
    public static Item CRYING_OBSIDIAN;
    @Mutable
    @Shadow
    @Final
    public static Item BLACKSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item BLACKSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item BLACKSTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item GILDED_BLACKSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item CHISELED_POLISHED_BLACKSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_BRICK_SLAB;
    @Mutable
    @Shadow
    @Final
    public static Item POLISHED_BLACKSTONE_BRICK_STAIRS;
    @Mutable
    @Shadow
    @Final
    public static Item CRACKED_POLISHED_BLACKSTONE_BRICKS;
    @Mutable
    @Shadow
    @Final
    public static Item RESPAWN_ANCHOR;
    @Mutable
    @Shadow
    @Final
    public static Item CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item WHITE_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item ORANGE_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item MAGENTA_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_BLUE_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item YELLOW_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item LIME_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item PINK_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item GRAY_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item LIGHT_GRAY_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item CYAN_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item PURPLE_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item BLUE_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item BROWN_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item GREEN_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item RED_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item BLACK_CANDLE;
    @Mutable
    @Shadow
    @Final
    public static Item SMALL_AMETHYST_BUD;
    @Mutable
    @Shadow
    @Final
    public static Item MEDIUM_AMETHYST_BUD;
    @Mutable
    @Shadow
    @Final
    public static Item LARGE_AMETHYST_BUD;
    @Mutable
    @Shadow
    @Final
    public static Item AMETHYST_CLUSTER;
    @Mutable
    @Shadow
    @Final
    public static Item POINTED_DRIPSTONE;
    @Mutable
    @Shadow
    @Final
    public static Item OCHRE_FROGLIGHT;
    @Mutable
    @Shadow
    @Final
    public static Item VERDANT_FROGLIGHT;
    @Mutable
    @Shadow
    @Final
    public static Item PEARLESCENT_FROGLIGHT;
    @Mutable
    @Shadow
    @Final
    public static Item FROGSPAWN;
    @Mutable
    @Shadow
    @Final
    public static Item ECHO_SHARD;
    @Mutable
    @Shadow
    @Final
    public static Item BRUSH;
    @Mutable
    @Shadow
    @Final
    public static Item NETHERITE_UPGRADE_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item DUNE_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item COAST_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item WILD_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item WARD_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item EYE_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item VEX_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item TIDE_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item RIB_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item RAISER_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item HOST_ARMOR_TRIM_SMITHING_TEMPLATE;
    @Mutable
    @Shadow
    @Final
    public static Item ANGLER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item ARCHER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item ARMS_UP_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item BLADE_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item BREWER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item BURN_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item DANGER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item EXPLORER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item FRIEND_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item HEART_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item HEARTBREAK_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item HOWL_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item MINER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item MOURNER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item PLENTY_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item PRIZE_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item SHEAF_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item SHELTER_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item SKULL_POTTERY_SHERD;
    @Mutable
    @Shadow
    @Final
    public static Item SNORT_POTTERY_SHERD;

    @Shadow
    public static Item register(Block block) {
        return null;
    }

    @Shadow
    public static Item register(BlockItem item) {
        return null;
    }

    @Shadow
    public static Item register(Block block, Block... blocks) {
        return null;
    }

    @Shadow
    public static Item register(Block block, Item item) {
        return null;
    }

    @Shadow
    public static Item register(String id, Item item) {
        return null;
    }

    @Inject(method = "<clinit>", at = @At("HEAD"), cancellable = true)
    private static void clinit(CallbackInfo ci) {
        vanillaItems();
        trtrItems();
        ci.cancel();
    }

    @Unique
    private static void trtrItems() {
        FLINT = register("flint",
                         new FlintItem(new Item.Settings())
        );
        STICK = register("stick",
                         new StickItem(new Item.Settings())
        );
    }

    @Unique
    private static void vanillaItems() {
        AIR = register(Blocks.AIR,
                       new AirBlockItem(Blocks.AIR,
                                        new Item.Settings()
                       )
        );
        STONE = register(Blocks.STONE);
        GRANITE = register(Blocks.GRANITE);
        POLISHED_GRANITE = register(Blocks.POLISHED_GRANITE);
        DIORITE = register(Blocks.DIORITE);
        POLISHED_DIORITE = register(Blocks.POLISHED_DIORITE);
        ANDESITE = register(Blocks.ANDESITE);
        POLISHED_ANDESITE = register(Blocks.POLISHED_ANDESITE);
        DEEPSLATE = register(Blocks.DEEPSLATE);
        COBBLED_DEEPSLATE = register(Blocks.COBBLED_DEEPSLATE);
        POLISHED_DEEPSLATE = register(Blocks.POLISHED_DEEPSLATE);
        CALCITE = register(Blocks.CALCITE);
        TUFF = register(Blocks.TUFF);
        DRIPSTONE_BLOCK = register(Blocks.DRIPSTONE_BLOCK);
        GRASS_BLOCK = register(Blocks.GRASS_BLOCK);
        DIRT = register(Blocks.DIRT);
        COARSE_DIRT = register(Blocks.COARSE_DIRT);
        PODZOL = register(Blocks.PODZOL);
        ROOTED_DIRT = register(Blocks.ROOTED_DIRT);
        MUD = register(Blocks.MUD);
        CRIMSON_NYLIUM = register(Blocks.CRIMSON_NYLIUM);
        WARPED_NYLIUM = register(Blocks.WARPED_NYLIUM);
        COBBLESTONE = register(Blocks.COBBLESTONE);
        OAK_PLANKS = register(Blocks.OAK_PLANKS);
        SPRUCE_PLANKS = register(Blocks.SPRUCE_PLANKS);
        BIRCH_PLANKS = register(Blocks.BIRCH_PLANKS);
        JUNGLE_PLANKS = register(Blocks.JUNGLE_PLANKS);
        ACACIA_PLANKS = register(Blocks.ACACIA_PLANKS);
        CHERRY_PLANKS = register(Blocks.CHERRY_PLANKS);
        DARK_OAK_PLANKS = register(Blocks.DARK_OAK_PLANKS);
        MANGROVE_PLANKS = register(Blocks.MANGROVE_PLANKS);
        BAMBOO_PLANKS = register(Blocks.BAMBOO_PLANKS);
        CRIMSON_PLANKS = register(Blocks.CRIMSON_PLANKS);
        WARPED_PLANKS = register(Blocks.WARPED_PLANKS);
        BAMBOO_MOSAIC = register(Blocks.BAMBOO_MOSAIC);
        OAK_SAPLING = register(Blocks.OAK_SAPLING);
        SPRUCE_SAPLING = register(Blocks.SPRUCE_SAPLING);
        BIRCH_SAPLING = register(Blocks.BIRCH_SAPLING);
        JUNGLE_SAPLING = register(Blocks.JUNGLE_SAPLING);
        ACACIA_SAPLING = register(Blocks.ACACIA_SAPLING);
        CHERRY_SAPLING = register(Blocks.CHERRY_SAPLING);
        DARK_OAK_SAPLING = register(Blocks.DARK_OAK_SAPLING);
        MANGROVE_PROPAGULE = register(Blocks.MANGROVE_PROPAGULE);
        BEDROCK = register(Blocks.BEDROCK);
        SAND = register(Blocks.SAND);
        SUSPICIOUS_SAND = register(new BlockItem(Blocks.SUSPICIOUS_SAND,
                                                 new Item.Settings()
        ));
        SUSPICIOUS_GRAVEL = register(new BlockItem(Blocks.SUSPICIOUS_GRAVEL,
                                                   new Item.Settings()
        ));
        RED_SAND = register(Blocks.RED_SAND);
        GRAVEL = register(Blocks.GRAVEL);
        COAL_ORE = register(Blocks.COAL_ORE);
        DEEPSLATE_COAL_ORE = register(Blocks.DEEPSLATE_COAL_ORE);
        IRON_ORE = register(Blocks.IRON_ORE);
        DEEPSLATE_IRON_ORE = register(Blocks.DEEPSLATE_IRON_ORE);
        COPPER_ORE = register(Blocks.COPPER_ORE);
        DEEPSLATE_COPPER_ORE = register(Blocks.DEEPSLATE_COPPER_ORE);
        GOLD_ORE = register(Blocks.GOLD_ORE);
        DEEPSLATE_GOLD_ORE = register(Blocks.DEEPSLATE_GOLD_ORE);
        REDSTONE_ORE = register(Blocks.REDSTONE_ORE);
        DEEPSLATE_REDSTONE_ORE = register(Blocks.DEEPSLATE_REDSTONE_ORE);
        EMERALD_ORE = register(Blocks.EMERALD_ORE);
        DEEPSLATE_EMERALD_ORE = register(Blocks.DEEPSLATE_EMERALD_ORE);
        LAPIS_ORE = register(Blocks.LAPIS_ORE);
        DEEPSLATE_LAPIS_ORE = register(Blocks.DEEPSLATE_LAPIS_ORE);
        DIAMOND_ORE = register(Blocks.DIAMOND_ORE);
        DEEPSLATE_DIAMOND_ORE = register(Blocks.DEEPSLATE_DIAMOND_ORE);
        NETHER_GOLD_ORE = register(Blocks.NETHER_GOLD_ORE);
        NETHER_QUARTZ_ORE = register(Blocks.NETHER_QUARTZ_ORE);
        ANCIENT_DEBRIS = register(new BlockItem(Blocks.ANCIENT_DEBRIS,
                                                (new Item.Settings()).fireproof()
        ));
        COAL_BLOCK = register(Blocks.COAL_BLOCK);
        RAW_IRON_BLOCK = register(Blocks.RAW_IRON_BLOCK);
        RAW_COPPER_BLOCK = register(Blocks.RAW_COPPER_BLOCK);
        RAW_GOLD_BLOCK = register(Blocks.RAW_GOLD_BLOCK);
        AMETHYST_BLOCK = register(Blocks.AMETHYST_BLOCK);
        BUDDING_AMETHYST = register(Blocks.BUDDING_AMETHYST);
        IRON_BLOCK = register(Blocks.IRON_BLOCK);
        COPPER_BLOCK = register(Blocks.COPPER_BLOCK);
        GOLD_BLOCK = register(Blocks.GOLD_BLOCK);
        DIAMOND_BLOCK = register(Blocks.DIAMOND_BLOCK);
        NETHERITE_BLOCK = register(new BlockItem(Blocks.NETHERITE_BLOCK,
                                                 (new Item.Settings()).fireproof()
        ));
        EXPOSED_COPPER = register(Blocks.EXPOSED_COPPER);
        WEATHERED_COPPER = register(Blocks.WEATHERED_COPPER);
        OXIDIZED_COPPER = register(Blocks.OXIDIZED_COPPER);
        CUT_COPPER = register(Blocks.CUT_COPPER);
        EXPOSED_CUT_COPPER = register(Blocks.EXPOSED_CUT_COPPER);
        WEATHERED_CUT_COPPER = register(Blocks.WEATHERED_CUT_COPPER);
        OXIDIZED_CUT_COPPER = register(Blocks.OXIDIZED_CUT_COPPER);
        CUT_COPPER_STAIRS = register(Blocks.CUT_COPPER_STAIRS);
        EXPOSED_CUT_COPPER_STAIRS = register(Blocks.EXPOSED_CUT_COPPER_STAIRS);
        WEATHERED_CUT_COPPER_STAIRS = register(Blocks.WEATHERED_CUT_COPPER_STAIRS);
        OXIDIZED_CUT_COPPER_STAIRS = register(Blocks.OXIDIZED_CUT_COPPER_STAIRS);
        CUT_COPPER_SLAB = register(Blocks.CUT_COPPER_SLAB);
        EXPOSED_CUT_COPPER_SLAB = register(Blocks.EXPOSED_CUT_COPPER_SLAB);
        WEATHERED_CUT_COPPER_SLAB = register(Blocks.WEATHERED_CUT_COPPER_SLAB);
        OXIDIZED_CUT_COPPER_SLAB = register(Blocks.OXIDIZED_CUT_COPPER_SLAB);
        WAXED_COPPER_BLOCK = register(Blocks.WAXED_COPPER_BLOCK);
        WAXED_EXPOSED_COPPER = register(Blocks.WAXED_EXPOSED_COPPER);
        WAXED_WEATHERED_COPPER = register(Blocks.WAXED_WEATHERED_COPPER);
        WAXED_OXIDIZED_COPPER = register(Blocks.WAXED_OXIDIZED_COPPER);
        WAXED_CUT_COPPER = register(Blocks.WAXED_CUT_COPPER);
        WAXED_EXPOSED_CUT_COPPER = register(Blocks.WAXED_EXPOSED_CUT_COPPER);
        WAXED_WEATHERED_CUT_COPPER = register(Blocks.WAXED_WEATHERED_CUT_COPPER);
        WAXED_OXIDIZED_CUT_COPPER = register(Blocks.WAXED_OXIDIZED_CUT_COPPER);
        WAXED_CUT_COPPER_STAIRS = register(Blocks.WAXED_CUT_COPPER_STAIRS);
        WAXED_EXPOSED_CUT_COPPER_STAIRS = register(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS);
        WAXED_WEATHERED_CUT_COPPER_STAIRS = register(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS);
        WAXED_OXIDIZED_CUT_COPPER_STAIRS = register(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        WAXED_CUT_COPPER_SLAB = register(Blocks.WAXED_CUT_COPPER_SLAB);
        WAXED_EXPOSED_CUT_COPPER_SLAB = register(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB);
        WAXED_WEATHERED_CUT_COPPER_SLAB = register(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB);
        WAXED_OXIDIZED_CUT_COPPER_SLAB = register(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB);
        OAK_LOG = register(Blocks.OAK_LOG);
        SPRUCE_LOG = register(Blocks.SPRUCE_LOG);
        BIRCH_LOG = register(Blocks.BIRCH_LOG);
        JUNGLE_LOG = register(Blocks.JUNGLE_LOG);
        ACACIA_LOG = register(Blocks.ACACIA_LOG);
        CHERRY_LOG = register(Blocks.CHERRY_LOG);
        DARK_OAK_LOG = register(Blocks.DARK_OAK_LOG);
        MANGROVE_LOG = register(Blocks.MANGROVE_LOG);
        MANGROVE_ROOTS = register(Blocks.MANGROVE_ROOTS);
        MUDDY_MANGROVE_ROOTS = register(Blocks.MUDDY_MANGROVE_ROOTS);
        CRIMSON_STEM = register(Blocks.CRIMSON_STEM);
        WARPED_STEM = register(Blocks.WARPED_STEM);
        BAMBOO_BLOCK = register(Blocks.BAMBOO_BLOCK);
        STRIPPED_OAK_LOG = register(Blocks.STRIPPED_OAK_LOG);
        STRIPPED_SPRUCE_LOG = register(Blocks.STRIPPED_SPRUCE_LOG);
        STRIPPED_BIRCH_LOG = register(Blocks.STRIPPED_BIRCH_LOG);
        STRIPPED_JUNGLE_LOG = register(Blocks.STRIPPED_JUNGLE_LOG);
        STRIPPED_ACACIA_LOG = register(Blocks.STRIPPED_ACACIA_LOG);
        STRIPPED_CHERRY_LOG = register(Blocks.STRIPPED_CHERRY_LOG);
        STRIPPED_DARK_OAK_LOG = register(Blocks.STRIPPED_DARK_OAK_LOG);
        STRIPPED_MANGROVE_LOG = register(Blocks.STRIPPED_MANGROVE_LOG);
        STRIPPED_CRIMSON_STEM = register(Blocks.STRIPPED_CRIMSON_STEM);
        STRIPPED_WARPED_STEM = register(Blocks.STRIPPED_WARPED_STEM);
        STRIPPED_OAK_WOOD = register(Blocks.STRIPPED_OAK_WOOD);
        STRIPPED_SPRUCE_WOOD = register(Blocks.STRIPPED_SPRUCE_WOOD);
        STRIPPED_BIRCH_WOOD = register(Blocks.STRIPPED_BIRCH_WOOD);
        STRIPPED_JUNGLE_WOOD = register(Blocks.STRIPPED_JUNGLE_WOOD);
        STRIPPED_ACACIA_WOOD = register(Blocks.STRIPPED_ACACIA_WOOD);
        STRIPPED_CHERRY_WOOD = register(Blocks.STRIPPED_CHERRY_WOOD);
        STRIPPED_DARK_OAK_WOOD = register(Blocks.STRIPPED_DARK_OAK_WOOD);
        STRIPPED_MANGROVE_WOOD = register(Blocks.STRIPPED_MANGROVE_WOOD);
        STRIPPED_CRIMSON_HYPHAE = register(Blocks.STRIPPED_CRIMSON_HYPHAE);
        STRIPPED_WARPED_HYPHAE = register(Blocks.STRIPPED_WARPED_HYPHAE);
        STRIPPED_BAMBOO_BLOCK = register(Blocks.STRIPPED_BAMBOO_BLOCK);
        OAK_WOOD = register(Blocks.OAK_WOOD);
        SPRUCE_WOOD = register(Blocks.SPRUCE_WOOD);
        BIRCH_WOOD = register(Blocks.BIRCH_WOOD);
        JUNGLE_WOOD = register(Blocks.JUNGLE_WOOD);
        ACACIA_WOOD = register(Blocks.ACACIA_WOOD);
        CHERRY_WOOD = register(Blocks.CHERRY_WOOD);
        DARK_OAK_WOOD = register(Blocks.DARK_OAK_WOOD);
        MANGROVE_WOOD = register(Blocks.MANGROVE_WOOD);
        CRIMSON_HYPHAE = register(Blocks.CRIMSON_HYPHAE);
        WARPED_HYPHAE = register(Blocks.WARPED_HYPHAE);
        OAK_LEAVES = register(Blocks.OAK_LEAVES);
        SPRUCE_LEAVES = register(Blocks.SPRUCE_LEAVES);
        BIRCH_LEAVES = register(Blocks.BIRCH_LEAVES);
        JUNGLE_LEAVES = register(Blocks.JUNGLE_LEAVES);
        ACACIA_LEAVES = register(Blocks.ACACIA_LEAVES);
        CHERRY_LEAVES = register(Blocks.CHERRY_LEAVES);
        DARK_OAK_LEAVES = register(Blocks.DARK_OAK_LEAVES);
        MANGROVE_LEAVES = register(Blocks.MANGROVE_LEAVES);
        AZALEA_LEAVES = register(Blocks.AZALEA_LEAVES);
        FLOWERING_AZALEA_LEAVES = register(Blocks.FLOWERING_AZALEA_LEAVES);
        SPONGE = register(Blocks.SPONGE);
        WET_SPONGE = register(Blocks.WET_SPONGE);
        GLASS = register(Blocks.GLASS);
        TINTED_GLASS = register(Blocks.TINTED_GLASS);
        LAPIS_BLOCK = register(Blocks.LAPIS_BLOCK);
        SANDSTONE = register(Blocks.SANDSTONE);
        CHISELED_SANDSTONE = register(Blocks.CHISELED_SANDSTONE);
        CUT_SANDSTONE = register(Blocks.CUT_SANDSTONE);
        COBWEB = register(Blocks.COBWEB);
        GRASS = register(Blocks.GRASS);
        FERN = register(Blocks.FERN);
        AZALEA = register(Blocks.AZALEA);
        FLOWERING_AZALEA = register(Blocks.FLOWERING_AZALEA);
        DEAD_BUSH = register(Blocks.DEAD_BUSH);
        SEAGRASS = register(Blocks.SEAGRASS);
        SEA_PICKLE = register(Blocks.SEA_PICKLE);
        WHITE_WOOL = register(Blocks.WHITE_WOOL);
        ORANGE_WOOL = register(Blocks.ORANGE_WOOL);
        MAGENTA_WOOL = register(Blocks.MAGENTA_WOOL);
        LIGHT_BLUE_WOOL = register(Blocks.LIGHT_BLUE_WOOL);
        YELLOW_WOOL = register(Blocks.YELLOW_WOOL);
        LIME_WOOL = register(Blocks.LIME_WOOL);
        PINK_WOOL = register(Blocks.PINK_WOOL);
        GRAY_WOOL = register(Blocks.GRAY_WOOL);
        LIGHT_GRAY_WOOL = register(Blocks.LIGHT_GRAY_WOOL);
        CYAN_WOOL = register(Blocks.CYAN_WOOL);
        PURPLE_WOOL = register(Blocks.PURPLE_WOOL);
        BLUE_WOOL = register(Blocks.BLUE_WOOL);
        BROWN_WOOL = register(Blocks.BROWN_WOOL);
        GREEN_WOOL = register(Blocks.GREEN_WOOL);
        RED_WOOL = register(Blocks.RED_WOOL);
        BLACK_WOOL = register(Blocks.BLACK_WOOL);
        DANDELION = register(Blocks.DANDELION);
        POPPY = register(Blocks.POPPY);
        BLUE_ORCHID = register(Blocks.BLUE_ORCHID);
        ALLIUM = register(Blocks.ALLIUM);
        AZURE_BLUET = register(Blocks.AZURE_BLUET);
        RED_TULIP = register(Blocks.RED_TULIP);
        ORANGE_TULIP = register(Blocks.ORANGE_TULIP);
        WHITE_TULIP = register(Blocks.WHITE_TULIP);
        PINK_TULIP = register(Blocks.PINK_TULIP);
        OXEYE_DAISY = register(Blocks.OXEYE_DAISY);
        CORNFLOWER = register(Blocks.CORNFLOWER);
        LILY_OF_THE_VALLEY = register(Blocks.LILY_OF_THE_VALLEY);
        WITHER_ROSE = register(Blocks.WITHER_ROSE);
        TORCHFLOWER = register(Blocks.TORCHFLOWER);
        PITCHER_PLANT = register(Blocks.PITCHER_PLANT);
        SPORE_BLOSSOM = register(Blocks.SPORE_BLOSSOM);
        BROWN_MUSHROOM = register(Blocks.BROWN_MUSHROOM);
        RED_MUSHROOM = register(Blocks.RED_MUSHROOM);
        CRIMSON_FUNGUS = register(Blocks.CRIMSON_FUNGUS);
        WARPED_FUNGUS = register(Blocks.WARPED_FUNGUS);
        CRIMSON_ROOTS = register(Blocks.CRIMSON_ROOTS);
        WARPED_ROOTS = register(Blocks.WARPED_ROOTS);
        NETHER_SPROUTS = register(Blocks.NETHER_SPROUTS);
        WEEPING_VINES = register(Blocks.WEEPING_VINES);
        TWISTING_VINES = register(Blocks.TWISTING_VINES);
        SUGAR_CANE = register(Blocks.SUGAR_CANE);
        KELP = register(Blocks.KELP);
        MOSS_CARPET = register(Blocks.MOSS_CARPET);
        PINK_PETALS = register(Blocks.PINK_PETALS);
        MOSS_BLOCK = register(Blocks.MOSS_BLOCK);
        HANGING_ROOTS = register(Blocks.HANGING_ROOTS);
        BIG_DRIPLEAF = register(Blocks.BIG_DRIPLEAF,
                                Blocks.BIG_DRIPLEAF_STEM
        );
        SMALL_DRIPLEAF = register(new TallBlockItem(Blocks.SMALL_DRIPLEAF,
                                                    new Item.Settings()
        ));
        BAMBOO = register(Blocks.BAMBOO);
        OAK_SLAB = register(Blocks.OAK_SLAB);
        SPRUCE_SLAB = register(Blocks.SPRUCE_SLAB);
        BIRCH_SLAB = register(Blocks.BIRCH_SLAB);
        JUNGLE_SLAB = register(Blocks.JUNGLE_SLAB);
        ACACIA_SLAB = register(Blocks.ACACIA_SLAB);
        CHERRY_SLAB = register(Blocks.CHERRY_SLAB);
        DARK_OAK_SLAB = register(Blocks.DARK_OAK_SLAB);
        MANGROVE_SLAB = register(Blocks.MANGROVE_SLAB);
        BAMBOO_SLAB = register(Blocks.BAMBOO_SLAB);
        BAMBOO_MOSAIC_SLAB = register(Blocks.BAMBOO_MOSAIC_SLAB);
        CRIMSON_SLAB = register(Blocks.CRIMSON_SLAB);
        WARPED_SLAB = register(Blocks.WARPED_SLAB);
        STONE_SLAB = register(Blocks.STONE_SLAB);
        SMOOTH_STONE_SLAB = register(Blocks.SMOOTH_STONE_SLAB);
        SANDSTONE_SLAB = register(Blocks.SANDSTONE_SLAB);
        CUT_SANDSTONE_SLAB = register(Blocks.CUT_SANDSTONE_SLAB);
        PETRIFIED_OAK_SLAB = register(Blocks.PETRIFIED_OAK_SLAB);
        COBBLESTONE_SLAB = register(Blocks.COBBLESTONE_SLAB);
        BRICK_SLAB = register(Blocks.BRICK_SLAB);
        STONE_BRICK_SLAB = register(Blocks.STONE_BRICK_SLAB);
        MUD_BRICK_SLAB = register(Blocks.MUD_BRICK_SLAB);
        NETHER_BRICK_SLAB = register(Blocks.NETHER_BRICK_SLAB);
        QUARTZ_SLAB = register(Blocks.QUARTZ_SLAB);
        RED_SANDSTONE_SLAB = register(Blocks.RED_SANDSTONE_SLAB);
        CUT_RED_SANDSTONE_SLAB = register(Blocks.CUT_RED_SANDSTONE_SLAB);
        PURPUR_SLAB = register(Blocks.PURPUR_SLAB);
        PRISMARINE_SLAB = register(Blocks.PRISMARINE_SLAB);
        PRISMARINE_BRICK_SLAB = register(Blocks.PRISMARINE_BRICK_SLAB);
        DARK_PRISMARINE_SLAB = register(Blocks.DARK_PRISMARINE_SLAB);
        SMOOTH_QUARTZ = register(Blocks.SMOOTH_QUARTZ);
        SMOOTH_RED_SANDSTONE = register(Blocks.SMOOTH_RED_SANDSTONE);
        SMOOTH_SANDSTONE = register(Blocks.SMOOTH_SANDSTONE);
        SMOOTH_STONE = register(Blocks.SMOOTH_STONE);
        BRICKS = register(Blocks.BRICKS);
        BOOKSHELF = register(Blocks.BOOKSHELF);
        CHISELED_BOOKSHELF = register(Blocks.CHISELED_BOOKSHELF);
        DECORATED_POT = register(new BlockItem(Blocks.DECORATED_POT,
                                               (new Item.Settings()).maxCount(1)
        ));
        MOSSY_COBBLESTONE = register(Blocks.MOSSY_COBBLESTONE);
        OBSIDIAN = register(Blocks.OBSIDIAN);
        TORCH = register(new VerticallyAttachableBlockItem(Blocks.TORCH,
                                                           Blocks.WALL_TORCH,
                                                           new Item.Settings(),
                                                           Direction.DOWN
        ));
        END_ROD = register(Blocks.END_ROD);
        CHORUS_PLANT = register(Blocks.CHORUS_PLANT);
        CHORUS_FLOWER = register(Blocks.CHORUS_FLOWER);
        PURPUR_BLOCK = register(Blocks.PURPUR_BLOCK);
        PURPUR_PILLAR = register(Blocks.PURPUR_PILLAR);
        PURPUR_STAIRS = register(Blocks.PURPUR_STAIRS);
        SPAWNER = register(Blocks.SPAWNER);
        CHEST = register(Blocks.CHEST);
        CRAFTING_TABLE = register(Blocks.CRAFTING_TABLE);
        FARMLAND = register(Blocks.FARMLAND);
        FURNACE = register(Blocks.FURNACE);
        LADDER = register(Blocks.LADDER);
        COBBLESTONE_STAIRS = register(Blocks.COBBLESTONE_STAIRS);
        SNOW = register(Blocks.SNOW);
        ICE = register(Blocks.ICE);
        SNOW_BLOCK = register(Blocks.SNOW_BLOCK);
        CACTUS = register(Blocks.CACTUS);
        CLAY = register(Blocks.CLAY);
        JUKEBOX = register(Blocks.JUKEBOX);
        OAK_FENCE = register(Blocks.OAK_FENCE);
        SPRUCE_FENCE = register(Blocks.SPRUCE_FENCE);
        BIRCH_FENCE = register(Blocks.BIRCH_FENCE);
        JUNGLE_FENCE = register(Blocks.JUNGLE_FENCE);
        ACACIA_FENCE = register(Blocks.ACACIA_FENCE);
        CHERRY_FENCE = register(Blocks.CHERRY_FENCE);
        DARK_OAK_FENCE = register(Blocks.DARK_OAK_FENCE);
        MANGROVE_FENCE = register(Blocks.MANGROVE_FENCE);
        BAMBOO_FENCE = register(Blocks.BAMBOO_FENCE);
        CRIMSON_FENCE = register(Blocks.CRIMSON_FENCE);
        WARPED_FENCE = register(Blocks.WARPED_FENCE);
        PUMPKIN = register(Blocks.PUMPKIN);
        CARVED_PUMPKIN = register(Blocks.CARVED_PUMPKIN);
        JACK_O_LANTERN = register(Blocks.JACK_O_LANTERN);
        NETHERRACK = register(Blocks.NETHERRACK);
        SOUL_SAND = register(Blocks.SOUL_SAND);
        SOUL_SOIL = register(Blocks.SOUL_SOIL);
        BASALT = register(Blocks.BASALT);
        POLISHED_BASALT = register(Blocks.POLISHED_BASALT);
        SMOOTH_BASALT = register(Blocks.SMOOTH_BASALT);
        SOUL_TORCH = register(new VerticallyAttachableBlockItem(Blocks.SOUL_TORCH,
                                                                Blocks.SOUL_WALL_TORCH,
                                                                new Item.Settings(),
                                                                Direction.DOWN
        ));
        GLOWSTONE = register(Blocks.GLOWSTONE);
        INFESTED_STONE = register(Blocks.INFESTED_STONE);
        INFESTED_COBBLESTONE = register(Blocks.INFESTED_COBBLESTONE);
        INFESTED_STONE_BRICKS = register(Blocks.INFESTED_STONE_BRICKS);
        INFESTED_MOSSY_STONE_BRICKS = register(Blocks.INFESTED_MOSSY_STONE_BRICKS);
        INFESTED_CRACKED_STONE_BRICKS = register(Blocks.INFESTED_CRACKED_STONE_BRICKS);
        INFESTED_CHISELED_STONE_BRICKS = register(Blocks.INFESTED_CHISELED_STONE_BRICKS);
        INFESTED_DEEPSLATE = register(Blocks.INFESTED_DEEPSLATE);
        STONE_BRICKS = register(Blocks.STONE_BRICKS);
        MOSSY_STONE_BRICKS = register(Blocks.MOSSY_STONE_BRICKS);
        CRACKED_STONE_BRICKS = register(Blocks.CRACKED_STONE_BRICKS);
        CHISELED_STONE_BRICKS = register(Blocks.CHISELED_STONE_BRICKS);
        PACKED_MUD = register(Blocks.PACKED_MUD);
        MUD_BRICKS = register(Blocks.MUD_BRICKS);
        DEEPSLATE_BRICKS = register(Blocks.DEEPSLATE_BRICKS);
        CRACKED_DEEPSLATE_BRICKS = register(Blocks.CRACKED_DEEPSLATE_BRICKS);
        DEEPSLATE_TILES = register(Blocks.DEEPSLATE_TILES);
        CRACKED_DEEPSLATE_TILES = register(Blocks.CRACKED_DEEPSLATE_TILES);
        CHISELED_DEEPSLATE = register(Blocks.CHISELED_DEEPSLATE);
        REINFORCED_DEEPSLATE = register(Blocks.REINFORCED_DEEPSLATE);
        BROWN_MUSHROOM_BLOCK = register(Blocks.BROWN_MUSHROOM_BLOCK);
        RED_MUSHROOM_BLOCK = register(Blocks.RED_MUSHROOM_BLOCK);
        MUSHROOM_STEM = register(Blocks.MUSHROOM_STEM);
        IRON_BARS = register(Blocks.IRON_BARS);
        CHAIN = register(Blocks.CHAIN);
        GLASS_PANE = register(Blocks.GLASS_PANE);
        MELON = register(Blocks.MELON);
        VINE = register(Blocks.VINE);
        GLOW_LICHEN = register(Blocks.GLOW_LICHEN);
        BRICK_STAIRS = register(Blocks.BRICK_STAIRS);
        STONE_BRICK_STAIRS = register(Blocks.STONE_BRICK_STAIRS);
        MUD_BRICK_STAIRS = register(Blocks.MUD_BRICK_STAIRS);
        MYCELIUM = register(Blocks.MYCELIUM);
        LILY_PAD = register(new PlaceableOnWaterItem(Blocks.LILY_PAD,
                                                     new Item.Settings()
        ));
        NETHER_BRICKS = register(Blocks.NETHER_BRICKS);
        CRACKED_NETHER_BRICKS = register(Blocks.CRACKED_NETHER_BRICKS);
        CHISELED_NETHER_BRICKS = register(Blocks.CHISELED_NETHER_BRICKS);
        NETHER_BRICK_FENCE = register(Blocks.NETHER_BRICK_FENCE);
        NETHER_BRICK_STAIRS = register(Blocks.NETHER_BRICK_STAIRS);
        SCULK = register(Blocks.SCULK);
        SCULK_VEIN = register(Blocks.SCULK_VEIN);
        SCULK_CATALYST = register(Blocks.SCULK_CATALYST);
        SCULK_SHRIEKER = register(Blocks.SCULK_SHRIEKER);
        ENCHANTING_TABLE = register(Blocks.ENCHANTING_TABLE);
        END_PORTAL_FRAME = register(Blocks.END_PORTAL_FRAME);
        END_STONE = register(Blocks.END_STONE);
        END_STONE_BRICKS = register(Blocks.END_STONE_BRICKS);
        DRAGON_EGG = register(new BlockItem(Blocks.DRAGON_EGG,
                                            (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        SANDSTONE_STAIRS = register(Blocks.SANDSTONE_STAIRS);
        ENDER_CHEST = register(Blocks.ENDER_CHEST);
        EMERALD_BLOCK = register(Blocks.EMERALD_BLOCK);
        OAK_STAIRS = register(Blocks.OAK_STAIRS);
        SPRUCE_STAIRS = register(Blocks.SPRUCE_STAIRS);
        BIRCH_STAIRS = register(Blocks.BIRCH_STAIRS);
        JUNGLE_STAIRS = register(Blocks.JUNGLE_STAIRS);
        ACACIA_STAIRS = register(Blocks.ACACIA_STAIRS);
        CHERRY_STAIRS = register(Blocks.CHERRY_STAIRS);
        DARK_OAK_STAIRS = register(Blocks.DARK_OAK_STAIRS);
        MANGROVE_STAIRS = register(Blocks.MANGROVE_STAIRS);
        BAMBOO_STAIRS = register(Blocks.BAMBOO_STAIRS);
        BAMBOO_MOSAIC_STAIRS = register(Blocks.BAMBOO_MOSAIC_STAIRS);
        CRIMSON_STAIRS = register(Blocks.CRIMSON_STAIRS);
        WARPED_STAIRS = register(Blocks.WARPED_STAIRS);
        COMMAND_BLOCK = register(new OperatorOnlyBlockItem(Blocks.COMMAND_BLOCK,
                                                           (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        BEACON = register(new BlockItem(Blocks.BEACON,
                                        (new Item.Settings()).rarity(Rarity.RARE)
        ));
        COBBLESTONE_WALL = register(Blocks.COBBLESTONE_WALL);
        MOSSY_COBBLESTONE_WALL = register(Blocks.MOSSY_COBBLESTONE_WALL);
        BRICK_WALL = register(Blocks.BRICK_WALL);
        PRISMARINE_WALL = register(Blocks.PRISMARINE_WALL);
        RED_SANDSTONE_WALL = register(Blocks.RED_SANDSTONE_WALL);
        MOSSY_STONE_BRICK_WALL = register(Blocks.MOSSY_STONE_BRICK_WALL);
        GRANITE_WALL = register(Blocks.GRANITE_WALL);
        STONE_BRICK_WALL = register(Blocks.STONE_BRICK_WALL);
        MUD_BRICK_WALL = register(Blocks.MUD_BRICK_WALL);
        NETHER_BRICK_WALL = register(Blocks.NETHER_BRICK_WALL);
        ANDESITE_WALL = register(Blocks.ANDESITE_WALL);
        RED_NETHER_BRICK_WALL = register(Blocks.RED_NETHER_BRICK_WALL);
        SANDSTONE_WALL = register(Blocks.SANDSTONE_WALL);
        END_STONE_BRICK_WALL = register(Blocks.END_STONE_BRICK_WALL);
        DIORITE_WALL = register(Blocks.DIORITE_WALL);
        BLACKSTONE_WALL = register(Blocks.BLACKSTONE_WALL);
        POLISHED_BLACKSTONE_WALL = register(Blocks.POLISHED_BLACKSTONE_WALL);
        POLISHED_BLACKSTONE_BRICK_WALL = register(Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        COBBLED_DEEPSLATE_WALL = register(Blocks.COBBLED_DEEPSLATE_WALL);
        POLISHED_DEEPSLATE_WALL = register(Blocks.POLISHED_DEEPSLATE_WALL);
        DEEPSLATE_BRICK_WALL = register(Blocks.DEEPSLATE_BRICK_WALL);
        DEEPSLATE_TILE_WALL = register(Blocks.DEEPSLATE_TILE_WALL);
        ANVIL = register(Blocks.ANVIL);
        CHIPPED_ANVIL = register(Blocks.CHIPPED_ANVIL);
        DAMAGED_ANVIL = register(Blocks.DAMAGED_ANVIL);
        CHISELED_QUARTZ_BLOCK = register(Blocks.CHISELED_QUARTZ_BLOCK);
        QUARTZ_BLOCK = register(Blocks.QUARTZ_BLOCK);
        QUARTZ_BRICKS = register(Blocks.QUARTZ_BRICKS);
        QUARTZ_PILLAR = register(Blocks.QUARTZ_PILLAR);
        QUARTZ_STAIRS = register(Blocks.QUARTZ_STAIRS);
        WHITE_TERRACOTTA = register(Blocks.WHITE_TERRACOTTA);
        ORANGE_TERRACOTTA = register(Blocks.ORANGE_TERRACOTTA);
        MAGENTA_TERRACOTTA = register(Blocks.MAGENTA_TERRACOTTA);
        LIGHT_BLUE_TERRACOTTA = register(Blocks.LIGHT_BLUE_TERRACOTTA);
        YELLOW_TERRACOTTA = register(Blocks.YELLOW_TERRACOTTA);
        LIME_TERRACOTTA = register(Blocks.LIME_TERRACOTTA);
        PINK_TERRACOTTA = register(Blocks.PINK_TERRACOTTA);
        GRAY_TERRACOTTA = register(Blocks.GRAY_TERRACOTTA);
        LIGHT_GRAY_TERRACOTTA = register(Blocks.LIGHT_GRAY_TERRACOTTA);
        CYAN_TERRACOTTA = register(Blocks.CYAN_TERRACOTTA);
        PURPLE_TERRACOTTA = register(Blocks.PURPLE_TERRACOTTA);
        BLUE_TERRACOTTA = register(Blocks.BLUE_TERRACOTTA);
        BROWN_TERRACOTTA = register(Blocks.BROWN_TERRACOTTA);
        GREEN_TERRACOTTA = register(Blocks.GREEN_TERRACOTTA);
        RED_TERRACOTTA = register(Blocks.RED_TERRACOTTA);
        BLACK_TERRACOTTA = register(Blocks.BLACK_TERRACOTTA);
        BARRIER = register(new BlockItem(Blocks.BARRIER,
                                         (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        LIGHT = register(new BlockItem(Blocks.LIGHT,
                                       (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        HAY_BLOCK = register(Blocks.HAY_BLOCK);
        WHITE_CARPET = register(Blocks.WHITE_CARPET);
        ORANGE_CARPET = register(Blocks.ORANGE_CARPET);
        MAGENTA_CARPET = register(Blocks.MAGENTA_CARPET);
        LIGHT_BLUE_CARPET = register(Blocks.LIGHT_BLUE_CARPET);
        YELLOW_CARPET = register(Blocks.YELLOW_CARPET);
        LIME_CARPET = register(Blocks.LIME_CARPET);
        PINK_CARPET = register(Blocks.PINK_CARPET);
        GRAY_CARPET = register(Blocks.GRAY_CARPET);
        LIGHT_GRAY_CARPET = register(Blocks.LIGHT_GRAY_CARPET);
        CYAN_CARPET = register(Blocks.CYAN_CARPET);
        PURPLE_CARPET = register(Blocks.PURPLE_CARPET);
        BLUE_CARPET = register(Blocks.BLUE_CARPET);
        BROWN_CARPET = register(Blocks.BROWN_CARPET);
        GREEN_CARPET = register(Blocks.GREEN_CARPET);
        RED_CARPET = register(Blocks.RED_CARPET);
        BLACK_CARPET = register(Blocks.BLACK_CARPET);
        TERRACOTTA = register(Blocks.TERRACOTTA);
        PACKED_ICE = register(Blocks.PACKED_ICE);
        DIRT_PATH = register(Blocks.DIRT_PATH);
        SUNFLOWER = register(new TallBlockItem(Blocks.SUNFLOWER,
                                               new Item.Settings()
        ));
        LILAC = register(new TallBlockItem(Blocks.LILAC,
                                           new Item.Settings()
        ));
        ROSE_BUSH = register(new TallBlockItem(Blocks.ROSE_BUSH,
                                               new Item.Settings()
        ));
        PEONY = register(new TallBlockItem(Blocks.PEONY,
                                           new Item.Settings()
        ));
        TALL_GRASS = register(new TallBlockItem(Blocks.TALL_GRASS,
                                                new Item.Settings()
        ));
        LARGE_FERN = register(new TallBlockItem(Blocks.LARGE_FERN,
                                                new Item.Settings()
        ));
        WHITE_STAINED_GLASS = register(Blocks.WHITE_STAINED_GLASS);
        ORANGE_STAINED_GLASS = register(Blocks.ORANGE_STAINED_GLASS);
        MAGENTA_STAINED_GLASS = register(Blocks.MAGENTA_STAINED_GLASS);
        LIGHT_BLUE_STAINED_GLASS = register(Blocks.LIGHT_BLUE_STAINED_GLASS);
        YELLOW_STAINED_GLASS = register(Blocks.YELLOW_STAINED_GLASS);
        LIME_STAINED_GLASS = register(Blocks.LIME_STAINED_GLASS);
        PINK_STAINED_GLASS = register(Blocks.PINK_STAINED_GLASS);
        GRAY_STAINED_GLASS = register(Blocks.GRAY_STAINED_GLASS);
        LIGHT_GRAY_STAINED_GLASS = register(Blocks.LIGHT_GRAY_STAINED_GLASS);
        CYAN_STAINED_GLASS = register(Blocks.CYAN_STAINED_GLASS);
        PURPLE_STAINED_GLASS = register(Blocks.PURPLE_STAINED_GLASS);
        BLUE_STAINED_GLASS = register(Blocks.BLUE_STAINED_GLASS);
        BROWN_STAINED_GLASS = register(Blocks.BROWN_STAINED_GLASS);
        GREEN_STAINED_GLASS = register(Blocks.GREEN_STAINED_GLASS);
        RED_STAINED_GLASS = register(Blocks.RED_STAINED_GLASS);
        BLACK_STAINED_GLASS = register(Blocks.BLACK_STAINED_GLASS);
        WHITE_STAINED_GLASS_PANE = register(Blocks.WHITE_STAINED_GLASS_PANE);
        ORANGE_STAINED_GLASS_PANE = register(Blocks.ORANGE_STAINED_GLASS_PANE);
        MAGENTA_STAINED_GLASS_PANE = register(Blocks.MAGENTA_STAINED_GLASS_PANE);
        LIGHT_BLUE_STAINED_GLASS_PANE = register(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE);
        YELLOW_STAINED_GLASS_PANE = register(Blocks.YELLOW_STAINED_GLASS_PANE);
        LIME_STAINED_GLASS_PANE = register(Blocks.LIME_STAINED_GLASS_PANE);
        PINK_STAINED_GLASS_PANE = register(Blocks.PINK_STAINED_GLASS_PANE);
        GRAY_STAINED_GLASS_PANE = register(Blocks.GRAY_STAINED_GLASS_PANE);
        LIGHT_GRAY_STAINED_GLASS_PANE = register(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE);
        CYAN_STAINED_GLASS_PANE = register(Blocks.CYAN_STAINED_GLASS_PANE);
        PURPLE_STAINED_GLASS_PANE = register(Blocks.PURPLE_STAINED_GLASS_PANE);
        BLUE_STAINED_GLASS_PANE = register(Blocks.BLUE_STAINED_GLASS_PANE);
        BROWN_STAINED_GLASS_PANE = register(Blocks.BROWN_STAINED_GLASS_PANE);
        GREEN_STAINED_GLASS_PANE = register(Blocks.GREEN_STAINED_GLASS_PANE);
        RED_STAINED_GLASS_PANE = register(Blocks.RED_STAINED_GLASS_PANE);
        BLACK_STAINED_GLASS_PANE = register(Blocks.BLACK_STAINED_GLASS_PANE);
        PRISMARINE = register(Blocks.PRISMARINE);
        PRISMARINE_BRICKS = register(Blocks.PRISMARINE_BRICKS);
        DARK_PRISMARINE = register(Blocks.DARK_PRISMARINE);
        PRISMARINE_STAIRS = register(Blocks.PRISMARINE_STAIRS);
        PRISMARINE_BRICK_STAIRS = register(Blocks.PRISMARINE_BRICK_STAIRS);
        DARK_PRISMARINE_STAIRS = register(Blocks.DARK_PRISMARINE_STAIRS);
        SEA_LANTERN = register(Blocks.SEA_LANTERN);
        RED_SANDSTONE = register(Blocks.RED_SANDSTONE);
        CHISELED_RED_SANDSTONE = register(Blocks.CHISELED_RED_SANDSTONE);
        CUT_RED_SANDSTONE = register(Blocks.CUT_RED_SANDSTONE);
        RED_SANDSTONE_STAIRS = register(Blocks.RED_SANDSTONE_STAIRS);
        REPEATING_COMMAND_BLOCK = register(new OperatorOnlyBlockItem(Blocks.REPEATING_COMMAND_BLOCK,
                                                                     (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        CHAIN_COMMAND_BLOCK = register(new OperatorOnlyBlockItem(Blocks.CHAIN_COMMAND_BLOCK,
                                                                 (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        MAGMA_BLOCK = register(Blocks.MAGMA_BLOCK);
        NETHER_WART_BLOCK = register(Blocks.NETHER_WART_BLOCK);
        WARPED_WART_BLOCK = register(Blocks.WARPED_WART_BLOCK);
        RED_NETHER_BRICKS = register(Blocks.RED_NETHER_BRICKS);
        BONE_BLOCK = register(Blocks.BONE_BLOCK);
        STRUCTURE_VOID = register(new BlockItem(Blocks.STRUCTURE_VOID,
                                                (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        SHULKER_BOX = register(new BlockItem(Blocks.SHULKER_BOX,
                                             (new Item.Settings()).maxCount(1)
        ));
        WHITE_SHULKER_BOX = register(new BlockItem(Blocks.WHITE_SHULKER_BOX,
                                                   (new Item.Settings()).maxCount(1)
        ));
        ORANGE_SHULKER_BOX = register(new BlockItem(Blocks.ORANGE_SHULKER_BOX,
                                                    (new Item.Settings()).maxCount(1)
        ));
        MAGENTA_SHULKER_BOX = register(new BlockItem(Blocks.MAGENTA_SHULKER_BOX,
                                                     (new Item.Settings()).maxCount(1)
        ));
        LIGHT_BLUE_SHULKER_BOX = register(new BlockItem(Blocks.LIGHT_BLUE_SHULKER_BOX,
                                                        (new Item.Settings()).maxCount(1)
        ));
        YELLOW_SHULKER_BOX = register(new BlockItem(Blocks.YELLOW_SHULKER_BOX,
                                                    (new Item.Settings()).maxCount(1)
        ));
        LIME_SHULKER_BOX = register(new BlockItem(Blocks.LIME_SHULKER_BOX,
                                                  (new Item.Settings()).maxCount(1)
        ));
        PINK_SHULKER_BOX = register(new BlockItem(Blocks.PINK_SHULKER_BOX,
                                                  (new Item.Settings()).maxCount(1)
        ));
        GRAY_SHULKER_BOX = register(new BlockItem(Blocks.GRAY_SHULKER_BOX,
                                                  (new Item.Settings()).maxCount(1)
        ));
        LIGHT_GRAY_SHULKER_BOX = register(new BlockItem(Blocks.LIGHT_GRAY_SHULKER_BOX,
                                                        (new Item.Settings()).maxCount(1)
        ));
        CYAN_SHULKER_BOX = register(new BlockItem(Blocks.CYAN_SHULKER_BOX,
                                                  (new Item.Settings()).maxCount(1)
        ));
        PURPLE_SHULKER_BOX = register(new BlockItem(Blocks.PURPLE_SHULKER_BOX,
                                                    (new Item.Settings()).maxCount(1)
        ));
        BLUE_SHULKER_BOX = register(new BlockItem(Blocks.BLUE_SHULKER_BOX,
                                                  (new Item.Settings()).maxCount(1)
        ));
        BROWN_SHULKER_BOX = register(new BlockItem(Blocks.BROWN_SHULKER_BOX,
                                                   (new Item.Settings()).maxCount(1)
        ));
        GREEN_SHULKER_BOX = register(new BlockItem(Blocks.GREEN_SHULKER_BOX,
                                                   (new Item.Settings()).maxCount(1)
        ));
        RED_SHULKER_BOX = register(new BlockItem(Blocks.RED_SHULKER_BOX,
                                                 (new Item.Settings()).maxCount(1)
        ));
        BLACK_SHULKER_BOX = register(new BlockItem(Blocks.BLACK_SHULKER_BOX,
                                                   (new Item.Settings()).maxCount(1)
        ));
        WHITE_GLAZED_TERRACOTTA = register(Blocks.WHITE_GLAZED_TERRACOTTA);
        ORANGE_GLAZED_TERRACOTTA = register(Blocks.ORANGE_GLAZED_TERRACOTTA);
        MAGENTA_GLAZED_TERRACOTTA = register(Blocks.MAGENTA_GLAZED_TERRACOTTA);
        LIGHT_BLUE_GLAZED_TERRACOTTA = register(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA);
        YELLOW_GLAZED_TERRACOTTA = register(Blocks.YELLOW_GLAZED_TERRACOTTA);
        LIME_GLAZED_TERRACOTTA = register(Blocks.LIME_GLAZED_TERRACOTTA);
        PINK_GLAZED_TERRACOTTA = register(Blocks.PINK_GLAZED_TERRACOTTA);
        GRAY_GLAZED_TERRACOTTA = register(Blocks.GRAY_GLAZED_TERRACOTTA);
        LIGHT_GRAY_GLAZED_TERRACOTTA = register(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA);
        CYAN_GLAZED_TERRACOTTA = register(Blocks.CYAN_GLAZED_TERRACOTTA);
        PURPLE_GLAZED_TERRACOTTA = register(Blocks.PURPLE_GLAZED_TERRACOTTA);
        BLUE_GLAZED_TERRACOTTA = register(Blocks.BLUE_GLAZED_TERRACOTTA);
        BROWN_GLAZED_TERRACOTTA = register(Blocks.BROWN_GLAZED_TERRACOTTA);
        GREEN_GLAZED_TERRACOTTA = register(Blocks.GREEN_GLAZED_TERRACOTTA);
        RED_GLAZED_TERRACOTTA = register(Blocks.RED_GLAZED_TERRACOTTA);
        BLACK_GLAZED_TERRACOTTA = register(Blocks.BLACK_GLAZED_TERRACOTTA);
        WHITE_CONCRETE = register(Blocks.WHITE_CONCRETE);
        ORANGE_CONCRETE = register(Blocks.ORANGE_CONCRETE);
        MAGENTA_CONCRETE = register(Blocks.MAGENTA_CONCRETE);
        LIGHT_BLUE_CONCRETE = register(Blocks.LIGHT_BLUE_CONCRETE);
        YELLOW_CONCRETE = register(Blocks.YELLOW_CONCRETE);
        LIME_CONCRETE = register(Blocks.LIME_CONCRETE);
        PINK_CONCRETE = register(Blocks.PINK_CONCRETE);
        GRAY_CONCRETE = register(Blocks.GRAY_CONCRETE);
        LIGHT_GRAY_CONCRETE = register(Blocks.LIGHT_GRAY_CONCRETE);
        CYAN_CONCRETE = register(Blocks.CYAN_CONCRETE);
        PURPLE_CONCRETE = register(Blocks.PURPLE_CONCRETE);
        BLUE_CONCRETE = register(Blocks.BLUE_CONCRETE);
        BROWN_CONCRETE = register(Blocks.BROWN_CONCRETE);
        GREEN_CONCRETE = register(Blocks.GREEN_CONCRETE);
        RED_CONCRETE = register(Blocks.RED_CONCRETE);
        BLACK_CONCRETE = register(Blocks.BLACK_CONCRETE);
        WHITE_CONCRETE_POWDER = register(Blocks.WHITE_CONCRETE_POWDER);
        ORANGE_CONCRETE_POWDER = register(Blocks.ORANGE_CONCRETE_POWDER);
        MAGENTA_CONCRETE_POWDER = register(Blocks.MAGENTA_CONCRETE_POWDER);
        LIGHT_BLUE_CONCRETE_POWDER = register(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        YELLOW_CONCRETE_POWDER = register(Blocks.YELLOW_CONCRETE_POWDER);
        LIME_CONCRETE_POWDER = register(Blocks.LIME_CONCRETE_POWDER);
        PINK_CONCRETE_POWDER = register(Blocks.PINK_CONCRETE_POWDER);
        GRAY_CONCRETE_POWDER = register(Blocks.GRAY_CONCRETE_POWDER);
        LIGHT_GRAY_CONCRETE_POWDER = register(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        CYAN_CONCRETE_POWDER = register(Blocks.CYAN_CONCRETE_POWDER);
        PURPLE_CONCRETE_POWDER = register(Blocks.PURPLE_CONCRETE_POWDER);
        BLUE_CONCRETE_POWDER = register(Blocks.BLUE_CONCRETE_POWDER);
        BROWN_CONCRETE_POWDER = register(Blocks.BROWN_CONCRETE_POWDER);
        GREEN_CONCRETE_POWDER = register(Blocks.GREEN_CONCRETE_POWDER);
        RED_CONCRETE_POWDER = register(Blocks.RED_CONCRETE_POWDER);
        BLACK_CONCRETE_POWDER = register(Blocks.BLACK_CONCRETE_POWDER);
        TURTLE_EGG = register(Blocks.TURTLE_EGG);
        SNIFFER_EGG = register(Blocks.SNIFFER_EGG);
        DEAD_TUBE_CORAL_BLOCK = register(Blocks.DEAD_TUBE_CORAL_BLOCK);
        DEAD_BRAIN_CORAL_BLOCK = register(Blocks.DEAD_BRAIN_CORAL_BLOCK);
        DEAD_BUBBLE_CORAL_BLOCK = register(Blocks.DEAD_BUBBLE_CORAL_BLOCK);
        DEAD_FIRE_CORAL_BLOCK = register(Blocks.DEAD_FIRE_CORAL_BLOCK);
        DEAD_HORN_CORAL_BLOCK = register(Blocks.DEAD_HORN_CORAL_BLOCK);
        TUBE_CORAL_BLOCK = register(Blocks.TUBE_CORAL_BLOCK);
        BRAIN_CORAL_BLOCK = register(Blocks.BRAIN_CORAL_BLOCK);
        BUBBLE_CORAL_BLOCK = register(Blocks.BUBBLE_CORAL_BLOCK);
        FIRE_CORAL_BLOCK = register(Blocks.FIRE_CORAL_BLOCK);
        HORN_CORAL_BLOCK = register(Blocks.HORN_CORAL_BLOCK);
        TUBE_CORAL = register(Blocks.TUBE_CORAL);
        BRAIN_CORAL = register(Blocks.BRAIN_CORAL);
        BUBBLE_CORAL = register(Blocks.BUBBLE_CORAL);
        FIRE_CORAL = register(Blocks.FIRE_CORAL);
        HORN_CORAL = register(Blocks.HORN_CORAL);
        DEAD_BRAIN_CORAL = register(Blocks.DEAD_BRAIN_CORAL);
        DEAD_BUBBLE_CORAL = register(Blocks.DEAD_BUBBLE_CORAL);
        DEAD_FIRE_CORAL = register(Blocks.DEAD_FIRE_CORAL);
        DEAD_HORN_CORAL = register(Blocks.DEAD_HORN_CORAL);
        DEAD_TUBE_CORAL = register(Blocks.DEAD_TUBE_CORAL);
        TUBE_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.TUBE_CORAL_FAN,
                                                                    Blocks.TUBE_CORAL_WALL_FAN,
                                                                    new Item.Settings(),
                                                                    Direction.DOWN
        ));
        BRAIN_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.BRAIN_CORAL_FAN,
                                                                     Blocks.BRAIN_CORAL_WALL_FAN,
                                                                     new Item.Settings(),
                                                                     Direction.DOWN
        ));
        BUBBLE_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.BUBBLE_CORAL_FAN,
                                                                      Blocks.BUBBLE_CORAL_WALL_FAN,
                                                                      new Item.Settings(),
                                                                      Direction.DOWN
        ));
        FIRE_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.FIRE_CORAL_FAN,
                                                                    Blocks.FIRE_CORAL_WALL_FAN,
                                                                    new Item.Settings(),
                                                                    Direction.DOWN
        ));
        HORN_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.HORN_CORAL_FAN,
                                                                    Blocks.HORN_CORAL_WALL_FAN,
                                                                    new Item.Settings(),
                                                                    Direction.DOWN
        ));
        DEAD_TUBE_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.DEAD_TUBE_CORAL_FAN,
                                                                         Blocks.DEAD_TUBE_CORAL_WALL_FAN,
                                                                         new Item.Settings(),
                                                                         Direction.DOWN
        ));
        DEAD_BRAIN_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.DEAD_BRAIN_CORAL_FAN,
                                                                          Blocks.DEAD_BRAIN_CORAL_WALL_FAN,
                                                                          new Item.Settings(),
                                                                          Direction.DOWN
        ));
        DEAD_BUBBLE_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.DEAD_BUBBLE_CORAL_FAN,
                                                                           Blocks.DEAD_BUBBLE_CORAL_WALL_FAN,
                                                                           new Item.Settings(),
                                                                           Direction.DOWN
        ));
        DEAD_FIRE_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.DEAD_FIRE_CORAL_FAN,
                                                                         Blocks.DEAD_FIRE_CORAL_WALL_FAN,
                                                                         new Item.Settings(),
                                                                         Direction.DOWN
        ));
        DEAD_HORN_CORAL_FAN = register(new VerticallyAttachableBlockItem(Blocks.DEAD_HORN_CORAL_FAN,
                                                                         Blocks.DEAD_HORN_CORAL_WALL_FAN,
                                                                         new Item.Settings(),
                                                                         Direction.DOWN
        ));
        BLUE_ICE = register(Blocks.BLUE_ICE);
        CONDUIT = register(new BlockItem(Blocks.CONDUIT,
                                         (new Item.Settings()).rarity(Rarity.RARE)
        ));
        POLISHED_GRANITE_STAIRS = register(Blocks.POLISHED_GRANITE_STAIRS);
        SMOOTH_RED_SANDSTONE_STAIRS = register(Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
        MOSSY_STONE_BRICK_STAIRS = register(Blocks.MOSSY_STONE_BRICK_STAIRS);
        POLISHED_DIORITE_STAIRS = register(Blocks.POLISHED_DIORITE_STAIRS);
        MOSSY_COBBLESTONE_STAIRS = register(Blocks.MOSSY_COBBLESTONE_STAIRS);
        END_STONE_BRICK_STAIRS = register(Blocks.END_STONE_BRICK_STAIRS);
        STONE_STAIRS = register(Blocks.STONE_STAIRS);
        SMOOTH_SANDSTONE_STAIRS = register(Blocks.SMOOTH_SANDSTONE_STAIRS);
        SMOOTH_QUARTZ_STAIRS = register(Blocks.SMOOTH_QUARTZ_STAIRS);
        GRANITE_STAIRS = register(Blocks.GRANITE_STAIRS);
        ANDESITE_STAIRS = register(Blocks.ANDESITE_STAIRS);
        RED_NETHER_BRICK_STAIRS = register(Blocks.RED_NETHER_BRICK_STAIRS);
        POLISHED_ANDESITE_STAIRS = register(Blocks.POLISHED_ANDESITE_STAIRS);
        DIORITE_STAIRS = register(Blocks.DIORITE_STAIRS);
        COBBLED_DEEPSLATE_STAIRS = register(Blocks.COBBLED_DEEPSLATE_STAIRS);
        POLISHED_DEEPSLATE_STAIRS = register(Blocks.POLISHED_DEEPSLATE_STAIRS);
        DEEPSLATE_BRICK_STAIRS = register(Blocks.DEEPSLATE_BRICK_STAIRS);
        DEEPSLATE_TILE_STAIRS = register(Blocks.DEEPSLATE_TILE_STAIRS);
        POLISHED_GRANITE_SLAB = register(Blocks.POLISHED_GRANITE_SLAB);
        SMOOTH_RED_SANDSTONE_SLAB = register(Blocks.SMOOTH_RED_SANDSTONE_SLAB);
        MOSSY_STONE_BRICK_SLAB = register(Blocks.MOSSY_STONE_BRICK_SLAB);
        POLISHED_DIORITE_SLAB = register(Blocks.POLISHED_DIORITE_SLAB);
        MOSSY_COBBLESTONE_SLAB = register(Blocks.MOSSY_COBBLESTONE_SLAB);
        END_STONE_BRICK_SLAB = register(Blocks.END_STONE_BRICK_SLAB);
        SMOOTH_SANDSTONE_SLAB = register(Blocks.SMOOTH_SANDSTONE_SLAB);
        SMOOTH_QUARTZ_SLAB = register(Blocks.SMOOTH_QUARTZ_SLAB);
        GRANITE_SLAB = register(Blocks.GRANITE_SLAB);
        ANDESITE_SLAB = register(Blocks.ANDESITE_SLAB);
        RED_NETHER_BRICK_SLAB = register(Blocks.RED_NETHER_BRICK_SLAB);
        POLISHED_ANDESITE_SLAB = register(Blocks.POLISHED_ANDESITE_SLAB);
        DIORITE_SLAB = register(Blocks.DIORITE_SLAB);
        COBBLED_DEEPSLATE_SLAB = register(Blocks.COBBLED_DEEPSLATE_SLAB);
        POLISHED_DEEPSLATE_SLAB = register(Blocks.POLISHED_DEEPSLATE_SLAB);
        DEEPSLATE_BRICK_SLAB = register(Blocks.DEEPSLATE_BRICK_SLAB);
        DEEPSLATE_TILE_SLAB = register(Blocks.DEEPSLATE_TILE_SLAB);
        SCAFFOLDING = register(new ScaffoldingItem(Blocks.SCAFFOLDING,
                                                   new Item.Settings()
        ));
        REDSTONE = register("redstone",
                            new AliasedBlockItem(Blocks.REDSTONE_WIRE,
                                                 new Item.Settings()
                            )
        );
        REDSTONE_TORCH = register(new VerticallyAttachableBlockItem(Blocks.REDSTONE_TORCH,
                                                                    Blocks.REDSTONE_WALL_TORCH,
                                                                    new Item.Settings(),
                                                                    Direction.DOWN
        ));
        REDSTONE_BLOCK = register(Blocks.REDSTONE_BLOCK);
        REPEATER = register(Blocks.REPEATER);
        COMPARATOR = register(Blocks.COMPARATOR);
        PISTON = register(Blocks.PISTON);
        STICKY_PISTON = register(Blocks.STICKY_PISTON);
        SLIME_BLOCK = register(Blocks.SLIME_BLOCK);
        HONEY_BLOCK = register(Blocks.HONEY_BLOCK);
        OBSERVER = register(Blocks.OBSERVER);
        HOPPER = register(Blocks.HOPPER);
        DISPENSER = register(Blocks.DISPENSER);
        DROPPER = register(Blocks.DROPPER);
        LECTERN = register(Blocks.LECTERN);
        TARGET = register(Blocks.TARGET);
        LEVER = register(Blocks.LEVER);
        LIGHTNING_ROD = register(Blocks.LIGHTNING_ROD);
        DAYLIGHT_DETECTOR = register(Blocks.DAYLIGHT_DETECTOR);
        SCULK_SENSOR = register(Blocks.SCULK_SENSOR);
        CALIBRATED_SCULK_SENSOR = register(Blocks.CALIBRATED_SCULK_SENSOR);
        TRIPWIRE_HOOK = register(Blocks.TRIPWIRE_HOOK);
        TRAPPED_CHEST = register(Blocks.TRAPPED_CHEST);
        TNT = register(Blocks.TNT);
        REDSTONE_LAMP = register(Blocks.REDSTONE_LAMP);
        NOTE_BLOCK = register(Blocks.NOTE_BLOCK);
        STONE_BUTTON = register(Blocks.STONE_BUTTON);
        POLISHED_BLACKSTONE_BUTTON = register(Blocks.POLISHED_BLACKSTONE_BUTTON);
        OAK_BUTTON = register(Blocks.OAK_BUTTON);
        SPRUCE_BUTTON = register(Blocks.SPRUCE_BUTTON);
        BIRCH_BUTTON = register(Blocks.BIRCH_BUTTON);
        JUNGLE_BUTTON = register(Blocks.JUNGLE_BUTTON);
        ACACIA_BUTTON = register(Blocks.ACACIA_BUTTON);
        CHERRY_BUTTON = register(Blocks.CHERRY_BUTTON);
        DARK_OAK_BUTTON = register(Blocks.DARK_OAK_BUTTON);
        MANGROVE_BUTTON = register(Blocks.MANGROVE_BUTTON);
        BAMBOO_BUTTON = register(Blocks.BAMBOO_BUTTON);
        CRIMSON_BUTTON = register(Blocks.CRIMSON_BUTTON);
        WARPED_BUTTON = register(Blocks.WARPED_BUTTON);
        STONE_PRESSURE_PLATE = register(Blocks.STONE_PRESSURE_PLATE);
        POLISHED_BLACKSTONE_PRESSURE_PLATE = register(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE);
        LIGHT_WEIGHTED_PRESSURE_PLATE = register(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
        HEAVY_WEIGHTED_PRESSURE_PLATE = register(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
        OAK_PRESSURE_PLATE = register(Blocks.OAK_PRESSURE_PLATE);
        SPRUCE_PRESSURE_PLATE = register(Blocks.SPRUCE_PRESSURE_PLATE);
        BIRCH_PRESSURE_PLATE = register(Blocks.BIRCH_PRESSURE_PLATE);
        JUNGLE_PRESSURE_PLATE = register(Blocks.JUNGLE_PRESSURE_PLATE);
        ACACIA_PRESSURE_PLATE = register(Blocks.ACACIA_PRESSURE_PLATE);
        CHERRY_PRESSURE_PLATE = register(Blocks.CHERRY_PRESSURE_PLATE);
        DARK_OAK_PRESSURE_PLATE = register(Blocks.DARK_OAK_PRESSURE_PLATE);
        MANGROVE_PRESSURE_PLATE = register(Blocks.MANGROVE_PRESSURE_PLATE);
        BAMBOO_PRESSURE_PLATE = register(Blocks.BAMBOO_PRESSURE_PLATE);
        CRIMSON_PRESSURE_PLATE = register(Blocks.CRIMSON_PRESSURE_PLATE);
        WARPED_PRESSURE_PLATE = register(Blocks.WARPED_PRESSURE_PLATE);
        IRON_DOOR = register(new TallBlockItem(Blocks.IRON_DOOR,
                                               new Item.Settings()
        ));
        OAK_DOOR = register(new TallBlockItem(Blocks.OAK_DOOR,
                                              new Item.Settings()
        ));
        SPRUCE_DOOR = register(new TallBlockItem(Blocks.SPRUCE_DOOR,
                                                 new Item.Settings()
        ));
        BIRCH_DOOR = register(new TallBlockItem(Blocks.BIRCH_DOOR,
                                                new Item.Settings()
        ));
        JUNGLE_DOOR = register(new TallBlockItem(Blocks.JUNGLE_DOOR,
                                                 new Item.Settings()
        ));
        ACACIA_DOOR = register(new TallBlockItem(Blocks.ACACIA_DOOR,
                                                 new Item.Settings()
        ));
        CHERRY_DOOR = register(new TallBlockItem(Blocks.CHERRY_DOOR,
                                                 new Item.Settings()
        ));
        DARK_OAK_DOOR = register(new TallBlockItem(Blocks.DARK_OAK_DOOR,
                                                   new Item.Settings()
        ));
        MANGROVE_DOOR = register(new TallBlockItem(Blocks.MANGROVE_DOOR,
                                                   new Item.Settings()
        ));
        BAMBOO_DOOR = register(new TallBlockItem(Blocks.BAMBOO_DOOR,
                                                 new Item.Settings()
        ));
        CRIMSON_DOOR = register(new TallBlockItem(Blocks.CRIMSON_DOOR,
                                                  new Item.Settings()
        ));
        WARPED_DOOR = register(new TallBlockItem(Blocks.WARPED_DOOR,
                                                 new Item.Settings()
        ));
        IRON_TRAPDOOR = register(Blocks.IRON_TRAPDOOR);
        OAK_TRAPDOOR = register(Blocks.OAK_TRAPDOOR);
        SPRUCE_TRAPDOOR = register(Blocks.SPRUCE_TRAPDOOR);
        BIRCH_TRAPDOOR = register(Blocks.BIRCH_TRAPDOOR);
        JUNGLE_TRAPDOOR = register(Blocks.JUNGLE_TRAPDOOR);
        ACACIA_TRAPDOOR = register(Blocks.ACACIA_TRAPDOOR);
        CHERRY_TRAPDOOR = register(Blocks.CHERRY_TRAPDOOR);
        DARK_OAK_TRAPDOOR = register(Blocks.DARK_OAK_TRAPDOOR);
        MANGROVE_TRAPDOOR = register(Blocks.MANGROVE_TRAPDOOR);
        BAMBOO_TRAPDOOR = register(Blocks.BAMBOO_TRAPDOOR);
        CRIMSON_TRAPDOOR = register(Blocks.CRIMSON_TRAPDOOR);
        WARPED_TRAPDOOR = register(Blocks.WARPED_TRAPDOOR);
        OAK_FENCE_GATE = register(Blocks.OAK_FENCE_GATE);
        SPRUCE_FENCE_GATE = register(Blocks.SPRUCE_FENCE_GATE);
        BIRCH_FENCE_GATE = register(Blocks.BIRCH_FENCE_GATE);
        JUNGLE_FENCE_GATE = register(Blocks.JUNGLE_FENCE_GATE);
        ACACIA_FENCE_GATE = register(Blocks.ACACIA_FENCE_GATE);
        CHERRY_FENCE_GATE = register(Blocks.CHERRY_FENCE_GATE);
        DARK_OAK_FENCE_GATE = register(Blocks.DARK_OAK_FENCE_GATE);
        MANGROVE_FENCE_GATE = register(Blocks.MANGROVE_FENCE_GATE);
        BAMBOO_FENCE_GATE = register(Blocks.BAMBOO_FENCE_GATE);
        CRIMSON_FENCE_GATE = register(Blocks.CRIMSON_FENCE_GATE);
        WARPED_FENCE_GATE = register(Blocks.WARPED_FENCE_GATE);
        POWERED_RAIL = register(Blocks.POWERED_RAIL);
        DETECTOR_RAIL = register(Blocks.DETECTOR_RAIL);
        RAIL = register(Blocks.RAIL);
        ACTIVATOR_RAIL = register(Blocks.ACTIVATOR_RAIL);
        SADDLE = register("saddle",
                          new SaddleItem((new Item.Settings()).maxCount(1))
        );
        MINECART = register("minecart",
                            new MinecartItem(AbstractMinecartEntity.Type.RIDEABLE,
                                             (new Item.Settings()).maxCount(1)
                            )
        );
        CHEST_MINECART = register("chest_minecart",
                                  new MinecartItem(AbstractMinecartEntity.Type.CHEST,
                                                   (new Item.Settings()).maxCount(1)
                                  )
        );
        FURNACE_MINECART = register("furnace_minecart",
                                    new MinecartItem(AbstractMinecartEntity.Type.FURNACE,
                                                     (new Item.Settings()).maxCount(1)
                                    )
        );
        TNT_MINECART = register("tnt_minecart",
                                new MinecartItem(AbstractMinecartEntity.Type.TNT,
                                                 (new Item.Settings()).maxCount(1)
                                )
        );
        HOPPER_MINECART = register("hopper_minecart",
                                   new MinecartItem(AbstractMinecartEntity.Type.HOPPER,
                                                    (new Item.Settings()).maxCount(1)
                                   )
        );
        CARROT_ON_A_STICK = register("carrot_on_a_stick",
                                     new OnAStickItem((new Item.Settings()).maxDamage(25),
                                                      EntityType.PIG,
                                                      7
                                     )
        );
        WARPED_FUNGUS_ON_A_STICK = register("warped_fungus_on_a_stick",
                                            new OnAStickItem((new Item.Settings()).maxDamage(100),
                                                             EntityType.STRIDER,
                                                             1
                                            )
        );
        ELYTRA = register("elytra",
                          new ElytraItem((new Item.Settings()).maxDamage(432)
                                                              .rarity(Rarity.UNCOMMON))
        );
        OAK_BOAT = register("oak_boat",
                            new BoatItem(false,
                                         net.minecraft.entity.vehicle.BoatEntity.Type.OAK,
                                         (new Item.Settings()).maxCount(1)
                            )
        );
        OAK_CHEST_BOAT = register("oak_chest_boat",
                                  new BoatItem(true,
                                               net.minecraft.entity.vehicle.BoatEntity.Type.OAK,
                                               (new Item.Settings()).maxCount(1)
                                  )
        );
        SPRUCE_BOAT = register("spruce_boat",
                               new BoatItem(false,
                                            net.minecraft.entity.vehicle.BoatEntity.Type.SPRUCE,
                                            (new Item.Settings()).maxCount(1)
                               )
        );
        SPRUCE_CHEST_BOAT = register("spruce_chest_boat",
                                     new BoatItem(true,
                                                  net.minecraft.entity.vehicle.BoatEntity.Type.SPRUCE,
                                                  (new Item.Settings()).maxCount(1)
                                     )
        );
        BIRCH_BOAT = register("birch_boat",
                              new BoatItem(false,
                                           net.minecraft.entity.vehicle.BoatEntity.Type.BIRCH,
                                           (new Item.Settings()).maxCount(1)
                              )
        );
        BIRCH_CHEST_BOAT = register("birch_chest_boat",
                                    new BoatItem(true,
                                                 net.minecraft.entity.vehicle.BoatEntity.Type.BIRCH,
                                                 (new Item.Settings()).maxCount(1)
                                    )
        );
        JUNGLE_BOAT = register("jungle_boat",
                               new BoatItem(false,
                                            net.minecraft.entity.vehicle.BoatEntity.Type.JUNGLE,
                                            (new Item.Settings()).maxCount(1)
                               )
        );
        JUNGLE_CHEST_BOAT = register("jungle_chest_boat",
                                     new BoatItem(true,
                                                  net.minecraft.entity.vehicle.BoatEntity.Type.JUNGLE,
                                                  (new Item.Settings()).maxCount(1)
                                     )
        );
        ACACIA_BOAT = register("acacia_boat",
                               new BoatItem(false,
                                            net.minecraft.entity.vehicle.BoatEntity.Type.ACACIA,
                                            (new Item.Settings()).maxCount(1)
                               )
        );
        ACACIA_CHEST_BOAT = register("acacia_chest_boat",
                                     new BoatItem(true,
                                                  net.minecraft.entity.vehicle.BoatEntity.Type.ACACIA,
                                                  (new Item.Settings()).maxCount(1)
                                     )
        );
        CHERRY_BOAT = register("cherry_boat",
                               new BoatItem(false,
                                            net.minecraft.entity.vehicle.BoatEntity.Type.CHERRY,
                                            (new Item.Settings()).maxCount(1)
                               )
        );
        CHERRY_CHEST_BOAT = register("cherry_chest_boat",
                                     new BoatItem(true,
                                                  net.minecraft.entity.vehicle.BoatEntity.Type.CHERRY,
                                                  (new Item.Settings()).maxCount(1)
                                     )
        );
        DARK_OAK_BOAT = register("dark_oak_boat",
                                 new BoatItem(false,
                                              net.minecraft.entity.vehicle.BoatEntity.Type.DARK_OAK,
                                              (new Item.Settings()).maxCount(1)
                                 )
        );
        DARK_OAK_CHEST_BOAT = register("dark_oak_chest_boat",
                                       new BoatItem(true,
                                                    net.minecraft.entity.vehicle.BoatEntity.Type.DARK_OAK,
                                                    (new Item.Settings()).maxCount(1)
                                       )
        );
        MANGROVE_BOAT = register("mangrove_boat",
                                 new BoatItem(false,
                                              net.minecraft.entity.vehicle.BoatEntity.Type.MANGROVE,
                                              (new Item.Settings()).maxCount(1)
                                 )
        );
        MANGROVE_CHEST_BOAT = register("mangrove_chest_boat",
                                       new BoatItem(true,
                                                    net.minecraft.entity.vehicle.BoatEntity.Type.MANGROVE,
                                                    (new Item.Settings()).maxCount(1)
                                       )
        );
        BAMBOO_RAFT = register("bamboo_raft",
                               new BoatItem(false,
                                            net.minecraft.entity.vehicle.BoatEntity.Type.BAMBOO,
                                            (new Item.Settings()).maxCount(1)
                               )
        );
        BAMBOO_CHEST_RAFT = register("bamboo_chest_raft",
                                     new BoatItem(true,
                                                  net.minecraft.entity.vehicle.BoatEntity.Type.BAMBOO,
                                                  (new Item.Settings()).maxCount(1)
                                     )
        );
        STRUCTURE_BLOCK = register(new OperatorOnlyBlockItem(Blocks.STRUCTURE_BLOCK,
                                                             (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        JIGSAW = register(new OperatorOnlyBlockItem(Blocks.JIGSAW,
                                                    (new Item.Settings()).rarity(Rarity.EPIC)
        ));
        TURTLE_HELMET = register("turtle_helmet",
                                 new ArmorItem(ArmorMaterials.TURTLE,
                                               ArmorItem.Type.HELMET,
                                               new Item.Settings()
                                 )
        );
        SCUTE = register("scute",
                         new Item(new Item.Settings())
        );
        FLINT_AND_STEEL = register("flint_and_steel",
                                   new FlintAndSteelItem((new Item.Settings()).maxDamage(64))
        );
        APPLE = register("apple",
                         new Item((new Item.Settings()).food(FoodComponents.APPLE))
        );
        BOW = register("bow",
                       new BowItem((new Item.Settings()).maxDamage(384))
        );
        ARROW = register("arrow",
                         new ArrowItem(new Item.Settings())
        );
        COAL = register("coal",
                        new Item(new Item.Settings())
        );
        CHARCOAL = register("charcoal",
                            new Item(new Item.Settings())
        );
        DIAMOND = register("diamond",
                           new Item(new Item.Settings())
        );
        EMERALD = register("emerald",
                           new Item(new Item.Settings())
        );
        LAPIS_LAZULI = register("lapis_lazuli",
                                new Item(new Item.Settings())
        );
        QUARTZ = register("quartz",
                          new Item(new Item.Settings())
        );
        AMETHYST_SHARD = register("amethyst_shard",
                                  new Item(new Item.Settings())
        );
        RAW_IRON = register("raw_iron",
                            new Item(new Item.Settings())
        );
        IRON_INGOT = register("iron_ingot",
                              new Item(new Item.Settings())
        );
        RAW_COPPER = register("raw_copper",
                              new Item(new Item.Settings())
        );
        COPPER_INGOT = register("copper_ingot",
                                new Item(new Item.Settings())
        );
        RAW_GOLD = register("raw_gold",
                            new Item(new Item.Settings())
        );
        GOLD_INGOT = register("gold_ingot",
                              new Item(new Item.Settings())
        );
        NETHERITE_INGOT = register("netherite_ingot",
                                   new Item((new Item.Settings()).fireproof())
        );
        NETHERITE_SCRAP = register("netherite_scrap",
                                   new Item((new Item.Settings()).fireproof())
        );
        WOODEN_SWORD = register("wooden_sword",
                                new SwordItem(ToolMaterials.WOOD,
                                              3,
                                              - 2.4F,
                                              new Item.Settings()
                                )
        );
        WOODEN_SHOVEL = register("wooden_shovel",
                                 new ShovelItem(ToolMaterials.WOOD,
                                                1.5F,
                                                - 3.0F,
                                                new Item.Settings()
                                 )
        );
        WOODEN_PICKAXE = register("wooden_pickaxe",
                                  new PickaxeItem(ToolMaterials.WOOD,
                                                  1,
                                                  - 2.8F,
                                                  new Item.Settings()
                                  )
        );
        WOODEN_AXE = register("wooden_axe",
                              new AxeItem(ToolMaterials.WOOD,
                                          6.0F,
                                          - 3.2F,
                                          new Item.Settings()
                              )
        );
        WOODEN_HOE = register("wooden_hoe",
                              new HoeItem(ToolMaterials.WOOD,
                                          0,
                                          - 3.0F,
                                          new Item.Settings()
                              )
        );
        STONE_SWORD = register("stone_sword",
                               new SwordItem(ToolMaterials.STONE,
                                             3,
                                             - 2.4F,
                                             new Item.Settings()
                               )
        );
        STONE_SHOVEL = register("stone_shovel",
                                new ShovelItem(ToolMaterials.STONE,
                                               1.5F,
                                               - 3.0F,
                                               new Item.Settings()
                                )
        );
        STONE_PICKAXE = register("stone_pickaxe",
                                 new PickaxeItem(ToolMaterials.STONE,
                                                 1,
                                                 - 2.8F,
                                                 new Item.Settings()
                                 )
        );
        STONE_AXE = register("stone_axe",
                             new AxeItem(ToolMaterials.STONE,
                                         7.0F,
                                         - 3.2F,
                                         new Item.Settings()
                             )
        );
        STONE_HOE = register("stone_hoe",
                             new HoeItem(ToolMaterials.STONE,
                                         - 1,
                                         - 2.0F,
                                         new Item.Settings()
                             )
        );
        GOLDEN_SWORD = register("golden_sword",
                                new SwordItem(ToolMaterials.GOLD,
                                              3,
                                              - 2.4F,
                                              new Item.Settings()
                                )
        );
        GOLDEN_SHOVEL = register("golden_shovel",
                                 new ShovelItem(ToolMaterials.GOLD,
                                                1.5F,
                                                - 3.0F,
                                                new Item.Settings()
                                 )
        );
        GOLDEN_PICKAXE = register("golden_pickaxe",
                                  new PickaxeItem(ToolMaterials.GOLD,
                                                  1,
                                                  - 2.8F,
                                                  new Item.Settings()
                                  )
        );
        GOLDEN_AXE = register("golden_axe",
                              new AxeItem(ToolMaterials.GOLD,
                                          6.0F,
                                          - 3.0F,
                                          new Item.Settings()
                              )
        );
        GOLDEN_HOE = register("golden_hoe",
                              new HoeItem(ToolMaterials.GOLD,
                                          0,
                                          - 3.0F,
                                          new Item.Settings()
                              )
        );
        IRON_SWORD = register("iron_sword",
                              new SwordItem(ToolMaterials.IRON,
                                            3,
                                            - 2.4F,
                                            new Item.Settings()
                              )
        );
        IRON_SHOVEL = register("iron_shovel",
                               new ShovelItem(ToolMaterials.IRON,
                                              1.5F,
                                              - 3.0F,
                                              new Item.Settings()
                               )
        );
        IRON_PICKAXE = register("iron_pickaxe",
                                new PickaxeItem(ToolMaterials.IRON,
                                                1,
                                                - 2.8F,
                                                new Item.Settings()
                                )
        );
        IRON_AXE = register("iron_axe",
                            new AxeItem(ToolMaterials.IRON,
                                        6.0F,
                                        - 3.1F,
                                        new Item.Settings()
                            )
        );
        IRON_HOE = register("iron_hoe",
                            new HoeItem(ToolMaterials.IRON,
                                        - 2,
                                        - 1.0F,
                                        new Item.Settings()
                            )
        );
        DIAMOND_SWORD = register("diamond_sword",
                                 new SwordItem(ToolMaterials.DIAMOND,
                                               3,
                                               - 2.4F,
                                               new Item.Settings()
                                 )
        );
        DIAMOND_SHOVEL = register("diamond_shovel",
                                  new ShovelItem(ToolMaterials.DIAMOND,
                                                 1.5F,
                                                 - 3.0F,
                                                 new Item.Settings()
                                  )
        );
        DIAMOND_PICKAXE = register("diamond_pickaxe",
                                   new PickaxeItem(ToolMaterials.DIAMOND,
                                                   1,
                                                   - 2.8F,
                                                   new Item.Settings()
                                   )
        );
        DIAMOND_AXE = register("diamond_axe",
                               new AxeItem(ToolMaterials.DIAMOND,
                                           5.0F,
                                           - 3.0F,
                                           new Item.Settings()
                               )
        );
        DIAMOND_HOE = register("diamond_hoe",
                               new HoeItem(ToolMaterials.DIAMOND,
                                           - 3,
                                           0.0F,
                                           new Item.Settings()
                               )
        );
        NETHERITE_SWORD = register("netherite_sword",
                                   new SwordItem(ToolMaterials.NETHERITE,
                                                 3,
                                                 - 2.4F,
                                                 (new Item.Settings()).fireproof()
                                   )
        );
        NETHERITE_SHOVEL = register("netherite_shovel",
                                    new ShovelItem(ToolMaterials.NETHERITE,
                                                   1.5F,
                                                   - 3.0F,
                                                   (new Item.Settings()).fireproof()
                                    )
        );
        NETHERITE_PICKAXE = register("netherite_pickaxe",
                                     new PickaxeItem(ToolMaterials.NETHERITE,
                                                     1,
                                                     - 2.8F,
                                                     (new Item.Settings()).fireproof()
                                     )
        );
        NETHERITE_AXE = register("netherite_axe",
                                 new AxeItem(ToolMaterials.NETHERITE,
                                             5.0F,
                                             - 3.0F,
                                             (new Item.Settings()).fireproof()
                                 )
        );
        NETHERITE_HOE = register("netherite_hoe",
                                 new HoeItem(ToolMaterials.NETHERITE,
                                             - 4,
                                             0.0F,
                                             (new Item.Settings()).fireproof()
                                 )
        );
        BOWL = register("bowl",
                        new Item(new Item.Settings())
        );
        MUSHROOM_STEW = register("mushroom_stew",
                                 new StewItem((new Item.Settings()).maxCount(1)
                                                                   .food(FoodComponents.MUSHROOM_STEW))
        );
        STRING = register("string",
                          new AliasedBlockItem(Blocks.TRIPWIRE,
                                               new Item.Settings()
                          )
        );
        FEATHER = register("feather",
                           new Item(new Item.Settings())
        );
        GUNPOWDER = register("gunpowder",
                             new Item(new Item.Settings())
        );
        WHEAT_SEEDS = register("wheat_seeds",
                               new AliasedBlockItem(Blocks.WHEAT,
                                                    new Item.Settings()
                               )
        );
        WHEAT = register("wheat",
                         new Item(new Item.Settings())
        );
        BREAD = register("bread",
                         new Item((new Item.Settings()).food(FoodComponents.BREAD))
        );
        LEATHER_HELMET = register("leather_helmet",
                                  new DyeableArmorItem(ArmorMaterials.LEATHER,
                                                       ArmorItem.Type.HELMET,
                                                       new Item.Settings()
                                  )
        );
        LEATHER_CHESTPLATE = register("leather_chestplate",
                                      new DyeableArmorItem(ArmorMaterials.LEATHER,
                                                           ArmorItem.Type.CHESTPLATE,
                                                           new Item.Settings()
                                      )
        );
        LEATHER_LEGGINGS = register("leather_leggings",
                                    new DyeableArmorItem(ArmorMaterials.LEATHER,
                                                         ArmorItem.Type.LEGGINGS,
                                                         new Item.Settings()
                                    )
        );
        LEATHER_BOOTS = register("leather_boots",
                                 new DyeableArmorItem(ArmorMaterials.LEATHER,
                                                      ArmorItem.Type.BOOTS,
                                                      new Item.Settings()
                                 )
        );
        CHAINMAIL_HELMET = register("chainmail_helmet",
                                    new ArmorItem(ArmorMaterials.CHAIN,
                                                  ArmorItem.Type.HELMET,
                                                  new Item.Settings()
                                    )
        );
        CHAINMAIL_CHESTPLATE = register("chainmail_chestplate",
                                        new ArmorItem(ArmorMaterials.CHAIN,
                                                      ArmorItem.Type.CHESTPLATE,
                                                      new Item.Settings()
                                        )
        );
        CHAINMAIL_LEGGINGS = register("chainmail_leggings",
                                      new ArmorItem(ArmorMaterials.CHAIN,
                                                    ArmorItem.Type.LEGGINGS,
                                                    new Item.Settings()
                                      )
        );
        CHAINMAIL_BOOTS = register("chainmail_boots",
                                   new ArmorItem(ArmorMaterials.CHAIN,
                                                 ArmorItem.Type.BOOTS,
                                                 new Item.Settings()
                                   )
        );
        IRON_HELMET = register("iron_helmet",
                               new ArmorItem(ArmorMaterials.IRON,
                                             ArmorItem.Type.HELMET,
                                             new Item.Settings()
                               )
        );
        IRON_CHESTPLATE = register("iron_chestplate",
                                   new ArmorItem(ArmorMaterials.IRON,
                                                 ArmorItem.Type.CHESTPLATE,
                                                 new Item.Settings()
                                   )
        );
        IRON_LEGGINGS = register("iron_leggings",
                                 new ArmorItem(ArmorMaterials.IRON,
                                               ArmorItem.Type.LEGGINGS,
                                               new Item.Settings()
                                 )
        );
        IRON_BOOTS = register("iron_boots",
                              new ArmorItem(ArmorMaterials.IRON,
                                            ArmorItem.Type.BOOTS,
                                            new Item.Settings()
                              )
        );
        DIAMOND_HELMET = register("diamond_helmet",
                                  new ArmorItem(ArmorMaterials.DIAMOND,
                                                ArmorItem.Type.HELMET,
                                                new Item.Settings()
                                  )
        );
        DIAMOND_CHESTPLATE = register("diamond_chestplate",
                                      new ArmorItem(ArmorMaterials.DIAMOND,
                                                    ArmorItem.Type.CHESTPLATE,
                                                    new Item.Settings()
                                      )
        );
        DIAMOND_LEGGINGS = register("diamond_leggings",
                                    new ArmorItem(ArmorMaterials.DIAMOND,
                                                  ArmorItem.Type.LEGGINGS,
                                                  new Item.Settings()
                                    )
        );
        DIAMOND_BOOTS = register("diamond_boots",
                                 new ArmorItem(ArmorMaterials.DIAMOND,
                                               ArmorItem.Type.BOOTS,
                                               new Item.Settings()
                                 )
        );
        GOLDEN_HELMET = register("golden_helmet",
                                 new ArmorItem(ArmorMaterials.GOLD,
                                               ArmorItem.Type.HELMET,
                                               new Item.Settings()
                                 )
        );
        GOLDEN_CHESTPLATE = register("golden_chestplate",
                                     new ArmorItem(ArmorMaterials.GOLD,
                                                   ArmorItem.Type.CHESTPLATE,
                                                   new Item.Settings()
                                     )
        );
        GOLDEN_LEGGINGS = register("golden_leggings",
                                   new ArmorItem(ArmorMaterials.GOLD,
                                                 ArmorItem.Type.LEGGINGS,
                                                 new Item.Settings()
                                   )
        );
        GOLDEN_BOOTS = register("golden_boots",
                                new ArmorItem(ArmorMaterials.GOLD,
                                              ArmorItem.Type.BOOTS,
                                              new Item.Settings()
                                )
        );
        NETHERITE_HELMET = register("netherite_helmet",
                                    new ArmorItem(ArmorMaterials.NETHERITE,
                                                  ArmorItem.Type.HELMET,
                                                  (new Item.Settings()).fireproof()
                                    )
        );
        NETHERITE_CHESTPLATE = register("netherite_chestplate",
                                        new ArmorItem(ArmorMaterials.NETHERITE,
                                                      ArmorItem.Type.CHESTPLATE,
                                                      (new Item.Settings()).fireproof()
                                        )
        );
        NETHERITE_LEGGINGS = register("netherite_leggings",
                                      new ArmorItem(ArmorMaterials.NETHERITE,
                                                    ArmorItem.Type.LEGGINGS,
                                                    (new Item.Settings()).fireproof()
                                      )
        );
        NETHERITE_BOOTS = register("netherite_boots",
                                   new ArmorItem(ArmorMaterials.NETHERITE,
                                                 ArmorItem.Type.BOOTS,
                                                 (new Item.Settings()).fireproof()
                                   )
        );
        PORKCHOP = register("porkchop",
                            new Item((new Item.Settings()).food(FoodComponents.PORKCHOP))
        );
        COOKED_PORKCHOP = register("cooked_porkchop",
                                   new Item((new Item.Settings()).food(FoodComponents.COOKED_PORKCHOP))
        );
        PAINTING = register("painting",
                            new DecorationItem(EntityType.PAINTING,
                                               new Item.Settings()
                            )
        );
        GOLDEN_APPLE = register("golden_apple",
                                new Item((new Item.Settings()).rarity(Rarity.RARE)
                                                              .food(FoodComponents.GOLDEN_APPLE))
        );
        ENCHANTED_GOLDEN_APPLE = register("enchanted_golden_apple",
                                          new EnchantedGoldenAppleItem((new Item.Settings()).rarity(Rarity.EPIC)
                                                                                            .food(FoodComponents.ENCHANTED_GOLDEN_APPLE))
        );
        OAK_SIGN = register("oak_sign",
                            new SignItem((new Item.Settings()).maxCount(16),
                                         Blocks.OAK_SIGN,
                                         Blocks.OAK_WALL_SIGN
                            )
        );
        SPRUCE_SIGN = register("spruce_sign",
                               new SignItem((new Item.Settings()).maxCount(16),
                                            Blocks.SPRUCE_SIGN,
                                            Blocks.SPRUCE_WALL_SIGN
                               )
        );
        BIRCH_SIGN = register("birch_sign",
                              new SignItem((new Item.Settings()).maxCount(16),
                                           Blocks.BIRCH_SIGN,
                                           Blocks.BIRCH_WALL_SIGN
                              )
        );
        JUNGLE_SIGN = register("jungle_sign",
                               new SignItem((new Item.Settings()).maxCount(16),
                                            Blocks.JUNGLE_SIGN,
                                            Blocks.JUNGLE_WALL_SIGN
                               )
        );
        ACACIA_SIGN = register("acacia_sign",
                               new SignItem((new Item.Settings()).maxCount(16),
                                            Blocks.ACACIA_SIGN,
                                            Blocks.ACACIA_WALL_SIGN
                               )
        );
        CHERRY_SIGN = register("cherry_sign",
                               new SignItem((new Item.Settings()).maxCount(16),
                                            Blocks.CHERRY_SIGN,
                                            Blocks.CHERRY_WALL_SIGN
                               )
        );
        DARK_OAK_SIGN = register("dark_oak_sign",
                                 new SignItem((new Item.Settings()).maxCount(16),
                                              Blocks.DARK_OAK_SIGN,
                                              Blocks.DARK_OAK_WALL_SIGN
                                 )
        );
        MANGROVE_SIGN = register("mangrove_sign",
                                 new SignItem((new Item.Settings()).maxCount(16),
                                              Blocks.MANGROVE_SIGN,
                                              Blocks.MANGROVE_WALL_SIGN
                                 )
        );
        BAMBOO_SIGN = register("bamboo_sign",
                               new SignItem((new Item.Settings()).maxCount(16),
                                            Blocks.BAMBOO_SIGN,
                                            Blocks.BAMBOO_WALL_SIGN
                               )
        );
        CRIMSON_SIGN = register("crimson_sign",
                                new SignItem((new Item.Settings()).maxCount(16),
                                             Blocks.CRIMSON_SIGN,
                                             Blocks.CRIMSON_WALL_SIGN
                                )
        );
        WARPED_SIGN = register("warped_sign",
                               new SignItem((new Item.Settings()).maxCount(16),
                                            Blocks.WARPED_SIGN,
                                            Blocks.WARPED_WALL_SIGN
                               )
        );
        OAK_HANGING_SIGN = register("oak_hanging_sign",
                                    new HangingSignItem(Blocks.OAK_HANGING_SIGN,
                                                        Blocks.OAK_WALL_HANGING_SIGN,
                                                        (new Item.Settings()).maxCount(16)
                                    )
        );
        SPRUCE_HANGING_SIGN = register("spruce_hanging_sign",
                                       new HangingSignItem(Blocks.SPRUCE_HANGING_SIGN,
                                                           Blocks.SPRUCE_WALL_HANGING_SIGN,
                                                           (new Item.Settings()).maxCount(16)
                                       )
        );
        BIRCH_HANGING_SIGN = register("birch_hanging_sign",
                                      new HangingSignItem(Blocks.BIRCH_HANGING_SIGN,
                                                          Blocks.BIRCH_WALL_HANGING_SIGN,
                                                          (new Item.Settings()).maxCount(16)
                                      )
        );
        JUNGLE_HANGING_SIGN = register("jungle_hanging_sign",
                                       new HangingSignItem(Blocks.JUNGLE_HANGING_SIGN,
                                                           Blocks.JUNGLE_WALL_HANGING_SIGN,
                                                           (new Item.Settings()).maxCount(16)
                                       )
        );
        ACACIA_HANGING_SIGN = register("acacia_hanging_sign",
                                       new HangingSignItem(Blocks.ACACIA_HANGING_SIGN,
                                                           Blocks.ACACIA_WALL_HANGING_SIGN,
                                                           (new Item.Settings()).maxCount(16)
                                       )
        );
        CHERRY_HANGING_SIGN = register("cherry_hanging_sign",
                                       new HangingSignItem(Blocks.CHERRY_HANGING_SIGN,
                                                           Blocks.CHERRY_WALL_HANGING_SIGN,
                                                           (new Item.Settings()).maxCount(16)
                                       )
        );
        DARK_OAK_HANGING_SIGN = register("dark_oak_hanging_sign",
                                         new HangingSignItem(Blocks.DARK_OAK_HANGING_SIGN,
                                                             Blocks.DARK_OAK_WALL_HANGING_SIGN,
                                                             (new Item.Settings()).maxCount(16)
                                         )
        );
        MANGROVE_HANGING_SIGN = register("mangrove_hanging_sign",
                                         new HangingSignItem(Blocks.MANGROVE_HANGING_SIGN,
                                                             Blocks.MANGROVE_WALL_HANGING_SIGN,
                                                             (new Item.Settings()).maxCount(16)
                                         )
        );
        BAMBOO_HANGING_SIGN = register("bamboo_hanging_sign",
                                       new HangingSignItem(Blocks.BAMBOO_HANGING_SIGN,
                                                           Blocks.BAMBOO_WALL_HANGING_SIGN,
                                                           (new Item.Settings()).maxCount(16)
                                       )
        );
        CRIMSON_HANGING_SIGN = register("crimson_hanging_sign",
                                        new HangingSignItem(Blocks.CRIMSON_HANGING_SIGN,
                                                            Blocks.CRIMSON_WALL_HANGING_SIGN,
                                                            (new Item.Settings()).maxCount(16)
                                        )
        );
        WARPED_HANGING_SIGN = register("warped_hanging_sign",
                                       new HangingSignItem(Blocks.WARPED_HANGING_SIGN,
                                                           Blocks.WARPED_WALL_HANGING_SIGN,
                                                           (new Item.Settings()).maxCount(16)
                                       )
        );
        BUCKET = register("bucket",
                          new BucketItem(Fluids.EMPTY,
                                         (new Item.Settings()).maxCount(16)
                          )
        );
        WATER_BUCKET = register("water_bucket",
                                new BucketItem(Fluids.WATER,
                                               (new Item.Settings()).recipeRemainder(BUCKET)
                                                                    .maxCount(1)
                                )
        );
        LAVA_BUCKET = register("lava_bucket",
                               new BucketItem(Fluids.LAVA,
                                              (new Item.Settings()).recipeRemainder(BUCKET)
                                                                   .maxCount(1)
                               )
        );
        POWDER_SNOW_BUCKET = register("powder_snow_bucket",
                                      new PowderSnowBucketItem(Blocks.POWDER_SNOW,
                                                               SoundEvents.ITEM_BUCKET_EMPTY_POWDER_SNOW,
                                                               (new Item.Settings()).maxCount(1)
                                      )
        );
        SNOWBALL = register("snowball",
                            new SnowballItem((new Item.Settings()).maxCount(16))
        );
        LEATHER = register("leather",
                           new Item(new Item.Settings())
        );
        MILK_BUCKET = register("milk_bucket",
                               new MilkBucketItem((new Item.Settings()).recipeRemainder(BUCKET)
                                                                       .maxCount(1))
        );
        PUFFERFISH_BUCKET = register("pufferfish_bucket",
                                     new EntityBucketItem(EntityType.PUFFERFISH,
                                                          Fluids.WATER,
                                                          SoundEvents.ITEM_BUCKET_EMPTY_FISH,
                                                          (new Item.Settings()).maxCount(1)
                                     )
        );
        SALMON_BUCKET = register("salmon_bucket",
                                 new EntityBucketItem(EntityType.SALMON,
                                                      Fluids.WATER,
                                                      SoundEvents.ITEM_BUCKET_EMPTY_FISH,
                                                      (new Item.Settings()).maxCount(1)
                                 )
        );
        COD_BUCKET = register("cod_bucket",
                              new EntityBucketItem(EntityType.COD,
                                                   Fluids.WATER,
                                                   SoundEvents.ITEM_BUCKET_EMPTY_FISH,
                                                   (new Item.Settings()).maxCount(1)
                              )
        );
        TROPICAL_FISH_BUCKET = register("tropical_fish_bucket",
                                        new EntityBucketItem(EntityType.TROPICAL_FISH,
                                                             Fluids.WATER,
                                                             SoundEvents.ITEM_BUCKET_EMPTY_FISH,
                                                             (new Item.Settings()).maxCount(1)
                                        )
        );
        AXOLOTL_BUCKET = register("axolotl_bucket",
                                  new EntityBucketItem(EntityType.AXOLOTL,
                                                       Fluids.WATER,
                                                       SoundEvents.ITEM_BUCKET_EMPTY_AXOLOTL,
                                                       (new Item.Settings()).maxCount(1)
                                  )
        );
        TADPOLE_BUCKET = register("tadpole_bucket",
                                  new EntityBucketItem(EntityType.TADPOLE,
                                                       Fluids.WATER,
                                                       SoundEvents.ITEM_BUCKET_EMPTY_TADPOLE,
                                                       (new Item.Settings()).maxCount(1)
                                  )
        );
        BRICK = register("brick",
                         new Item(new Item.Settings())
        );
        CLAY_BALL = register("clay_ball",
                             new Item(new Item.Settings())
        );
        DRIED_KELP_BLOCK = register(Blocks.DRIED_KELP_BLOCK);
        PAPER = register("paper",
                         new Item(new Item.Settings())
        );
        BOOK = register("book",
                        new BookItem(new Item.Settings())
        );
        SLIME_BALL = register("slime_ball",
                              new Item(new Item.Settings())
        );
        EGG = register("egg",
                       new EggItem((new Item.Settings()).maxCount(16))
        );
        COMPASS = register("compass",
                           new CompassItem(new Item.Settings())
        );
        RECOVERY_COMPASS = register("recovery_compass",
                                    new Item(new Item.Settings())
        );
        BUNDLE = register("bundle",
                          new BundleItem((new Item.Settings()).maxCount(1))
        );
        FISHING_ROD = register("fishing_rod",
                               new FishingRodItem((new Item.Settings()).maxDamage(64))
        );
        CLOCK = register("clock",
                         new Item(new Item.Settings())
        );
        SPYGLASS = register("spyglass",
                            new SpyglassItem((new Item.Settings()).maxCount(1))
        );
        GLOWSTONE_DUST = register("glowstone_dust",
                                  new Item(new Item.Settings())
        );
        COD = register("cod",
                       new Item((new Item.Settings()).food(FoodComponents.COD))
        );
        SALMON = register("salmon",
                          new Item((new Item.Settings()).food(FoodComponents.SALMON))
        );
        TROPICAL_FISH = register("tropical_fish",
                                 new Item((new Item.Settings()).food(FoodComponents.TROPICAL_FISH))
        );
        PUFFERFISH = register("pufferfish",
                              new Item((new Item.Settings()).food(FoodComponents.PUFFERFISH))
        );
        COOKED_COD = register("cooked_cod",
                              new Item((new Item.Settings()).food(FoodComponents.COOKED_COD))
        );
        COOKED_SALMON = register("cooked_salmon",
                                 new Item((new Item.Settings()).food(FoodComponents.COOKED_SALMON))
        );
        INK_SAC = register("ink_sac",
                           new InkSacItem(new Item.Settings())
        );
        GLOW_INK_SAC = register("glow_ink_sac",
                                new GlowInkSacItem(new Item.Settings())
        );
        COCOA_BEANS = register("cocoa_beans",
                               new AliasedBlockItem(Blocks.COCOA,
                                                    new Item.Settings()
                               )
        );
        WHITE_DYE = register("white_dye",
                             new DyeItem(DyeColor.WHITE,
                                         new Item.Settings()
                             )
        );
        ORANGE_DYE = register("orange_dye",
                              new DyeItem(DyeColor.ORANGE,
                                          new Item.Settings()
                              )
        );
        MAGENTA_DYE = register("magenta_dye",
                               new DyeItem(DyeColor.MAGENTA,
                                           new Item.Settings()
                               )
        );
        LIGHT_BLUE_DYE = register("light_blue_dye",
                                  new DyeItem(DyeColor.LIGHT_BLUE,
                                              new Item.Settings()
                                  )
        );
        YELLOW_DYE = register("yellow_dye",
                              new DyeItem(DyeColor.YELLOW,
                                          new Item.Settings()
                              )
        );
        LIME_DYE = register("lime_dye",
                            new DyeItem(DyeColor.LIME,
                                        new Item.Settings()
                            )
        );
        PINK_DYE = register("pink_dye",
                            new DyeItem(DyeColor.PINK,
                                        new Item.Settings()
                            )
        );
        GRAY_DYE = register("gray_dye",
                            new DyeItem(DyeColor.GRAY,
                                        new Item.Settings()
                            )
        );
        LIGHT_GRAY_DYE = register("light_gray_dye",
                                  new DyeItem(DyeColor.LIGHT_GRAY,
                                              new Item.Settings()
                                  )
        );
        CYAN_DYE = register("cyan_dye",
                            new DyeItem(DyeColor.CYAN,
                                        new Item.Settings()
                            )
        );
        PURPLE_DYE = register("purple_dye",
                              new DyeItem(DyeColor.PURPLE,
                                          new Item.Settings()
                              )
        );
        BLUE_DYE = register("blue_dye",
                            new DyeItem(DyeColor.BLUE,
                                        new Item.Settings()
                            )
        );
        BROWN_DYE = register("brown_dye",
                             new DyeItem(DyeColor.BROWN,
                                         new Item.Settings()
                             )
        );
        GREEN_DYE = register("green_dye",
                             new DyeItem(DyeColor.GREEN,
                                         new Item.Settings()
                             )
        );
        RED_DYE = register("red_dye",
                           new DyeItem(DyeColor.RED,
                                       new Item.Settings()
                           )
        );
        BLACK_DYE = register("black_dye",
                             new DyeItem(DyeColor.BLACK,
                                         new Item.Settings()
                             )
        );
        BONE_MEAL = register("bone_meal",
                             new BoneMealItem(new Item.Settings())
        );
        BONE = register("bone",
                        new Item(new Item.Settings())
        );
        SUGAR = register("sugar",
                         new Item(new Item.Settings())
        );
        CAKE = register(new BlockItem(Blocks.CAKE,
                                      (new Item.Settings()).maxCount(1)
        ));
        WHITE_BED = register(new BedItem(Blocks.WHITE_BED,
                                         (new Item.Settings()).maxCount(1)
        ));
        ORANGE_BED = register(new BedItem(Blocks.ORANGE_BED,
                                          (new Item.Settings()).maxCount(1)
        ));
        MAGENTA_BED = register(new BedItem(Blocks.MAGENTA_BED,
                                           (new Item.Settings()).maxCount(1)
        ));
        LIGHT_BLUE_BED = register(new BedItem(Blocks.LIGHT_BLUE_BED,
                                              (new Item.Settings()).maxCount(1)
        ));
        YELLOW_BED = register(new BedItem(Blocks.YELLOW_BED,
                                          (new Item.Settings()).maxCount(1)
        ));
        LIME_BED = register(new BedItem(Blocks.LIME_BED,
                                        (new Item.Settings()).maxCount(1)
        ));
        PINK_BED = register(new BedItem(Blocks.PINK_BED,
                                        (new Item.Settings()).maxCount(1)
        ));
        GRAY_BED = register(new BedItem(Blocks.GRAY_BED,
                                        (new Item.Settings()).maxCount(1)
        ));
        LIGHT_GRAY_BED = register(new BedItem(Blocks.LIGHT_GRAY_BED,
                                              (new Item.Settings()).maxCount(1)
        ));
        CYAN_BED = register(new BedItem(Blocks.CYAN_BED,
                                        (new Item.Settings()).maxCount(1)
        ));
        PURPLE_BED = register(new BedItem(Blocks.PURPLE_BED,
                                          (new Item.Settings()).maxCount(1)
        ));
        BLUE_BED = register(new BedItem(Blocks.BLUE_BED,
                                        (new Item.Settings()).maxCount(1)
        ));
        BROWN_BED = register(new BedItem(Blocks.BROWN_BED,
                                         (new Item.Settings()).maxCount(1)
        ));
        GREEN_BED = register(new BedItem(Blocks.GREEN_BED,
                                         (new Item.Settings()).maxCount(1)
        ));
        RED_BED = register(new BedItem(Blocks.RED_BED,
                                       (new Item.Settings()).maxCount(1)
        ));
        BLACK_BED = register(new BedItem(Blocks.BLACK_BED,
                                         (new Item.Settings()).maxCount(1)
        ));
        COOKIE = register("cookie",
                          new Item((new Item.Settings()).food(FoodComponents.COOKIE))
        );
        FILLED_MAP = register("filled_map",
                              new FilledMapItem(new Item.Settings())
        );
        SHEARS = register("shears",
                          new ShearsItem((new Item.Settings()).maxDamage(238))
        );
        MELON_SLICE = register("melon_slice",
                               new Item((new Item.Settings()).food(FoodComponents.MELON_SLICE))
        );
        DRIED_KELP = register("dried_kelp",
                              new Item((new Item.Settings()).food(FoodComponents.DRIED_KELP))
        );
        PUMPKIN_SEEDS = register("pumpkin_seeds",
                                 new AliasedBlockItem(Blocks.PUMPKIN_STEM,
                                                      new Item.Settings()
                                 )
        );
        MELON_SEEDS = register("melon_seeds",
                               new AliasedBlockItem(Blocks.MELON_STEM,
                                                    new Item.Settings()
                               )
        );
        BEEF = register("beef",
                        new Item((new Item.Settings()).food(FoodComponents.BEEF))
        );
        COOKED_BEEF = register("cooked_beef",
                               new Item((new Item.Settings()).food(FoodComponents.COOKED_BEEF))
        );
        CHICKEN = register("chicken",
                           new Item((new Item.Settings()).food(FoodComponents.CHICKEN))
        );
        COOKED_CHICKEN = register("cooked_chicken",
                                  new Item((new Item.Settings()).food(FoodComponents.COOKED_CHICKEN))
        );
        ROTTEN_FLESH = register("rotten_flesh",
                                new Item((new Item.Settings()).food(FoodComponents.ROTTEN_FLESH))
        );
        ENDER_PEARL = register("ender_pearl",
                               new EnderPearlItem((new Item.Settings()).maxCount(16))
        );
        BLAZE_ROD = register("blaze_rod",
                             new Item(new Item.Settings())
        );
        GHAST_TEAR = register("ghast_tear",
                              new Item(new Item.Settings())
        );
        GOLD_NUGGET = register("gold_nugget",
                               new Item(new Item.Settings())
        );
        NETHER_WART = register("nether_wart",
                               new AliasedBlockItem(Blocks.NETHER_WART,
                                                    new Item.Settings()
                               )
        );
        POTION = register("potion",
                          new PotionItem((new Item.Settings()).maxCount(1))
        );
        GLASS_BOTTLE = register("glass_bottle",
                                new GlassBottleItem(new Item.Settings())
        );
        SPIDER_EYE = register("spider_eye",
                              new Item((new Item.Settings()).food(FoodComponents.SPIDER_EYE))
        );
        FERMENTED_SPIDER_EYE = register("fermented_spider_eye",
                                        new Item(new Item.Settings())
        );
        BLAZE_POWDER = register("blaze_powder",
                                new Item(new Item.Settings())
        );
        MAGMA_CREAM = register("magma_cream",
                               new Item(new Item.Settings())
        );
        BREWING_STAND = register(Blocks.BREWING_STAND);
        CAULDRON = register(Blocks.CAULDRON,
                            Blocks.WATER_CAULDRON,
                            Blocks.LAVA_CAULDRON,
                            Blocks.POWDER_SNOW_CAULDRON
        );
        ENDER_EYE = register("ender_eye",
                             new EnderEyeItem(new Item.Settings())
        );
        GLISTERING_MELON_SLICE = register("glistering_melon_slice",
                                          new Item(new Item.Settings())
        );
        ALLAY_SPAWN_EGG = register("allay_spawn_egg",
                                   new SpawnEggItem(EntityType.ALLAY,
                                                    56063,
                                                    44543,
                                                    new Item.Settings()
                                   )
        );
        AXOLOTL_SPAWN_EGG = register("axolotl_spawn_egg",
                                     new SpawnEggItem(EntityType.AXOLOTL,
                                                      16499171,
                                                      10890612,
                                                      new Item.Settings()
                                     )
        );
        BAT_SPAWN_EGG = register("bat_spawn_egg",
                                 new SpawnEggItem(EntityType.BAT,
                                                  4996656,
                                                  986895,
                                                  new Item.Settings()
                                 )
        );
        BEE_SPAWN_EGG = register("bee_spawn_egg",
                                 new SpawnEggItem(EntityType.BEE,
                                                  15582019,
                                                  4400155,
                                                  new Item.Settings()
                                 )
        );
        BLAZE_SPAWN_EGG = register("blaze_spawn_egg",
                                   new SpawnEggItem(EntityType.BLAZE,
                                                    16167425,
                                                    16775294,
                                                    new Item.Settings()
                                   )
        );
        CAT_SPAWN_EGG = register("cat_spawn_egg",
                                 new SpawnEggItem(EntityType.CAT,
                                                  15714446,
                                                  9794134,
                                                  new Item.Settings()
                                 )
        );
        CAMEL_SPAWN_EGG = register("camel_spawn_egg",
                                   new SpawnEggItem(EntityType.CAMEL,
                                                    16565097,
                                                    13341495,
                                                    new Item.Settings()
                                   )
        );
        CAVE_SPIDER_SPAWN_EGG = register("cave_spider_spawn_egg",
                                         new SpawnEggItem(EntityType.CAVE_SPIDER,
                                                          803406,
                                                          11013646,
                                                          new Item.Settings()
                                         )
        );
        CHICKEN_SPAWN_EGG = register("chicken_spawn_egg",
                                     new SpawnEggItem(EntityType.CHICKEN,
                                                      10592673,
                                                      16711680,
                                                      new Item.Settings()
                                     )
        );
        COD_SPAWN_EGG = register("cod_spawn_egg",
                                 new SpawnEggItem(EntityType.COD,
                                                  12691306,
                                                  15058059,
                                                  new Item.Settings()
                                 )
        );
        COW_SPAWN_EGG = register("cow_spawn_egg",
                                 new SpawnEggItem(EntityType.COW,
                                                  4470310,
                                                  10592673,
                                                  new Item.Settings()
                                 )
        );
        CREEPER_SPAWN_EGG = register("creeper_spawn_egg",
                                     new SpawnEggItem(EntityType.CREEPER,
                                                      894731,
                                                      0,
                                                      new Item.Settings()
                                     )
        );
        DOLPHIN_SPAWN_EGG = register("dolphin_spawn_egg",
                                     new SpawnEggItem(EntityType.DOLPHIN,
                                                      2243405,
                                                      16382457,
                                                      new Item.Settings()
                                     )
        );
        DONKEY_SPAWN_EGG = register("donkey_spawn_egg",
                                    new SpawnEggItem(EntityType.DONKEY,
                                                     5457209,
                                                     8811878,
                                                     new Item.Settings()
                                    )
        );
        DROWNED_SPAWN_EGG = register("drowned_spawn_egg",
                                     new SpawnEggItem(EntityType.DROWNED,
                                                      9433559,
                                                      7969893,
                                                      new Item.Settings()
                                     )
        );
        ELDER_GUARDIAN_SPAWN_EGG = register("elder_guardian_spawn_egg",
                                            new SpawnEggItem(EntityType.ELDER_GUARDIAN,
                                                             13552826,
                                                             7632531,
                                                             new Item.Settings()
                                            )
        );
        ENDER_DRAGON_SPAWN_EGG = register("ender_dragon_spawn_egg",
                                          new SpawnEggItem(EntityType.ENDER_DRAGON,
                                                           1842204,
                                                           14711290,
                                                           new Item.Settings()
                                          )
        );
        ENDERMAN_SPAWN_EGG = register("enderman_spawn_egg",
                                      new SpawnEggItem(EntityType.ENDERMAN,
                                                       1447446,
                                                       0,
                                                       new Item.Settings()
                                      )
        );
        ENDERMITE_SPAWN_EGG = register("endermite_spawn_egg",
                                       new SpawnEggItem(EntityType.ENDERMITE,
                                                        1447446,
                                                        7237230,
                                                        new Item.Settings()
                                       )
        );
        EVOKER_SPAWN_EGG = register("evoker_spawn_egg",
                                    new SpawnEggItem(EntityType.EVOKER,
                                                     9804699,
                                                     1973274,
                                                     new Item.Settings()
                                    )
        );
        FOX_SPAWN_EGG = register("fox_spawn_egg",
                                 new SpawnEggItem(EntityType.FOX,
                                                  14005919,
                                                  13396256,
                                                  new Item.Settings()
                                 )
        );
        FROG_SPAWN_EGG = register("frog_spawn_egg",
                                  new SpawnEggItem(EntityType.FROG,
                                                   13661252,
                                                   16762748,
                                                   new Item.Settings()
                                  )
        );
        GHAST_SPAWN_EGG = register("ghast_spawn_egg",
                                   new SpawnEggItem(EntityType.GHAST,
                                                    16382457,
                                                    12369084,
                                                    new Item.Settings()
                                   )
        );
        GLOW_SQUID_SPAWN_EGG = register("glow_squid_spawn_egg",
                                        new SpawnEggItem(EntityType.GLOW_SQUID,
                                                         611926,
                                                         8778172,
                                                         new Item.Settings()
                                        )
        );
        GOAT_SPAWN_EGG = register("goat_spawn_egg",
                                  new SpawnEggItem(EntityType.GOAT,
                                                   10851452,
                                                   5589310,
                                                   new Item.Settings()
                                  )
        );
        GUARDIAN_SPAWN_EGG = register("guardian_spawn_egg",
                                      new SpawnEggItem(EntityType.GUARDIAN,
                                                       5931634,
                                                       15826224,
                                                       new Item.Settings()
                                      )
        );
        HOGLIN_SPAWN_EGG = register("hoglin_spawn_egg",
                                    new SpawnEggItem(EntityType.HOGLIN,
                                                     13004373,
                                                     6251620,
                                                     new Item.Settings()
                                    )
        );
        HORSE_SPAWN_EGG = register("horse_spawn_egg",
                                   new SpawnEggItem(EntityType.HORSE,
                                                    12623485,
                                                    15656192,
                                                    new Item.Settings()
                                   )
        );
        HUSK_SPAWN_EGG = register("husk_spawn_egg",
                                  new SpawnEggItem(EntityType.HUSK,
                                                   7958625,
                                                   15125652,
                                                   new Item.Settings()
                                  )
        );
        IRON_GOLEM_SPAWN_EGG = register("iron_golem_spawn_egg",
                                        new SpawnEggItem(EntityType.IRON_GOLEM,
                                                         14405058,
                                                         7643954,
                                                         new Item.Settings()
                                        )
        );
        LLAMA_SPAWN_EGG = register("llama_spawn_egg",
                                   new SpawnEggItem(EntityType.LLAMA,
                                                    12623485,
                                                    10051392,
                                                    new Item.Settings()
                                   )
        );
        MAGMA_CUBE_SPAWN_EGG = register("magma_cube_spawn_egg",
                                        new SpawnEggItem(EntityType.MAGMA_CUBE,
                                                         3407872,
                                                         16579584,
                                                         new Item.Settings()
                                        )
        );
        MOOSHROOM_SPAWN_EGG = register("mooshroom_spawn_egg",
                                       new SpawnEggItem(EntityType.MOOSHROOM,
                                                        10489616,
                                                        12040119,
                                                        new Item.Settings()
                                       )
        );
        MULE_SPAWN_EGG = register("mule_spawn_egg",
                                  new SpawnEggItem(EntityType.MULE,
                                                   1769984,
                                                   5321501,
                                                   new Item.Settings()
                                  )
        );
        OCELOT_SPAWN_EGG = register("ocelot_spawn_egg",
                                    new SpawnEggItem(EntityType.OCELOT,
                                                     15720061,
                                                     5653556,
                                                     new Item.Settings()
                                    )
        );
        PANDA_SPAWN_EGG = register("panda_spawn_egg",
                                   new SpawnEggItem(EntityType.PANDA,
                                                    15198183,
                                                    1776418,
                                                    new Item.Settings()
                                   )
        );
        PARROT_SPAWN_EGG = register("parrot_spawn_egg",
                                    new SpawnEggItem(EntityType.PARROT,
                                                     894731,
                                                     16711680,
                                                     new Item.Settings()
                                    )
        );
        PHANTOM_SPAWN_EGG = register("phantom_spawn_egg",
                                     new SpawnEggItem(EntityType.PHANTOM,
                                                      4411786,
                                                      8978176,
                                                      new Item.Settings()
                                     )
        );
        PIG_SPAWN_EGG = register("pig_spawn_egg",
                                 new SpawnEggItem(EntityType.PIG,
                                                  15771042,
                                                  14377823,
                                                  new Item.Settings()
                                 )
        );
        PIGLIN_SPAWN_EGG = register("piglin_spawn_egg",
                                    new SpawnEggItem(EntityType.PIGLIN,
                                                     10051392,
                                                     16380836,
                                                     new Item.Settings()
                                    )
        );
        PIGLIN_BRUTE_SPAWN_EGG = register("piglin_brute_spawn_egg",
                                          new SpawnEggItem(EntityType.PIGLIN_BRUTE,
                                                           5843472,
                                                           16380836,
                                                           new Item.Settings()
                                          )
        );
        PILLAGER_SPAWN_EGG = register("pillager_spawn_egg",
                                      new SpawnEggItem(EntityType.PILLAGER,
                                                       5451574,
                                                       9804699,
                                                       new Item.Settings()
                                      )
        );
        POLAR_BEAR_SPAWN_EGG = register("polar_bear_spawn_egg",
                                        new SpawnEggItem(EntityType.POLAR_BEAR,
                                                         15658718,
                                                         14014157,
                                                         new Item.Settings()
                                        )
        );
        PUFFERFISH_SPAWN_EGG = register("pufferfish_spawn_egg",
                                        new SpawnEggItem(EntityType.PUFFERFISH,
                                                         16167425,
                                                         3654642,
                                                         new Item.Settings()
                                        )
        );
        RABBIT_SPAWN_EGG = register("rabbit_spawn_egg",
                                    new SpawnEggItem(EntityType.RABBIT,
                                                     10051392,
                                                     7555121,
                                                     new Item.Settings()
                                    )
        );
        RAVAGER_SPAWN_EGG = register("ravager_spawn_egg",
                                     new SpawnEggItem(EntityType.RAVAGER,
                                                      7697520,
                                                      5984329,
                                                      new Item.Settings()
                                     )
        );
        SALMON_SPAWN_EGG = register("salmon_spawn_egg",
                                    new SpawnEggItem(EntityType.SALMON,
                                                     10489616,
                                                     951412,
                                                     new Item.Settings()
                                    )
        );
        SHEEP_SPAWN_EGG = register("sheep_spawn_egg",
                                   new SpawnEggItem(EntityType.SHEEP,
                                                    15198183,
                                                    16758197,
                                                    new Item.Settings()
                                   )
        );
        SHULKER_SPAWN_EGG = register("shulker_spawn_egg",
                                     new SpawnEggItem(EntityType.SHULKER,
                                                      9725844,
                                                      5060690,
                                                      new Item.Settings()
                                     )
        );
        SILVERFISH_SPAWN_EGG = register("silverfish_spawn_egg",
                                        new SpawnEggItem(EntityType.SILVERFISH,
                                                         7237230,
                                                         3158064,
                                                         new Item.Settings()
                                        )
        );
        SKELETON_SPAWN_EGG = register("skeleton_spawn_egg",
                                      new SpawnEggItem(EntityType.SKELETON,
                                                       12698049,
                                                       4802889,
                                                       new Item.Settings()
                                      )
        );
        SKELETON_HORSE_SPAWN_EGG = register("skeleton_horse_spawn_egg",
                                            new SpawnEggItem(EntityType.SKELETON_HORSE,
                                                             6842447,
                                                             15066584,
                                                             new Item.Settings()
                                            )
        );
        SLIME_SPAWN_EGG = register("slime_spawn_egg",
                                   new SpawnEggItem(EntityType.SLIME,
                                                    5349438,
                                                    8306542,
                                                    new Item.Settings()
                                   )
        );
        SNIFFER_SPAWN_EGG = register("sniffer_spawn_egg",
                                     new SpawnEggItem(EntityType.SNIFFER,
                                                      8855049,
                                                      2468720,
                                                      new Item.Settings()
                                     )
        );
        SNOW_GOLEM_SPAWN_EGG = register("snow_golem_spawn_egg",
                                        new SpawnEggItem(EntityType.SNOW_GOLEM,
                                                         14283506,
                                                         8496292,
                                                         new Item.Settings()
                                        )
        );
        SPIDER_SPAWN_EGG = register("spider_spawn_egg",
                                    new SpawnEggItem(EntityType.SPIDER,
                                                     3419431,
                                                     11013646,
                                                     new Item.Settings()
                                    )
        );
        SQUID_SPAWN_EGG = register("squid_spawn_egg",
                                   new SpawnEggItem(EntityType.SQUID,
                                                    2243405,
                                                    7375001,
                                                    new Item.Settings()
                                   )
        );
        STRAY_SPAWN_EGG = register("stray_spawn_egg",
                                   new SpawnEggItem(EntityType.STRAY,
                                                    6387319,
                                                    14543594,
                                                    new Item.Settings()
                                   )
        );
        STRIDER_SPAWN_EGG = register("strider_spawn_egg",
                                     new SpawnEggItem(EntityType.STRIDER,
                                                      10236982,
                                                      5065037,
                                                      new Item.Settings()
                                     )
        );
        TADPOLE_SPAWN_EGG = register("tadpole_spawn_egg",
                                     new SpawnEggItem(EntityType.TADPOLE,
                                                      7164733,
                                                      1444352,
                                                      new Item.Settings()
                                     )
        );
        TRADER_LLAMA_SPAWN_EGG = register("trader_llama_spawn_egg",
                                          new SpawnEggItem(EntityType.TRADER_LLAMA,
                                                           15377456,
                                                           4547222,
                                                           new Item.Settings()
                                          )
        );
        TROPICAL_FISH_SPAWN_EGG = register("tropical_fish_spawn_egg",
                                           new SpawnEggItem(EntityType.TROPICAL_FISH,
                                                            15690005,
                                                            16775663,
                                                            new Item.Settings()
                                           )
        );
        TURTLE_SPAWN_EGG = register("turtle_spawn_egg",
                                    new SpawnEggItem(EntityType.TURTLE,
                                                     15198183,
                                                     44975,
                                                     new Item.Settings()
                                    )
        );
        VEX_SPAWN_EGG = register("vex_spawn_egg",
                                 new SpawnEggItem(EntityType.VEX,
                                                  8032420,
                                                  15265265,
                                                  new Item.Settings()
                                 )
        );
        VILLAGER_SPAWN_EGG = register("villager_spawn_egg",
                                      new SpawnEggItem(EntityType.VILLAGER,
                                                       5651507,
                                                       12422002,
                                                       new Item.Settings()
                                      )
        );
        VINDICATOR_SPAWN_EGG = register("vindicator_spawn_egg",
                                        new SpawnEggItem(EntityType.VINDICATOR,
                                                         9804699,
                                                         2580065,
                                                         new Item.Settings()
                                        )
        );
        WANDERING_TRADER_SPAWN_EGG = register("wandering_trader_spawn_egg",
                                              new SpawnEggItem(EntityType.WANDERING_TRADER,
                                                               4547222,
                                                               15377456,
                                                               new Item.Settings()
                                              )
        );
        WARDEN_SPAWN_EGG = register("warden_spawn_egg",
                                    new SpawnEggItem(EntityType.WARDEN,
                                                     1001033,
                                                     3790560,
                                                     new Item.Settings()
                                    )
        );
        WITCH_SPAWN_EGG = register("witch_spawn_egg",
                                   new SpawnEggItem(EntityType.WITCH,
                                                    3407872,
                                                    5349438,
                                                    new Item.Settings()
                                   )
        );
        WITHER_SPAWN_EGG = register("wither_spawn_egg",
                                    new SpawnEggItem(EntityType.WITHER,
                                                     1315860,
                                                     5075616,
                                                     new Item.Settings()
                                    )
        );
        WITHER_SKELETON_SPAWN_EGG = register("wither_skeleton_spawn_egg",
                                             new SpawnEggItem(EntityType.WITHER_SKELETON,
                                                              1315860,
                                                              4672845,
                                                              new Item.Settings()
                                             )
        );
        WOLF_SPAWN_EGG = register("wolf_spawn_egg",
                                  new SpawnEggItem(EntityType.WOLF,
                                                   14144467,
                                                   13545366,
                                                   new Item.Settings()
                                  )
        );
        ZOGLIN_SPAWN_EGG = register("zoglin_spawn_egg",
                                    new SpawnEggItem(EntityType.ZOGLIN,
                                                     13004373,
                                                     15132390,
                                                     new Item.Settings()
                                    )
        );
        ZOMBIE_SPAWN_EGG = register("zombie_spawn_egg",
                                    new SpawnEggItem(EntityType.ZOMBIE,
                                                     44975,
                                                     7969893,
                                                     new Item.Settings()
                                    )
        );
        ZOMBIE_HORSE_SPAWN_EGG = register("zombie_horse_spawn_egg",
                                          new SpawnEggItem(EntityType.ZOMBIE_HORSE,
                                                           3232308,
                                                           9945732,
                                                           new Item.Settings()
                                          )
        );
        ZOMBIE_VILLAGER_SPAWN_EGG = register("zombie_villager_spawn_egg",
                                             new SpawnEggItem(EntityType.ZOMBIE_VILLAGER,
                                                              5651507,
                                                              7969893,
                                                              new Item.Settings()
                                             )
        );
        ZOMBIFIED_PIGLIN_SPAWN_EGG = register("zombified_piglin_spawn_egg",
                                              new SpawnEggItem(EntityType.ZOMBIFIED_PIGLIN,
                                                               15373203,
                                                               5009705,
                                                               new Item.Settings()
                                              )
        );
        EXPERIENCE_BOTTLE = register("experience_bottle",
                                     new ExperienceBottleItem((new Item.Settings()).rarity(Rarity.UNCOMMON))
        );
        FIRE_CHARGE = register("fire_charge",
                               new FireChargeItem(new Item.Settings())
        );
        WRITABLE_BOOK = register("writable_book",
                                 new WritableBookItem((new Item.Settings()).maxCount(1))
        );
        WRITTEN_BOOK = register("written_book",
                                new WrittenBookItem((new Item.Settings()).maxCount(16))
        );
        ITEM_FRAME = register("item_frame",
                              new ItemFrameItem(EntityType.ITEM_FRAME,
                                                new Item.Settings()
                              )
        );
        GLOW_ITEM_FRAME = register("glow_item_frame",
                                   new ItemFrameItem(EntityType.GLOW_ITEM_FRAME,
                                                     new Item.Settings()
                                   )
        );
        FLOWER_POT = register(Blocks.FLOWER_POT);
        CARROT = register("carrot",
                          new AliasedBlockItem(Blocks.CARROTS,
                                               (new Item.Settings()).food(FoodComponents.CARROT)
                          )
        );
        POTATO = register("potato",
                          new AliasedBlockItem(Blocks.POTATOES,
                                               (new Item.Settings()).food(FoodComponents.POTATO)
                          )
        );
        BAKED_POTATO = register("baked_potato",
                                new Item((new Item.Settings()).food(FoodComponents.BAKED_POTATO))
        );
        POISONOUS_POTATO = register("poisonous_potato",
                                    new Item((new Item.Settings()).food(FoodComponents.POISONOUS_POTATO))
        );
        MAP = register("map",
                       new EmptyMapItem(new Item.Settings())
        );
        GOLDEN_CARROT = register("golden_carrot",
                                 new Item((new Item.Settings()).food(FoodComponents.GOLDEN_CARROT))
        );
        SKELETON_SKULL = register(new VerticallyAttachableBlockItem(Blocks.SKELETON_SKULL,
                                                                    Blocks.SKELETON_WALL_SKULL,
                                                                    (new Item.Settings()).rarity(Rarity.UNCOMMON),
                                                                    Direction.DOWN
        ));
        WITHER_SKELETON_SKULL = register(new VerticallyAttachableBlockItem(Blocks.WITHER_SKELETON_SKULL,
                                                                           Blocks.WITHER_SKELETON_WALL_SKULL,
                                                                           (new Item.Settings()).rarity(Rarity.UNCOMMON),
                                                                           Direction.DOWN
        ));
        PLAYER_HEAD = register(new PlayerHeadItem(Blocks.PLAYER_HEAD,
                                                  Blocks.PLAYER_WALL_HEAD,
                                                  (new Item.Settings()).rarity(Rarity.UNCOMMON)
        ));
        ZOMBIE_HEAD = register(new VerticallyAttachableBlockItem(Blocks.ZOMBIE_HEAD,
                                                                 Blocks.ZOMBIE_WALL_HEAD,
                                                                 (new Item.Settings()).rarity(Rarity.UNCOMMON),
                                                                 Direction.DOWN
        ));
        CREEPER_HEAD = register(new VerticallyAttachableBlockItem(Blocks.CREEPER_HEAD,
                                                                  Blocks.CREEPER_WALL_HEAD,
                                                                  (new Item.Settings()).rarity(Rarity.UNCOMMON),
                                                                  Direction.DOWN
        ));
        DRAGON_HEAD = register(new VerticallyAttachableBlockItem(Blocks.DRAGON_HEAD,
                                                                 Blocks.DRAGON_WALL_HEAD,
                                                                 (new Item.Settings()).rarity(Rarity.UNCOMMON),
                                                                 Direction.DOWN
        ));
        PIGLIN_HEAD = register(new VerticallyAttachableBlockItem(Blocks.PIGLIN_HEAD,
                                                                 Blocks.PIGLIN_WALL_HEAD,
                                                                 (new Item.Settings()).rarity(Rarity.UNCOMMON),
                                                                 Direction.DOWN
        ));
        NETHER_STAR = register("nether_star",
                               new NetherStarItem((new Item.Settings()).rarity(Rarity.UNCOMMON))
        );
        PUMPKIN_PIE = register("pumpkin_pie",
                               new Item((new Item.Settings()).food(FoodComponents.PUMPKIN_PIE))
        );
        FIREWORK_ROCKET = register("firework_rocket",
                                   new FireworkRocketItem(new Item.Settings())
        );
        FIREWORK_STAR = register("firework_star",
                                 new FireworkStarItem(new Item.Settings())
        );
        ENCHANTED_BOOK = register("enchanted_book",
                                  new EnchantedBookItem((new Item.Settings()).maxCount(1)
                                                                             .rarity(Rarity.UNCOMMON))
        );
        NETHER_BRICK = register("nether_brick",
                                new Item(new Item.Settings())
        );
        PRISMARINE_SHARD = register("prismarine_shard",
                                    new Item(new Item.Settings())
        );
        PRISMARINE_CRYSTALS = register("prismarine_crystals",
                                       new Item(new Item.Settings())
        );
        RABBIT = register("rabbit",
                          new Item((new Item.Settings()).food(FoodComponents.RABBIT))
        );
        COOKED_RABBIT = register("cooked_rabbit",
                                 new Item((new Item.Settings()).food(FoodComponents.COOKED_RABBIT))
        );
        RABBIT_STEW = register("rabbit_stew",
                               new StewItem((new Item.Settings()).maxCount(1)
                                                                 .food(FoodComponents.RABBIT_STEW))
        );
        RABBIT_FOOT = register("rabbit_foot",
                               new Item(new Item.Settings())
        );
        RABBIT_HIDE = register("rabbit_hide",
                               new Item(new Item.Settings())
        );
        ARMOR_STAND = register("armor_stand",
                               new ArmorStandItem((new Item.Settings()).maxCount(16))
        );
        IRON_HORSE_ARMOR = register("iron_horse_armor",
                                    new HorseArmorItem(5,
                                                       "iron",
                                                       (new Item.Settings()).maxCount(1)
                                    )
        );
        GOLDEN_HORSE_ARMOR = register("golden_horse_armor",
                                      new HorseArmorItem(7,
                                                         "gold",
                                                         (new Item.Settings()).maxCount(1)
                                      )
        );
        DIAMOND_HORSE_ARMOR = register("diamond_horse_armor",
                                       new HorseArmorItem(11,
                                                          "diamond",
                                                          (new Item.Settings()).maxCount(1)
                                       )
        );
        LEATHER_HORSE_ARMOR = register("leather_horse_armor",
                                       new DyeableHorseArmorItem(3,
                                                                 "leather",
                                                                 (new Item.Settings()).maxCount(1)
                                       )
        );
        LEAD = register("lead",
                        new LeadItem(new Item.Settings())
        );
        NAME_TAG = register("name_tag",
                            new NameTagItem(new Item.Settings())
        );
        COMMAND_BLOCK_MINECART = register("command_block_minecart",
                                          new MinecartItem(AbstractMinecartEntity.Type.COMMAND_BLOCK,
                                                           (new Item.Settings()).maxCount(1)
                                                                                .rarity(Rarity.EPIC)
                                          )
        );
        MUTTON = register("mutton",
                          new Item((new Item.Settings()).food(FoodComponents.MUTTON))
        );
        COOKED_MUTTON = register("cooked_mutton",
                                 new Item((new Item.Settings()).food(FoodComponents.COOKED_MUTTON))
        );
        WHITE_BANNER = register("white_banner",
                                new BannerItem(Blocks.WHITE_BANNER,
                                               Blocks.WHITE_WALL_BANNER,
                                               (new Item.Settings()).maxCount(16)
                                )
        );
        ORANGE_BANNER = register("orange_banner",
                                 new BannerItem(Blocks.ORANGE_BANNER,
                                                Blocks.ORANGE_WALL_BANNER,
                                                (new Item.Settings()).maxCount(16)
                                 )
        );
        MAGENTA_BANNER = register("magenta_banner",
                                  new BannerItem(Blocks.MAGENTA_BANNER,
                                                 Blocks.MAGENTA_WALL_BANNER,
                                                 (new Item.Settings()).maxCount(16)
                                  )
        );
        LIGHT_BLUE_BANNER = register("light_blue_banner",
                                     new BannerItem(Blocks.LIGHT_BLUE_BANNER,
                                                    Blocks.LIGHT_BLUE_WALL_BANNER,
                                                    (new Item.Settings()).maxCount(16)
                                     )
        );
        YELLOW_BANNER = register("yellow_banner",
                                 new BannerItem(Blocks.YELLOW_BANNER,
                                                Blocks.YELLOW_WALL_BANNER,
                                                (new Item.Settings()).maxCount(16)
                                 )
        );
        LIME_BANNER = register("lime_banner",
                               new BannerItem(Blocks.LIME_BANNER,
                                              Blocks.LIME_WALL_BANNER,
                                              (new Item.Settings()).maxCount(16)
                               )
        );
        PINK_BANNER = register("pink_banner",
                               new BannerItem(Blocks.PINK_BANNER,
                                              Blocks.PINK_WALL_BANNER,
                                              (new Item.Settings()).maxCount(16)
                               )
        );
        GRAY_BANNER = register("gray_banner",
                               new BannerItem(Blocks.GRAY_BANNER,
                                              Blocks.GRAY_WALL_BANNER,
                                              (new Item.Settings()).maxCount(16)
                               )
        );
        LIGHT_GRAY_BANNER = register("light_gray_banner",
                                     new BannerItem(Blocks.LIGHT_GRAY_BANNER,
                                                    Blocks.LIGHT_GRAY_WALL_BANNER,
                                                    (new Item.Settings()).maxCount(16)
                                     )
        );
        CYAN_BANNER = register("cyan_banner",
                               new BannerItem(Blocks.CYAN_BANNER,
                                              Blocks.CYAN_WALL_BANNER,
                                              (new Item.Settings()).maxCount(16)
                               )
        );
        PURPLE_BANNER = register("purple_banner",
                                 new BannerItem(Blocks.PURPLE_BANNER,
                                                Blocks.PURPLE_WALL_BANNER,
                                                (new Item.Settings()).maxCount(16)
                                 )
        );
        BLUE_BANNER = register("blue_banner",
                               new BannerItem(Blocks.BLUE_BANNER,
                                              Blocks.BLUE_WALL_BANNER,
                                              (new Item.Settings()).maxCount(16)
                               )
        );
        BROWN_BANNER = register("brown_banner",
                                new BannerItem(Blocks.BROWN_BANNER,
                                               Blocks.BROWN_WALL_BANNER,
                                               (new Item.Settings()).maxCount(16)
                                )
        );
        GREEN_BANNER = register("green_banner",
                                new BannerItem(Blocks.GREEN_BANNER,
                                               Blocks.GREEN_WALL_BANNER,
                                               (new Item.Settings()).maxCount(16)
                                )
        );
        RED_BANNER = register("red_banner",
                              new BannerItem(Blocks.RED_BANNER,
                                             Blocks.RED_WALL_BANNER,
                                             (new Item.Settings()).maxCount(16)
                              )
        );
        BLACK_BANNER = register("black_banner",
                                new BannerItem(Blocks.BLACK_BANNER,
                                               Blocks.BLACK_WALL_BANNER,
                                               (new Item.Settings()).maxCount(16)
                                )
        );
        END_CRYSTAL = register("end_crystal",
                               new EndCrystalItem((new Item.Settings()).rarity(Rarity.RARE))
        );
        CHORUS_FRUIT = register("chorus_fruit",
                                new ChorusFruitItem((new Item.Settings()).food(FoodComponents.CHORUS_FRUIT))
        );
        POPPED_CHORUS_FRUIT = register("popped_chorus_fruit",
                                       new Item(new Item.Settings())
        );
        TORCHFLOWER_SEEDS = register("torchflower_seeds",
                                     new AliasedBlockItem(Blocks.TORCHFLOWER_CROP,
                                                          new Item.Settings()
                                     )
        );
        PITCHER_POD = register("pitcher_pod",
                               new AliasedBlockItem(Blocks.PITCHER_CROP,
                                                    new Item.Settings()
                               )
        );
        BEETROOT = register("beetroot",
                            new Item((new Item.Settings()).food(FoodComponents.BEETROOT))
        );
        BEETROOT_SEEDS = register("beetroot_seeds",
                                  new AliasedBlockItem(Blocks.BEETROOTS,
                                                       new Item.Settings()
                                  )
        );
        BEETROOT_SOUP = register("beetroot_soup",
                                 new StewItem((new Item.Settings()).maxCount(1)
                                                                   .food(FoodComponents.BEETROOT_SOUP))
        );
        DRAGON_BREATH = register("dragon_breath",
                                 new Item((new Item.Settings()).recipeRemainder(GLASS_BOTTLE)
                                                               .rarity(Rarity.UNCOMMON))
        );
        SPLASH_POTION = register("splash_potion",
                                 new SplashPotionItem((new Item.Settings()).maxCount(1))
        );
        SPECTRAL_ARROW = register("spectral_arrow",
                                  new SpectralArrowItem(new Item.Settings())
        );
        TIPPED_ARROW = register("tipped_arrow",
                                new TippedArrowItem(new Item.Settings())
        );
        LINGERING_POTION = register("lingering_potion",
                                    new LingeringPotionItem((new Item.Settings()).maxCount(1))
        );
        SHIELD = register("shield",
                          new ShieldItem((new Item.Settings()).maxDamage(336))
        );
        TOTEM_OF_UNDYING = register("totem_of_undying",
                                    new Item((new Item.Settings()).maxCount(1)
                                                                  .rarity(Rarity.UNCOMMON))
        );
        SHULKER_SHELL = register("shulker_shell",
                                 new Item(new Item.Settings())
        );
        IRON_NUGGET = register("iron_nugget",
                               new Item(new Item.Settings())
        );
        KNOWLEDGE_BOOK = register("knowledge_book",
                                  new KnowledgeBookItem((new Item.Settings()).maxCount(1)
                                                                             .rarity(Rarity.EPIC))
        );
        DEBUG_STICK = register("debug_stick",
                               new DebugStickItem((new Item.Settings()).maxCount(1)
                                                                       .rarity(Rarity.EPIC))
        );
        MUSIC_DISC_13 = register("music_disc_13",
                                 new MusicDiscItem(1,
                                                   SoundEvents.MUSIC_DISC_13,
                                                   (new Item.Settings()).maxCount(1)
                                                                        .rarity(Rarity.RARE),
                                                   178
                                 )
        );
        MUSIC_DISC_CAT = register("music_disc_cat",
                                  new MusicDiscItem(2,
                                                    SoundEvents.MUSIC_DISC_CAT,
                                                    (new Item.Settings()).maxCount(1)
                                                                         .rarity(Rarity.RARE),
                                                    185
                                  )
        );
        MUSIC_DISC_BLOCKS = register("music_disc_blocks",
                                     new MusicDiscItem(3,
                                                       SoundEvents.MUSIC_DISC_BLOCKS,
                                                       (new Item.Settings()).maxCount(1)
                                                                            .rarity(Rarity.RARE),
                                                       345
                                     )
        );
        MUSIC_DISC_CHIRP = register("music_disc_chirp",
                                    new MusicDiscItem(4,
                                                      SoundEvents.MUSIC_DISC_CHIRP,
                                                      (new Item.Settings()).maxCount(1)
                                                                           .rarity(Rarity.RARE),
                                                      185
                                    )
        );
        MUSIC_DISC_FAR = register("music_disc_far",
                                  new MusicDiscItem(5,
                                                    SoundEvents.MUSIC_DISC_FAR,
                                                    (new Item.Settings()).maxCount(1)
                                                                         .rarity(Rarity.RARE),
                                                    174
                                  )
        );
        MUSIC_DISC_MALL = register("music_disc_mall",
                                   new MusicDiscItem(6,
                                                     SoundEvents.MUSIC_DISC_MALL,
                                                     (new Item.Settings()).maxCount(1)
                                                                          .rarity(Rarity.RARE),
                                                     197
                                   )
        );
        MUSIC_DISC_MELLOHI = register("music_disc_mellohi",
                                      new MusicDiscItem(7,
                                                        SoundEvents.MUSIC_DISC_MELLOHI,
                                                        (new Item.Settings()).maxCount(1)
                                                                             .rarity(Rarity.RARE),
                                                        96
                                      )
        );
        MUSIC_DISC_STAL = register("music_disc_stal",
                                   new MusicDiscItem(8,
                                                     SoundEvents.MUSIC_DISC_STAL,
                                                     (new Item.Settings()).maxCount(1)
                                                                          .rarity(Rarity.RARE),
                                                     150
                                   )
        );
        MUSIC_DISC_STRAD = register("music_disc_strad",
                                    new MusicDiscItem(9,
                                                      SoundEvents.MUSIC_DISC_STRAD,
                                                      (new Item.Settings()).maxCount(1)
                                                                           .rarity(Rarity.RARE),
                                                      188
                                    )
        );
        MUSIC_DISC_WARD = register("music_disc_ward",
                                   new MusicDiscItem(10,
                                                     SoundEvents.MUSIC_DISC_WARD,
                                                     (new Item.Settings()).maxCount(1)
                                                                          .rarity(Rarity.RARE),
                                                     251
                                   )
        );
        MUSIC_DISC_11 = register("music_disc_11",
                                 new MusicDiscItem(11,
                                                   SoundEvents.MUSIC_DISC_11,
                                                   (new Item.Settings()).maxCount(1)
                                                                        .rarity(Rarity.RARE),
                                                   71
                                 )
        );
        MUSIC_DISC_WAIT = register("music_disc_wait",
                                   new MusicDiscItem(12,
                                                     SoundEvents.MUSIC_DISC_WAIT,
                                                     (new Item.Settings()).maxCount(1)
                                                                          .rarity(Rarity.RARE),
                                                     238
                                   )
        );
        MUSIC_DISC_OTHERSIDE = register("music_disc_otherside",
                                        new MusicDiscItem(14,
                                                          SoundEvents.MUSIC_DISC_OTHERSIDE,
                                                          (new Item.Settings()).maxCount(1)
                                                                               .rarity(Rarity.RARE),
                                                          195
                                        )
        );
        MUSIC_DISC_RELIC = register("music_disc_relic",
                                    new MusicDiscItem(14,
                                                      SoundEvents.MUSIC_DISC_RELIC,
                                                      (new Item.Settings()).maxCount(1)
                                                                           .rarity(Rarity.RARE),
                                                      218
                                    )
        );
        MUSIC_DISC_5 = register("music_disc_5",
                                new MusicDiscItem(15,
                                                  SoundEvents.MUSIC_DISC_5,
                                                  (new Item.Settings()).maxCount(1)
                                                                       .rarity(Rarity.RARE),
                                                  178
                                )
        );
        MUSIC_DISC_PIGSTEP = register("music_disc_pigstep",
                                      new MusicDiscItem(13,
                                                        SoundEvents.MUSIC_DISC_PIGSTEP,
                                                        (new Item.Settings()).maxCount(1)
                                                                             .rarity(Rarity.RARE),
                                                        149
                                      )
        );
        DISC_FRAGMENT_5 = register("disc_fragment_5",
                                   new DiscFragmentItem(new Item.Settings())
        );
        TRIDENT = register("trident",
                           new TridentItem((new Item.Settings()).maxDamage(250))
        );
        PHANTOM_MEMBRANE = register("phantom_membrane",
                                    new Item(new Item.Settings())
        );
        NAUTILUS_SHELL = register("nautilus_shell",
                                  new Item(new Item.Settings())
        );
        HEART_OF_THE_SEA = register("heart_of_the_sea",
                                    new Item((new Item.Settings()).rarity(Rarity.UNCOMMON))
        );
        CROSSBOW = register("crossbow",
                            new CrossbowItem((new Item.Settings()).maxCount(1)
                                                                  .maxDamage(465))
        );
        SUSPICIOUS_STEW = register("suspicious_stew",
                                   new SuspiciousStewItem((new Item.Settings()).maxCount(1)
                                                                               .food(FoodComponents.SUSPICIOUS_STEW))
        );
        LOOM = register(Blocks.LOOM);
        FLOWER_BANNER_PATTERN = register("flower_banner_pattern",
                                         new BannerPatternItem(BannerPatternTags.FLOWER_PATTERN_ITEM,
                                                               (new Item.Settings()).maxCount(1)
                                         )
        );
        CREEPER_BANNER_PATTERN = register("creeper_banner_pattern",
                                          new BannerPatternItem(BannerPatternTags.CREEPER_PATTERN_ITEM,
                                                                (new Item.Settings()).maxCount(1)
                                                                                     .rarity(Rarity.UNCOMMON)
                                          )
        );
        SKULL_BANNER_PATTERN = register("skull_banner_pattern",
                                        new BannerPatternItem(BannerPatternTags.SKULL_PATTERN_ITEM,
                                                              (new Item.Settings()).maxCount(1)
                                                                                   .rarity(Rarity.UNCOMMON)
                                        )
        );
        MOJANG_BANNER_PATTERN = register("mojang_banner_pattern",
                                         new BannerPatternItem(BannerPatternTags.MOJANG_PATTERN_ITEM,
                                                               (new Item.Settings()).maxCount(1)
                                                                                    .rarity(Rarity.EPIC)
                                         )
        );
        GLOBE_BANNER_PATTERN = register("globe_banner_pattern",
                                        new BannerPatternItem(BannerPatternTags.GLOBE_PATTERN_ITEM,
                                                              (new Item.Settings()).maxCount(1)
                                        )
        );
        PIGLIN_BANNER_PATTERN = register("piglin_banner_pattern",
                                         new BannerPatternItem(BannerPatternTags.PIGLIN_PATTERN_ITEM,
                                                               (new Item.Settings()).maxCount(1)
                                         )
        );
        GOAT_HORN = register("goat_horn",
                             new GoatHornItem((new Item.Settings()).maxCount(1),
                                              InstrumentTags.GOAT_HORNS
                             )
        );
        COMPOSTER = register(Blocks.COMPOSTER);
        BARREL = register(Blocks.BARREL);
        SMOKER = register(Blocks.SMOKER);
        BLAST_FURNACE = register(Blocks.BLAST_FURNACE);
        CARTOGRAPHY_TABLE = register(Blocks.CARTOGRAPHY_TABLE);
        FLETCHING_TABLE = register(Blocks.FLETCHING_TABLE);
        GRINDSTONE = register(Blocks.GRINDSTONE);
        SMITHING_TABLE = register(Blocks.SMITHING_TABLE);
        STONECUTTER = register(Blocks.STONECUTTER);
        BELL = register(Blocks.BELL);
        LANTERN = register(Blocks.LANTERN);
        SOUL_LANTERN = register(Blocks.SOUL_LANTERN);
        SWEET_BERRIES = register("sweet_berries",
                                 new AliasedBlockItem(Blocks.SWEET_BERRY_BUSH,
                                                      (new Item.Settings()).food(FoodComponents.SWEET_BERRIES)
                                 )
        );
        GLOW_BERRIES = register("glow_berries",
                                new AliasedBlockItem(Blocks.CAVE_VINES,
                                                     (new Item.Settings()).food(FoodComponents.GLOW_BERRIES)
                                )
        );
        CAMPFIRE = register(Blocks.CAMPFIRE);
        SOUL_CAMPFIRE = register(Blocks.SOUL_CAMPFIRE);
        SHROOMLIGHT = register(Blocks.SHROOMLIGHT);
        HONEYCOMB = register("honeycomb",
                             new HoneycombItem(new Item.Settings())
        );
        BEE_NEST = register(Blocks.BEE_NEST);
        BEEHIVE = register(Blocks.BEEHIVE);
        HONEY_BOTTLE = register("honey_bottle",
                                new HoneyBottleItem((new Item.Settings()).recipeRemainder(GLASS_BOTTLE)
                                                                         .food(FoodComponents.HONEY_BOTTLE)
                                                                         .maxCount(16))
        );
        HONEYCOMB_BLOCK = register(Blocks.HONEYCOMB_BLOCK);
        LODESTONE = register(Blocks.LODESTONE);
        CRYING_OBSIDIAN = register(Blocks.CRYING_OBSIDIAN);
        BLACKSTONE = register(Blocks.BLACKSTONE);
        BLACKSTONE_SLAB = register(Blocks.BLACKSTONE_SLAB);
        BLACKSTONE_STAIRS = register(Blocks.BLACKSTONE_STAIRS);
        GILDED_BLACKSTONE = register(Blocks.GILDED_BLACKSTONE);
        POLISHED_BLACKSTONE = register(Blocks.POLISHED_BLACKSTONE);
        POLISHED_BLACKSTONE_SLAB = register(Blocks.POLISHED_BLACKSTONE_SLAB);
        POLISHED_BLACKSTONE_STAIRS = register(Blocks.POLISHED_BLACKSTONE_STAIRS);
        CHISELED_POLISHED_BLACKSTONE = register(Blocks.CHISELED_POLISHED_BLACKSTONE);
        POLISHED_BLACKSTONE_BRICKS = register(Blocks.POLISHED_BLACKSTONE_BRICKS);
        POLISHED_BLACKSTONE_BRICK_SLAB = register(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);
        POLISHED_BLACKSTONE_BRICK_STAIRS = register(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS);
        CRACKED_POLISHED_BLACKSTONE_BRICKS = register(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        RESPAWN_ANCHOR = register(Blocks.RESPAWN_ANCHOR);
        CANDLE = register(Blocks.CANDLE);
        WHITE_CANDLE = register(Blocks.WHITE_CANDLE);
        ORANGE_CANDLE = register(Blocks.ORANGE_CANDLE);
        MAGENTA_CANDLE = register(Blocks.MAGENTA_CANDLE);
        LIGHT_BLUE_CANDLE = register(Blocks.LIGHT_BLUE_CANDLE);
        YELLOW_CANDLE = register(Blocks.YELLOW_CANDLE);
        LIME_CANDLE = register(Blocks.LIME_CANDLE);
        PINK_CANDLE = register(Blocks.PINK_CANDLE);
        GRAY_CANDLE = register(Blocks.GRAY_CANDLE);
        LIGHT_GRAY_CANDLE = register(Blocks.LIGHT_GRAY_CANDLE);
        CYAN_CANDLE = register(Blocks.CYAN_CANDLE);
        PURPLE_CANDLE = register(Blocks.PURPLE_CANDLE);
        BLUE_CANDLE = register(Blocks.BLUE_CANDLE);
        BROWN_CANDLE = register(Blocks.BROWN_CANDLE);
        GREEN_CANDLE = register(Blocks.GREEN_CANDLE);
        RED_CANDLE = register(Blocks.RED_CANDLE);
        BLACK_CANDLE = register(Blocks.BLACK_CANDLE);
        SMALL_AMETHYST_BUD = register(Blocks.SMALL_AMETHYST_BUD);
        MEDIUM_AMETHYST_BUD = register(Blocks.MEDIUM_AMETHYST_BUD);
        LARGE_AMETHYST_BUD = register(Blocks.LARGE_AMETHYST_BUD);
        AMETHYST_CLUSTER = register(Blocks.AMETHYST_CLUSTER);
        POINTED_DRIPSTONE = register(Blocks.POINTED_DRIPSTONE);
        OCHRE_FROGLIGHT = register(Blocks.OCHRE_FROGLIGHT);
        VERDANT_FROGLIGHT = register(Blocks.VERDANT_FROGLIGHT);
        PEARLESCENT_FROGLIGHT = register(Blocks.PEARLESCENT_FROGLIGHT);
        FROGSPAWN = register(new PlaceableOnWaterItem(Blocks.FROGSPAWN,
                                                      new Item.Settings()
        ));
        ECHO_SHARD = register("echo_shard",
                              new Item(new Item.Settings())
        );
        BRUSH = register("brush",
                         new BrushItem((new Item.Settings()).maxDamage(64))
        );
        NETHERITE_UPGRADE_SMITHING_TEMPLATE = register("netherite_upgrade_smithing_template",
                                                       SmithingTemplateItem.createNetheriteUpgrade()
        );
        SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE = register("sentry_armor_trim_smithing_template",
                                                       SmithingTemplateItem.of(ArmorTrimPatterns.SENTRY)
        );
        DUNE_ARMOR_TRIM_SMITHING_TEMPLATE = register("dune_armor_trim_smithing_template",
                                                     SmithingTemplateItem.of(ArmorTrimPatterns.DUNE)
        );
        COAST_ARMOR_TRIM_SMITHING_TEMPLATE = register("coast_armor_trim_smithing_template",
                                                      SmithingTemplateItem.of(ArmorTrimPatterns.COAST)
        );
        WILD_ARMOR_TRIM_SMITHING_TEMPLATE = register("wild_armor_trim_smithing_template",
                                                     SmithingTemplateItem.of(ArmorTrimPatterns.WILD)
        );
        WARD_ARMOR_TRIM_SMITHING_TEMPLATE = register("ward_armor_trim_smithing_template",
                                                     SmithingTemplateItem.of(ArmorTrimPatterns.WARD)
        );
        EYE_ARMOR_TRIM_SMITHING_TEMPLATE = register("eye_armor_trim_smithing_template",
                                                    SmithingTemplateItem.of(ArmorTrimPatterns.EYE)
        );
        VEX_ARMOR_TRIM_SMITHING_TEMPLATE = register("vex_armor_trim_smithing_template",
                                                    SmithingTemplateItem.of(ArmorTrimPatterns.VEX)
        );
        TIDE_ARMOR_TRIM_SMITHING_TEMPLATE = register("tide_armor_trim_smithing_template",
                                                     SmithingTemplateItem.of(ArmorTrimPatterns.TIDE)
        );
        SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE = register("snout_armor_trim_smithing_template",
                                                      SmithingTemplateItem.of(ArmorTrimPatterns.SNOUT)
        );
        RIB_ARMOR_TRIM_SMITHING_TEMPLATE = register("rib_armor_trim_smithing_template",
                                                    SmithingTemplateItem.of(ArmorTrimPatterns.RIB)
        );
        SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE = register("spire_armor_trim_smithing_template",
                                                      SmithingTemplateItem.of(ArmorTrimPatterns.SPIRE)
        );
        WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE = register("wayfinder_armor_trim_smithing_template",
                                                          SmithingTemplateItem.of(ArmorTrimPatterns.WAYFINDER)
        );
        SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE = register("shaper_armor_trim_smithing_template",
                                                       SmithingTemplateItem.of(ArmorTrimPatterns.SHAPER)
        );
        SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE = register("silence_armor_trim_smithing_template",
                                                        SmithingTemplateItem.of(ArmorTrimPatterns.SILENCE)
        );
        RAISER_ARMOR_TRIM_SMITHING_TEMPLATE = register("raiser_armor_trim_smithing_template",
                                                       SmithingTemplateItem.of(ArmorTrimPatterns.RAISER)
        );
        HOST_ARMOR_TRIM_SMITHING_TEMPLATE = register("host_armor_trim_smithing_template",
                                                     SmithingTemplateItem.of(ArmorTrimPatterns.HOST)
        );
        ANGLER_POTTERY_SHERD = register("angler_pottery_sherd",
                                        new Item(new Item.Settings())
        );
        ARCHER_POTTERY_SHERD = register("archer_pottery_sherd",
                                        new Item(new Item.Settings())
        );
        ARMS_UP_POTTERY_SHERD = register("arms_up_pottery_sherd",
                                         new Item(new Item.Settings())
        );
        BLADE_POTTERY_SHERD = register("blade_pottery_sherd",
                                       new Item(new Item.Settings())
        );
        BREWER_POTTERY_SHERD = register("brewer_pottery_sherd",
                                        new Item(new Item.Settings())
        );
        BURN_POTTERY_SHERD = register("burn_pottery_sherd",
                                      new Item(new Item.Settings())
        );
        DANGER_POTTERY_SHERD = register("danger_pottery_sherd",
                                        new Item(new Item.Settings())
        );
        EXPLORER_POTTERY_SHERD = register("explorer_pottery_sherd",
                                          new Item(new Item.Settings())
        );
        FRIEND_POTTERY_SHERD = register("friend_pottery_sherd",
                                        new Item(new Item.Settings())
        );
        HEART_POTTERY_SHERD = register("heart_pottery_sherd",
                                       new Item(new Item.Settings())
        );
        HEARTBREAK_POTTERY_SHERD = register("heartbreak_pottery_sherd",
                                            new Item(new Item.Settings())
        );
        HOWL_POTTERY_SHERD = register("howl_pottery_sherd",
                                      new Item(new Item.Settings())
        );
        MINER_POTTERY_SHERD = register("miner_pottery_sherd",
                                       new Item(new Item.Settings())
        );
        MOURNER_POTTERY_SHERD = register("mourner_pottery_sherd",
                                         new Item(new Item.Settings())
        );
        PLENTY_POTTERY_SHERD = register("plenty_pottery_sherd",
                                        new Item(new Item.Settings())
        );
        PRIZE_POTTERY_SHERD = register("prize_pottery_sherd",
                                       new Item(new Item.Settings())
        );
        SHEAF_POTTERY_SHERD = register("sheaf_pottery_sherd",
                                       new Item(new Item.Settings())
        );
        SHELTER_POTTERY_SHERD = register("shelter_pottery_sherd",
                                         new Item(new Item.Settings())
        );
        SKULL_POTTERY_SHERD = register("skull_pottery_sherd",
                                       new Item(new Item.Settings())
        );
        SNORT_POTTERY_SHERD = register("snort_pottery_sherd",
                                       new Item(new Item.Settings())
        );
    }
}
