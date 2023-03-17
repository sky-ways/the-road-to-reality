package com.github.cao.awa.trtr.mixin.resource;

//import bot.inker.inkrender.MemoryResourcePack;

import net.minecraft.resource.DefaultResourcePack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DefaultResourcePack.class)
public class DefaultResourcePackMixin {
//    @Inject(at = @At("HEAD"), method = "open", cancellable = true)
//    public void open(ResourceType type, Identifier id, CallbackInfoReturnable<InputSupplier<InputStream>> cir) {
//        if (MemoryResourcePack.NAMESPACE.equals(id.getNamespace())) {
//            try {
//                cir.setReturnValue(MemoryResourcePack.INSTANCE.open(type, id));
//            } catch (Throwable ignored) {
//
//            }
//        }
//    }
//
//    @Inject(at = @At("RETURN"), method = "getNamespaces", cancellable = true)
//    public void addNamespace(ResourceType type, CallbackInfoReturnable<Set<String>> cir) {
//        HashSet<String> set = new HashSet<>(cir.getReturnValue().size() + 1);
//        set.addAll(cir.getReturnValue());
//        set.add(MemoryResourcePack.NAMESPACE);
//        cir.setReturnValue(set);
//    }
}
