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
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class CyberImplantsScreen extends Screen {
    public CyberImplantsScreen() {
        super(Component.literal(""));
    }

    private static final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/cyber_menu.png");
    private static final ResourceLocation VOID = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID,"textures/gui/void.png");

    ImageButton button = null;
    @Override
    protected void init() {
        super.init();
        this.clearWidgets();
        int x = width/2;
        int y = height/2;
        Player player = Minecraft.getInstance().player;

        button = new ImageButton(x, y, 16, 16, new WidgetSprites(VOID, VOID), but -> {
            if (true) {
                new SendCyberPacket();
            }
        });
        this.addRenderableWidget(button);
        button.setTooltip(Tooltip.create(Component.literal("dd")));
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
