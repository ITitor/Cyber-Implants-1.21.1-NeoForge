package net.ititor.cyber_implants.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.ititor.cyber_implants.gui.CyberImplantsScreen;
import net.ititor.cyber_implants.network.SendAbilityPacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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


    @EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(GUI);
            event.register(ABILITY);
        }
    }

    @EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (GUI.isDown()){
                Minecraft.getInstance().setScreen(new CyberImplantsScreen());
            }
            if (ABILITY.isDown() && ClientData.implant[0] > 0){
                PacketDistributor.sendToServer(new SendAbilityPacket(0));
            }
        }
    }
}
