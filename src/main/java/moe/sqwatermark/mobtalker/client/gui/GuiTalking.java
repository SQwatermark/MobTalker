package moe.sqwatermark.mobtalker.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import moe.sqwatermark.mobtalker.MobTalker;
import moe.sqwatermark.mobtalker.client.session.SessionBase;
import moe.sqwatermark.mobtalker.client.session.SessionCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

import java.util.Locale;
import java.util.Vector;

public class GuiTalking extends Screen {

    private static final ResourceLocation DIALOG_BOX_LOCATION = new ResourceLocation(MobTalker.MOD_ID, "textures/gui/square.png");

    protected String[] Mesg;
    protected SessionBase mainScript;
    protected EnumFaces face = EnumFaces.NORMAL;

    // 不要直接对其赋值！用setCharaImagePath()
    private String CharaImagePath;

    public static final int DIALOG_BOX_WIDTH = 300;
    public static final int DIALOG_BOX_HEIGHT = 60;
    public static final int CHARA_IMAGE_HEIGHT = 287;
    public static final int CHARA_IMAGE_WIDTH = 230;
    public static final int TEXT_HEIGHT_UNIT = 9;

    //protected int textSpeed = mod_Mobtalker.mainOption.getMesgSpeed();
    protected int textSpeed = 1;

    private int tick = 0;
    protected int msgShowCounts = 1;
    protected int msgCompletedIndex = -1;

    protected static boolean isHidden = false;

    protected Vector<String> sessionOptions = null;
    protected boolean waitingForOption = false;

    public GuiTalking(SessionBase session) {
        super(new StringTextComponent("Mob Talker"));
        mainScript = session;
        setCharaImagePath(mainScript.getFacePath() + (face.toString().toLowerCase(Locale.ROOT)) + ".png");

//        do {
            this.getScriptContent();
//            if (mainScript.hasNext() && (mainScript.hasMotion() || mainScript.changeFace())) {
//                showNextContent();
//            }
//        } while (mainScript.hasNext() && (mainScript.hasMotion() || mainScript.changeFace()));
    }

    //下一个对话是选项的时候，返回true，并且打开选择GUI
    protected boolean testAndSwapGui() {
        boolean result = false;
        if (mainScript instanceof SessionCondition) {
            Minecraft Corl = Minecraft.getInstance();
            Corl.setScreen(new GuiTalkingSelection(this.mainScript, this.Mesg, this.face));
            result = true;
        }
        return result;
    }

    protected boolean setCharaImagePath(String imagePath) {
        if (isValidPath(imagePath)) {
            CharaImagePath = imagePath;
            return true;
        }
        MobTalker.LOGGER.warn("Invalid image path：" + imagePath);
        return false;
    }

