package net.ititor.cyber_implants.event;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.network.SendCyberPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = CyberImplants.MOD_ID)
public class PayloadHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(CyberImplants.MOD_ID).versioned("1.0.0").optional();

        payloadRegistrar.playToServer(SendCyberPacket.TYPE, SendCyberPacket.STREAM_CODEC, SendCyberPacket::handle);
    }
}