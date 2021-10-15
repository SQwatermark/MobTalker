package moe.sqwatermark.mobtalker.client.gui;

import moe.sqwatermark.mobtalker.client.session.SessionBase;
import moe.sqwatermark.mobtalker.client.session.SessionCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

import java.util.Locale;
import java.util.Vector;

//TODO 合并这俩GUI
public class GuiTalkingSelection extends GuiTalking {

    private final int BUTTON_HEIGHT = 20;
    private final int BUTTON_DEFAULT_WIDTH = 200;

    public GuiTalkingSelection(SessionBase script,String[] preLoadMsg,EnumFaces facePar) {
        this(script,facePar);
        this.Mesg = preLoadMsg;
        this.textSpeed = 0;
        this.face = facePar;
    }

    public GuiTalkingSelection(SessionBase script, EnumFaces facePar) {

        super(script);
        SessionCondition condSession = (SessionCondition)mainScript;
        this.sessionOptions = condSession.optionText;
        waitingForOption = true;
        this.face = facePar;
        super.setCharaImagePath(mainScript.getFacePath() + (face.toString().toLowerCase(Locale.ROOT)) + ".png");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init()
    {
        //controlList.clear();
        final int heightInit = height / 4;
        final int widthPos;
        final int diff=24;
        int buttonHeightPos = heightInit + 48;
        if (this.sessionOptions == null) {
            //controlList.clear();
        }
        else{
            int contSize=sessionOptions.size();
            int buttonWidth = getButtonWidth(sessionOptions);
            widthPos = (width - buttonWidth) / 2;
            switch (contSize) {
                case 1:
                    buttonHeightPos += diff;
                case 2:
                    buttonHeightPos += (diff/2);
            }
            for (int i = 0; i < contSize && i < 3; i++){
                int tmp = i;
                this.addButton(new Button(widthPos, buttonHeightPos, buttonWidth, BUTTON_HEIGHT, new StringTextComponent(this.sessionOptions.get(i)), (a) -> {
                    ((SessionCondition)this.mainScript).optionToken(tmp);
                    testAndSwapGui();
                }));
                buttonHeightPos+=diff;
            }
        }
    }

    private int getButtonWidth(Vector<String> parmOptions) {
        int result=BUTTON_DEFAULT_WIDTH;
        int tmp;
        for (String parmOption : parmOptions) {
            tmp = this.font.width(parmOption) + 5;
            if (tmp > result) result = tmp;
        }
        return result;
    }

    @Override
    protected boolean testAndSwapGui() {
        boolean result = false;
        if (!(mainScript instanceof SessionCondition)) {
            Minecraft Corl = Minecraft.getInstance();
            Corl.setScreen(new GuiTalking(this.mainScript));
            result = true;
        }
        return result;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.msgCompletedIndex >= 0) {
            this.msgShowCounts = this.Mesg[this.msgCompletedIndex].length();
        }
        else {
            this.msgShowCounts = this.Mesg[0].length();
        }
    }
}
