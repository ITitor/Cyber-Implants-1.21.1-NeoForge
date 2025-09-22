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
    public static final ResourceLocation XP_BAR = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/xp_bar.png");
    Button[] buttons = {null, null, null, null, null, null, null, null, null, null};
    Button[] buttons1 = {null, null, null, null, null, null, null, null, null, null};
    Button upButton = null;
    Button backButton = null;
    Button flipBackButton = null;
    int page = 0;

    public static int need_points;

    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        int x = width/2;
        int y = height/2;
        Player player = Minecraft.getInstance().player;

        if (page == 0) {
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                if (i < 3) {
                    createButton(i, x - 96 + (80 * i), y - 52, but -> {
                        if (true) {
                            page = finalI + 1;
                            init();
                        }
                    }, "tooltip.cyber_implants.button" + i);
                }else {
                    createButton(i, x - 96 + (80 * (i-3)) + 40, y + 12, but -> {
                        if (true) {
                            page = finalI+1;
                            init();
                        }
                    }, "tooltip.cyber_implants.button" + i);
                }
            }
        }

        else if (page == 1){
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                createButton1(i, x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.eye_implant" + finalI);

                        open = finalI;

                        if (upButton != null) {
                            this.removeWidget(upButton);
                        }
                        if (drawTooltip) {
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                        } else if (upButton != null) {
                            this.removeWidget(upButton);
                        }
                    }
                }, "tooltip.cyber_implants.eye_implant" + i);
            }
        }

        else if (page == 2){
            for (int i = 0; i < 6; i++) {
                int finalI = i + 3;
                if (i < 3) {
                    createButton1(i, x - 112 + 16 + (80 * i), y - 52, but -> {
                        if (true) {
                            if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.body_implant" + (finalI-3));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                            } else if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                        }
                    }, "tooltip.cyber_implants.body_implant" + i);
                }else {
                    createButton1(i, x - 112 + 16 + (80 * (i-3)), y + 12, but -> {
                        if (true) {
                            if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.body_implant" + (finalI-3));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip){
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                            if (true) {
                                PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                            }
                        });
                        this.addRenderableWidget(upButton);
                        upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                            }else if (upButton != null){this.removeWidget(upButton);}
                        }
                    }, "tooltip.cyber_implants.body_implant" + i);
                }
            }
        }

        else if (page == 3){
            for (int i = 0; i < 3; i++) {
                int finalI = i + 9;
                createButton1(i, x - 112 + 16 + (80 * i), y - 52, but -> {
                    if (true) {
                        if (open == finalI || !drawTooltip) {drawTooltip = !drawTooltip;}
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.neural_implant" + (finalI-9));

                        open = finalI;

                        if (upButton != null) {
                            this.removeWidget(upButton);
                        }
                        if (drawTooltip) {
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                        } else if (upButton != null) {
                            this.removeWidget(upButton);
                        }
                    }
                }, "tooltip.cyber_implants.neural_implant" + i);
            }
        }

        else if (page == 4){
            for (int i = 0; i < 4; i++) {
                int finalI = i + 12;
                if (i < 2) {
                    createButton1(i, x - 112 + 16 + (80 * i) + 40, y - 52, but -> {
                        if (true) {
                            if (open == finalI || !drawTooltip) {
                                drawTooltip = !drawTooltip;
                            }
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.combat_implant" + (finalI - 12));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                    if (true) {
                                        PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                    }
                                });
                                this.addRenderableWidget(upButton);
                                upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                            } else if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                        }
                    }, "tooltip.cyber_implants.combat_implant" + i);
                }else {
                    createButton1(i, x - 112 + 16 + (80 * (i-2)) + 40, y + 12, but -> {
                        if (true) {
                            if (open == finalI || !drawTooltip) {
                                drawTooltip = !drawTooltip;
                            }
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.combat_implant" + (finalI - 12));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                            if (true) {
                                PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                            }
                        });
                        this.addRenderableWidget(upButton);
                        upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                            } else if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                        }
                    }, "tooltip.cyber_implants.combat_implant" + i);
                }
            }
        }

        else if (page == 5){
            for (int i = 0; i < 4; i++) {
                int finalI = i + 16;
                if (i < 2) {
                    createButton1(i, x - 112 + 16 + (80 * i) + 40, y - 52, but -> {
                        if (true) {
                            if (open == finalI || !drawTooltip) {
                                drawTooltip = !drawTooltip;
                            }
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.systemic_implant" + (finalI - 16));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                            } else if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                        }
                    }, "tooltip.cyber_implants.systemic_implant" + i);
                }else {
                    createButton1(i, x - 112 + 16 + (80 * (i-2)) + 40, y + 12, but -> {
                        if (true) {
                            if (open == finalI || !drawTooltip) {
                                drawTooltip = !drawTooltip;
                            }
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.systemic_implant" + (finalI - 16));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (true) {
                                    PacketDistributor.sendToServer(new SendCyberPacket(1, finalI));
                                }
                            });
                            this.addRenderableWidget(upButton);
                            upButton.setTooltip(Tooltip.create(Component.translatable("component.cyber_implants.upgrade")));
                            } else if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                        }
                    }, "tooltip.cyber_implants.systemic_implant" + i);
                }
            }
        }

        if (page != 0) {
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
        flipBackButton = new BackButton(x-9 +142 -16, y-5 +80, 18, 10, but -> {
            if (true) {
                page = 0;
                drawTooltip = false;
                init();
            }
        }, true);
        this.addRenderableWidget(flipBackButton);
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
            for (int i = 0; i < 5; i++) {
                if (i < 3) {
                    gui.blit(GUI, x - 114 + 16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
                }else {
                    gui.blit(GUI, x - 114 +16 + (80 * (i - 3)) + 40, y + 10, 350, 14, 36, 40, 640, 640);
                }
            }

            gui.blit(XP_BAR, x-92, y+76, 0, 42, 184, 7, 256, 256);

            int points = ClientData.cyber_points;
            need_points = 1000;
            gui.blit(XP_BAR, x-92, y+76, 0, 49, Math.round(((((float) points /need_points)) * 184)), 7, 256, 256);

            gui.drawCenteredString(font, ClientData.cyber_points+"/"+need_points+" | "+ClientData.cyber_level, x, y+66, Color.WHITE.getRGB());

//            gui.blit(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "textures/gui/titan_bone.png"),
//                    x - 112 +16, y - 52, 0, 0, 32, 32, 32, 32);
        }else if (page == 1){
            for (int i = 0; i < 3; i++) {
                gui.blit(GUI, x - 114 + 16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
            }
            drawTooltip(gui, x, y);
        }else if (page == 2){
            for (int i = 0; i < 6; i++) {
                if (i < 3) {
                    gui.blit(GUI, x - 114 + 16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
                }else {
                    gui.blit(GUI, x - 114 +16 + (80 * (i-3)), y + 10, 350, 14, 36, 40, 640, 640);
                }
            }
            drawTooltip(gui, x, y);
        }else if (page == 3){
            for (int i = 0; i < 3; i++) {
                gui.blit(GUI, x - 114 + 16 + (80 * i), y - 54, 350, 14, 36, 40, 640, 640);
            }
            drawTooltip(gui, x, y);
        }else if (page == 4){
            for (int i = 0; i < 4; i++) {
                if (i < 2) {
                    gui.blit(GUI, x - 114 + 16 + (80 * i) + 40, y - 54, 350, 14, 36, 40, 640, 640);
                }else {
                    gui.blit(GUI, x - 114 +16 + (80 * (i-2)) + 40, y + 10, 350, 14, 36, 40, 640, 640);
                }
            }
            drawTooltip(gui, x, y);
        }else if (page == 5){
            for (int i = 0; i < 4; i++) {
                if (i < 2) {
                    gui.blit(GUI, x - 114 + 16 + (80 * i) + 40, y - 54, 350, 14, 36, 40, 640, 640);
                }else {
                    gui.blit(GUI, x - 114 +16 + (80 * (i-2)) + 40, y + 10, 350, 14, 36, 40, 640, 640);
                }
            }
            drawTooltip(gui, x, y);
        }

        active();
        RenderSystem.disableBlend();
    }

    private void active(){
        for (int i = 0; i <= 19; i++) {
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
        int x0 = x;
        int y0 = y;
        Font font = Minecraft.getInstance().font;
        if (drawTooltip) {
            if ((page == 1 && open == 2) || (page == 2 && (open == 5 || open == 8)) || (page == 3 && open == 11)){
                gui.blit(GUI, x - 56 - 96, y - 92, 352, 64, 96, 176,
                        640, 640);

                float scale = 0.7f;
                gui.pose().pushPose();
                gui.pose().scale(scale, scale, scale);
                x = (int) (x / scale);
                y = (int) (y / scale);

                gui.drawWordWrap(font, tooltipComponent, x - (int) (70 + 96 / scale), y - (int) (70 / scale), (int) (84 / scale), Color.WHITE.getRGB());

                gui.drawCenteredString(font, Component.translatable("component.cyber_implants.upgrade"), x - (int) (105 / scale), y + (int) (60 / scale), Color.WHITE.getRGB());

                gui.pose().popPose();

                if (page == 1 || page == 2 || page == 3) {
                    if (buttons1[2] != null) {
                        buttons1[2].visible = true;
                    }
                    if (buttons1[5] != null) {
                        buttons1[5].visible = true;
                    }
                    if (buttons1[0] != null) {
                        buttons1[0].visible = false;
                    }
                    if (buttons1[3] != null) {
                        buttons1[3].visible = false;
                    }
                    if (flipBackButton != null) {
                        flipBackButton.visible = true;
                    }
                    if (backButton != null) {
                        backButton.visible = false;
                    }
                    if (upButton != null){
                        upButton.setPosition(x0 - 105 - 20, y0 + 60);
                    }
                }
            }else {
                gui.blit(GUI, x + 56, y - 92, 352, 64, 96, 176,
                        640, 640);

                float scale = 0.7f;
                gui.pose().pushPose();
                gui.pose().scale(scale, scale, scale);
                x = (int) (x / scale);
                y = (int) (y / scale);

                gui.drawWordWrap(font, tooltipComponent, x + (int) (64 / scale), y - (int) (70 / scale), (int) (84 / scale), Color.WHITE.getRGB());

                gui.drawCenteredString(font, Component.translatable("component.cyber_implants.upgrade"), x + (int) (105 / scale), y + (int) (60 / scale), Color.WHITE.getRGB());

                gui.pose().popPose();

                if (page == 1 || page == 2 || page == 3) {
                    if (buttons1[2] != null) {
                        buttons1[2].visible = false;
                    }
                    if (buttons1[5] != null) {
                        buttons1[5].visible = false;
                    }
                    if (buttons1[0] != null) {
                        buttons1[0].visible = true;
                    }
                    if (buttons1[3] != null) {
                        buttons1[3].visible = true;
                    }
                    if (flipBackButton != null) {
                        flipBackButton.visible = false;
                    }
                    if (backButton != null) {
                        backButton.visible = true;
                    }
                    if (upButton != null){
                        upButton.setPosition(x0 + 105 - 20, y0 + 60);
                    }
                }
            }
        }
        else {
            if (buttons1[2] != null){
                buttons1[2].visible = true;
            }if (buttons1[5] != null){
                buttons1[5].visible = true;
            }if (buttons1[0] != null) {
                buttons1[0].visible = true;
            }if (buttons1[3] != null) {
                buttons1[3].visible = true;
            }if (flipBackButton != null) {
                flipBackButton.visible = false;
            }if (backButton != null) {
                backButton.visible = true;
            }if (upButton != null){
                this.removeWidget(upButton);
            }
        }
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
