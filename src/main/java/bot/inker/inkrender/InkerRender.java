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

    public static Block TEST_BLOCK;

    public static ResourceService resourceService() {
        return resourceService;
    }

    @Override
    public void onInitialize() {
        TEST_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.0f).nonOpaque());

        Registry.register(Registry.BLOCK, new Identifier("irender", "test"), TEST_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("irender", "test"), new BlockItem(TEST_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE)));
    }

    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(new InkRenderLoader());
    }
}
