package net.ititor.cyber_implants.effect.effect;

import net.ititor.cyber_implants.sound.ModSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class CyberpsychosisEffect extends MobEffect {
    public CyberpsychosisEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {

        if (livingEntity.tickCount % 250 == 0){
            if (new Random().nextInt(0, 100) < 27){
                livingEntity.hurt(livingEntity.damageSources().generic(), 0.5f);
            }
        }

        if (livingEntity.tickCount % 260 == 0){
            if (new Random().nextInt(0, 100) < 25){
                livingEntity.hurt(livingEntity.damageSources().generic(), 1);
            }
        }

        if (livingEntity.tickCount % 400 == 0){
            if (new Random().nextInt(0, 100) < 17){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 65, 0, false, false, false));
            }
        }

        if (livingEntity.tickCount % 400 == 0){
            if (new Random().nextInt(0, 100) < 15){
                livingEntity.playSound(SoundEvents.WARDEN_HEARTBEAT, 0.75f, 1f);
            }
        }

        if (livingEntity.tickCount % 400 == 0){
            if (new Random().nextInt(0, 100) < 15){
                livingEntity.playSound(SoundEvents.PHANTOM_BITE, 0.5f, 1f);
            }
        }

        if (livingEntity.tickCount % 3 == 0){
            livingEntity.playSound(ModSounds.GLITCH.get(), 0.075f, 2.0f);
        }

        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
