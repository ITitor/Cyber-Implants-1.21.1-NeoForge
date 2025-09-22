package net.ititor.cyber_implants.data;

import com.mojang.serialization.Codec;
import net.ititor.cyber_implants.CyberImplants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModData {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, CyberImplants.MOD_ID);

    public static final Supplier<AttachmentType<Integer>> EYE_IMPLANT0 = ATTACHMENT_TYPES.register(
            "eye_implant0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> EYE_IMPLANT1 = ATTACHMENT_TYPES.register(
            "eye_implant1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> EYE_IMPLANT2 = ATTACHMENT_TYPES.register(
            "eye_implant2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> BODY_IMPLANT0 = ATTACHMENT_TYPES.register(
            "body_implant0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> BODY_IMPLANT1 = ATTACHMENT_TYPES.register(
            "body_implant1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> BODY_IMPLANT2 = ATTACHMENT_TYPES.register(
            "body_implant2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> BODY_IMPLANT3 = ATTACHMENT_TYPES.register(
            "body_implant3", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> BODY_IMPLANT4 = ATTACHMENT_TYPES.register(
            "body_implant4", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> BODY_IMPLANT5 = ATTACHMENT_TYPES.register(
            "body_implant5", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> NEURAL_IMPLANT0 = ATTACHMENT_TYPES.register(
            "neural_implant0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> NEURAL_IMPLANT1 = ATTACHMENT_TYPES.register(
            "neural_implant1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> NEURAL_IMPLANT2 = ATTACHMENT_TYPES.register(
            "neural_implant2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> COMBAT_IMPLANT0 = ATTACHMENT_TYPES.register(
            "combat_implant0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> COMBAT_IMPLANT1 = ATTACHMENT_TYPES.register(
            "combat_implant1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> COMBAT_IMPLANT2 = ATTACHMENT_TYPES.register(
            "combat_implant2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> COMBAT_IMPLANT3 = ATTACHMENT_TYPES.register(
            "combat_implant3", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> SYSTEMIC_IMPLANT0 = ATTACHMENT_TYPES.register(
            "systemic_implant0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> SYSTEMIC_IMPLANT1 = ATTACHMENT_TYPES.register(
            "systemic_implant1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> SYSTEMIC_IMPLANT2 = ATTACHMENT_TYPES.register(
            "systemic_implant2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> SYSTEMIC_IMPLANT3 = ATTACHMENT_TYPES.register(
            "systemic_implant3", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );


    public static final Supplier<AttachmentType<Integer>> COOLDOWN0 = ATTACHMENT_TYPES.register(
            "cooldown0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> COOLDOWN1 = ATTACHMENT_TYPES.register(
            "cooldown1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> COOLDOWN2 = ATTACHMENT_TYPES.register(
            "cooldown2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> SELECT_ABILITY = ATTACHMENT_TYPES.register(
            "select_ability", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> CYBER_POINTS = ATTACHMENT_TYPES.register(
            "cyber_points", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );
    public static final Supplier<AttachmentType<Integer>> CYBER_LEVEL = ATTACHMENT_TYPES.register(
            "cyber_level", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );


    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
