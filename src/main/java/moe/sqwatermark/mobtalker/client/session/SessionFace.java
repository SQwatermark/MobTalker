package moe.sqwatermark.mobtalker.client.session;

import moe.sqwatermark.mobtalker.MobTalker;
import moe.sqwatermark.mobtalker.client.gui.EnumFaces;

import java.util.Locale;

/**
 * 迁移完成
 * 2021/10/15
 */
public class SessionFace extends SessionBase {

    private static final String FACE_CODE="#FACE";
    protected EnumFaces face = EnumFaces.NORMAL;

    public SessionFace() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public SessionBase addCode(String code) {
        if (!code.contains(FACE_CODE)) return this;
        String subCode = code.substring(FACE_CODE.length() + 1);
        subCode = subCode.replace(" ", "");
        //subCode = subCode.substring(0, 1).toUpperCase() + subCode.substring(1).toLowerCase();
        try{
            this.face = EnumFaces.valueOf(subCode.toUpperCase(Locale.ROOT));
        } catch(Throwable ignored) {
            //MobTalker.LOGGER.warn(code + "脸不对头");
        }
        if (face == null) {
            face = EnumFaces.NORMAL;
        }
        return this;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public String[] getContent() {
        return null;
    }

    @Override
    public EnumFaces getFace() {
        return this.face;
    }

    @Override
    public boolean changeFace() {
        return true;
    }

    @Override
    public boolean hasMotion() {
        return false;
    }

    @Override
    public void motion() {

    }
}
