package net.ititor.cyber_implants.event;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.data.ModData;
import net.ititor.cyber_implants.network.SyncDataPacket;
import net.ititor.cyber_implants.particle.particle.OreParticleOptions;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
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
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.IMPLANT0), 0));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.IMPLANT1), 1));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.IMPLANT2), 2));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.IMPLANT3), 3));
            PacketDistributor.sendToPlayer((ServerPlayer) player, new SyncDataPacket(player.getData(ModData.IMPLANT4), 4));
        }

        if (!player.hasData(ModData.COOLDOWN0)) {
            player.setData(ModData.COOLDOWN0, 0);
        }

        if (player.getData(ModData.COOLDOWN0) > 0 && player.hasData(ModData.COOLDOWN0)) {
            player.setData(ModData.COOLDOWN0, player.getData(ModData.COOLDOWN0) - 1);
        }
    }

    @SubscribeEvent
    public static void onClone(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.getData(ModData.IMPLANT1)){
            if (event.getSource().getDirectEntity() != null && new Random().nextInt(0, 100) < 100){
                entity.level().playSound((Player) null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP,
                        SoundSource.MASTER, 0.75F, 1.15F);
                event.setCanceled(true);
            }
        }

    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ModData.IMPLANT0)) {
            event.getEntity().setData(ModData.IMPLANT0, event.getOriginal().getData(ModData.IMPLANT0));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.IMPLANT1)) {
            event.getEntity().setData(ModData.IMPLANT1, event.getOriginal().getData(ModData.IMPLANT1));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.IMPLANT2)) {
            event.getEntity().setData(ModData.IMPLANT2, event.getOriginal().getData(ModData.IMPLANT2));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.IMPLANT3)) {
            event.getEntity().setData(ModData.IMPLANT3, event.getOriginal().getData(ModData.IMPLANT3));
        }if (event.isWasDeath() && event.getOriginal().hasData(ModData.IMPLANT4)) {
            event.getEntity().setData(ModData.IMPLANT4, event.getOriginal().getData(ModData.IMPLANT4));
        }


        if (event.isWasDeath() && event.getOriginal().hasData(ModData.COOLDOWN0)) {
            event.getEntity().setData(ModData.COOLDOWN0, event.getOriginal().getData(ModData.COOLDOWN0));
        }
    }

}
