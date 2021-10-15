package moe.sqwatermark.mobtalker.item;

import moe.sqwatermark.mobtalker.client.gui.GuiTalking;
import moe.sqwatermark.mobtalker.client.session.SessionBase;
import moe.sqwatermark.mobtalker.client.session.SessionCondition;
import moe.sqwatermark.mobtalker.client.session.SessionFace;
import moe.sqwatermark.mobtalker.client.session.SessionText;
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

        //临时设置
        SessionText sessionText1 = new SessionText("歪比巴卜");
        SessionText sessionText2 = new SessionText("早上好(playername)$今天天气真好$");
        SessionBase sessionFace = new SessionFace().addCode("#FACE happy");
        SessionBase sessionFace1 = new SessionFace().addCode("#FACE normal");
        SessionBase sessionCondition = new SessionCondition().addCode("#CONDITION{[歪比巴卜歪比巴卜歪比巴卜歪比巴卜歪比巴卜歪比巴卜歪比巴卜歪比巴卜,Option1A],[月色真美,Option1B],[早上好,Option1C]},[早上好,Option1C]}");
        sessionText1.setNext(sessionFace);
        sessionFace.setNext(sessionText2);
        sessionText2.setNext(sessionFace1);
        sessionFace1.setNext(sessionCondition);

        Minecraft.getInstance().setScreen(new GuiTalking(sessionText1));
        return super.interactLivingEntity(stack, playerIn, target, hand);
    }
}