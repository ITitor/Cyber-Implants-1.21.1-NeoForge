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

    public static final Supplier<AttachmentType<Boolean>> IMPLANT0 = ATTACHMENT_TYPES.register(
            "implant0", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );public static final Supplier<AttachmentType<Boolean>> IMPLANT1 = ATTACHMENT_TYPES.register(
            "implant1", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );public static final Supplier<AttachmentType<Boolean>> IMPLANT2 = ATTACHMENT_TYPES.register(
            "implant2", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );public static final Supplier<AttachmentType<Boolean>> IMPLANT3 = ATTACHMENT_TYPES.register(
            "implant3", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );public static final Supplier<AttachmentType<Boolean>> IMPLANT4 = ATTACHMENT_TYPES.register(
            "implant4", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );

    public static final Supplier<AttachmentType<Integer>> COOLDOWN0 = ATTACHMENT_TYPES.register(
            "cooldown0", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );


    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
