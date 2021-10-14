package moe.sqwatermark.mobtalker.client.renderer;

import moe.sqwatermark.mobtalker.entity.passive.EntityFriendlyMob;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EntityFriendlyMobRenderer extends MobRenderer<EntityFriendlyMob, CreeperModel<EntityFriendlyMob>> {

    private static final ResourceLocation CREEPER_LOCATION = new ResourceLocation("textures/entity/creeper/creeper.png");

    public EntityFriendlyMobRenderer(EntityRendererManager manager) {
        super(manager, new CreeperModel<>(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFriendlyMob entity) {
        return CREEPER_LOCATION;
    }

}