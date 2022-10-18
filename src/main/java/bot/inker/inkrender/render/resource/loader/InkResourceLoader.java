package bot.inker.inkrender.render.resource.loader;

import java.io.InputStream;

public interface InkResourceLoader {
    InputStream openTexture(String name);

    InputStream openMtl(String name);

    InputStream openObj();
}
