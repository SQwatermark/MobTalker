package moe.sqwatermark.mobtalker.client.session;

import moe.sqwatermark.mobtalker.client.gui.EnumFaces;

public class SessionText extends SessionBase {

    private static String PLAYNAME_REPLACE_CODE = "(playername)";
    private final int TALKING_SQR_WIDTH = 280;
    private String[] content;
    protected SessionBase next = null;

    public SessionText() {
        super();
    }

    public SessionText(String defaultSpeech){
        this.content = defaultSpeech.split("\\$");
        replacePlayerName();
    }

    private void replacePlayerName() {
        if (this.playerName == null || this.playerName.isEmpty()) return;
        if (this.content == null || this.content.length <= 0) return;
        for (int i = 0;i < content.length; i++) {
            if (this.content[i].contains(PLAYNAME_REPLACE_CODE)){
                this.content[i] = this.content[i].replaceAll(PLAYNAME_REPLACE_CODE, this.playerName).replace("(", "").replace(")", "");
            }
        }
    }

    @Override
    public String[] getContent() {
        //replacePlayerName();
        return this.content;
    }

    @Override
    protected SessionBase addCode(String code) {
        return null;
    }

    @Override
    public boolean hasContent() {
        return (content!=null);
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
