package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ClientData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SyncDataPacket implements CustomPacketPayload {
    public static final Type<SyncDataPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "sync_data"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SyncDataPacket> STREAM_CODEC = CustomPacketPayload.codec(SyncDataPacket::write, SyncDataPacket::new);

    private final boolean implant;
    private final int id;

    public SyncDataPacket(boolean implant, int id) {
        this.implant = implant;
        this.id = id;
    }

    public SyncDataPacket(FriendlyByteBuf buf) {
        this.implant = buf.readBoolean();
        this.id = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeBoolean(implant);
        buf.writeInt(id);
    }

    public static void handle(SyncDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientData.implant[packet.id] = packet.implant;
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
