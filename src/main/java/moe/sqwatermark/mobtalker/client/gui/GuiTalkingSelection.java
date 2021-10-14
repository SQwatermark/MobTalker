package moe.sqwatermark.mobtalker.client.gui;

import moe.sqwatermark.mobtalker.client.session.SessionBase;

public class GuiTalkingSelection extends GuiTalking {

    public GuiTalkingSelection(SessionBase script,String[] preLoadMsg,EnumFaces facePar) {
        this(script,facePar);
        this.Mesg=preLoadMsg;
        this.textSpeed=0;
        this.face=facePar;
    }

    public GuiTalkingSelection(SessionBase script,EnumFaces facePar) {

        super(script);
//        SessionCondition condSession=(SessionCondition)mainScript;
//        this.sessionOptions=condSession.optionText;
//        waitingForOption=true;
//        this.face=facePar;
//        CharaImagePath = mainScript.getFacePath() + (face.toString())
//                + ".png";
//        // TODO Auto-generated constructor stub
    }
}
