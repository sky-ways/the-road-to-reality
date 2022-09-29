package bot.inker.inkrender.api;

import java.util.Optional;

public interface InkResourceLocator {
  Optional<InkResourceLoader> findModel(String name);
}
