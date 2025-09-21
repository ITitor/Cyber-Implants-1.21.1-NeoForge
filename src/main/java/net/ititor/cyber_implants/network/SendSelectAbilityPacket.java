package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SendSelectAbilityPacket implements CustomPacketPayload {
    public static final Type<SendSelectAbilityPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "select_ability"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SendSelectAbilityPacket> STREAM_CODEC = CustomPacketPayload.codec(SendSelectAbilityPacket::write, buf -> new SendSelectAbilityPacket(buf));


    private final int id;
    public SendSelectAbilityPacket(int id) {
        this.id = id;
    }

    public SendSelectAbilityPacket(FriendlyByteBuf buf) {
        this.id = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(id);
    }

    public static void handle(SendSelectAbilityPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {

                serverPlayer.setData(ModData.SELECT_ABILITY, packet.id);

            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
