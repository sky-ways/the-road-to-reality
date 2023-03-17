package com.github.cao.awa.trtr.block.stove.mud;

import com.github.cao.awa.apricot.anntation.Auto;
import com.github.cao.awa.trtr.block.TrtrBlockWithEntity;
import com.github.cao.awa.trtr.data.gen.loot.GenericBlockLootProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;

@Auto
public class MudStove extends TrtrBlockWithEntity {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.of("trtr",
                                                              "mud_stove"
    );

    @Auto
    public static final FabricBlockSettings SETTINGS = FabricBlockSettings.of(Material.SOIL,
                                                                              MapColor.BROWN
                                                                          )
                                                                          .hardness(1F)
                                                                          .strength(4.0F);

    @Auto
    public static BlockItem ITEM;

    @Auto
    public static MudStoveBlockEntity ENTITY;

    @Auto
    public static GenericBlockLootProvider LOOT;

    @Auto
    protected MudStove(Settings settings) {
        super(settings);
    }
}
