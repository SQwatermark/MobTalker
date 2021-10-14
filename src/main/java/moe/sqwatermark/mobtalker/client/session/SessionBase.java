package moe.sqwatermark.mobtalker.client.session;

import moe.sqwatermark.mobtalker.client.gui.EnumFaces;
import moe.sqwatermark.mobtalker.client.gui.NameTextSqr;
import moe.sqwatermark.mobtalker.entity.IFriendAble;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;

public abstract class SessionBase {

    public final static int MAX_SHOWN_LINES = 4;

    protected SessionBase next = null;
    public static String sourceName = "creeper";

    protected String playerName = "SQwatermark";

    protected LivingEntity motionTarget = null;

    public static NameTextSqr nameSqr = new NameTextSqr().setCharaName("库帕");

    // 获取此对话的角色外观文件夹的路径
    public String getFacePath() {
        StringBuilder result = new StringBuilder();
        result.append( "textures/characters/").append(sourceName).append("/" );
        if (this.motionTarget instanceof IFriendAble) {
            IFriendAble tmp = (IFriendAble) this.motionTarget;
            if (tmp.hasSubChara()) result.append(tmp.getSubChara()).append("/");
        } else {
            String tmpS = null;
            if (this.motionTarget instanceof SlimeEntity) {
                SlimeEntity hook = (SlimeEntity) this.motionTarget;
                tmpS = String.valueOf(hook.getSize());
            }
            if (tmpS != null) {
                result.append(tmpS).append("/");
            }
        }
        return result.toString();
    }

    public abstract boolean hasContent();

    public abstract String[] getContent();

    public abstract EnumFaces getFace();

    public abstract boolean changeFace();

    public abstract boolean hasMotion();

    public abstract void motion();

    public boolean hasNext() {
        return (next != null);
    }

    public SessionBase getNext() {
        return next;
    }

    public void setNext(SessionBase parm) {
        this.next = parm;
    }

}
