package net.ititor.cyber_implants.effect.effect;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;

public class NeuroShockEffect extends MobEffect {
    public NeuroShockEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {

        if (livingEntity instanceof Mob mob) {
            mob.setTarget(null);
            mob.setLastHurtMob(null);
            mob.setLastHurtByMob(null);
            mob.targetSelector.getAvailableGoals().forEach(WrappedGoal::stop);
            mob.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
            mob.getBrain().eraseMemory(MemoryModuleType.PATH);
            mob.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
            mob.getBrain().eraseMemory(MemoryModuleType.CELEBRATE_LOCATION);
            mob.getBrain().eraseMemory(MemoryModuleType.DISTURBANCE_LOCATION);

            mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 4, 99, false, false, false));
        }

        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


    @EventBusSubscriber(modid = CyberImplants.MOD_ID, value = Dist.CLIENT)
    public static class ClientEventsStun {

        @SubscribeEvent
        public static void onMouseInputStun(InputEvent.InteractionKeyMappingTriggered event) {
            Player player = Minecraft.getInstance().player;

            if (player != null && player.hasEffect(ModEffects.NEUROSHOCK)) {
                event.setSwingHand(false);

                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onMovementInputStun(MovementInputUpdateEvent event) {
            Player player = event.getEntity();

            if (player.hasEffect(ModEffects.NEUROSHOCK)) {
                Input input = event.getInput();

                input.shiftKeyDown = false;
                input.jumping = false;

                input.forwardImpulse = 0;
                input.leftImpulse = 0;
            }
        }

        @SubscribeEvent
        public static void onBlockHighlightStun(RenderHighlightEvent.Block event) {
            Player player = Minecraft.getInstance().player;

            if (player != null && player.hasEffect(ModEffects.NEUROSHOCK))
                event.setCanceled(true);
        }
    }
}
