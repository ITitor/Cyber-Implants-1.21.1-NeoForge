package net.ititor.cyber_implants.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.network.SendCyberPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
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

    public static final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/cyber_menu.png");
    Button[] buttons = {null, null, null, null, null, null, null, null, null, null};
    Button[] buttons1 = {null, null, null, null, null, null, null, null, null, null};
    Button upButton = null;
    Button backButton = null;
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
                createButton(i, x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        page = finalI+1;
                        init();
                    }
                }, "tooltip.cyber_implants.button" + i);
            }
            for (int i = 3; i < 6; i++) {
                int finalI = i;
                createButton(i, x - 112 + 16 + (80 * (i-3)), y + 12, but -> {
                    if (true) {
                        page = finalI+1;
                        init();
                    }
                }, "tooltip.cyber_implants.button" + i);
            }
        }
        else if (page == 1){
            for (int i = 0; i < 2; i++) {
                int finalI = i;
                createButton1(i, x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.implant" + finalI)
                        .append(Component.literal(" DDDDD Uwu dDDudUDUau fjhafh"));

                        open = finalI;

                        if (drawTooltip){
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                        }else if (upButton != null){
                            this.removeWidget(upButton);
                        }
                    }
                }, "tooltip.cyber_implants.implant" + i);
            }
            createBackButton(x, y);
        }else if (page == 2){
            for (int i = 0; i < 2; i++) {
                int finalI = i+2;
                createButton1(i, x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.implant" + (finalI))
                        .append(Component.literal(" DDDDD Uwu dDDudUDUau fjhafh"));

                        open = finalI;

                        if (drawTooltip){
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                        }else if (upButton != null){
                            this.removeWidget(upButton);
                        }
                    }
                }, "tooltip.cyber_implants.implant" + (i+2));
            }
            for (int i = 3; i < 4; i++) {
                int finalI = i+1;
                createButton1(i, x - 112 + 16 + (80 * (i-3)), y + 12, but -> {
                    if (true) {
                        if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.implant" + (finalI))
                        .append(Component.literal(" DDDDD Uwu dDDudUDUau fjhafh"));

                        open = finalI;

                        if (drawTooltip){
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                        }else if (upButton != null){
                            this.removeWidget(upButton);
                        }
                    }
                }, "tooltip.cyber_implants.implant" + (i+1));
            }
            createBackButton(x, y);
        }
    }

    private void createButton(int button, int x, int y, Button.OnPress press, String tooltip){
        buttons[button] = new VoidButton(x, y, 32, 32, press);
        this.addRenderableWidget(buttons[button]);
        buttons[button].setTooltip(Tooltip.create(Component.translatable(tooltip)));
    }
    private void createButton1(int button, int x, int y, Button.OnPress press, String tooltip){
        buttons1[button] = new VoidButton(x, y, 32, 32, press);
        this.addRenderableWidget(buttons1[button]);
        buttons1[button].setTooltip(Tooltip.create(Component.translatable(tooltip)));
    }
    private void createBackButton(int x, int y/*, Button.OnPress press, String tooltip*/){
        backButton = new BackButton(x-9 -142, y-5 +80, 18, 10, but -> {
            if (true) {
                page = 0;
                drawTooltip = false;
                init();
            }
        });
        this.addRenderableWidget(backButton);
//        backButton.setTooltip(Tooltip.create(Component.translatable(tooltip)));
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

            gui.drawCenteredString(font, ClientData.cyber_points+"/??? | "+ClientData.cyber_level, x, y+50, Color.WHITE.getRGB());

//            gui.blit(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "textures/gui/titan_bone.png"),
//                    x - 112 +16, y - 52, 0, 0, 32, 32, 32, 32);
        }else if (page == 1){
            for (int i = 0; i < 2; i++) {
                gui.blit(GUI, x - 114 +16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
            }
//            for (int i = 3; i < 6; i++) {
//                gui.blit(GUI, x - 114 +16 + (80 * (i - 3)), y + 10, 350, 14, 36, 40, 640, 640);
//            }
//
            drawTooltip(gui, x, y);
        }else if (page == 2){
            for (int i = 0; i < 2; i++) {
                gui.blit(GUI, x - 114 +16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
            }
            for (int i = 3; i < 6; i++) {
                gui.blit(GUI, x - 114 +16 + (80 * (i - 3)), y + 10, 350, 14, 36, 40, 640, 640);
                break;
            }
            drawTooltip(gui, x, y);
        }

//        /**debug**/
//        gui.drawCenteredString(font,  ClientData.implant[0]+"/"+page, x, y - 100, Color.WHITE.getRGB());
//        /**debug**/

        active();
        RenderSystem.disableBlend();
    }

    private void active(){
        for (int i = 0; i <= 4; i++) {
            if (ClientData.implant[i] > 0 && open == i && upButton != null) {
                upButton.active = false;
            } else if (ClientData.implant[i] <= 0 && open == i && upButton != null) {
                upButton.active = true;
            }
        }
    }


    private boolean drawTooltip = false;
    private int open = 0;
    Component tooltipComponent = Component.empty();
    private void drawTooltip(GuiGraphics gui, int x, int y){
        Font font = Minecraft.getInstance().font;
        if (drawTooltip) {
            gui.blit(GUI, x + 56, y - 92, 352, 64, 96, 176,
                    640, 640);
            
            float scale = 0.7f;
            gui.pose().pushPose();
            gui.pose().scale(scale, scale, scale);
            x = (int)(x/scale);
            y = (int)(y/scale);

            gui.drawWordWrap(font, tooltipComponent,x + (int)(64/scale),y - (int)(70/scale), (int)(84/scale), Color.WHITE.getRGB());

            gui.drawCenteredString(font, Component.translatable("component.cyber_implants.upgrade"),x + (int)(105/scale),y + (int)(60/scale), Color.WHITE.getRGB());

            gui.pose().popPose();
        }
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