    private static boolean isValidPath(String pathIn) {
        for(int i = 0; i < pathIn.length(); ++i) {
            if (!ResourceLocation.validPathChar(pathIn.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected void getScriptContent() {
        if (testAndSwapGui())
            return;
        if (mainScript.changeFace()) {
            face = mainScript.getFace();
            setCharaImagePath(mainScript.getFacePath() + (face.toString().toLowerCase(Locale.ROOT) + ".png"));
        }
        if (mainScript.hasContent()) {
            //textSpeed = mod_Mobtalker.mainOption.getMesgSpeed();
            Mesg = mainScript.getContent();
            this.tick = 0;
            this.msgCompletedIndex = -1;
            this.msgShowCounts = 0;
        }

        if (mainScript.hasMotion())
            mainScript.motion();
    }

    protected void showNextContent() {
        //boolean swaped = false;
        if (mainScript == null) {
            this.onClose();
            return;
        }
        if (mainScript.hasNext()) {
            do {
                mainScript = mainScript.getNext();
                //swaped = (mainScript instanceof SessionCondition);
                this.getScriptContent();
            } while (mainScript.hasNext() && (mainScript.changeFace() || mainScript.hasMotion()));
        }
        /*
         * if (!mainScript.hasNext() && !swaped) mc.thePlayer.closeScreen();
         */
    }

    @Override
    public void tick() {
        final int MSG_TOTAL;
        super.tick();
        if (!this.mainScript.hasContent() || this.Mesg == null) {
            if (this.Mesg != null)
                msgCompletedIndex = this.Mesg.length - 1;
            return;
        }
        if (msgCompletedIndex + 1 < Mesg.length)
            MSG_TOTAL = this.Mesg[msgCompletedIndex + 1].length();
        else {
            MSG_TOTAL = 0;
            // this.textSpeed = 0;
        }
        if (this.textSpeed == 0)
            this.msgShowCounts = MSG_TOTAL;
        if (this.msgShowCounts < MSG_TOTAL) {
            this.tick++;
            if (this.tick % this.textSpeed == 0) {
                msgShowCounts++;
            }
        } else if (this.msgCompletedIndex < this.Mesg.length - 1) {
            this.msgCompletedIndex++;
            msgShowCounts = 0;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        //renderBackground(matrixStack);
        renderCharacter(matrixStack);

        if (!isHidden && Mesg != null) {
            renderDialogBox(matrixStack);

            int i = (this.width - DIALOG_BOX_WIDTH) / 2;
            int j = (this.height - DIALOG_BOX_HEIGHT) - 2;

            if (SessionBase.nameSqr.getCharaName() != null) {
                drawString(matrixStack, font, SessionBase.nameSqr.getCharaName(), i + this.getNamePosWidth(), j - TEXT_HEIGHT_UNIT, SessionBase.nameSqr.getTextColor());
            }

            int textHeightOffset;
            for (int k = 0; k <= this.msgCompletedIndex && k < Mesg.length && k < SessionBase.MAX_SHOWN_LINES; k++) {
                textHeightOffset = TEXT_HEIGHT_UNIT * k;
                drawString(matrixStack,font, Mesg[k], i + 8, j + 7 + textHeightOffset, 0xffffff);
            }

            if (this.msgCompletedIndex < Mesg.length - 1 && this.msgCompletedIndex + 1 < SessionBase.MAX_SHOWN_LINES) {
                drawString(matrixStack, font, Mesg[this.msgCompletedIndex + 1].substring(0, this.msgShowCounts), i + 8, j + 7
                        + (msgCompletedIndex + 1) * TEXT_HEIGHT_UNIT, 0xffffff);
            }
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (waitingForOption)
            super.mouseClicked(mouseX, mouseY, button);
        else {
            assert minecraft != null;
            minecraft.getSoundManager().play(SimpleSound.forUI(SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON, 1.0F, 1.0F));
        }
        switch (button) {
            case 1:
                isHidden = !isHidden;
                break;
            case 0:
            default:
                /*
                 * if (this.textSpeed != 0) this.textSpeed = 0;
                 */
                if (isHidden)
                    isHidden = false;
                else {
                    if (Mesg != null && msgCompletedIndex + 1 < Math.min(Mesg.length, SessionBase.MAX_SHOWN_LINES)) {
                        msgCompletedIndex = SessionBase.MAX_SHOWN_LINES;
                    } else if (mainScript.hasNext()) {
                        showNextContent();
                        this.tick = 0;
                        if (!mainScript.hasContent()
                                && !(mainScript instanceof SessionCondition)) {
                            assert minecraft != null;
                            minecraft.setScreen(null);
                        }
                    } else {
                        assert minecraft != null;
                        minecraft.setScreen(null);
                    }
                }
        }
        return true;
    }

    protected void renderCharacter(MatrixStack matrixStack) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation location = new ResourceLocation(MobTalker.MOD_ID, CharaImagePath);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(location);
        int i = (width - CHARA_IMAGE_WIDTH) / 2;
        int j = height - (int)(CHARA_IMAGE_HEIGHT * 0.8);
        blit(matrixStack, i, j, 0, 0, CHARA_IMAGE_WIDTH, CHARA_IMAGE_HEIGHT, CHARA_IMAGE_WIDTH, CHARA_IMAGE_HEIGHT);
    }

    protected void renderDialogBox(MatrixStack matrixStack) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(DIALOG_BOX_LOCATION);
        int i = (this.width - DIALOG_BOX_WIDTH) / 2;
        int j = (this.height - DIALOG_BOX_HEIGHT) - 2;
        blit(matrixStack, i, j, 0, 0, DIALOG_BOX_WIDTH, DIALOG_BOX_HEIGHT, DIALOG_BOX_WIDTH, DIALOG_BOX_HEIGHT);
        RenderSystem.disableBlend();
    }

    private int getNamePosWidth() {
        NameTextSqr tmp = SessionBase.nameSqr;
        switch (tmp.getPostion()) {
            case MIDDLE:
                return (GuiTalking.DIALOG_BOX_WIDTH - font.width(tmp
                        .getCharaName())) / 2;
            case RIGHT:
                return GuiTalking.DIALOG_BOX_WIDTH
                        - font.width(tmp.getCharaName());
            case LEFT:
            default:
                return font.lineHeight;
        }
    }

}