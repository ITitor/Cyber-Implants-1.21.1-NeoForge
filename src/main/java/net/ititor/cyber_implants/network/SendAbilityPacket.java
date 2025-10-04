package net.ititor.cyber_implants.network;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.effect.ModEffects;
import net.ititor.cyber_implants.particle.particle.BlastwaveParticleOptions;
import net.ititor.cyber_implants.particle.particle.OreParticleOptions;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.joml.Vector3f;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
    public static final int cd5 = 100;
    public static final int cd6 = 100;
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

                    //Squid
                    if (player.getData(ModData.COMBAT_IMPLANT1) > 0 && player.getData(ModData.COOLDOWN5) <= 0 && player.getData(ModData.SELECT_ABILITY) == 5){
                        float radius = 5.0f;

                        if (!level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius, radius, radius), livingEntity -> livingEntity != player).isEmpty()) {
                            if (!level.isClientSide) {
                                ModUtils.spawnParticles(level, new BlastwaveParticleOptions(new Vector3f(0.0F, 0.0F, 0.0F), radius * 2),
                                        player.position().x, player.blockPosition().getY(), player.position().z, 1, 0, 0, 0, 0, false);
                            }
                            level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRE_EXTINGUISH,
                                    SoundSource.MASTER, 0.78F, 0.7F);

                            level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(radius, radius, radius),
                                    livingEntity -> livingEntity != player &&
                                            livingEntity.isPickable() &&
                                            !livingEntity.isSpectator() &&
                                            !ModUtils.shouldHealEntity((LivingEntity) player, livingEntity)
                            ).forEach(targetEntity -> {
                                if (!level.isClientSide) {
                                    for (int i = 0; i < 16; i++) {
                                        ModUtils.spawnParticles(level, ParticleTypes.LARGE_SMOKE, targetEntity.position().x + new Random().nextDouble(-0.8, 0.8),
                                                targetEntity.getEyePosition().y - 0.55 + new Random().nextDouble(-0.85, 0.85), targetEntity.position().z + new Random().nextDouble(-0.8, 0.8),
                                                1, 0.0, 0.0, 0.0, 0.05, false);
                                    }

                                    targetEntity.hurt(player.damageSources().playerAttack(player), 3);

                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 130, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 0));
                                    targetEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 150, 0));
                                }
                            });
                            player.setData(ModData.COOLDOWN5, packet.cd5);
                        }
                    }

                    //Overload
                    if (player.getData(ModData.COMBAT_IMPLANT2) > 0 && player.getData(ModData.COOLDOWN6) <= 0 && player.getData(ModData.SELECT_ABILITY) == 6){
                        if (!level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5, 5, 5), livingEntity -> livingEntity != player
                                && !ModUtils.shouldHealEntity((LivingEntity) player, livingEntity)).isEmpty()) {

                            level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_SONIC_CHARGE,
                                    SoundSource.MASTER, 0.9F, 1.6525F);
                            if (!level.isClientSide) {
                                ModUtils.spawnParticles(level, new BlastwaveParticleOptions(new Vector3f(162 / 255F, 0, 0), 6),
                                        player.position().x, player.blockPosition().getY() + 0.05, player.position().z, 1, 0, 0, 0, 0, false);
                            }

                            /**Timer**/
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                public void run() {

                                    for (int i = 0; i < 5; i++) {
                                        level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_EXPLODE,
                                                SoundSource.MASTER, 1.7F, 0.8F);
                                        if (!level.isClientSide()) {
                                            ModUtils.spawnParticles(level, ParticleTypes.EXPLOSION_EMITTER,
                                                    player.position().x, player.position().y + 1, player.position().z,
                                                    1, 0.0, 0.0, 0.0, 0.0325, false);
                                        }
                                    }

                                    level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5, 5, 5),
                                            livingEntity -> livingEntity != player &&
                                                    livingEntity.isPickable() &&
                                                    !livingEntity.isSpectator() &&
                                                    !ModUtils.shouldHealEntity((LivingEntity) player, livingEntity)
                                    ).forEach(targetEntity -> {
                                        targetEntity.hurt(player.damageSources().playerAttack(player), 15);

                                        double d0 = player.getX() - targetEntity.getX();
                                        double d1 = player.getZ() - targetEntity.getZ();
                                        targetEntity.push(-d0 * 0.175, 0.685, -d1 * 0.175);

                                        if (targetEntity instanceof Player) {
                                            if (!level.isClientSide()) {
                                                //Add Motion
//                                                ModMessages.sendToPlayer(new PacketPlayerMotion(targetEntity.getDeltaMovement().x,
//                                                        targetEntity.getDeltaMovement().y, targetEntity.getDeltaMovement().z), (ServerPlayer) targetEntity);
                                            }
                                        }
                                    });
                                    player.hurt(player.damageSources().generic(), 4);
                                }
                            }, 1155);
                            /**Timer**/
                            player.setData(ModData.COOLDOWN6, packet.cd6);
                        }
                    }

                    //EnderChest
                    if (player.getData(ModData.SYSTEMIC_IMPLANT1) > 0 && player.getData(ModData.SELECT_ABILITY) == 7) {

                        PlayerEnderChestContainer playerenderchestcontainer = player.getEnderChestInventory();
                        player.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> {
                            return ChestMenu.threeRows(p_53124_, p_53125_, playerenderchestcontainer);
                        }, Component.translatable("container.enderchest")));
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
