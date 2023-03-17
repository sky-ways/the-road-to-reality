package com.github.cao.awa.trtr.framework.accessor.block.entity;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;

public class BlockEntityAccessor implements FieldAccessor {
    public static final BlockEntityAccessor ACCESSOR = new BlockEntityAccessor();

//    public BlockEntityFactor get(Class<Block> clazz) {
//        return EntrustEnvironment.nonnull(get(clazz,
//                                              "ENTITY"
//                                          ),
//                                          () -> get(clazz,
//                                                    "BLOCK_ENTITY"
//                                          )
//        );
//    }
//
//    @SuppressWarnings("unchecked")
//    public BlockEntityFactor get(Block block) {
//        return get((Class<Block>) block.getClass());
//    }

    public Class<? extends BlockEntity> getType(Class<? extends Block> clazz) {
        return EntrustEnvironment.nonnull(type(clazz,
                                               "ENTITY"
                                          ),
                                          () -> type(clazz,
                                                     "BLOCK_ENTITY"
                                          )
        );
    }

    @SuppressWarnings("unchecked")
    public Class<? extends BlockEntity> getType(Block block) {
        return getType(block.getClass());
    }

    public boolean has(Class<Block> clazz) {
        return has(clazz,
                   "ENTITY"
        ) || has(clazz,
                 "BLOCK_ENTITY"
        );
    }

    @SuppressWarnings("unchecked")
    public boolean has(Block block) {
        return has((Class<Block>) block.getClass());
    }
}
