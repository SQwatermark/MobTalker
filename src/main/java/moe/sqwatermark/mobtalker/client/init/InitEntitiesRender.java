package moe.sqwatermark.mobtalker.client.init;

import moe.sqwatermark.mobtalker.client.renderer.EntityFriendlyMobRenderer;
import moe.sqwatermark.mobtalker.entity.passive.EntityFriendlyMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class InitEntitiesRender {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent evt) {
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyMob.TYPE, EntityFriendlyMobRenderer::new);
    }
}