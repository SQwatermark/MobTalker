package moe.sqwatermark.mobtalker;

import moe.sqwatermark.mobtalker.init.InitEntities;
import moe.sqwatermark.mobtalker.init.InitItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MobTalker.MOD_ID)
public class MobTalker
{
    public static final String MOD_ID = "mobtalker";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public MobTalker() {
        InitEntities.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        InitItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
