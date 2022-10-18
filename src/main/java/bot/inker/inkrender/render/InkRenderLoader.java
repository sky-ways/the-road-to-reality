package bot.inker.inkrender.render;

import bot.inker.inkrender.InkerRender;
import bot.inker.inkrender.render.resource.loader.InkResourceLoader;
import bot.inker.inkrender.util.UncheckUtil;
import de.javagl.obj.*;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InkRenderLoader implements ModelResourceProvider, Function<ResourceManager, ModelResourceProvider> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) {
        return loadModelResource(
                identifier,
                modelProviderContext,
                ModelTransformation.NONE
        );
    }

    protected UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext, ModelTransformation transform) {
        // logger.info("loadModelResource({},{},{})", identifier, modelProviderContext, transform);
        if (identifier.getPath()
                      .endsWith(".obj")) {
            Optional<InkResourceLoader> resource = InkerRender.resourceService()
                                                              .findModel(identifier.toString());
            if (resource.isEmpty()) {
                return null;
            }
            return loadModel(
                    resource.get(),
                    transform
            );
        }

        return null;
    }

    public InkRenderUnbakedModel loadModel(InkResourceLoader resourceLoader, ModelTransformation transform) {
        // logger.info("loadModel({},{})", resourceLoader, transform);
        try {
            Obj obj = ObjUtils.convertToRenderable(ObjReader.read(new InputStreamReader(
                    resourceLoader.openObj(),
                    StandardCharsets.UTF_8
            )));
            return new InkRenderUnbakedModel(
                    resourceLoader,
                    ObjUtils.triangulate(obj),
                    loadMTL(
                            resourceLoader,
                            obj.getMtlFileNames()
                    ),
                    transform
            );
        } catch (Exception e) {
            LOGGER.error(
                    "Failed to load obj model",
                    e
            );
            return null;
        }
    }

    public Map<String, Mtl> loadMTL(InkResourceLoader resourceLoader, List<String> names) {
        return names.stream()
                    .flatMap(UncheckUtil.cast(task -> MtlReader.read(resourceLoader.openMtl(task))
                                                               .stream()))
                    .collect(Collectors.toMap(
                            Mtl::getName,
                            Function.identity()
                    ));
    }

    @Override
    public ModelResourceProvider apply(ResourceManager manager) {
        return this;
    }
}
