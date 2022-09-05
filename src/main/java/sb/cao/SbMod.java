package sb.cao;

import net.fabricmc.api.*;
import sb.cao.item.*;

public class SbMod implements ModInitializer {
    @Override
    public void onInitialize() {
        SbItem.register();
    }
}
