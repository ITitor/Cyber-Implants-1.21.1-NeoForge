package net.ititor.cyber_implants.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.network.SendCyberPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class CyberImplantsScreen extends Screen {

    public CyberImplantsScreen() {
        super(Component.literal(""));
    }

    private static final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/cyber_menu.png");
    Button[] buttons = {null, null, null, null, null, null, null, null, null, null};
    Button[] buttons1 = {null, null, null, null, null, null, null, null, null, null};
    int page = 0;

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        int x = width/2;
        int y = height/2;
        Player player = Minecraft.getInstance().player;

        if (page == 0) {
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                createButton(buttons[i], x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        PacketDistributor.sendToServer(new SendCyberPacket(false, 0));
                        page = finalI+1;
                        init();
                    }
                }, "tooltip.cyber_implants.button" + i);
            }

            for (int i = 3; i < 6; i++) {
                int finalI = i;
                createButton(buttons[i], x - 112 + 16 + (80 * (i-3)), y + 12, but -> {
                    if (true) {
                        PacketDistributor.sendToServer(new SendCyberPacket(false, 0));
                        page = finalI+1;
                        init();
                    }
                }, "tooltip.cyber_implants.button" + i);
            }
        }
        else if (page == 1){
            for (int i = 0; i < 2; i++) {
                int finalI = i;
                createButton(buttons1[i], x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        PacketDistributor.sendToServer(new SendCyberPacket(true, finalI));
                        init();
                    }
                }, "tooltip.cyber_implants.implant" + i, i);
            }

//            for (int i = 3; i < 6; i++) {
//                createButton(buttons1[i], x - 112 + 16 + (80 * (i-3)), y + 12, but -> {
//                    if (true) {
//                        PacketDistributor.sendToServer(new SendCyberPacket(true, ));
//                        init();
//                    }
//                }, "tooltip.cyber_implants.implant" + i);
//            }
        }
    }

    private void createButton(Button voidButton, int x, int y, Button.OnPress press, String tooltip){
        voidButton = new VoidButton(x, y, 32, 32, press);
        this.addRenderableWidget(voidButton);
        voidButton.setTooltip(Tooltip.create(Component.translatable(tooltip)));
    }
    private void createButton(Button voidButton, int x, int y, Button.OnPress press, String tooltip, int id){
        voidButton = new VoidButton(x, y, 32, 32, press);
        this.addRenderableWidget(voidButton);
        voidButton.setTooltip(Tooltip.create(Component.translatable(tooltip)));
        voidButton.active = !ClientData.implant[id];
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        super.render(gui, mouseX, mouseY, partialTick);
        int x = width/2;
        int y = height/2;
        Font font = Minecraft.getInstance().font;

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, GUI);
        RenderSystem.enableBlend();

        gui.blit(GUI, x-160, y-100, 0, 0, 320, 200,
                640, 640);

        if (page == 0) {
            for (int i = 0; i < 3; i++) {
                gui.blit(GUI, x - 114 +16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
            }
            for (int i = 3; i < 6; i++) {
                gui.blit(GUI, x - 114 +16 + (80 * (i - 3)), y + 10, 350, 14, 36, 40, 640, 640);
            }

//            gui.blit(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "textures/gui/titan_bone.png"),
//                    x - 112 +16, y - 52, 0, 0, 32, 32, 32, 32);
        }else if (page == 1){
            for (int i = 0; i < 2; i++) {
                gui.blit(GUI, x - 114 +16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
            }
//            for (int i = 3; i < 6; i++) {
//                gui.blit(GUI, x - 114 +16 + (80 * (i - 3)), y + 10, 350, 14, 36, 40, 640, 640);
//            }

        }

        /**debug**/

        gui.drawCenteredString(font,  ClientData.implant[0]+"/"+page, x, y - 100, Color.WHITE.getRGB());

        /**debug**/

        RenderSystem.disableBlend();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
