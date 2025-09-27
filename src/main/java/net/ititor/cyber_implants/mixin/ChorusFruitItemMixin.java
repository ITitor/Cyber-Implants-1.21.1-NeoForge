package net.ititor.cyber_implants.mixin;

import net.ititor.cyber_implants.data.ModData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChorusFruitItem.class)
public class ChorusFruitItemMixin {

    @Redirect(method = "finishUsingItem", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/event/EventHooks;onChorusFruitTeleport(Lnet/minecraft/world/entity/LivingEntity;DDD)Lnet/neoforged/neoforge/event/entity/EntityTeleportEvent$ChorusFruit;"))
    private EntityTeleportEvent.ChorusFruit finishUsingItem(LivingEntity entity, double targetX, double targetY, double targetZ) {
        Vec3 view = entity.getViewVector(0);
        Vec3 eyeVec = entity.getEyePosition(0);
        double maxDistance = 10;
        BlockHitResult ray = entity.level().clip(new ClipContext(eyeVec, eyeVec.add(view.x * maxDistance, view.y * maxDistance,
                view.z * maxDistance), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));

        Vec3 current = entity.position();
        Vec3 target = ray.getLocation();

        int distance = (int) Math.ceil(current.distanceTo(target));
        Vec3 start = current.add(0, 0, 0);
        Vec3 end = target.add(0, -1, 0);

        Vec3 delta = end.subtract(start);
        Vec3 dir = delta.normalize();

        double d1 = Mth.clamp(target.y, (double)entity.level().getMinBuildHeight(), (double)(entity.level().getMinBuildHeight() + ((ServerLevel)entity.level()).getLogicalHeight() - 1));


        if (entity.getData(ModData.BODY_IMPLANT4) > 0) {
            return EventHooks.onChorusFruitTeleport(entity, target.x, d1, target.z);
        }else {
            return EventHooks.onChorusFruitTeleport(entity, targetX, targetY,targetZ);
        }
    }
}
