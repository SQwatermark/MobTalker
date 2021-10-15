package moe.sqwatermark.mobtalker.client.session;

import moe.sqwatermark.mobtalker.client.gui.EnumFaces;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import javax.annotation.Nonnull;

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

    @Nonnull
    @Override
    protected SessionBase addCode(String code) {
        FontRenderer fontRender = Minecraft.getInstance().font;
        String overTmp;
        this.content=code.split("::");
        for (int i=0;i<this.content.length;i++){
            int overCount=0;
            //TODO
//            String ttt = this.content[i].substring(0, content[i].length()-overCount);
//            while(fontRender.width("你脑子秀逗了吗？现在是早上啊！") > TALKING_SQR_WIDTH) {
//                overCount++;
//                ttt = this.content[i].substring(0, content[i].length()-overCount);
//            }
//            if (overCount!=0) {
//                overTmp=content[i].substring(content[i].length()-overCount);
//                content[i]=content[i].substring(0, content[i].length()-overCount);
//                String[] ansArray=new String[content.length+1];
//                System.arraycopy(content, 0, ansArray, 0, i + 1);
//                ansArray[i+1]=overTmp;
//                if (content.length - (i + 1) >= 0)
//                    System.arraycopy(content, i + 1, ansArray, i + 1 + 1, content.length - (i + 1));
//                this.content=ansArray;
//            }
            overTmp=null;
        }
        if (this.content.length> MAX_SHOWN_LINES){
            String[] resultTmp=new String[MAX_SHOWN_LINES];
            for (int i=0;i<this.content.length;i++){
                if (i< MAX_SHOWN_LINES) resultTmp[i] = this.content[i];
                else {
                    resultTmp[MAX_SHOWN_LINES-1]=resultTmp[MAX_SHOWN_LINES-1]+this.content[i];
                }
            }
            this.content=resultTmp;
        }
        replacePlayerName();
        return this;
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
