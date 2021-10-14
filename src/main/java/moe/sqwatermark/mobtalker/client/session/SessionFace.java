package moe.sqwatermark.mobtalker.client.session;

import moe.sqwatermark.mobtalker.client.gui.EnumFaces;

public class SessionFace extends SessionBase {

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public String[] getContent() {
        return new String[0];
    }

    @Override
    public EnumFaces getFace() {
        return null;
    }

    @Override
    public boolean changeFace() {
        return false;
    }

    @Override
    public boolean hasMotion() {
        return false;
    }

    @Override
    public void motion() {

    }
}
