package net.ititor.cyber_implants.effect.effect;

import net.ititor.cyber_implants.effect.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class CyberRageEffect extends MobEffect {
    public CyberRageEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {

        if (livingEntity.tickCount % 50 == 0){
            livingEntity.heal(1);
        }

        if (livingEntity.getEffect(ModEffects.CYBER_RAGE).getDuration() <= 1) {
            livingEntity.hurt(livingEntity.damageSources().genericKill(), 2);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 140, 0, false, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 140, 0, false, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 140, 0, false, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0, false, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0, false, false, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0, false, false, false));
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
