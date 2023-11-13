package com.github.cao.awa.trtr.constant.campfire;

import com.github.cao.awa.apricot.util.collection.ApricotCollectionFactor;
import com.github.zhuaidadaya.rikaishinikui.handler.universal.entrust.EntrustEnvironment;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Map;

public class CampfireConstants {
    // TODO fuel map.
    public static Map<Item, Integer> fuels = EntrustEnvironment.operation(ApricotCollectionFactor.hashMap(),
                                                                          map -> {
                                                                              map.put(Items.STICK,
                                                                                      100
                                                                              );
                                                                              map.put(Items.COAL,
                                                                                      1000
                                                                              );
                                                                          }
    );
}
