package net.ititor.cyber_implants.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.gui.CyberImplantsScreen;
import net.ititor.cyber_implants.gui.SelectAbilityScreen;
import net.ititor.cyber_implants.network.SendAbilityPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

import java.util.Timer;
import java.util.TimerTask;

public class KeyBindingEvent {

    public static final String KEY_CATEGORY = "key.category.cyber_implants";

    public static final KeyMapping GUI = new KeyMapping("key.cyber_implants.gui", KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, KEY_CATEGORY);

    public static final KeyMapping ABILITY = new KeyMapping("key.cyber_implants.ability", KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY);

    public static final KeyMapping ABILITY1 = new KeyMapping("key.cyber_implants.ability1", KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KEY_CATEGORY);


    @EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(GUI);
            event.register(ABILITY);
            event.register(ABILITY1);
        }
    }

    @EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Player player = Minecraft.getInstance().player;
            if (GUI.isDown() && Minecraft.getInstance().screen instanceof CyberImplantsScreen){
                Minecraft.getInstance().screen.onClose();
            }else if (GUI.isDown()){
                Minecraft.getInstance().setScreen(new CyberImplantsScreen());
            }

            if (ABILITY.isDown() && ClientData.selectAbility != 1){
                PacketDistributor.sendToServer(new SendAbilityPacket(ClientData.selectAbility));
            }
            else if (ABILITY.isDown() && ClientData.implant[5] > 0 && ClientData.cooldown[1] <= 0 && ClientData.selectAbility == 1){
                float f1 = (float) Math.cos(Math.toRadians(player.getYRot() + 90));
                float f2 = (float) Math.sin(Math.toRadians(player.getYRot() + 90));
                if (player.onGround()) {
                    player.addDeltaMovement(new Vec3(f1 * 1.75, 0, f2 * 1.75));// 1x - 2 block dash
                    /**Timer**/
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (!player.onGround()){
                                player.setDeltaMovement(new Vec3(player.getDeltaMovement().x * 0.25, player.getDeltaMovement().y, player.getDeltaMovement().z * 0.25));// 1x - 2 block dash
                            }
                        }
                    }, 100);
                    /**Timer**/
                } else if (!player.onGround() && player.isSprinting()){
                    player.addDeltaMovement(new Vec3(f1 * 0.50, 0, f2 * 0.50));// /3.6
                } else if (!player.onGround()){
                    player.addDeltaMovement(new Vec3(f1 * 0.55, 0, f2 * 0.55));// /3.6
                }
                player.hasImpulse = false;
                PacketDistributor.sendToServer(new SendAbilityPacket(1));
                ClientData.cooldown[1] = SendAbilityPacket.cd1;
            }
            if (ABILITY1.isDown() && (ClientData.implant[0] > 0 || ClientData.implant[5] > 0)){
                Minecraft.getInstance().setScreen(new SelectAbilityScreen());
            }
        }
    }
}
