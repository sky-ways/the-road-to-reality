package com.github.cao.awa.trtr.command;

import com.github.cao.awa.trtr.gas.BlockGas;
import com.github.cao.awa.trtr.gas.manager.WorldGasManager;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.server.command.CommandManager.literal;

public class GasCommand {
    public static void register(MinecraftServer server) {
        server.getCommandManager()
              .getDispatcher()
              .register(literal("gas").then(literal("getProperty").then(CommandManager.argument("pos",
                                                                                                Vec3ArgumentType.vec3()
                                                                                      )
                                                                                      .executes(context -> {
                                                                                          try {
                                                                                              Vec3d vec = Vec3ArgumentType.getVec3(context,
                                                                                                                                   "pos"
                                                                                              );
                                                                                              context.getSource()
                                                                                                     .sendFeedback(() -> {
                                                                                                                       BlockPos pos = new BlockPos(
                                                                                                                               (int) vec.x,
                                                                                                                               (int) vec.y,
                                                                                                                               (int) vec.z
                                                                                                                       );
                                                                                                                       BlockGas gas = WorldGasManager.GAS_MANAGER.getGas(
                                                                                                                               pos
                                                                                                                       );

                                                                                                                       if (gas != null) {
                                                                                                                           return Text.of(String.format("The gas pressure of (%s, %s, %s) is %s Pa",
                                                                                                                                                        pos.getX(),
                                                                                                                                                        pos.getY(),
                                                                                                                                                        pos.getZ(),
                                                                                                                                                        gas.pressure.value()
                                                                                                                           ));
                                                                                                                       } else {
                                                                                                                           return Text.of(String.format("The gas data of (%s, %s, %s) is not present",
                                                                                                                                                        pos.getX(),
                                                                                                                                                        pos.getY(),
                                                                                                                                                        pos.getZ()
                                                                                                                           ));
                                                                                                                       }
                                                                                                                   },
                                                                                                                   false
                                                                                                     );
                                                                                              return 0;
                                                                                          } catch (Exception e) {
                                                                                              e.printStackTrace();
                                                                                              return - 1;
                                                                                          }
                                                                                      })))
                                      .then(literal("isTicking").then(CommandManager.argument("pos",
                                                                                              Vec3ArgumentType.vec3()
                                                                                    )
                                                                                    .executes(context -> {
                                                                                        Vec3d vec = Vec3ArgumentType.getVec3(context,
                                                                                                                             "pos"
                                                                                        );
                                                                                        context.getSource()
                                                                                               .sendFeedback(() -> {
                                                                                                                 return Text.of(String.format("Is (%s, %s, %s) ticking: %s",
                                                                                                                                              vec.x,
                                                                                                                                              vec.y,
                                                                                                                                              vec.z,
                                                                                                                                              WorldGasManager.GAS_MANAGER.isTicking(new BlockPos(
                                                                                                                                                                                            (int) vec.x,
                                                                                                                                                                                            (int) vec.y,
                                                                                                                                                                                            (int) vec.z
                                                                                                                                                                                    )
                                                                                                                                              )
                                                                                                                 ));
                                                                                                             },
                                                                                                             false
                                                                                               );
                                                                                        return 0;
                                                                                    }))));
    }
}
