package net.ititor.cyber_implants.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ititor.cyber_implants.CyberImplants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BackButton extends Button {
//    protected final WidgetSprites sprites;

    public BackButton(int x, int y, int width, int height, OnPress onPress) {
        this(x, y, width, height, onPress, CommonComponents.EMPTY);
    }

    private boolean flip;
    public BackButton(int x, int y, int width, int height, OnPress onPress, boolean flip) {
        this(x, y, width, height, onPress, CommonComponents.EMPTY);
        this.flip = flip;
    }

    public BackButton(int x, int y, int width, int height, OnPress onPress, Component message) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
//        this.sprites = sprites;
    }

    public BackButton(int width, int height, OnPress onPress, Component message) {
        this(0, 0, width, height, onPress, message);
    }

    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        ResourceLocation resourcelocation = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "textures/gui/cyber_menu.png"); //this.sprites.get(this.isActive(), this.isHoveredOrFocused());

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, resourcelocation);
        RenderSystem.enableBlend();

        if (!flip){
            guiGraphics.blit(resourcelocation, this.getX(), this.getY(), 416, 32, 18, 10, 640, 640);
        }else {
            guiGraphics.blit(resourcelocation, this.getX(), this.getY(), 416, 48, 18, 10, 640, 640);
        }

        RenderSystem.disableBlend();
    }
}