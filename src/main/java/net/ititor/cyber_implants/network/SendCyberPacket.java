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

public class SendCyberPacket implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SendCyberPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "add_cyber_to_player"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SendCyberPacket> STREAM_CODEC = CustomPacketPayload.codec(SendCyberPacket::write, buf -> new SendCyberPacket(buf));

    private final int implant;
    private final int id;
    public SendCyberPacket(int implant, int id) {
        this.implant = implant;
        this.id = id;
    }

    public SendCyberPacket(FriendlyByteBuf buf) {
        this.implant = buf.readInt();
        this.id = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(implant);
        buf.writeInt(id);
    }

    public static void handle(SendCyberPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                if (packet.id == 0) {
                    serverPlayer.setData(ModData.EYE_IMPLANT0, packet.implant);
                }if (packet.id == 1) {
                    serverPlayer.setData(ModData.EYE_IMPLANT1, packet.implant);
                }if (packet.id == 2) {
                    serverPlayer.setData(ModData.EYE_IMPLANT2, packet.implant);
                }

                if (packet.id == 3) {
                    serverPlayer.setData(ModData.BODY_IMPLANT0, packet.implant);
                }if (packet.id == 4) {
                    serverPlayer.setData(ModData.BODY_IMPLANT1, packet.implant);
                }if (packet.id == 5) {
                    serverPlayer.setData(ModData.BODY_IMPLANT2, packet.implant);
                }if (packet.id == 6) {
                    serverPlayer.setData(ModData.BODY_IMPLANT3, packet.implant);
                }if (packet.id == 7) {
                    serverPlayer.setData(ModData.BODY_IMPLANT4, packet.implant);
                }if (packet.id == 8) {
                    serverPlayer.setData(ModData.BODY_IMPLANT5, packet.implant);
                }

                if (packet.id == 9) {
                    serverPlayer.setData(ModData.NEURAL_IMPLANT0, packet.implant);
                }if (packet.id == 10) {
                    serverPlayer.setData(ModData.NEURAL_IMPLANT1, packet.implant);
                }if (packet.id == 11) {
                    serverPlayer.setData(ModData.NEURAL_IMPLANT2, packet.implant);
                }

                if (packet.id == 12) {
                    serverPlayer.setData(ModData.COMBAT_IMPLANT0, packet.implant);
                }if (packet.id == 13) {
                    serverPlayer.setData(ModData.COMBAT_IMPLANT1, packet.implant);
                }if (packet.id == 14) {
                    serverPlayer.setData(ModData.COMBAT_IMPLANT2, packet.implant);
                }if (packet.id == 15) {
                    serverPlayer.setData(ModData.COMBAT_IMPLANT3, packet.implant);
                }

                if (packet.id == 16) {
                    serverPlayer.setData(ModData.SYSTEMIC_IMPLANT0, packet.implant);
                }if (packet.id == 17) {
                    serverPlayer.setData(ModData.SYSTEMIC_IMPLANT1, packet.implant);
                }if (packet.id == 18) {
                    serverPlayer.setData(ModData.SYSTEMIC_IMPLANT2, packet.implant);
                }if (packet.id == 19) {
                    serverPlayer.setData(ModData.SYSTEMIC_IMPLANT3, packet.implant);
                }
            }
        });
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
