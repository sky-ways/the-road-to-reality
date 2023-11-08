package com.github.cao.awa.trtr.framework.nbt.serializer.type.list;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.cao.awa.trtr.TrtrMod;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializable;
import com.github.cao.awa.trtr.framework.nbt.serializer.NbtSerializer;
import com.github.cao.awa.trtr.util.string.StringConcat;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NbtListSerializer implements NbtSerializer<List<?>> {
    private static String checkType(List<?> list) {
        String type = list.get(0)
                          .getClass()
                          .getName();
        for (Object o : list) {
            if (! type.equals(o.getClass()
                               .getName())) {
                return null;
            }
        }
        return type;
    }

    private static NbtSerializer<?> checkType(Object o) {
        if (o instanceof NbtSerializable) {
            return null;
        } else {
            NbtSerializer<?> serializer = TrtrMod.BLOCK_FRAMEWORK.nbtSerializeFramework()
                                                                 .getNbtSerializer(o.getClass());
            if (serializer == null) {
                throw new IllegalArgumentException(StringConcat.concat("The type '",
                                                                       o.getClass()
                                                                        .getName(),
                                                                       "' is not nbt serializable and missing serializer"
                ));
            } else {
                return serializer;
            }
        }
    }

    @Override
    public NbtElement serialize(List<?> objects) {
        if (objects.size() < 1) {
            return compound(compound -> compound.putString("l-e",
                                                           ""
            ));
        }
        String type = checkType(objects);
        NbtList list = new NbtList();
        if (type == null) {
            for (Object o : objects) {
                NbtElement element;

                if (o instanceof NbtSerializable serializable) {
                    element = serializable.toNbt();
                } else {
                    NbtSerializer<?> serializer = TrtrMod.BLOCK_FRAMEWORK.nbtSerializeFramework()
                                                                         .getNbtSerializer(o.getClass());
                    if (serializer != null) {
                        element = serializer.serialize(EntrustEnvironment.cast(o));
                    } else {
                        element = null;
                    }
                }

                if (element == null) {
                    throw new IllegalArgumentException(StringConcat.concat("The type '",
                                                                           o.getClass()
                                                                            .getName(),
                                                                           "' is not nbt serializable and missing serializer"
                    ));
                }

                NbtCompound compound = compound(c -> {
                    c.putString("t",
                                o.getClass()
                                 .getName()
                    );
                    c.put("e",
                          element
                    );
                });

                list.add(compound);
            }

            return compound(compound -> {
                compound.put("l",
                             list
                );
                compound.putString("t",
                                   "*"
                );
            });
        } else {
            NbtSerializer<?> serializer = checkType(objects.get(0));

            for (Object o : objects) {
                if (serializer == null) {
                    list.add(Objects.requireNonNull(EntrustEnvironment.cast(o,
                                                                            NbtSerializable.class
                                    ))
                                    .toNbt());
                } else {
                    list.add(serializer.serialize(EntrustEnvironment.cast(o)));
                }
            }

            return compound(compound -> {
                compound.put("l",
                             list
                );
                compound.putString("t",
                                   type
                );
            });
        }
    }

    @Override
    public List<?> deserialize(NbtElement element) {
        return as(element,
                  NbtCompound.class,
                  compound -> {
                      String type = compound.getString("t");
                      if (type.equals("*")) {
                          return as(compound.get("l"),
                                    NbtList.class,
                                    list -> {
                                        ArrayList<?> arrayList = ApricotCollectionFactor.arrayList();
                                        for (NbtElement nbtElement : list) {
                                            arrayList.add(EntrustEnvironment.cast(is(nbtElement,
                                                                                     NbtCompound.class,
                                                                                     data -> {
                                                                                         String detailsType = data.getString("t");
                                                                                         try {
                                                                                             Class<?> typeOf = Class.forName(detailsType);
                                                                                             if (NbtSerializable.class.isAssignableFrom(typeOf)) {
                                                                                                 Constructor<?> constructor = typeOf.getConstructor();
                                                                                                 NbtSerializable serializable = EntrustEnvironment.cast(constructor.newInstance(),
                                                                                                                                                        NbtSerializable.class
                                                                                                 );
                                                                                                 if (serializable == null) {
                                                                                                     throw new NullPointerException();
                                                                                                 }
                                                                                                 serializable.fromNbt(data.get("e"));
                                                                                                 return serializable;
                                                                                             } else {
                                                                                                 NbtSerializer<?> serializer = TrtrMod.BLOCK_FRAMEWORK.nbtSerializeFramework()
                                                                                                                                                      .getNbtSerializer(typeOf);

                                                                                                 return serializer.deserialize(data.get("e"));
                                                                                             }
                                                                                         } catch (Exception e) {
                                                                                             throw new IllegalArgumentException(StringConcat.concat("The type '",
                                                                                                                                                    detailsType,
                                                                                                                                                    "' is not nbt serializable and missing serializer"
                                                                                             ));
                                                                                         }
                                                                                     }
                                            )));
                                        }
                                        return arrayList;
                                    }
                          );
                      } else {
                          return as(compound.get("l"),
                                    NbtList.class,
                                    list -> {
                                        try {
                                            Class<?> typeOf = Class.forName(type);
                                            ArrayList<?> arrayList = ApricotCollectionFactor.arrayList();
                                            if (NbtSerializable.class.isAssignableFrom(typeOf)) {
                                                Constructor<?> constructor = typeOf.getConstructor();
                                                for (NbtElement nbtElement : list) {
                                                    NbtSerializable serializable = EntrustEnvironment.cast(constructor.newInstance(),
                                                                                                           NbtSerializable.class
                                                    );
                                                    if (serializable == null) {
                                                        continue;
                                                    }
                                                    serializable.fromNbt(nbtElement);
                                                    arrayList.add(EntrustEnvironment.cast(serializable));
                                                }
                                            } else {
                                                NbtSerializer<?> serializer = TrtrMod.BLOCK_FRAMEWORK.nbtSerializeFramework()
                                                                                                     .getNbtSerializer(typeOf);
                                                for (NbtElement nbtElement : list) {
                                                    arrayList.add(EntrustEnvironment.cast(serializer.deserialize(nbtElement)));
                                                }
                                            }
                                            return arrayList;
                                        } catch (Exception e) {
                                            throw new IllegalArgumentException(StringConcat.concat("The type '",
                                                                                                   type,
                                                                                                   "' is not nbt serializable and missing serializer"
                                            ));
                                        }
                                    }
                          );
                      }
                  }
        );
    }

    @Override
    public List<?> initializer() {
        return ApricotCollectionFactor.arrayList();
    }
}
