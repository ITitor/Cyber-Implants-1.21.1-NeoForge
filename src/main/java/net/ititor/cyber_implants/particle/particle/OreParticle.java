package net.ititor.cyber_implants.particle.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ititor.cyber_implants.util.ModUtils;
import net.minecraft.Util;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class OreParticle extends TextureSheetParticle {

    static final RandomSource RANDOM = RandomSource.create();
    private final SpriteSet sprites;
    private final float targetSize;
    protected OreParticle(ClientLevel level, double v, double v1, double v2, double v3, double v4, double v5, SpriteSet spriteSet, OreParticleOptions options) {
        super(level, v, v1, v2, v3, v4, v5);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = spriteSet;
        //this.quadSize *= 0.75F;
        this.targetSize = options.getScale();
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

    @Override
    public float getQuadSize(float partialTick) {
        return this.quadSize *  targetSize;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }


    private static final Vector3f ROTATION_VECTOR = Util.make(new Vector3f(0.5F, 0.5F, 0.5F), Vector3f::normalize);
    private static final Vector3f TRANSFORM_VECTOR = new Vector3f(-1.0F, -1.0F, 0.0F);
    private static final float DEGREES_90 = Mth.PI / 2f;
    @Override
    public void render(VertexConsumer buffer, Camera camera, float partialticks) {
        this.alpha = 1.0F - Mth.clamp((this.age + partialticks) / (float) this.lifetime, 0, 1F);
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(0));
            quaternionf.mul(Axis.XP.rotation(-DEGREES_90));
        }, new Vec3(0,0.5,0));
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(-(float) Math.PI));
            quaternionf.mul(Axis.XP.rotation(DEGREES_90));
        }, new Vec3(0,0.5,0));

        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(0));
            quaternionf.mul(Axis.XP.rotation(-DEGREES_90));
        }, new Vec3(0,-0.5,0));
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(-(float) Math.PI));
            quaternionf.mul(Axis.XP.rotation(DEGREES_90));
        }, new Vec3(0,-0.5,0));


        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(0));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(0,0,0.5));
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(-(float) Math.PI));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(0,0,0.5));

        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(0));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(0,0,-0.5));
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(-(float) Math.PI));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(0,0,-0.5));


        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(0 + ((float) Math.PI/2)));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(0.5,0,0));
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(-(float) Math.PI + ((float) Math.PI/2)));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(0.5,0,0));

        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(0 +((float) Math.PI/2)));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(-0.5,0,0));
        this.renderRotatedParticle(buffer, camera, partialticks, (quaternionf) -> {
            quaternionf.mul(Axis.YP.rotation(-(float) Math.PI +((float) Math.PI/2)));
            quaternionf.mul(Axis.XP.rotation(0));
        }, new Vec3(-0.5,0,0));
    }

    private void renderRotatedParticle(VertexConsumer pConsumer, Camera camera, float partialTick, Consumer<Quaternionf> pQuaternion, Vec3 add) {
        Vec3 vec3 = camera.getPosition().add(add);
        float f = (float) (Mth.lerp(partialTick, this.xo, this.x) - vec3.x());
        float f1 = (float) (Mth.lerp(partialTick, this.yo, this.y) - vec3.y());
        float f2 = (float) (Mth.lerp(partialTick, this.zo, this.z) - vec3.z());
        Quaternionf quaternion = (new Quaternionf()).setAngleAxis(0.0F, ROTATION_VECTOR.x(), ROTATION_VECTOR.y(), ROTATION_VECTOR.z());

        pQuaternion.accept(quaternion);
        quaternion.transform(TRANSFORM_VECTOR);
        Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        float f3 = this.getQuadSize(partialTick);

        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.rotate(quaternion);
            vector3f.mul(f3);
            vector3f.add(f, f1, f2);
        }

        int j = this.getLightColor(partialTick);
        this.makeCornerVertex(pConsumer, avector3f[0], this.getU1(), this.getV1(), j);
        this.makeCornerVertex(pConsumer, avector3f[1], this.getU1(), this.getV0(), j);
        this.makeCornerVertex(pConsumer, avector3f[2], this.getU0(), this.getV0(), j);
        this.makeCornerVertex(pConsumer, avector3f[3], this.getU0(), this.getV1(), j);
    }

    private void makeCornerVertex(VertexConsumer pConsumer, Vector3f pVec3f, float p_233996_, float p_233997_, int p_233998_) {
        pConsumer.addVertex(pVec3f.x(), pVec3f.y() + .08f, pVec3f.z()).setUv(p_233996_, p_233997_).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(p_233998_);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<OreParticleOptions> {
        private final double SPEED_FACTOR = 0.25;
        private final SpriteSet sprite;

        public Provider(SpriteSet p_172151_) {
            this.sprite = p_172151_;
        }

        public Particle createParticle(OreParticleOptions options, ClientLevel p_172163_, double p_172164_, double p_172165_, double p_172166_, double p_172167_, double p_172168_, double p_172169_) {
            OreParticle particle = new OreParticle(p_172163_, p_172164_, p_172165_, p_172166_, 0.0, 0.0, 0.0, this.sprite, options);
            particle.setColor(options.getColor().x, options.getColor().y, options.getColor().z);
            particle.setParticleSpeed(p_172167_ * 0.01, p_172168_ * 0.01, p_172169_ * 0.01);
            particle.setSize(options.getScale(), options.getScale());
            particle.setLifetime(300);
            return particle;
        }
    }
}
