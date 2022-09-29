package bot.inker.inkrender.api;

public interface InkResourceService extends InkResourceLocator {
  void register(InkResourceLocator locator);
}
