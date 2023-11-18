package com.github.cao.awa.trtr.item.hammer.stone;

import com.github.cao.awa.apricot.anntation.Auto;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

@Auto
public class StoneHammerItem extends MiningToolItem {
    @Auto
    public static final Identifier IDENTIFIER = Identifier.tryParse("trtr:stone_hammer");

    @Auto
    public StoneHammerItem(Settings settings) {
        super(
                8,
                - 3.4F,
                ToolMaterials.STONE,
                BlockTags.PICKAXE_MINEABLE,
                settings
        );
    }
}
