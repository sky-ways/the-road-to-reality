package com.github.cao.awa.trtr.mixin.item.entity;

import com.github.cao.awa.trtr.dev.dump.mixin.item.entity.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.*;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.function.*;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.*;
import java.util.function.*;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    private static final Map<String, Consumer<ItemEntity>> actions = EntrustParser.operation(
            new Object2ObjectOpenHashMap<>(),
            map -> {
                map.put(
                        "inFire",
                        ItemEntityMixinDev::inFire
                );
                map.put(
                        "onFire",
                        ItemEntityMixinDev::onFire
                );
                map.put(
                        "inLava",
                        ItemEntityMixinDev::inLava
                );
            }
    );

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;isInvulnerableTo(Lnet/minecraft/entity/damage/DamageSource;)Z"))
    public boolean firing(ItemEntity instance, DamageSource source) {
        if (actions.containsKey(source.getName())) {
            actions.get(source.getName()).accept(instance);
            return false;
        }
        return instance.isInvulnerableTo(source);
    }
}
