package bot.inker.inkrender;

import bot.inker.inkrender.api.InkResourceLoader;
import bot.inker.inkrender.api.InkResourceLocator;
import bot.inker.inkrender.api.InkResourceService;
import bot.inker.inkrender.locators.FabricAdapterResourceLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceService implements InkResourceService {
    private static final Logger logger = LogManager.getLogger();
    private final List<InkResourceLocator> locators = new ArrayList<>();

    public ResourceService() {
        locators.add(new FabricAdapterResourceLocator());
    }

    @Override
    public void register(InkResourceLocator locator) {
        locators.add(locator);
    }

    @Override
    public Optional<InkResourceLoader> findModel(String name) {
        logger.debug("Find model {}", name);
        for (InkResourceLocator locator : locators) {
            try {
                Optional<InkResourceLoader> loader = locator.findModel(name);
                if (loader.isPresent()) {
                    logger.debug("Found {} from {}", name, loader);
                    return loader;
                }
            } catch (Exception e) {
                logger.error("Failed to locate resource from {}", locator, e);
            }
        }
        logger.debug("Model not found: {}", name);
        return Optional.empty();
    }
}
