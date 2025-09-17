package net.ititor.cyber_implants.event;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.effect.ModEffects;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Math;
import org.joml.Quaternionf;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


@EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onEntityRender(RenderPlayerEvent.Pre event) {
//        LivingEntity player = event.getEntity();
//        var model = event.getRenderer().getModel();
//
//        if (!player.getTags().contains("cyber_1")){
//            return;
//        }

    }

    @SubscribeEvent
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        Player player = Minecraft.getInstance().player;
        float delta = Minecraft.getInstance().getFrameTimeNs();
        float ticksExistedDelta = player.tickCount + delta;

        if (player != null) {
            float shakeAmplitude = 0;

            if (player.hasEffect(ModEffects.CYBERPSYCHOSIS)){
                shakeAmplitude = 0.017f;
            }
            event.setPitch((float) (event.getPitch() + shakeAmplitude * Math.cos(ticksExistedDelta * 3 + 2) * 25));
            event.setYaw((float) (event.getYaw() + shakeAmplitude * Math.cos(ticksExistedDelta * 5 + 1) * 25));
//            event.setRoll((float) (event.getRoll() + shakeAmplitude * Math.cos(ticksExistedDelta * 4) * 25));
        }

    }


    private static final ResourceLocation NAUSEA_LOCATION = ResourceLocation.withDefaultNamespace("textures/misc/nausea.png");
    @SubscribeEvent
    public static void onPreRenderHUD(RenderGuiLayerEvent.Pre event) {
        Player player = Minecraft.getInstance().player;
        Font font = Minecraft.getInstance().font;
        if (player != null) {
            Minecraft mc = Minecraft.getInstance();
            Gui gui = mc.gui;
            if (event.getName() == VanillaGuiLayers.PLAYER_HEALTH && !mc.options.hideGui && !player.isCreative() && !player.isSpectator()) {
                if (player.hasEffect(ModEffects.CYBERPSYCHOSIS)) {
                    CustomHealth(event, 0);

                    renderConfusionOverlay(event.getGuiGraphics(), 0.25f);
                }
            }
            if (event.getName() == VanillaGuiLayers.HOTBAR && !mc.options.hideGui  && !player.isSpectator()) {
                if (ClientData.implant[1]) {
                    int x = event.getGuiGraphics().guiWidth() / 2;
                    int y = event.getGuiGraphics().guiHeight() / 2;
                    LivingEntity target = ModUtils.findTarget(player, 5, true);
                    if (target != null) {
                        event.getGuiGraphics().drawCenteredString(font, "Health: "+String.format("%.1f",target.getHealth()), x, y + 120, Color.WHITE.getRGB());
                    }
                }
            }
        }
    }

    private static void renderConfusionOverlay(GuiGraphics guiGraphics, float scalar) {
        int i = guiGraphics.guiWidth();
        int j = guiGraphics.guiHeight();
        guiGraphics.pose().pushPose();
        float f = Mth.lerp(scalar, 2.0F, 1.0F);
        guiGraphics.pose().translate((float)i / 2.0F, (float)j / 2.0F, 0.0F);
        guiGraphics.pose().scale(f, f, f);
        guiGraphics.pose().translate((float)(-i) / 2.0F, (float)(-j) / 2.0F, 0.0F);
        float f1 = 0.2F * scalar;
        float f2 = 0.4F * scalar;
        float f3 = 0.2F * scalar;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE
        );
        guiGraphics.setColor(254/255f, 0, 52/255f, 1.0F);
        guiGraphics.blit(NAUSEA_LOCATION, 0, 0, -90, 0.0F, 0.0F, i, j, i, j);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        guiGraphics.pose().popPose();
    }

    public static final ResourceLocation EFFECT_HEART = ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "textures/gui/glitch_heart.png");
    public static final Random random = new Random();
    public static int lastHealth;
    public static int displayHealth;
    public static long lastHealthTime;
    public static long healthBlinkTime;
    private static void CustomHealth(RenderGuiLayerEvent.Pre event,int back){
        Player player = Minecraft.getInstance().player;
        Minecraft mc = Minecraft.getInstance();
        Gui gui = mc.gui;
        GuiGraphics stack = event.getGuiGraphics();

        stack.pose().pushPose();
        stack.pose().scale(0.235f, 0.235f, 0.235f);
        int width = (int)(stack.guiWidth() *4.25);
        int height = (int)(stack.guiHeight() *4.25);

        event.setCanceled(true);
        RenderSystem.setShaderTexture(0, EFFECT_HEART);
        RenderSystem.enableBlend();
        int health = Mth.ceil(player.getHealth());
        int tickCount = gui.getGuiTicks();
        boolean highlight = ClientEvents.healthBlinkTime > (long) tickCount && (ClientEvents.healthBlinkTime - (long) tickCount) / 3L % 2L == 1L;
        if (health < ClientEvents.lastHealth && player.invulnerableTime > 0) {
            ClientEvents.lastHealthTime = Util.getMillis();
            ClientEvents.healthBlinkTime = (long) (tickCount + 20);
        } else if (health > ClientEvents.lastHealth && player.invulnerableTime > 0) {
            ClientEvents.lastHealthTime = Util.getMillis();
            ClientEvents.healthBlinkTime = (long) (tickCount + 10);
        }

        if (Util.getMillis() - ClientEvents.lastHealthTime > 1000L) {
            ClientEvents.lastHealth = health;
            ClientEvents.displayHealth = health;
            ClientEvents.lastHealthTime = Util.getMillis();
        }

        ClientEvents.lastHealth = health;
        int healthLast = ClientEvents.displayHealth;
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        float healthMax = (float) maxHealth.getValue();
        int absorbtion = Mth.ceil(player.getAbsorptionAmount());
        int healthRows = Mth.ceil((healthMax + (float) absorbtion) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);
        ClientEvents.random.setSeed((long) (tickCount * 312871L));
        int left = width / 2 - 91;
        int top = height - gui.leftHeight;
        gui.leftHeight += healthRows * rowHeight;
        if (rowHeight != 10) {
            gui.leftHeight += 10 - rowHeight;
        }

        int regen = -1;
        if (player.hasEffect(MobEffects.REGENERATION)) {
            regen = tickCount % Mth.ceil(healthMax + 5.0F);
        }

//        int TOP = player.level().getLevelData().isHardcore() ? 9 : 0;
        int TOP = 0;
//        int BACKGROUND = highlight ? back : 16;
        int BACKGROUND = 0;
//        int margin = 34;
        int margin = 48;
        float absorbtionRemaining = (float) absorbtion;

        for (int i = Mth.ceil((healthMax + (float) absorbtion) / 2.0F) - 1; i >= 0; --i) {
            int row = Mth.ceil((float) (i + 1) / 10.0F) - 1;
            int x = (left + i % 10 * 8) + (i*26) -295;
            int y = (top - row * rowHeight) -125;
            if (health <= 4) {
                y += ClientEvents.random.nextInt(2);
            }

            if (i == regen) {
                y -= 2;
            }

            stack.blit(EFFECT_HEART, x, y, BACKGROUND, TOP, 43/*9*/, 36/*9*/, 160, 128);
            if (highlight) {
                if (i * 2 + 1 < healthLast) {
                    stack.blit(EFFECT_HEART, x, y, margin, TOP, 43, 36, 160, 128);
                } else if (i * 2 + 1 == healthLast) {
                    stack.blit(EFFECT_HEART, x, y, margin + 48, TOP, 43, 36, 160, 128);
                }
            }

            if (absorbtionRemaining > 0.0F) {
                if (absorbtionRemaining == (float) absorbtion && (float) absorbtion % 2.0F == 1.0F) {
                    stack.blit(EFFECT_HEART, x, y, margin + 48, TOP, 43, 36, 160, 128);
                    --absorbtionRemaining;
                } else {
                    stack.blit(EFFECT_HEART, x, y, margin, TOP, 43, 36, 160, 128);
                    absorbtionRemaining -= 2.0F;
                }
            } else if (i * 2 + 1 < health) {
                stack.blit(EFFECT_HEART, x, y, margin, TOP, 43, 36, 160, 128);
            } else if (i * 2 + 1 == health) {
                stack.blit(EFFECT_HEART, x, y, margin + 48, TOP, 43, 36, 160, 128);
            }
        }

        stack.pose().popPose();
        RenderSystem.disableBlend();
        RenderSystem.setShaderTexture(0, EFFECT_HEART);
    }
}
