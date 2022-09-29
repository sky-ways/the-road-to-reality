package bot.inker.inkrender.locators;

import bot.inker.inkrender.api.InkResourceLoader;
import bot.inker.inkrender.api.InkResourceLocator;
import bot.inker.inkrender.util.UncheckUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.Optional;

public class FabricAdapterResourceLocator implements InkResourceLocator {
    private ResourceManager resourceManager;

    @Override
    public Optional<InkResourceLoader> findModel(String name) {
        Identifier identifier = Identifier.tryParse(name);
        if (identifier == null) {
            return Optional.empty();
        }
        if (resourceManager().getResource(Identifier.of(identifier.getNamespace(), "models/" + identifier.getPath())).isEmpty()) {
            return Optional.empty();
        }
        int lastSplitIndex = identifier.getPath().lastIndexOf('/');
        return Optional.of(new AdapterResourceLoader(identifier.getNamespace(), identifier.getPath().substring(0, lastSplitIndex + 1), identifier.getPath().substring(lastSplitIndex + 1)));
    }

    private ResourceManager resourceManager() {
        if (resourceManager != null) {
            return resourceManager;
        }
        synchronized (this) {
            if (resourceManager == null) {
                resourceManager = MinecraftClient.getInstance().getResourceManager();
            }
            return resourceManager;
        }
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
            return UncheckUtil.uncheck(() -> resourceManager().open(Identifier.of(namespace, "textures/" + path + name)));
        }

        @Override
        public InputStream openMtl(String name) {
            return UncheckUtil.uncheck(() -> resourceManager().open(Identifier.of(namespace, "models/" + path + name)));
        }

        @Override
        public InputStream openObj() {
            return UncheckUtil.uncheck(() -> resourceManager().open(Identifier.of(namespace, "models/" + path + objName)));
        }

        @Override
        public String toString() {
            return "FabricAdapterResourceLoader{" + "namespace='" + namespace + '\'' + ", path='" + path + '\'' + ", objName='" + objName + '\'' + '}';
        }
    }
}
