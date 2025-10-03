package net.ititor.cyber_implants.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.network.SendCyberPacket;
import net.minecraft.ChatFormatting;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public static int need_points = 1000;

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
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.eye_implant" + finalI).append(Component.translatable("description.cyber_implants.eye_implant" + (finalI)));

                        open = finalI;

                        if (upButton != null) {
                            this.removeWidget(upButton);
                        }
                        if (drawTooltip) {
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (craft(player, getRecipe(finalI))) {
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
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.body_implant" + (finalI-3)).append(Component.translatable("description.cyber_implants.body_implant" + (finalI-3)));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                    if (craft(player, getRecipe(finalI))) {
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
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.body_implant" + (finalI-3)).append(Component.translatable("description.cyber_implants.body_implant" + (finalI-3)));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip){
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                    if (craft(player, getRecipe(finalI))) {
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
                        tooltipComponent = Component.translatable("tooltip.cyber_implants.neural_implant" + (finalI-9)).append(Component.translatable("description.cyber_implants.neural_implant" + (finalI-9)));

                        open = finalI;

                        if (upButton != null) {
                            this.removeWidget(upButton);
                        }
                        if (drawTooltip) {
                            upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (craft(player, getRecipe(finalI))) {
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
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.combat_implant" + (finalI - 12)).append(Component.translatable("description.cyber_implants.combat_implant" + (finalI-12)));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                    if (craft(player, getRecipe(finalI))) {
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
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.combat_implant" + (finalI - 12)).append(Component.translatable("description.cyber_implants.combat_implant" + (finalI-12)));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                            if (craft(player, getRecipe(finalI))) {
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
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.systemic_implant" + (finalI - 16)).append(Component.translatable("description.cyber_implants.systemic_implant" + (finalI-16)));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (craft(player, getRecipe(finalI))) {
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
                            tooltipComponent = Component.translatable("tooltip.cyber_implants.systemic_implant" + (finalI - 16)).append(Component.translatable("description.cyber_implants.systemic_implant" + (finalI-16)));

                            open = finalI;

                            if (upButton != null) {
                                this.removeWidget(upButton);
                            }
                            if (drawTooltip) {
                                upButton = new VoidButton(x + 105 - 20, y + 60, 40, 12, upBut -> {
                                if (craft(player, getRecipe(finalI))) {
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

                /****/
                drawRecipe(gui, getRecipe(open),x - (int) (70 + 96 / scale), y - (int) (50 / scale));
                /****/

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

                /****/
                drawRecipe(gui, getRecipe(open),x + (int) (64 / scale), y - (int) (50 / scale));
                /****/

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


    private void drawRecipe(GuiGraphics gui, List<ItemStack> list, int x, int y){
        Font font = Minecraft.getInstance().font;

        if (tooltipComponent != null){
            for (int s = 1; s < 10; s++){
                if (tooltipComponent.getString().length() / (22*s) > 0){
                    y+=5;
                }
            }
        }

        for (int i = 0; i < list.size(); i++){
            ItemStack item = list.get(i);

            Component component = Component.literal(item.getCount() + "x     ").append(Component.translatable(item.getItem().getDescriptionId()));
            int itemCount = 0;
            for (int ii = 0; ii < minecraft.player.getInventory().getContainerSize(); ii++){
                ItemStack locItem = minecraft.player.getInventory().getItem(ii);
                if (locItem.is(item.getItem())) {
                    itemCount += locItem.getCount();
                    if (itemCount >= item.getCount()) {
                        component = (Component.literal(item.getCount() + "x     ").append(Component.translatable(item.getItem().getDescriptionId()))).withStyle(ChatFormatting.GREEN);
                    }
                }
            }

            if (item.getCount() >= 100){
                gui.renderItem(item, x+14+12, y-5+(18*i), 0, 0);
            }else if (item.getCount() >= 10){
                gui.renderItem(item, x+14+6, y-5+(18*i), 0, 0);
            }else {
                gui.renderItem(item, x+14, y-5+(18*i), 0, 0);
            }

            gui.drawString(font, component, x, y+(18*i), Color.WHITE.getRGB(), false);
        }
    }

    private List<ItemStack> getRecipe(int id){
        List<ItemStack> list = new ArrayList<>();

        if (id == 0) {
            list.add(new ItemStack(Items.STICK, 1));
            list.add(new ItemStack(Items.IRON_INGOT, 16));
        }if (id == 1) {
            list.add(new ItemStack(Items.STICK, 1));
            list.add(new ItemStack(Items.GOLD_INGOT, 16));
        }if (id == 2) {
            list.add(new ItemStack(Items.STICK, 1));
            list.add(new ItemStack(Items.DIAMOND, 16));
        }if (id == 3) {
            list.add(new ItemStack(Items.STICK, 1));
            list.add(new ItemStack(Items.NETHERITE_INGOT, 16));
        }if (id == 4) {
            list.add(new ItemStack(Items.STICK, 1));
            list.add(new ItemStack(Items.EMERALD, 16));
        }

        return list;
    }


    private boolean craft(Player player, List<ItemStack> list){
        int allConditions = 0;
        int allItemCount = 0;
        List<Integer> condition = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            condition.add(i, 0);
        }

        for (int l = 0; l < list.size(); l++) {
            for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                ItemStack item = player.getInventory().getItem(i);
                if (item.is(list.get(l).getItem())) {
                    if (condition.get(l) < list.get(l).getCount()) {
                        condition.set(l, condition.get(l) + item.getCount());
                    }
                }
            }
        }


        for (int l = 0; l < list.size(); l++) {
            allItemCount += list.get(l).getCount();
        }
        for (int l = 0; l < list.size(); l++) {
            if (condition.get(l) >= list.get(l).getCount()) {
                allConditions++;
            }
        }

        if (!player.isCreative()) {
            if (allConditions >= list.size()) {
                for (int l = 0; l < list.size(); l++) {
                    reduce(player, list.get(l));
                }

                return true;

            } else {
                player.displayClientMessage(Component.translatable("component.cyber_implants.missing").withStyle(ChatFormatting.RED), false);
                for (int l = 0; l < list.size(); l++) {
                    if (condition.get(l) < list.get(l).getCount()) {
                        int missingCount = list.get(l).getCount() - condition.get(l);
                        player.displayClientMessage((Component.literal(missingCount + "x ")
                                .append(Component.translatable(list.get(l).getItem().getDescriptionId()))).withStyle(ChatFormatting.RED), false);
                    }
                }
            }
        }else {
            return true;
        }
        return false;
    }
    private void reduce(Player player, ItemStack stack){
        for (int need = 0; need < stack.getCount();) {
            for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                ItemStack item = player.getInventory().getItem(i);
                if (item.is(stack.getItem())) {
                    item.shrink(1);
                    need++;
                    if (need >= stack.getCount()) {
                        break;
                    }
                }
            }
        }
    }


    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
