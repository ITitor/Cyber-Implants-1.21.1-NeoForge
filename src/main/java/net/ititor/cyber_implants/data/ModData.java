package net.ititor.cyber_implants.data;

import com.mojang.serialization.Codec;
import net.ititor.cyber_implants.CyberImplants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModData {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, CyberImplants.MOD_ID);

    public static final Supplier<AttachmentType<Integer>> IMPLANT0 = ATTACHMENT_TYPES.register(
            "implant0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> IMPLANT1 = ATTACHMENT_TYPES.register(
            "implant1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()

    );public static final Supplier<AttachmentType<Integer>> IMPLANT2 = ATTACHMENT_TYPES.register(
            "implant2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> IMPLANT3 = ATTACHMENT_TYPES.register(
            "implant3", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );public static final Supplier<AttachmentType<Integer>> IMPLANT4 = ATTACHMENT_TYPES.register(
            "implant4", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );

    public static final Supplier<AttachmentType<Integer>> COOLDOWN0 = ATTACHMENT_TYPES.register(
            "cooldown0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );
    public static final Supplier<AttachmentType<Integer>> COOLDOWN1 = ATTACHMENT_TYPES.register(
            "cooldown1", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );
    public static final Supplier<AttachmentType<Integer>> COOLDOWN2 = ATTACHMENT_TYPES.register(
            "cooldown2", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );


    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
