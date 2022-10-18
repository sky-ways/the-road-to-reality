package bot.inker.inkrender.render.resource.locators;

import bot.inker.inkrender.render.resource.loader.InkResourceLoader;
import bot.inker.inkrender.util.UncheckUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.Optional;

public class FabricAdapterResourceLocator implements InkResourceLocator {
    private static ResourceManager resourceManager;

    @Override
    public Optional<InkResourceLoader> findModel(String name) {
        Identifier identifier = Identifier.tryParse(name);
        if (identifier == null || getResourceManager().getResource(Identifier.of(identifier.getNamespace(), "models/" + identifier.getPath())).isEmpty()) {
            return Optional.empty();
        }
        int lastSplitIndex = identifier.getPath().lastIndexOf('/') + 1;
        return Optional.of(new AdapterResourceLoader(identifier.getNamespace(), identifier.getPath().substring(0, lastSplitIndex), identifier.getPath().substring(lastSplitIndex)));
    }

    private ResourceManager getResourceManager() {
        if (resourceManager == null) {
            resourceManager = MinecraftClient.getInstance().getResourceManager();
        }
        return resourceManager;
    }

    private class AdapterResourceLoader implements InkResourceLoader {
        private final String namespace;
        private final String path;
        private final String objName;

        public AdapterResourceLoader(String namespace, String path, String objName) {
            this.namespace = namespace;
            this.path = path;
            this.objName = objName;
        }

        @Override
        public InputStream openTexture(String name) {
            return UncheckUtil.uncheck(() -> getResourceManager().open(Identifier.of(namespace, "textures/" + path + name)));
        }

        @Override
        public InputStream openMtl(String name) {
            return UncheckUtil.uncheck(() -> getResourceManager().open(Identifier.of(namespace, "models/" + path + name)));
        }

        @Override
        public InputStream openObj() {
            return UncheckUtil.uncheck(() -> getResourceManager().open(Identifier.of(namespace, "models/" + path + objName)));
        }

        @Override
        public String toString() {
            return "FabricAdapterResourceLoader{namespace='" + namespace + "', path='" + path + "', objName='" + objName + "'}";
        }
    }
}
