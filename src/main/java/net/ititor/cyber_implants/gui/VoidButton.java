package net.ititor.cyber_implants.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VoidButton extends Button {
//    protected final WidgetSprites sprites;

    public VoidButton(int x, int y, int width, int height, Button.OnPress onPress) {
        this(x, y, width, height, onPress, CommonComponents.EMPTY);
    }

    public VoidButton(int x, int y, int width, int height, Button.OnPress onPress, Component message) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
//        this.sprites = sprites;
    }

    public VoidButton(int width, int height, Button.OnPress onPress, Component message) {
        this(0, 0, width, height, onPress, message);
    }

    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//        ResourceLocation resourcelocation = this.sprites.get(this.isActive(), this.isHoveredOrFocused());
//        guiGraphics.blitSprite(resourcelocation, this.getX(), this.getY(), this.width, this.height);
    }
}