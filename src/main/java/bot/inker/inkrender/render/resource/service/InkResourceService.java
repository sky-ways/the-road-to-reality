package bot.inker.inkrender.render.resource.service;

import bot.inker.inkrender.render.resource.locators.*;

public interface InkResourceService extends InkResourceLocator {
    void register(InkResourceLocator locator);
}
