package moe.sqwatermark.mobtalker.entity.passive;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityFriendlyMob extends CreatureEntity {

    public static final EntityType<EntityFriendlyMob> TYPE = EntityType.Builder.<EntityFriendlyMob>of(EntityFriendlyMob::new, EntityClassification.CREATURE)
            .sized(0.6f, 1.5f).setTrackingRange(10).build("friendly_mob");

    protected EntityFriendlyMob(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
    }

    public EntityFriendlyMob(World world) {
        this(TYPE, world);
    }

}