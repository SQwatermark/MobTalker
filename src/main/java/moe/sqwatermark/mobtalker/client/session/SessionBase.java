package moe.sqwatermark.mobtalker.client.session;

import moe.sqwatermark.mobtalker.client.gui.EnumFaces;
import moe.sqwatermark.mobtalker.client.gui.EnumSpecHeaders;
import moe.sqwatermark.mobtalker.client.gui.NameTextSqr;
import moe.sqwatermark.mobtalker.entity.IFriendAble;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;

//TODO 把脚本逻辑改成索引表，链式的太混乱了
public abstract class SessionBase {

    public final static int MAX_SHOWN_LINES = 4;

    protected SessionBase next = null;
    public static String sourceName = "creeper";

    protected String playerName = "SQwatermark";

    protected LivingEntity motionTarget = null;

    public static NameTextSqr nameSqr = new NameTextSqr().setCharaName("库帕");

    public static boolean loadingFriendly = false;

    public SessionBase() {

    }

    public SessionBase(LivingEntity motionTar) {
        this.motionTarget = motionTar;
    }

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

    protected static SessionBase LoadScript(BufferedReader script,
                                            String sourceName) {
        return LoadScript(script, sourceName, 0);
    }

    protected static SessionBase LoadScript(BufferedReader script,
                                            String sourceName, int dayIndex) {
        SessionBase result;
        String codeTmp;
        try {
            codeTmp = script.readLine();
        } catch (IOException e) {
            return null;
        }
        if (codeTmp == null || codeTmp.equals("#END")) {
            try {
                script.close();
            } catch (IOException e) {
            }
            return null;
        }

        if (codeTmp.contains("#FACE"))
            result = new SessionFace();
        else if (EnumSpecHeaders.getEnumByStr(codeTmp) != null)
            result = new SessionSpecCode();
        else if (codeTmp.contains(SessionCondition.HEADER))
            result = new SessionCondition(sourceName, dayIndex);
        else
            result = new SessionText();
        result = result.addCode(codeTmp);
        result.setNext(LoadScript(script, sourceName, dayIndex));
        return result;
    }

    public void updateAllInheritDatas(LivingEntity motionTar, String playerNamePar) {
        SessionBase Loc = this;
        while (Loc != null) {
            Loc.motionTarget = motionTar;
            Loc.playerName = playerNamePar;
            Loc = Loc.next;
        }
    }

    public SessionBase getLast() {
        SessionBase tmp = this;
        while (tmp.hasNext())
            tmp = tmp.next;
        return tmp;
    }

    @Nonnull
    protected abstract SessionBase addCode(String code);

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

//    public static void preLoading() {
//        System.out.println("Start Preloading");
//        // if (!preLoad)
//        // return;
//        File scriptLoc = new File(Minecraft.getMinecraftDir(),
//                "/resources/mobTalker_script");
//        if (!scriptLoc.exists()) {
//            System.out.println(scriptLoc.getAbsolutePath() + " not found.");
//            return;
//        }
//
//        File tmpSubDir;
//        Vector<String> resultTmp = new Vector();
//        String[] subDirTmp = scriptLoc.list();
//        for (int i = 0; i < subDirTmp.length; i++) {
//            tmpSubDir = new File(scriptLoc, subDirTmp[i]);
//            if (tmpSubDir.isDirectory())
//                resultTmp.add(subDirTmp[i]);
//        }
//        supportedEntityNames = new String[resultTmp.size()];
//        for (int i = 0; i < supportedEntityNames.length; i++) {
//            supportedEntityNames[i] = new String(resultTmp.get(i));
//            sourceName = supportedEntityNames[i];
//            dataBase.put(sourceName, getSessionFromStatic());
//        }
//        try {
//            loadingFriendly = true;
//            preLoadFriendly();
//            loadingFriendly = false;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}
