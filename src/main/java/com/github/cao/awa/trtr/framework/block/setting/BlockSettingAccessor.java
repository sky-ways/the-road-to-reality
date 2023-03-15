package com.github.cao.awa.trtr.framework.block.setting;

import com.github.cao.awa.trtr.block.TrtrBlock;
import com.github.cao.awa.trtr.framework.reflection.filed.FieldAccessor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.AbstractBlock;

public class BlockSettingAccessor implements FieldAccessor {
    public static final BlockSettingAccessor ACCESSOR = new BlockSettingAccessor();

    public AbstractBlock.Settings get(Class<TrtrBlock> clazz) {
        return EntrustEnvironment.nonnull(get(clazz,
                                              "SETTINGS"
                                             ),
                                          get(clazz,
                                                 "SETTING"
                                             )
        );
    }

    @SuppressWarnings("unchecked")
    public AbstractBlock.Settings get(TrtrBlock block) {
        return get((Class<TrtrBlock>) block.getClass());
    }

    public boolean has(Class<TrtrBlock> clazz) {
        if (has(clazz, "SETTINGS")) {
            return true;
        }
        return has(clazz, "SETTING");
    }

    @SuppressWarnings("unchecked")
    public boolean has(TrtrBlock block) {
        return has((Class<TrtrBlock>) block.getClass());
    }
}
