package net.ititor.cyber_implants.particle;

import com.mojang.serialization.MapCodec;
import net.ititor.cyber_implants.CyberImplants;
import net.ititor.cyber_implants.particle.particle.OreParticle;
import net.ititor.cyber_implants.particle.particle.OreParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = CyberImplants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, CyberImplants.MOD_ID);

    public static final Supplier<ParticleType<OreParticleOptions>> ORE_PARTICLE = PARTICLE_TYPES.register("ore_particle", () -> new ParticleType<>(true) {
        public MapCodec<OreParticleOptions> codec() {
            return OreParticleOptions.MAP_CODEC;
        }
        public StreamCodec<? super RegistryFriendlyByteBuf, OreParticleOptions> streamCodec() {
            return OreParticleOptions.STREAM_CODEC;
        }
    });

    @SubscribeEvent
    public static void onParticleRegistry(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ORE_PARTICLE.get(), OreParticle.Provider::new);
    }


    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
