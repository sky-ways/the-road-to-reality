package bot.inker.inkrender;

import bot.inker.inkrender.render.InkRenderLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

public class InkerRender implements ModInitializer, ClientModInitializer {
    private static final ResourceService resourceService = new ResourceService();
    public static final String VERSION = "1.0.2";

    public static ResourceService resourceService() {
        return resourceService;
    }

    @Override
    public void onInitialize() {
    }

    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(new InkRenderLoader());
    }
}
