package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.particle.particle.OreParticleOptions;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SendAbilityPacket implements CustomPacketPayload {
    public static final Type<SendAbilityPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CyberImplants.MOD_ID, "ability"));
    public static final StreamCodec<RegistryFriendlyByteBuf, SendAbilityPacket> STREAM_CODEC = CustomPacketPayload.codec(SendAbilityPacket::write, SendAbilityPacket::new);

    private final int id;
    public SendAbilityPacket(int id) {
        this.id = id;
    }

    public SendAbilityPacket(FriendlyByteBuf buf) {
        this.id = buf.readInt();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(id);
    }


    private final int cd0 = 100;
    public static void handle(SendAbilityPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                Level level = player.level();

                if (packet.id == 0 && player.getData(ModData.COOLDOWN0) <= 0) {
                    for (int x = -15; x <= 15; x++) {
                    for (int y = -15; y <= 15; y++) {
                    for (int z = -15; z <= 15; z++) {
                        BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                        if (level.getBlockState(pos).is(Blocks.COAL_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_COAL_ORE)) {
                            if (!level.isClientSide) {
                                ModUtils.spawnParticles(player, level, new OreParticleOptions(0 / 255f, 0 / 255f, 0 / 255f, 3.35f),
                                        pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                        1, 0, 0, 0, 0, false);
                            }
                        }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.COPPER_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_COPPER_ORE)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(224 / 255f, 115 / 255f, 77 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.IRON_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_IRON_ORE)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(216 / 255f, 175 / 255f, 147 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.LAPIS_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_LAPIS_ORE)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(68 / 255f, 111 / 255f, 220 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.REDSTONE_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(255 / 255f, 0 / 255f, 0 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.EMERALD_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_EMERALD_ORE)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(23 / 255f, 221 / 255f, 98 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.DIAMOND_ORE) || level.getBlockState(pos).is(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(30 / 255f, 208 / 255f, 214 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                    for (int x = -15; x <= 15; x++) {
                        for (int y = -15; y <= 15; y++) {
                            for (int z = -15; z <= 15; z++) {
                                BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                                if (level.getBlockState(pos).is(Blocks.ANCIENT_DEBRIS)) {
                                    if (!level.isClientSide) {
                                        ModUtils.spawnParticles(player, level, new OreParticleOptions(101 / 255f, 71 / 255f, 64 / 255f, 3.35f),
                                                pos.getX() + 0.5, pos.getY() + 0.42, pos.getZ() + 0.5,
                                                1, 0, 0, 0, 0, false);
                                    }
                                }
                    }}}
                }

                player.setData(ModData.COOLDOWN0, packet.cd0);
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
