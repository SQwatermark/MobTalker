package moe.sqwatermark.mobtalker.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public interface IFriendAble {
	int FOLLOW_FLAG_INDEX=20;
	int ESCORT_FLAG_INDEX=21;
	int ACTIVE_ATTACK_FLAG_INDEX=22;
	boolean hasSubChara();
	String getSubChara();
	int getLovePoint(String playerName);
	void increaseLove(int value,String playerName);
	void decreaseLove(int value,String playerName);
	boolean isBlockingInteractive(String playerName);
	int getLivingDays();
	void IncreaseLivingDays();
	void updateDays();
	void setDayFinish();
	boolean isDayFinished();
	void actionAttack(int stranthOffset);
	void shadowInit(LivingEntity motionTarget);
	void revInit(LivingEntity revTarget);
	boolean isActiveAttack();
	void setActiveAttack(boolean activeAttack);
	void switchActiveAttack();
	boolean isFollowFlag() ;
	void setFollowFlag(boolean followFlag);
	void switchFollowFlag();
	boolean isEscortFlag();
	void setEscortFlag(boolean escortFlag);
	void switchEscortFlag();
	World getWorld();
}
