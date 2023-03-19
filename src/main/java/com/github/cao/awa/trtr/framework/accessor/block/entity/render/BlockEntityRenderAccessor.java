package com.github.cao.awa.trtr.framework.accessor.block.entity.render;

import com.github.cao.awa.trtr.framework.accessor.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;

public class BlockEntityRenderAccessor implements FieldAccessor {
    public static final BlockEntityRenderAccessor ACCESSOR = new BlockEntityRenderAccessor();

    public Class<? extends BlockEntityRenderer<?>> getType(Class<? extends Block> clazz) {
        return EntrustEnvironment.nonnull(type(clazz,
                                               "RENDER"
                                          ),
                                          () -> type(clazz,
                                                     "ENTITY_RENDER"
                                          )
        );
    }

    public Class<? extends BlockEntityRenderer<?>> getType(Block block) {
        return getType(block.getClass());
    }

    public boolean has(Class<Block> clazz) {
        return has(clazz,
                   "RENDER"
        ) || has(clazz,
                 "ENTITY_RENDER"
        );
    }

    @SuppressWarnings("unchecked")
    public boolean has(Block block) {
        return has((Class<Block>) block.getClass());
    }
}
