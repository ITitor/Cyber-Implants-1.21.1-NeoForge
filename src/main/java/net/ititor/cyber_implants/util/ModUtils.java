package net.ititor.cyber_implants.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.neoforged.neoforge.entity.PartEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ModUtils {

    public static ParticleRenderType THROUGHTWALL = new ParticleRenderType() {
        public BufferBuilder begin(Tesselator tesselator, TextureManager manager) {
            RenderSystem.depthMask(true);
            RenderSystem.disableDepthTest();
            RenderSystem.disableCull();
            RenderSystem.disableColorLogicOp();

            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            return tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        public String toString() {
            return "THROUGHTWALL";
        }
    };


    public static void spawnParticles(Level level, ParticleOptions particle, double x, double y, double z, int count,
                                      double deltaX, double deltaY, double deltaZ, double speed, boolean force) {
        level.getServer().getPlayerList().getPlayers().forEach(player -> (
                (ServerLevel) level).sendParticles(player, particle, force, x, y, z, count,
                deltaX, deltaY, deltaZ, speed)
        );
    }

    public static void spawnParticles(Player player, Level level, ParticleOptions particle, double x, double y, double z, int count,
                                      double deltaX, double deltaY, double deltaZ, double speed, boolean force) {
        ((ServerLevel) level).sendParticles(((ServerPlayer)player), particle, force, x, y, z, count, deltaX, deltaY, deltaZ, speed);
    }
    

    public static boolean shouldHealEntity(LivingEntity healer, LivingEntity target) {
//        if (healer instanceof NeutralMob neutralMob && neutralMob.isAngryAt(target)) {
//            return false;
//        } else if (healer == target) {
//            return true;
//        } else if (target.isAlliedTo(healer) || healer.isAlliedTo(target)) {
//            //Generic ally-check. Some mobs override it, such as summons
//            return true;
//        } else if (healer.getTeam() != null) {
//            //If we are on a team, only heal teammates
//            return target.isAlliedTo(healer.getTeam());
//        } else if (healer instanceof Player) {
//            //If we are a player and not on a team, we only want to heal other players
//            return target instanceof Player;
//        } else {
//            //Otherwise, heal like kind (ie undead to undead), but also xor check "enemy" status (most mob types are undefined)
//            return healer.getMobType() == target.getMobType() && (healer instanceof Enemy ^ target instanceof Enemy);
//        }
        if (target instanceof Player && healer instanceof Player){
            if (healer.getTeam() != null || target.getTeam() != null) {
                if (healer.getTeam() == target.getTeam()) {
                    return true;
                } else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public static float horizontalDistanceSqr(LivingEntity livingEntity, LivingEntity entity2) {
        var dx = livingEntity.getX() - entity2.getX();
        var dz = livingEntity.getZ() - entity2.getZ();
        return (float) (dx * dx + dz * dz);
    }

    /**Push**/
//    double d0 = player.getX() - targetEntity.getX();
//    double d1 = player.getZ() - targetEntity.getZ();
//                targetEntity.push(-d0 * 1.2, 0.1, -d1 * 1.2);
    /**Push**/


    @Nullable
    public static LivingEntity findTarget(LivingEntity caster, int distance, boolean checkForBlocks) {
        var target = ModUtils.raycastForEntity(caster.level(), caster, distance, checkForBlocks, 0.35f);
        if (target instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity livingTarget) {
            return livingTarget;
        } else {
            return null;
        }
    }

    /**AOE**/
//        var radiusSqr = 100; //5
//            pPlayer.level.getEntitiesOfClass(LivingEntity.class, pPlayer.getBoundingBox().inflate(5, 5, 5)/**Radius 10*10*10 **/,
//    livingEntity -> livingEntity != pPlayer &&
//            Utils.horizontalDistanceSqr(livingEntity, pPlayer) < radiusSqr &&
//            livingEntity.isPickable() &&
//            !livingEntity.isSpectator() &&
//            !Utils.shouldHealEntity(pPlayer, livingEntity)
//            ).forEach(targetEntity -> {
//        targetEntity.hurt(pPlayer.damageSources().magic(), (float)20);
//    });
    /**AOE**/


    public static HitResult raycastForEntity(Level level, Entity originEntity, float distance, boolean checkForBlocks) {
        Vec3 start = originEntity.getEyePosition();
        Vec3 end = originEntity.getLookAngle().normalize().scale(distance).add(start);

        return raycastForEntity(level, originEntity, start, end, checkForBlocks);
    }

    public static HitResult raycastForEntity(Level level, Entity originEntity, float distance, boolean checkForBlocks, float bbInflation) {
        Vec3 start = originEntity.getEyePosition();
        Vec3 end = originEntity.getLookAngle().normalize().scale(distance).add(start);

        return internalRaycastForEntity(level, originEntity, start, end, checkForBlocks, bbInflation, ModUtils::canHitWithRaycast);
    }

    public static HitResult raycastForEntity(Level level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks) {
        return internalRaycastForEntity(level, originEntity, start, end, checkForBlocks, 0, ModUtils::canHitWithRaycast);
    }

    public static HitResult raycastForEntity(Level level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks, float bbInflation, Predicate<? super Entity> filter) {
        return internalRaycastForEntity(level, originEntity, start, end, checkForBlocks, bbInflation, filter);
    }
    private static HitResult internalRaycastForEntity(Level level, Entity originEntity, Vec3 start, Vec3 end, boolean checkForBlocks, float bbInflation, Predicate<? super Entity> filter) {
        BlockHitResult blockHitResult = null;
        if (checkForBlocks) {
            blockHitResult = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, originEntity));
            end = blockHitResult.getLocation();
        }
        AABB range = originEntity.getBoundingBox().expandTowards(end.subtract(start));

        List<HitResult> hits = new ArrayList<>();
        List<? extends Entity> entities = level.getEntities(originEntity, range, filter);
        for (Entity target : entities) {
            HitResult hit = checkEntityIntersecting(target, start, end, bbInflation);
            if (hit.getType() != HitResult.Type.MISS)
                hits.add(hit);
        }

        if (!hits.isEmpty()) {
            hits.sort((o1, o2) -> o1.getLocation().distanceToSqr(start) < o2.getLocation().distanceToSqr(start) ? -1 : 1);
            return hits.get(0);
        } else if (checkForBlocks) {
            return blockHitResult;
        }
        return BlockHitResult.miss(end, Direction.UP, BlockPos.containing(end));
    }
    private static boolean canHitWithRaycast(Entity entity) {
        //IronsSpellbooks.LOGGER.debug("Utils.canHitWithRaycast: {} - {}", entity.getName().getString(), !(entity instanceof Projectile || entity instanceof AreaEffectCloud || entity instanceof ConePart));
        return entity.isPickable() && entity.isAlive();
    }
    public static HitResult checkEntityIntersecting(Entity entity, Vec3 start, Vec3 end, float bbInflation) {
        Vec3 hitPos = null;
        if (entity.isMultipartEntity()) {
            for (PartEntity p : entity.getParts()) {
                var hit = p.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
                if (hit != null) {
                    hitPos = hit;
                    break;
                }
            }
        } else {
            hitPos = entity.getBoundingBox().inflate(bbInflation).clip(start, end).orElse(null);
        }
        if (hitPos != null)
            return new EntityHitResult(entity, hitPos);
        else
            return BlockHitResult.miss(end, Direction.UP, BlockPos.containing(end));

    }

    public static BlockHitResult getTargetBlock(Level level, LivingEntity entity, ClipContext.Fluid clipContext, double reach) {
        var rotation = entity.getLookAngle().normalize().scale(reach);
        var pos = entity.getEyePosition();
        var dest = rotation.add(pos);
        return level.clip(new ClipContext(pos, dest, ClipContext.Block.COLLIDER, clipContext, entity));
    }
}
