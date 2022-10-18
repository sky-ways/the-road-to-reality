package bot.inker.inkrender.api;

import bot.inker.inkrender.render.resource.locators.*;

public interface InkResourceService extends InkResourceLocator {
    void register(InkResourceLocator locator);
}
