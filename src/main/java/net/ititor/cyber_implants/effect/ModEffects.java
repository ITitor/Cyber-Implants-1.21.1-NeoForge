package net.ititor.cyber_implants.effect;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.effect.effect.CyberpsychosisEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, CyberImplants.MOD_ID);

    public static final Holder<MobEffect> CYBERPSYCHOSIS = MOB_EFFECTS.register("cyberpsychosis",
            () -> new CyberpsychosisEffect(MobEffectCategory.NEUTRAL, 3240562));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}