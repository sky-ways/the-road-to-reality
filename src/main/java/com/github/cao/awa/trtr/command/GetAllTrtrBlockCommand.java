package com.github.cao.awa.trtr.command;

import com.github.cao.awa.trtr.block.TrtrBlockItems;
import net.minecraft.server.MinecraftServer;

import static net.minecraft.server.command.CommandManager.literal;

public class GetAllTrtrBlockCommand {
    public static void register(MinecraftServer server) {
        server.getCommandManager()
              .getDispatcher()
              .register(literal("trtr").then(literal("test").then(literal("block").then(literal("all").executes(e -> {
                  TrtrBlockItems.ITEMS.forEach((k, v) -> {
                      e.getSource()
                       .getPlayer()
                       .giveItemStack(v.getDefaultStack());
                  });
                  return 0;
              })))));
    }
}
