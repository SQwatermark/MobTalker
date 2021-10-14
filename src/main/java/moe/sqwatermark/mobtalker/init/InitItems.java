package moe.sqwatermark.mobtalker.init;

import moe.sqwatermark.mobtalker.MobTalker;
import moe.sqwatermark.mobtalker.item.ItemMobTalker;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class InitItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MobTalker.MOD_ID);

    public static RegistryObject<Item> MOB_TALKER = ITEMS.register("mob_talker", ItemMobTalker::new);

}
