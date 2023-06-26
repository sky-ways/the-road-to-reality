package bot.inker.inkrender.render.resource.service;

import bot.inker.inkrender.render.resource.loader.InkResourceLoader;
import bot.inker.inkrender.render.resource.locators.FabricAdapterResourceLocator;
import bot.inker.inkrender.render.resource.locators.InkResourceLocator;
import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ResourceService implements InkResourceService {
    private static final Logger LOGGER = LogManager.getLogger("InkResourceService");
    private final List<InkResourceLocator> locators = ApricotCollectionFactor.newArrayList();

    public ResourceService() {
        this.locators.add(new FabricAdapterResourceLocator());
    }

    @Override
    public void register(InkResourceLocator locator) {
        this.locators.add(locator);
    }

    public int registered() {
        return this.locators.size();
    }

    @Override
    public Optional<InkResourceLoader> findModel(String name) {
        LOGGER.debug("Find model {}",
                     name
        );
        for (InkResourceLocator locator : this.locators) {
            try {
                Optional<InkResourceLoader> loader = locator.findModel(name);
                if (loader.isPresent()) {
                    LOGGER.debug("Found {} from {}",
                                 name,
                                 loader
                    );
                    return loader;
                }
            } catch (Exception e) {
                LOGGER.error("Failed to locate resource from {}",
                             locator,
                             e
                );
            }
        }
        LOGGER.debug("Model not found: {}",
                     name
        );
        return Optional.empty();
    }

    public Optional<InkResourceLoader> findModel(Identifier identifier) {
        return findModel(identifier.toString());
    }
}
