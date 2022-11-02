package bot.inker.inkrender;

import bot.inker.inkrender.render.InkRenderLoader;
import bot.inker.inkrender.render.resource.service.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;

public class InkerRender implements ModInitializer, ClientModInitializer {
    private static final ResourceService resourceService = new ResourceService();
    public static final String VERSION = "1.0.4";

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
