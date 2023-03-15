package com.github.cao.awa.trtr.framework.block.item;

import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.block.TrtrBlockItem;
import com.github.cao.awa.trtr.framework.reflection.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.item.BlockItem;

public class BlockItemAccessor implements FieldAccessor {
    public static final BlockItemAccessor ACCESSOR = new BlockItemAccessor();

    public TrtrBlockItem get(Class<TrtrBlock> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "ITEM"
                                             ),
                                          get(clazz,
                                                 "BLOCK_ITEM"
                                             )
        );
    }

    @SuppressWarnings("unchecked")
    public BlockItem get(TrtrBlock block) {
        return get((Class<TrtrBlock>) block.getClass());
    }

    public Class<? extends BlockItem> getType(Class<TrtrBlock> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "ITEM"
                                             ),
                                          get(clazz,
                                                 "BLOCK_ITEM"
                                             )
        );
    }

    @SuppressWarnings("unchecked")
    public Class<? extends BlockItem> getType(TrtrBlock block) {
        return getType((Class<TrtrBlock>) block.getClass());
    }
}
