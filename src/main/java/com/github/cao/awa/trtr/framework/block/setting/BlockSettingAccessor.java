package com.github.cao.awa.trtr.framework.block.setting;

import com.github.cao.awa.trtr.framework.reflection.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class BlockSettingAccessor implements FieldAccessor {
    public static final BlockSettingAccessor ACCESSOR = new BlockSettingAccessor();

    public AbstractBlock.Settings get(Class<Block> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "SETTINGS"
                                          ),
                                          () -> get(clazz,
                                              "SETTING"
                                          )
        );
    }

    @SuppressWarnings("unchecked")
    public AbstractBlock.Settings get(Block block) {
        return get((Class<Block>) block.getClass());
    }

    public boolean has(Class<Block> clazz) {
        if (has(clazz,
                "SETTINGS"
        )) {
            return true;
        }
        return has(clazz,
                   "SETTING"
        );
    }

    @SuppressWarnings("unchecked")
    public boolean has(Block block) {
        return has((Class<Block>) block.getClass());
    }
}
