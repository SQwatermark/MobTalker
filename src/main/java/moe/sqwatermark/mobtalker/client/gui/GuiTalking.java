package moe.sqwatermark.mobtalker.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import moe.sqwatermark.mobtalker.MobTalker;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class GuiTalking extends Screen {

    private static final ResourceLocation DIALOG_BOX_LOCATION = new ResourceLocation(MobTalker.MOD_ID, "textures/gui/square.png");

    public static final int DIALOG_BOX_WIDTH = 300;
    public static final int DIALOG_BOX_HEIGHT = 60;
    public static final int CHARA_IMAGE_HEIGHT = 287;
    public static final int CHARA_IMAGE_WIDTH = 230;
    public static final int TEXT_HEIGHT_UNIT = 9;

    public final String chatter = "Creeper";
    public String emotion = "Happy";

    public NameTextSqr tmp = new NameTextSqr().setCharaName("库帕");

    public GuiTalking() {
        super(new StringTextComponent("Mob Talker"));
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        //renderBackground(matrixStack);
        renderCharacter(matrixStack);
        renderDialogBox(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    protected void renderCharacter(MatrixStack matrixStack) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bind(new ResourceLocation(MobTalker.MOD_ID, "textures/characters/creeper/normal.png"));
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

        drawString(matrixStack, font, tmp.getCharaName(), i + this.getNamePosWidth(), j - TEXT_HEIGHT_UNIT, tmp.getTextColor());

        int textHeightOffset = TEXT_HEIGHT_UNIT * 0;
        drawString(matrixStack,font, "Creeper?", i + 8, j + 7 + textHeightOffset, 0xffffff);
        RenderSystem.disableBlend();
    }

    private int getNamePosWidth() {
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