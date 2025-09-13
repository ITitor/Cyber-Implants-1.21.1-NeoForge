package net.ititor.cyber_implants.particle.particle;

import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class OreParticle extends TextureSheetParticle {

    static final RandomSource RANDOM = RandomSource.create();
    private final SpriteSet sprites;

    protected OreParticle(ClientLevel level, double v, double v1, double v2, double v3, double v4, double v5, SpriteSet spriteSet) {
        super(level, v, v1, v2, v3, v4, v5);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = spriteSet;
        //this.quadSize *= 0.75F;
        this.quadSize = 0.15f;
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ModUtils.THROUGHTWALL;
    }

    @Override
    public int getLightColor(float v) {
        float $$1 = ((float)this.age + v) / (float)this.lifetime;
        $$1 = Mth.clamp($$1, 0.0F, 1.0F);
        int lightColor = super.getLightColor(v);
        int $$3 = lightColor & 255;
        int $$4 = lightColor >> 16 & 255;
        $$3 += (int)($$1 * 15.0F * 16.0F);
        if ($$3 > 240) {
            $$3 = 240;
        }

        return 240;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<OreParticleOptions> {
        private final double SPEED_FACTOR = 0.25;
        private final SpriteSet sprite;

        public Provider(SpriteSet p_172151_) {
            this.sprite = p_172151_;
        }

        public Particle createParticle(OreParticleOptions options, ClientLevel p_172163_, double p_172164_, double p_172165_, double p_172166_, double p_172167_, double p_172168_, double p_172169_) {
            OreParticle particle = new OreParticle(p_172163_, p_172164_, p_172165_, p_172166_, 0.0, 0.0, 0.0, this.sprite);
            particle.setColor(options.getColor().x, options.getColor().y, options.getColor().z);
            particle.setParticleSpeed(p_172167_ * 0.01, p_172168_ * 0.01, p_172169_ * 0.01);
            particle.setLifetime(7);
            return particle;
        }
    }
}
