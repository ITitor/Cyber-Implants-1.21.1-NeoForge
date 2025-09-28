package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.effect.ModEffects;
import net.ititor.cyber_implants.particle.particle.OreParticleOptions;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
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


    public static final int cd0 = 100;
    public static final int cd1 = 60;
    public static final int cd2 = 100;
    public static final int cd3 = 100;
    public static final int cd4 = 100;
    public static void handle(SendAbilityPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                Level level = player.level();
                if (!player.hasEffect(ModEffects.NEUROSHOCK)) {

                    //X-Ray
                    if (player.getData(ModData.EYE_IMPLANT0) > 0 && player.getData(ModData.COOLDOWN0) <= 0 && player.getData(ModData.SELECT_ABILITY) == 0) {
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
                                }
                            }
                        }
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
                                }
                            }
                        }
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
                                }
                            }
                        }
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
                                }
                            }
                        }
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
                                }
                            }
                        }
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
                                }
                            }
                        }
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
                                }
                            }
                        }
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
                                }
                            }
                        }

                        player.setData(ModData.COOLDOWN0, packet.cd0);
                    }

                    //Dash
                    if (player.getData(ModData.BODY_IMPLANT2) > 0 && player.getData(ModData.COOLDOWN1) <= 0 && player.getData(ModData.SELECT_ABILITY) == 1) {

                        //Dash
//                    float f1 = (float) Math.cos(Math.toRadians(player.getYRot() + 90));
//                    float f2 = (float) Math.sin(Math.toRadians(player.getYRot() + 90));
//                    if (player.onGround()) {
//                        player.push(f1 * 1.5, 0, f2 * 1.5);// 1x - 2 block dash
//                    } else {
//                        player.push(f1 * 0.75, 0, f2 * 0.75);// 1x - 2 block dash
//                    }

                        player.setData(ModData.COOLDOWN1, packet.cd1);
                    }

                    //Regenerate
                    if (player.getData(ModData.NEURAL_IMPLANT0) > 0 && player.getData(ModData.COOLDOWN2) <= 0 && player.getData(ModData.SELECT_ABILITY) == 2 && player.getHealth() != player.getMaxHealth() && player.getFoodData().getFoodLevel() > 1) {

                        for (int i = 0; i <= 20; i++) {
                            if (player.getHealth() == player.getMaxHealth() || player.getFoodData().getFoodLevel() <= 1) {
                                break;
                            }
                            player.heal(0.55f);
                            player.getFoodData().eat(-1, 0);
                        }

//                    player.playNotifySound(SoundEvents., SoundSource.MASTER, 0.65f, -1.5f);
                        player.hurt(player.damageSources().generic(), 0.05f);
                        player.heal(0.05f);

                        player.setData(ModData.COOLDOWN2, packet.cd2);
                    }

                    //Rage
                    if (player.getData(ModData.NEURAL_IMPLANT1) > 0 && player.getData(ModData.COOLDOWN3) <= 0 && player.getData(ModData.SELECT_ABILITY) == 3) {

                        player.addEffect(new MobEffectInstance(ModEffects.CYBER_RAGE, 300, 0, false, false, false));

                        player.setData(ModData.COOLDOWN3, packet.cd3);
                    }

                    //Neuroshock
                    if (player.getData(ModData.NEURAL_IMPLANT2) > 0 && player.getData(ModData.COOLDOWN4) <= 0 && player.getData(ModData.SELECT_ABILITY) == 4) {

                        LivingEntity target = ModUtils.findTarget(player, 10, true);
                        if (target != null) {
                            target.hurt(player.damageSources().generic(), 2);
                            target.addEffect(new MobEffectInstance(ModEffects.NEUROSHOCK, 100, 0));

                            player.setData(ModData.COOLDOWN4, packet.cd4);
                        }

                    }

                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
