package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SendCyberPacket implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendCyberPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "add_cyber_to_player"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SendCyberPacket> STREAM_CODEC = CustomPacketPayload.codec(SendCyberPacket::write, SendCyberPacket::new);

    private final boolean implant;
    public SendCyberPacket(boolean implant) {
        this.implant = implant;
    }

    public SendCyberPacket(FriendlyByteBuf buf) {
        this.implant = buf.readBoolean();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(implant);
    }

    public static void handle(SendCyberPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                serverPlayer.setData(ModData.IMPLANT0, packet.implant);
//                serverPlayer.sendSystemMessage(Component.literal(""+serverPlayer.getData(ModData.IMPLANT)));
            }
        });
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
