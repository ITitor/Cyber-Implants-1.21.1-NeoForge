package net.ititor.cyber_implants.event;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.effect.ModEffects;
import net.ititor.cyber_implants.gui.CyberImplantsScreen;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.Util;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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
import java.util.function.Predicate;


@EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

//    @SubscribeEvent
//    public static void onEntityRender(RenderPlayerEvent.Pre event) {
//        Player player = event.getEntity();
//        var model = event.getRenderer().getModel();
////
////        if (!player.getTags().contains("cyber_1")){
////            return;
////        }
//
//
//    }

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
            GuiGraphics gui = event.getGuiGraphics();
            if (event.getName() == VanillaGuiLayers.PLAYER_HEALTH && !mc.options.hideGui && !player.isCreative() && !player.isSpectator()) {
                if (player.hasEffect(ModEffects.CYBERPSYCHOSIS)) {
                    CustomHealth(event, 0);

                    renderConfusionOverlay(event.getGuiGraphics(), 0.25f);
                }
            }
            if (event.getName() == VanillaGuiLayers.HOTBAR && !mc.options.hideGui  && !player.isSpectator()) {
                if (ClientData.implant[1] > 0) {
                    int x = event.getGuiGraphics().guiWidth();
                    int y = event.getGuiGraphics().guiHeight();
                    LivingEntity target = ModUtils.findTarget(player, 40, true);
                    if (target != null) {

                        gui.pose().pushPose();
                        gui.pose().scale(0.85f, 0.85f, 0.85f);
                        y = (int)(event.getGuiGraphics().guiHeight() / 0.85f);
                        if (target.getItemBySlot(EquipmentSlot.HEAD).isEmpty()){y+=(int)(18);}
                        if (target.getItemBySlot(EquipmentSlot.CHEST).isEmpty()){y+=(int)(18);}
                        if (target.getItemBySlot(EquipmentSlot.LEGS).isEmpty()){y+=(int)(18);}
                        if (target.getItemBySlot(EquipmentSlot.FEET).isEmpty()){y+=(int)(18);}
                        if (target.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()){y+=(int)(18);}
                        if (target.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty()){y+=(int)(18);}

                        {
                            String text = Component.translatable("component.cyber_implants.name").getString() + target.getDisplayName().getString();
                            gui.drawString(font, text, 2, y - 118 - 12, Color.WHITE.getRGB());
                        }
                        {
                            String text = Component.translatable("component.cyber_implants.health").getString() + String.format("%.1f", target.getHealth());
                            gui.drawString(font, text, 2, y - 108 - 12, Color.WHITE.getRGB());
                        }

                        renderSlot(gui, target, EquipmentSlot.HEAD, 2, y-96-12);
                        if (target.getItemBySlot(EquipmentSlot.HEAD).isEmpty()){y-=18;}
                        renderSlot(gui, target, EquipmentSlot.CHEST, 2, y-80-10);
                        if (target.getItemBySlot(EquipmentSlot.CHEST).isEmpty()){y-=18;}
                        renderSlot(gui, target, EquipmentSlot.LEGS, 2, y-64-8);
                        if (target.getItemBySlot(EquipmentSlot.LEGS).isEmpty()){y-=18;}
                        renderSlot(gui, target, EquipmentSlot.FEET, 2, y-48-6);
                        if (target.getItemBySlot(EquipmentSlot.FEET).isEmpty()){y-=18;}

                        renderSlot(gui, target, EquipmentSlot.MAINHAND, 2, y-32-4);
                        if (target.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()){y-=18;}
                        if (target instanceof Player targPlayer) {
                            if (target.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof ProjectileWeaponItem item) {
                                String text = (target.getItemBySlot(EquipmentSlot.MAINHAND).getMaxDamage() - target.getItemBySlot(EquipmentSlot.MAINHAND).getDamageValue()) + "";
                                gui.renderItem(new ItemStack(Items.ARROW), 22 + (text.length() * 2), y - 32 - 2);

                                int arrow = 0;
                                for (int i = 0; i < targPlayer.getInventory().getContainerSize(); i++){
                                    if (targPlayer.getInventory().getItem(i).is(ItemTags.ARROWS)){
                                        arrow+=targPlayer.getInventory().getItem(i).getCount();
                                    }
                                }

                                gui.drawString(font, arrow+"", 22 + (text.length() * 2), y - 32 - 2 + 3, Color.WHITE.getRGB());
                            }
                        }

                        renderSlot(gui, target, EquipmentSlot.OFFHAND, 2, y-16-2);

                        gui.pose().popPose();
                    }
                }

                if (ClientData.implant[0] > 0 || ClientData.implant[5] > 0 || ClientData.implant[9] > 0 || ClientData.implant[10] > 0 || ClientData.implant[11] > 0){
                    render2Ability(gui, KeyBindingEvent.ABILITY, KeyBindingEvent.ABILITY1, ClientData.selectAbility);
                }

            }
        }
    }

    private static void render2Ability(GuiGraphics gui, /*ItemStack mainHand,*/
    KeyMapping bind1, KeyMapping bind2,/* ItemStack item, ResourceLocation icon1, ResourceLocation icon2,*/ int id){
        int x = gui.guiWidth() / 2;
        int y = gui.guiHeight();
        Font font = Minecraft.getInstance().font;
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, CyberImplantsScreen.GUI);
        RenderSystem.enableBlend();

        gui.blit(CyberImplantsScreen.GUI, x - 171 + 2, y - 22, 480, 192, 42, 22, 640, 640);

        if (ClientData.cooldown[id] <= 0) {
            PoseStack stack = gui.pose();
            String key;
            key = bind1.getKey().getDisplayName().getString();
            key = key.replace("Right ", "R");
            key = key.replace("Left ", "L");
            key = key.replace("Middle ", "M");
            key = key.replace("Button", "B");
            key = key.replace("Control", "Ctrl");

            gui.drawCenteredString(font, key, x - 166 + 12, y - 11, Color.WHITE.getRGB());

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
        else {
            RenderSystem.setShaderColor(0.55F, 0.55F, 0.55F, 1.0F);

            PoseStack stack = gui.pose();
            Component key;

            key = Component.literal((ClientData.cooldown[id] / 20) + "");

            gui.drawCenteredString(font, key, x - 166 + 12, y - 11, Color.WHITE.getRGB());

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
        {
//            RenderSystem.setShaderTexture(0, icon2);
//            gui.blit(icon2, (x - xPad) - 50 + 3, (y - yPad) + 17 + 3, 0, 0, 16, 16,
//                    16, 16);

            PoseStack stack = gui.pose();
            String key;
            key = bind2.getKey().getDisplayName().getString();
            key = key.replace("Right ", "R");
            key = key.replace("Left ", "L");
            key = key.replace("Middle ", "M");
            key = key.replace("Button", "B");
            key = key.replace("Control", "Ctrl");
            stack.pushPose();
            stack.translate(0.5F, 0F, 0F);
            gui.drawCenteredString(font, key, x - 146 + 12, y - 11, Color.WHITE.getRGB());
            stack.popPose();
        }
    }

    private static void renderSlot(GuiGraphics gui, LivingEntity target, EquipmentSlot slot, int x, int y){
        Font font = Minecraft.getInstance().font;

        gui.renderItem(target.getItemBySlot(slot), x, y);
        if (target.getItemBySlot(slot).isDamageableItem()){
            gui.pose().pushPose();
            gui.pose().translate(0, 0, 0);
            String text = (target.getItemBySlot(slot).getMaxDamage()-target.getItemBySlot(slot).getDamageValue())+"";
            gui.drawString(font, text, x+18, y+4, Color.WHITE.getRGB());
            gui.pose().popPose();
        }else if (target.getItemBySlot(slot).getCount() > 1){
            gui.pose().pushPose();
            gui.pose().translate(0, 0, 0);
            String text = (target.getItemBySlot(slot).getCount())+"";
            gui.drawString(font, text, x+18, y+4, Color.WHITE.getRGB());
            gui.pose().popPose();
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
