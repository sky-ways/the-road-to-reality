package com.github.cao.awa.trtr.framework.nbt;

import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class NbtSerializeFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/NbtSerializeFramework");
    private final BlockFramework framework;

    public NbtSerializeFramework(BlockFramework framework) {
        this.framework = framework;
    }

    private void readNbt(Object entity, NbtCompound nbt, String callsName) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
                                                              entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      String name = field.getAnnotation(AutoNbt.class)
                                         .value();
                      name = name.equals("") ? field.getName() : name;

                      NbtElement element = nbt.get(name);

                      if (NbtSerializable.class.isAssignableFrom(field.getType()) && ensureAccessible(field.getType()
                                                                                                           .getConstructor()).newInstance() instanceof NbtSerializable serializer) {
                          serializer.fromNbt(element);
                          field.set(entity,
                                    serializer
                          );
                          LOGGER.debug("The {} reading nbt for field '{}': {}",
                                       callsName,
                                       field.getName(),
                                       element
                          );
                      } else {
                          NbtSerializer<?> nbtSerializer = this.framework.getNbtSerializer(field.getType());
                          if (nbtSerializer != null) {
                              field.set(entity,
                                        nbtSerializer.deserialize(element)
                              );
                              LOGGER.debug("The {} reading nbt for field '{}': {}",
                                           callsName,
                                           field.getName(),
                                           element
                              );
                          } else {
                              LOGGER.warn("The field in {} is not nbt serializable and missing serializer of type '{}'",
                                          callsName,
                                          field.getType()
                                               .getName()
                              );
                          }
                      }
                  });
              });
    }

    public void readNbt(BlockEntity entity, NbtCompound nbt) {
        readNbt(entity,
                nbt,
                "block entity '" + entity.getClass()
                                         .getName() + "' at " + entity.getPos()
        );
    }

    public void readNbt(Object entity, NbtCompound nbt) {
        readNbt(entity,
                nbt,
                "object '" + entity.getClass() + "'"
        );
    }

    public void writeNbt(BlockEntity entity, NbtCompound nbt) {
        writeNbt(entity,
                 nbt,
                 "block entity '" + entity.getClass()
                                          .getName() + "' at " + entity.getPos()
        );
    }

    public void writeNbt(Object entity, NbtCompound nbt) {
        writeNbt(entity,
                 nbt,
                 "object '" + entity.getClass() + "'"
        );
    }

    public void writeNbt(Object entity, NbtCompound nbt, String callsName) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
                                                              entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  String name = field.getAnnotation(AutoNbt.class)
                                     .value();
                  name = name.equals("") ? field.getName() : name;

                  NbtElement element = null;

                  String failedCause = null;

                  try {
                      Object f = field.get(entity);

                      if (f instanceof NbtSerializable serializable) {
                          element = serializable.toNbt();
                      } else {
                          NbtSerializer<?> nbtSerializer = this.framework.getNbtSerializer(field.getType());
                          if (nbtSerializer != null) {
                              element = nbtSerializer.serialize(EntrustEnvironment.cast(f));
                          } else {
                              failedCause = "'" + field.getName() + "' is not nbt serializable and missing serializer of type '" + field.getType()
                                                                                                                                        .getName() + "'";
                          }
                      }
                  } catch (Exception ex) {
                      failedCause = "exception " + ex;
                  }

                  if (element == null) {
                      if (failedCause == null) {
                          LOGGER.warn("Failed to write field '{}' as type '{}' to nbt for the {}  by unknown reason",
                                      field.getName(),
                                      field.getType()
                                           .getName(),
                                      callsName
                          );
                      } else {
                          LOGGER.warn("Failed to write field '{}' as type '{}' to nbt for the {} because {}",
                                      field.getName(),
                                      field.getType()
                                           .getName(),
                                      callsName,
                                      failedCause
                          );
                      }
                      return;
                  }

                  nbt.put(name,
                          element
                  );
                  LOGGER.debug("The {} has written nbt for field '{}': {}",
                               callsName,
                               field.getName(),
                               element
                  );
              });
    }

    public void init(BlockEntity entity) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.ensureAccessible(f,
                                                              entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      try {
                          NbtSerializer<?> serializer = this.framework.getNbtSerializer(field.getType());
                          if (serializer != null) {
                              field.set(entity,
                                        serializer.initializer()
                              );
                          } else {
                              Object o = ensureAccessible(field.getType()
                                                               .getConstructor()).newInstance();
                              if (o instanceof NbtSerializable serializable) {
                                  field.set(entity,
                                            serializable
                                  );
                                  LOGGER.debug("Block entity '{}' at {} initialized @AutoNbt field '{}' as type '{}'",
                                               entity.getClass()
                                                     .getName(),
                                               entity.getPos(),
                                               field.getName(),
                                               serializable.getClass()
                                                           .getName()
                                  );
                              }
                          }
                      } catch (Exception e) {
                          LOGGER.warn("Block entity '{}' at {} unable to initialize @AutoNbt field '{}' with type '{}'",
                                      entity.getClass()
                                            .getName(),
                                      entity.getPos(),
                                      field.getName(),
                                      field.getType()
                                           .getName()
                          );
                      }
                  });
              });
    }
}
