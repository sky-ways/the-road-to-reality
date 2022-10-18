package bot.inker.inkrender.render.resource.locators;

import bot.inker.inkrender.render.resource.loader.*;

import java.util.Optional;

public interface InkResourceLocator {
    Optional<InkResourceLoader> findModel(String name);
}
