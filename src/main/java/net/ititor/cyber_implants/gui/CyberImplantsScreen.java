package net.ititor.cyber_implants.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.network.SendCyberPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class CyberImplantsScreen extends Screen {
    public CyberImplantsScreen() {
        super(Component.literal(""));
    }

    private static final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/cyber_menu.png");

    ImageButton[] buttons = {null, null, null, null, null, null, null, null};
    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        int x = width/2;
        int y = height/2;
        Player player = Minecraft.getInstance().player;
        ResourceLocation res = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/void.png");
        WidgetSprites widgetSprites = new WidgetSprites(res, res);

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            buttons[i] = new ImageButton(x-112+(64*i), y-52, 32, 32, widgetSprites, but -> {
                if (true) {
                    PacketDistributor.sendToServer(new SendCyberPacket("cyber_"+ finalI));
                }
            });
            this.addRenderableWidget(buttons[i]);
            buttons[i].setTooltip(Tooltip.create(Component.literal("b"+i)));
        }

        for (int i = 4; i < 8; i++) {
            int finalI = i;
            buttons[i] = new ImageButton(x-112+(64*(i-4)), y+12, 32, 32, widgetSprites, but -> {
                if (true) {
                    PacketDistributor.sendToServer(new SendCyberPacket("cyber_"+ finalI));
                }
            });
            this.addRenderableWidget(buttons[i]);
            buttons[i].setTooltip(Tooltip.create(Component.literal("b"+i)));
        }
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        super.render(gui, mouseX, mouseY, partialTick);
        int x = width/2;
        int y = height/2;

        RenderSystem.setShaderTexture(0, GUI);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.enableBlend();


        gui.blit(GUI, x-160, y-100, 0, 0, 320, 200,
                512, 512);

        gui.blit(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/titan_bone.png"),
        x-112, y-52, 0, 0, 32, 32, 32, 32);


        RenderSystem.disableBlend();
    }
}
