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
    private static final ResourceLocation VOID = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/void.png");

    ImageButton[] buttons = {null, null};
    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        int x = width/2;
        int y = height/2;
        Player player = Minecraft.getInstance().player;

        buttons[0] = new ImageButton(x-24, y-12, 24, 24, new WidgetSprites(VOID, VOID), but -> {
            if (true) {
                PacketDistributor.sendToServer(new SendCyberPacket("cyber_1"));
            }
        });
        this.addRenderableWidget(buttons[0]);
        buttons[0].setTooltip(Tooltip.create(Component.literal("b1")));

        buttons[1] = new ImageButton(x+24, y-12, 24, 24, new WidgetSprites(VOID, VOID), but -> {
            if (true) {
                PacketDistributor.sendToServer(new SendCyberPacket("cyber_2"));
            }
        });
        this.addRenderableWidget(buttons[1]);
        buttons[1].setTooltip(Tooltip.create(Component.literal("b2")));
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


        RenderSystem.disableBlend();
    }
}
