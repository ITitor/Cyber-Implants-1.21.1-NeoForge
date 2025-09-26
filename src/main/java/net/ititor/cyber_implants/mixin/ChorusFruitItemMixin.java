package net.ititor.cyber_implants.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChorusFruitItem.class)
public class ChorusFruitItemMixin {

    @Inject(method = "finishUsingItem", at = @At(value = "HEAD"))
    public void finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving, CallbackInfoReturnable<ItemStack> cir) {
//        if (!level.isClientSide) {
//            for(int i = 0; i < 16; ++i) {
//                double d0 = entityLiving.getX() + (entityLiving.getRandom().nextDouble() - 0.5) * 16.0;
//                double d1 = Mth.clamp(entityLiving.getY() + (double)(entityLiving.getRandom().nextInt(16) - 8), (double)level.getMinBuildHeight(), (double)(level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1));
//                double d2 = entityLiving.getZ() + (entityLiving.getRandom().nextDouble() - 0.5) * 16.0;
//                if (entityLiving.isPassenger()) {
//                    entityLiving.stopRiding();
//                }
//
//                Vec3 vec3 = entityLiving.position();
////                EntityTeleportEvent.ChorusFruit event = EventHooks.onChorusFruitTeleport(entityLiving, d0, d1, d2);
////                if (event.isCanceled()) {
////                    return;
////                }
////
////                if (entityLiving.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
////                    level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entityLiving));
////                    SoundSource soundsource;
////                    SoundEvent soundevent;
////                    if (entityLiving instanceof Fox) {
////                        soundevent = SoundEvents.FOX_TELEPORT;
////                        soundsource = SoundSource.NEUTRAL;
////                    } else {
////                        soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
////                        soundsource = SoundSource.PLAYERS;
////                    }
////
////                    level.playSound((Player)null, entityLiving.getX(), entityLiving.getY(), entityLiving.getZ(), soundevent, soundsource);
////                    entityLiving.resetFallDistance();
////                    break;
////                }
//            }
//
//            if (entityLiving instanceof Player) {
//                Player player = (Player)entityLiving;
//                player.resetCurrentImpulseContext();
//                player.getCooldowns().addCooldown(Items.CHORUS_FRUIT, 20);
//            }
//        }
    }
}
