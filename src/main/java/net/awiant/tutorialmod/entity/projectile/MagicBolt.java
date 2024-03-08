package net.awiant.tutorialmod.entity.projectile;

import net.awiant.tutorialmod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
public class MagicBolt extends Projectile {
    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(MagicBolt.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HIT = SynchedEntityData.defineId(MagicBolt.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> COUNT = SynchedEntityData.defineId(MagicBolt.class, EntityDataSerializers.BOOLEAN);
    private Level level;

    public MagicBolt(EntityType<? extends MagicBolt> entityType, Level level) {
        super(entityType, level);
    }

    public MagicBolt(EntityType<? extends MagicBolt> entityType, double x, double y, double z, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, 0);
        this.entityData.define(COUNT, false);
        this.entityData.define(HIT, false);
    }

    public MagicBolt(Level level, LivingEntity owner) {
        this(ModEntities.MAGIC_BOLT.get(), level);
        this.setOwner(owner);
        BlockPos blockpos = owner.blockPosition();
        double d0 = (double) blockpos.getX() + 0.5D;
        double d1 = (double) blockpos.getY() + 1.75D;
        double d2 = (double) blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, this.getYRot(), this.getXRot());
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Entity hitEntity = hitResult.getEntity();
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity livingentity) {
            hitResult.getEntity().hurt(this.damageSources().mobProjectile(this, livingentity), 1.0F);
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if(this.level.isClientSide()) {
            return;
        }

    }

    private SimpleParticleType getParticleType() {
        return ParticleTypes.EXPLOSION_EMITTER;
    }
}
