package com.github.cao.awa.trtr.framework.nbt;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.annotation.serializer.AutoNbt;
import com.github.cao.awa.trtr.framework.block.BlockFramework;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import com.github.cao.awa.trtr.framework.nbt.serializer.type.item.NbtItemStackSerializer;
import com.github.cao.awa.trtr.framework.nbt.serializer.type.list.NbtListSerializer;
import com.github.cao.awa.trtr.framework.nbt.serializer.type.raw.*;
import com.github.cao.awa.trtr.framework.reflection.ReflectionFramework;
import com.github.cao.awa.trtr.util.string.StringConcat;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NbtSerializeFramework extends ReflectionFramework {
    private static final Logger LOGGER = LogManager.getLogger("Trtr/NbtSerializeFramework");
    private final Map<Class<?>, NbtSerializer<?>> nbtSerializers = ApricotCollectionFactor.newHashMap();
    private final BlockFramework framework;

    public NbtSerializeFramework(BlockFramework framework) {
        this.framework = framework;

        initNbtSerializers();
    }

    private void initNbtSerializers() {
        registerNbtSerializer(new NbtItemStackSerializer(),
                              ItemStack.class
        );
        registerNbtSerializer(new NbtBooleanSerializer(),
                              boolean.class,
                              Boolean.class
        );
        registerNbtSerializer(new NbtByteSerializer(),
                              byte.class,
                              Byte.class
        );
        registerNbtSerializer(new NbtCharSerializer(),
                              char.class,
                              Character.class
        );
        registerNbtSerializer(new NbtShortSerializer(),
                              short.class,
                              Short.class
        );
        registerNbtSerializer(new NbtIntSerializer(),
                              int.class,
                              Integer.class
        );
        registerNbtSerializer(new NbtLongSerializer(),
                              long.class,
                              Long.class
        );
        registerNbtSerializer(new NbtBigIntegerSerializer(),
                              BigInteger.class
        );
        registerNbtSerializer(new NbtFloatSerializer(),
                              float.class,
                              Float.class
        );
        registerNbtSerializer(new NbtDoubleSerializer(),
                              double.class,
                              Double.class
        );
        registerNbtSerializer(new NbtBigDecimalSerializer(),
                              BigDecimal.class
        );
        registerNbtSerializer(new NbtStringSerializer(),
                              String.class
        );
        registerNbtSerializer(new NbtListSerializer(),
                              List.class
        );
    }

    public final <T> void registerNbtSerializer(NbtSerializer<T> serializer, @Nullable Class<?>... matchType) {
        if (matchType == null) {
            return;
        }
        for (Class<?> t : matchType) {
            this.nbtSerializers.put(t,
                                    serializer
            );
        }
    }

    public <T> NbtSerializer<T> getNbtSerializer(Class<T> type) {
        if (type == null) {
            return null;
        }
        NbtSerializer<T> serializer = EntrustEnvironment.cast(this.nbtSerializers.get(type));
        if (serializer == null) {
            serializer = EntrustEnvironment.cast(getNbtSerializer(type.getSuperclass()));
            if (serializer == null) {
                for (Class<?> aInterface : type.getInterfaces()) {
                    serializer = EntrustEnvironment.cast(getNbtSerializer(aInterface));
                    if (serializer != null) {
                        break;
                    }
                }
            }
        }
        return serializer;
    }

    private void readNbt(Object entity, NbtCompound nbt, String callsName) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.accessible(f,
                                                        entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      String name = field.getAnnotation(AutoNbt.class)
                                         .value();
                      name = name.equals("") ? field.getName() : name;

                      NbtElement element = nbt.get(name);

                      if (NbtSerializable.class.isAssignableFrom(field.getType()) && accessible(field.getType()
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
                          NbtSerializer<?> nbtSerializer = getNbtSerializer(field.getType());
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
                StringConcat.concat("block entity '",
                                    entity.getClass()
                                          .getName(),
                                    "' at ",
                                    entity.getPos()
                )
        );
    }

    public void readNbt(Object entity, NbtCompound nbt) {
        readNbt(entity,
                nbt,
                StringConcat.concat("object '",
                                    entity.getClass(),
                                    "'"
                )
        );
    }

    public void writeNbt(BlockEntity entity, NbtCompound nbt) {
        writeNbt(entity,
                 nbt,
                 StringConcat.concat("block entity '",
                                     entity.getClass()
                                           .getName(),
                                     "' at ",
                                     entity.getPos()
                 )
        );
    }

    public void writeNbt(Object entity, NbtCompound nbt) {
        writeNbt(entity,
                 nbt,
                 StringConcat.concat("object '",
                                     entity.getClass(),
                                     "'"
                 )
        );
    }

    public void writeNbt(Object entity, NbtCompound nbt, String callsName) {
        Arrays.stream(entity.getClass()
                            .getDeclaredFields())
              .peek(f -> ReflectionFramework.accessible(f,
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
                          NbtSerializer<?> nbtSerializer = getNbtSerializer(field.getType());
                          if (nbtSerializer != null) {
                              element = nbtSerializer.serialize(EntrustEnvironment.cast(f));
                          } else {
                              failedCause = StringConcat.concat("'",
                                                                field.getName(),
                                                                "' is not nbt serializable and missing serializer of type '",
                                                                field.getType()
                                                                     .getName(),
                                                                "'"
                              );
                          }
                      }
                  } catch (Exception ex) {
                      failedCause = StringConcat.concat("exception ",
                                                        ex
                      );
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
              .peek(f -> ReflectionFramework.accessible(f,
                                                        entity
              ))
              .filter(f -> f.isAnnotationPresent(AutoNbt.class))
              .forEach(field -> {
                  EntrustEnvironment.trys(() -> {
                      try {
                          NbtSerializer<?> serializer = getNbtSerializer(field.getType());
                          if (serializer != null) {
                              field.set(entity,
                                        serializer.initializer()
                              );
                          } else {
                              Object o = accessible(field.getType()
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
