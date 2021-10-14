package moe.sqwatermark.mobtalker.item;

import moe.sqwatermark.mobtalker.client.gui.GuiTalking;
import moe.sqwatermark.mobtalker.entity.passive.EntityFriendlyMob;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

public class ItemMobTalker extends Item {

    public ItemMobTalker() {
        super(new Properties().tab(ItemGroup.TAB_MISC).stacksTo(1));
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if (target instanceof CreeperEntity) {
            if (!playerIn.level.isClientSide) {
                EntityFriendlyMob entityFriendlyMob = new EntityFriendlyMob(playerIn.level);
                entityFriendlyMob.setPos(target.position().x, target.position().y, target.position().z);
                entityFriendlyMob.xRot = target.xRot;
                entityFriendlyMob.yRot = target.yRot;
                playerIn.level.addFreshEntity(entityFriendlyMob);
                target.remove();
            }
        }
        Minecraft.getInstance().setScreen(new GuiTalking());
        return super.interactLivingEntity(stack, playerIn, target, hand);
    }
}
