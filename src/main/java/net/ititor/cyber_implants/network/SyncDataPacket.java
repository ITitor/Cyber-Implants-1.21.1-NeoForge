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

    private final int implant;
    private final int id;

    public SyncDataPacket(int implant, int id) {
        this.implant = implant;
        this.id = id;
    }

    public SyncDataPacket(FriendlyByteBuf buf) {
        this.implant = buf.readInt();
        this.id = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(implant);
        buf.writeInt(id);
    }

    public static void handle(SyncDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (packet.id == 100) {
                ClientData.selectAbility = packet.implant;
            }else if (packet.id >= 10) {
                ClientData.cooldown[packet.id - 10] = packet.implant;
            }else {
                ClientData.implant[packet.id] = packet.implant;
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
