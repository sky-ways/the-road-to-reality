package com.github.cao.awa.trtr.framework.block.item;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemAccessor implements FieldAccessor {
    public static final BlockItemAccessor ACCESSOR = new BlockItemAccessor();

    public BlockItem get(Class<Block> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "ITEM"
                                          ),
                                          () -> get(clazz,
                                                    "BLOCK_ITEM"
                                          )
        );
    }

    @SuppressWarnings("unchecked")
    public BlockItem get(Block block) {
        return get((Class<Block>) block.getClass());
    }

    public Class<? extends BlockItem> getType(Class<Block> clazz) {
        return EntrustEnvironment.nonnull(type(clazz,
                                               "ITEM"
                                          ),
                                          () -> type(clazz,
                                                     "BLOCK_ITEM"
                                          )
        );
    }

    @SuppressWarnings("unchecked")
    public Class<? extends BlockItem> getType(Block block) {
        return getType((Class<Block>) block.getClass());
    }

    public boolean has(Class<Block> clazz) {
        return has(clazz,
                   "ITEM"
        ) || has(clazz,
                 "BLOCK_ITEM"
        );
    }

    @SuppressWarnings("unchecked")
    public boolean has(Block block) {
        return has((Class<Block>) block.getClass());
    }
}
