package net.ititor.cyber_implants.event;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.particle.particle.OreParticleOptions;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = CyberImplants.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        if (player.getTags().contains("cyber_1")){
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 5, 1, false, false, false));
        }
        if (player.getTags().contains("cyber_2")){
            if (player.tickCount % 5 == 0) {
                for (int x = -10; x <= 10; x++) {
                for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {
                    BlockPos pos = new BlockPos(player.blockPosition().getX() + x, player.blockPosition().getY() + y, player.blockPosition().getZ() + z);
                    if (level.getBlockState(pos).is(Blocks.COAL_ORE)) {
                        ModUtils.spawnParticles(player, level, new OreParticleOptions(255/255f, 255/255f, 255/255f, 1),
                        pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5,
                        1, 0, 0, 0, 0, false);
                    }
                }}}
            }
        }
    }
}
