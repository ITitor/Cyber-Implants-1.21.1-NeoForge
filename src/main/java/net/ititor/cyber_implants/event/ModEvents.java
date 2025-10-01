package net.ititor.cyber_implants.event;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.effect.ModEffects;
import net.ititor.cyber_implants.network.SyncDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Random;

@EventBusSubscriber(modid = CyberImplants.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        Level level = event.getEntity().level();
//        if (player.getTags().contains("cyber_0")){
//            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5, 1, false, false, false));
//        }
//        if (player.getTags().contains("cyber_1")){
//            if (player.tickCount % 5 == 0) {
//                for (int x = -10; x <= 10; x++) {
//                for (int y = -10; y <= 10; y++) {
//                for (int z = -10; z <= 10; z++) {
//                    BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
//                    if (level.getBlockState(pos).is(Blocks.COAL_ORE)) {
//                        ModUtils.spawnParticles(player, level, new OreParticleOptions(0/255f, 0/255f, 0/255f, 1),
//                        pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5,
//                        1, 0, 0, 0, 0, false);
//                    }
//                }}}
//            }
//        }

        if (!level.isClientSide) {
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.EYE_IMPLANT0), 0));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.EYE_IMPLANT1), 1));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.EYE_IMPLANT2), 2));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.BODY_IMPLANT0), 3));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.BODY_IMPLANT1), 4));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.BODY_IMPLANT2), 5));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.BODY_IMPLANT3), 6));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.BODY_IMPLANT4), 7));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.BODY_IMPLANT5), 8));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.NEURAL_IMPLANT0), 9));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.NEURAL_IMPLANT1), 10));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.NEURAL_IMPLANT2), 11));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COMBAT_IMPLANT0), 12));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COMBAT_IMPLANT1), 13));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COMBAT_IMPLANT2), 14));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COMBAT_IMPLANT3), 15));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.SYSTEMIC_IMPLANT0), 16));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.SYSTEMIC_IMPLANT1), 17));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.SYSTEMIC_IMPLANT2), 18));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.SYSTEMIC_IMPLANT3), 19));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN0), 50));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN1), 51));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN2), 52));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN3), 53));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN4), 54));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN5), 55));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN6), 56));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN_FIRE), 57));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.COOLDOWN_UNDYING), 58));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.CYBER_POINTS), 98));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.CYBER_LEVEL), 99));

            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.SELECT_ABILITY), 100));
        }

        if (!player.hasData(ModData.COOLDOWN0)) {
            player.setData(ModData.COOLDOWN0, 0);
        }if (!player.hasData(ModData.COOLDOWN1)) {
            player.setData(ModData.COOLDOWN1, 0);
        }if (!player.hasData(ModData.COOLDOWN2)) {
            player.setData(ModData.COOLDOWN2, 0);
        }if (!player.hasData(ModData.COOLDOWN3)) {
            player.setData(ModData.COOLDOWN3, 0);
        }if (!player.hasData(ModData.COOLDOWN4)) {
            player.setData(ModData.COOLDOWN4, 0);
        }if (!player.hasData(ModData.COOLDOWN5)) {
            player.setData(ModData.COOLDOWN5, 0);
        }if (!player.hasData(ModData.COOLDOWN6)) {
            player.setData(ModData.COOLDOWN6, 0);
        }

        // Спектральный Анализ
        if (player.getData(ModData.COOLDOWN0) > 0 && player.hasData(ModData.COOLDOWN0)) {
            player.setData(ModData.COOLDOWN0, player.getData(ModData.COOLDOWN0) - 1);
        }
        //Импульсный Ускоритель
        if (player.getData(ModData.COOLDOWN1) > 0 && player.hasData(ModData.COOLDOWN1)) {
            player.setData(ModData.COOLDOWN1, player.getData(ModData.COOLDOWN1) - 1);
        }
        //Клеточный Реконструктор
        if (player.getData(ModData.COOLDOWN2) > 0 && player.hasData(ModData.COOLDOWN2)) {
            player.setData(ModData.COOLDOWN2, player.getData(ModData.COOLDOWN2) - 1);
        }
        //Нейростимулятор
        if (player.getData(ModData.COOLDOWN3) > 0 && player.hasData(ModData.COOLDOWN3)) {
            player.setData(ModData.COOLDOWN3, player.getData(ModData.COOLDOWN3) - 1);
        }
        if (player.getData(ModData.COOLDOWN4) > 0 && player.hasData(ModData.COOLDOWN4)) {
            player.setData(ModData.COOLDOWN4, player.getData(ModData.COOLDOWN4) - 1);
        }
        if (player.getData(ModData.COOLDOWN5) > 0 && player.hasData(ModData.COOLDOWN5)) {
            player.setData(ModData.COOLDOWN5, player.getData(ModData.COOLDOWN5) - 1);
        }
        if (player.getData(ModData.COOLDOWN6) > 0 && player.hasData(ModData.COOLDOWN6)) {
            player.setData(ModData.COOLDOWN6, player.getData(ModData.COOLDOWN6) - 1);
        }

        if (player.getData(ModData.EYE_IMPLANT2) > 0){
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 15, 0, false, false, false));
        }

        // Ингибитор Токсинов
        if (player.getData(ModData.BODY_IMPLANT1) > 0){
            if (player.hasEffect(MobEffects.HUNGER)){player.removeEffect(MobEffects.HUNGER);}
            if (player.hasEffect(MobEffects.POISON)){player.removeEffect(MobEffects.POISON);}
            if (player.hasEffect(MobEffects.WITHER)){player.removeEffect(MobEffects.WITHER);}
            if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)){player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);}
            if (player.hasEffect(MobEffects.WEAKNESS)){player.removeEffect(MobEffects.WEAKNESS);}
        }

        if (player.getData(ModData.BODY_IMPLANT5) > 0){
            player.addEffect(new MobEffectInstance(ModEffects.CYBER_FORCE, 15, 0, false, false, false));
        }

        if (player.getData(ModData.SYSTEMIC_IMPLANT2) > 0){
            if (!player.isOnFire()) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 150, 0, false, false, false));
            }
        }

        if (player.getData(ModData.SYSTEMIC_IMPLANT3) > 0){
            if (getDayTime(player.level()) >= 0 && getDayTime(player.level()) < 13000) {
                if (player.tickCount % 320 == 0){
                    player.heal(1);
                    if (player.getFoodData().getFoodLevel() < 20) {
                        player.getFoodData().eat(1, 0);
                    } if (player.getFoodData().getSaturationLevel() < 20) {
                        player.getFoodData().eat(0, 1);
                    }
                }
            }
        }
    }
    private static int getDayTime(Level level) {
        return (int)(level.getDayTime() % 24000L);
    }

    @SubscribeEvent
    public static void onDamageIncoming(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        // Титановый скелет - -10% урона
        if (entity.getData(ModData.BODY_IMPLANT0) > 0){
            event.setAmount(event.getOriginalAmount() * 0.9f);
        }

        // Тактик - 10% на уворот
        if (entity.getData(ModData.EYE_IMPLANT1) > 0){
            if (event.getSource().getDirectEntity() != null && new Random().nextInt(0, 100) < 10){
                entity.level().playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP,
                        SoundSource.MASTER, 0.75F, 1.15F);
                event.setCanceled(true);
            }
        }

        if (entity.getData(ModData.COMBAT_IMPLANT0) > 0){
            if (event.getSource().getDirectEntity() != null){
                event.getSource().getDirectEntity().hurt(event.getSource(), event.getOriginalAmount() * 0.2f);
            }
        }

        //Гидравлические Мышцы
        if (event.getSource().getDirectEntity() != null) {
            if (event.getSource().getDirectEntity().getData(ModData.COMBAT_IMPLANT3) > 0) {
                event.setAmount(event.getOriginalAmount() + 1);
            }
        }


        if (entity.hasEffect(ModEffects.INVULNERABILITY)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getData(ModData.SYSTEMIC_IMPLANT0) > 0){
            event.setCanceled(true);
            entity.setHealth(5);
            entity.addEffect(new MobEffectInstance(ModEffects.INVULNERABILITY, 100, 0, false, false, true));
        }
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        LivingEntity entity = event.getEntity();

        //Амортизационные пластины
        if (entity.getData(ModData.BODY_IMPLANT3) > 0) {
            event.setDistance(event.getDistance()-5);
        }
    }

    @SubscribeEvent
    public static void onPickupXp(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();

        player.setData(ModData.CYBER_POINTS, player.getData(ModData.CYBER_POINTS)+event.getOrb().getValue());
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ModData.EYE_IMPLANT0)) {
            event.getEntity().setData(ModData.EYE_IMPLANT0, event.getOriginal().getData(ModData.EYE_IMPLANT0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.EYE_IMPLANT1)) {
            event.getEntity().setData(ModData.EYE_IMPLANT1, event.getOriginal().getData(ModData.EYE_IMPLANT1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.EYE_IMPLANT2)) {
            event.getEntity().setData(ModData.EYE_IMPLANT2, event.getOriginal().getData(ModData.EYE_IMPLANT2));
        }

        if (event.isWasDeath() && event.getOriginal().hasData(ModData.BODY_IMPLANT0)) {
            event.getEntity().setData(ModData.BODY_IMPLANT0, event.getOriginal().getData(ModData.BODY_IMPLANT0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.BODY_IMPLANT1)) {
            event.getEntity().setData(ModData.BODY_IMPLANT1, event.getOriginal().getData(ModData.BODY_IMPLANT1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.BODY_IMPLANT2)) {
            event.getEntity().setData(ModData.BODY_IMPLANT2, event.getOriginal().getData(ModData.BODY_IMPLANT2));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.BODY_IMPLANT2)) {
            event.getEntity().setData(ModData.BODY_IMPLANT3, event.getOriginal().getData(ModData.BODY_IMPLANT3));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.BODY_IMPLANT3)) {
            event.getEntity().setData(ModData.BODY_IMPLANT4, event.getOriginal().getData(ModData.BODY_IMPLANT4));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.BODY_IMPLANT4)) {
            event.getEntity().setData(ModData.BODY_IMPLANT5, event.getOriginal().getData(ModData.BODY_IMPLANT5));
        }

        if (event.isWasDeath() && event.getOriginal().hasData(ModData.NEURAL_IMPLANT0)) {
            event.getEntity().setData(ModData.NEURAL_IMPLANT0, event.getOriginal().getData(ModData.NEURAL_IMPLANT0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.NEURAL_IMPLANT1)) {
            event.getEntity().setData(ModData.NEURAL_IMPLANT1, event.getOriginal().getData(ModData.NEURAL_IMPLANT1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.NEURAL_IMPLANT2)) {
            event.getEntity().setData(ModData.NEURAL_IMPLANT2, event.getOriginal().getData(ModData.NEURAL_IMPLANT2));
        }

        if (event.isWasDeath() && event.getOriginal().hasData(ModData.COMBAT_IMPLANT0)) {
            event.getEntity().setData(ModData.COMBAT_IMPLANT0, event.getOriginal().getData(ModData.COMBAT_IMPLANT0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COMBAT_IMPLANT1)) {
            event.getEntity().setData(ModData.COMBAT_IMPLANT1, event.getOriginal().getData(ModData.COMBAT_IMPLANT1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COMBAT_IMPLANT2)) {
            event.getEntity().setData(ModData.COMBAT_IMPLANT2, event.getOriginal().getData(ModData.COMBAT_IMPLANT2));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COMBAT_IMPLANT3)) {
            event.getEntity().setData(ModData.COMBAT_IMPLANT3, event.getOriginal().getData(ModData.COMBAT_IMPLANT3));
        }

        if (event.isWasDeath() && event.getOriginal().hasData(ModData.SYSTEMIC_IMPLANT0)) {
            event.getEntity().setData(ModData.SYSTEMIC_IMPLANT0, event.getOriginal().getData(ModData.SYSTEMIC_IMPLANT0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.SYSTEMIC_IMPLANT1)) {
            event.getEntity().setData(ModData.SYSTEMIC_IMPLANT1, event.getOriginal().getData(ModData.SYSTEMIC_IMPLANT1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.SYSTEMIC_IMPLANT2)) {
            event.getEntity().setData(ModData.SYSTEMIC_IMPLANT2, event.getOriginal().getData(ModData.SYSTEMIC_IMPLANT2));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.SYSTEMIC_IMPLANT3)) {
            event.getEntity().setData(ModData.SYSTEMIC_IMPLANT3, event.getOriginal().getData(ModData.SYSTEMIC_IMPLANT3));
        }


        if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN0)) {
            event.getEntity().setData(ModData.COOLDOWN0, event.getOriginal().getData(ModData.COOLDOWN0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN1)) {
            event.getEntity().setData(ModData.COOLDOWN1, event.getOriginal().getData(ModData.COOLDOWN1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN2)) {
            event.getEntity().setData(ModData.COOLDOWN2, event.getOriginal().getData(ModData.COOLDOWN2));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN3)) {
            event.getEntity().setData(ModData.COOLDOWN3, event.getOriginal().getData(ModData.COOLDOWN3));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN4)) {
            event.getEntity().setData(ModData.COOLDOWN4, event.getOriginal().getData(ModData.COOLDOWN4));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN5)) {
            event.getEntity().setData(ModData.COOLDOWN5, event.getOriginal().getData(ModData.COOLDOWN5));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN6)) {
            event.getEntity().setData(ModData.COOLDOWN6, event.getOriginal().getData(ModData.COOLDOWN6));
        }
        if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN_FIRE)) {
            event.getEntity().setData(ModData.COOLDOWN_FIRE, event.getOriginal().getData(ModData.COOLDOWN_FIRE));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN_UNDYING)) {
            event.getEntity().setData(ModData.COOLDOWN_UNDYING, event.getOriginal().getData(ModData.COOLDOWN_UNDYING));
        }

        if (event.isWasDeath() && event.getOriginal().hasData(ModData.SELECT_ABILITY)) {
            event.getEntity().setData(ModData.SELECT_ABILITY, event.getOriginal().getData(ModData.SELECT_ABILITY));
        }

        if (event.isWasDeath() && event.getOriginal().hasData(ModData.CYBER_POINTS)) {
            event.getEntity().setData(ModData.CYBER_POINTS, event.getOriginal().getData(ModData.CYBER_POINTS));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.CYBER_LEVEL)) {
            event.getEntity().setData(ModData.CYBER_LEVEL, event.getOriginal().getData(ModData.CYBER_LEVEL));
        }
    }

}
