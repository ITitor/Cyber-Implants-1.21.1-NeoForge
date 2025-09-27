package net.ititor.cyber_implants.effect;

import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.effect.effect.CyberRageEffect;
import net.ititor.cyber_implants.effect.effect.CyberpsychosisEffect;
import net.ititor.cyber_implants.effect.effect.ModEffect;
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

    public static final Holder<MobEffect> CYBER_FORCE = MOB_EFFECTS.register("cyber_force",
            () -> new ModEffect(MobEffectCategory.BENEFICIAL, 3240562)
        .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("effect.cyber_force"), 0.20000000298023224, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        .addAttributeModifier(Attributes.SNEAKING_SPEED, ResourceLocation.withDefaultNamespace("effect.cyber_force"), 0.10000000298023224, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        .addAttributeModifier(Attributes.STEP_HEIGHT, ResourceLocation.withDefaultNamespace("effect.cyber_force"), 1, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final Holder<MobEffect> CYBER_RAGE = MOB_EFFECTS.register("cyber_rage",
            () -> new CyberRageEffect(MobEffectCategory.BENEFICIAL, 3240562)
        .addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.withDefaultNamespace("effect.cyber_rage"), 5, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.ARMOR, ResourceLocation.withDefaultNamespace("effect.cyber_rage"), 5, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, ResourceLocation.withDefaultNamespace("effect.cyber_rage"), 0.555, AttributeModifier.Operation.ADD_VALUE)
        .addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.withDefaultNamespace("effect.cyber_rage"), 0.15000000298023224, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("effect.cyber_rage"), 0.27500000298023224, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
    );


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}