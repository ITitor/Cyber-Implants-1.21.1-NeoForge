package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public class SendCyberPacket implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendCyberPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "add_cyber_to_player"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SendCyberPacket> STREAM_CODEC = CustomPacketPayload.codec(SendCyberPacket::write, SendCyberPacket::new);

    public SendCyberPacket() {
    }

    public SendCyberPacket(FriendlyByteBuf buf) {
    }

    public void write(FriendlyByteBuf buf) {
    }

    public static void handle(SendCyberPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                serverPlayer.addTag("cyber_1");
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
