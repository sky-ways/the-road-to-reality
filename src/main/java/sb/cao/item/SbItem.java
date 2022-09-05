package sb.cao.item;

import net.minecraft.client.item.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class SbItem extends Item {
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    }

    public SbItem(Settings settings) {
        super(settings);
    }

    public static void register() {
        Settings settings = new Settings();
        SbItem item = new SbItem(settings);
        Identifier identifier = new Identifier("cao:sb");
        Registry.register(Registry.ITEM, identifier, item);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

    }
}
