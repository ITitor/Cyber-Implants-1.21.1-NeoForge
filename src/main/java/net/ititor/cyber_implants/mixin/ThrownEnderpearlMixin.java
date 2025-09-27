package net.ititor.cyber_implants.mixin;

import net.ititor.cyber_implants.data.ModData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThrownEnderpearl.class)
public class ThrownEnderpearlMixin {

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/event/EventHooks;onEnderPearlLand(Lnet/minecraft/server/level/ServerPlayer;DDDLnet/minecraft/world/entity/projectile/ThrownEnderpearl;FLnet/minecraft/world/phys/HitResult;)Lnet/neoforged/neoforge/event/entity/EntityTeleportEvent$EnderPearl;"))
    public EntityTeleportEvent.EnderPearl onHit(ServerPlayer entity, double targetX, double targetY, double targetZ, ThrownEnderpearl pearlEntity, float attackDamage, HitResult hitResult) {

        if (entity.getData(ModData.BODY_IMPLANT4) > 0) {
            return EventHooks.onEnderPearlLand(entity, targetX, targetY, targetZ, pearlEntity, 0, hitResult);
        }else {
            return EventHooks.onEnderPearlLand(entity, targetX, targetY, targetZ, pearlEntity, attackDamage, hitResult);
        }
    }
}
