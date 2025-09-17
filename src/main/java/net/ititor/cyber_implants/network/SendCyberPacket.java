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
    public static final StreamCodec<RegistryFriendlyByteBuf, SendCyberPacket> STREAM_CODEC = CustomPacketPayload.codec(SendCyberPacket::write, buf -> new SendCyberPacket(buf));

    private final boolean implant;
    private final int id;
    public SendCyberPacket(boolean implant, int id) {
        this.implant = implant;
        this.id = id;
    }

    public SendCyberPacket(FriendlyByteBuf buf) {
        this.implant = buf.readBoolean();
        this.id = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(implant);
        buf.writeInt(id);
    }

    public static void handle(SendCyberPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                if (packet.id == 0) {
                    serverPlayer.setData(ModData.IMPLANT0, packet.implant);
                }if (packet.id == 1) {
                    serverPlayer.setData(ModData.IMPLANT1, packet.implant);
                }if (packet.id == 2) {
                    serverPlayer.setData(ModData.IMPLANT2, packet.implant);
                }if (packet.id == 3) {
                    serverPlayer.setData(ModData.IMPLANT3, packet.implant);
                }if (packet.id == 4) {
                    serverPlayer.setData(ModData.IMPLANT4, packet.implant);
                }
            }
        });
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
