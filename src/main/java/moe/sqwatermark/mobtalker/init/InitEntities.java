package moe.sqwatermark.mobtalker.init;

import moe.sqwatermark.mobtalker.MobTalker;
import moe.sqwatermark.mobtalker.entity.passive.EntityFriendlyMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class InitEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MobTalker.MOD_ID);

    public static RegistryObject<EntityType<EntityFriendlyMob>> FRIENDLY_MOB = ENTITY_TYPES.register("friendly_mob", () -> EntityFriendlyMob.TYPE);

    @SubscribeEvent
    public static void addEntityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityFriendlyMob.TYPE, MonsterEntity.createMonsterAttributes().build());
    }
}