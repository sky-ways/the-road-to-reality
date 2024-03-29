package bot.inker.inkrender.render;

import bot.inker.inkrender.InkerRender;
import bot.inker.inkrender.render.resource.loader.InkResourceLoader;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InkRenderLoader implements ModelResourceProvider, Function<ResourceManager, ModelResourceProvider> {
    private static final Logger LOGGER = LogManager.getLogger("InkRenderLoader");

    @Override
    public UnbakedModel loadModelResource(Identifier identifier, ModelProviderContext modelProviderContext) {
        if (identifier.getPath()
                      .endsWith(".obj")) {
            Optional<InkResourceLoader> resource = InkerRender.resourceService()
                                                              .findModel(identifier);
            return resource.map(inkResourceLoader -> loadModel(
                                   inkResourceLoader,
                                   ModelTransformation.NONE
                           ))
                           .orElse(null);
        }

        return null;
    }

    public InkRenderUnbakedModel loadModel(InkResourceLoader resourceLoader, ModelTransformation transform) {
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
                    // Other way to do this
                    //.flatMap(str -> EntrustExecution.result(() -> MtlReader.read(resourceLoader.openMtl(str))
                    //                                                       .stream()))

                    // Old way to do this (Deprecated)
                    //.flatMap(UncheckUtil.cast(task -> MtlReader.read(resourceLoader.openMtl(task))
                    //                                           .stream()))
                    .flatMap(EntrustEnvironment.function(str -> MtlReader.read(resourceLoader.openMtl(str))
                                                                         .stream()))
                    .collect(Collectors.toMap(
                            Mtl :: getName,
                            Function.identity()
                    ));
    }

    @Override
    public ModelResourceProvider apply(ResourceManager manager) {
        return this;
    }
}
