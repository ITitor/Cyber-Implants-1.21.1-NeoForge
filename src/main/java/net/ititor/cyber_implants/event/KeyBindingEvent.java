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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

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
            if (GUI.isDown()){
                Minecraft.getInstance().setScreen(new CyberImplantsScreen());
            }
            if (ABILITY.isDown()){
                PacketDistributor.sendToServer(new SendAbilityPacket(0));
            }
            if (ABILITY.isDown() && ClientData.implant[4] > 0 && ClientData.cooldown[1] <= 0 && ClientData.selectAbility == 1){
                float f1 = (float) Math.cos(Math.toRadians(player.getYRot() + 90));
                float f2 = (float) Math.sin(Math.toRadians(player.getYRot() + 90));
                if (player.onGround()) {
                    player.push(f1 * 2.0, 0, f2 * 2.0);// 1x - 2 block dash
                } else {
                    player.push(f1 * 0.55, 0, f2 * 0.55);// 1x - 8 block dash
                }
                PacketDistributor.sendToServer(new SendAbilityPacket(1));
                ClientData.cooldown[1] = SendAbilityPacket.cd1;
            }
            if (ABILITY1.isDown()){
                Minecraft.getInstance().setScreen(new SelectAbilityScreen());
            }
        }
    }
}
