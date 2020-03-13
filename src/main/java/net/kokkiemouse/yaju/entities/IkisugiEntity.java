package net.kokkiemouse.yaju.entities;

import javax.annotation.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kokkiemouse.yaju.init.YajuMod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class IkisugiEntity extends Entity {
    private static final TrackedData<Integer> FUSE;
    @Nullable
    private LivingEntity causingEntity;
    private int fuseTimer;
    private BlockState block;

    @Environment(EnvType.CLIENT)
    public BlockPos getFallingBlockPos() {
        return (BlockPos)this.dataTracker.get(BLOCK_POS);
    }
    protected static final TrackedData<BlockPos> BLOCK_POS=null;
    public void setFallingBlockPos(BlockPos blockPos_1) {
        this.dataTracker.set(BLOCK_POS, blockPos_1);
    }


    public IkisugiEntity(EntityType<Entity> entityType_1, World world_1) {
        super(entityType_1, world_1);
        this.fuseTimer = 73;
        this.block = YajuMod.FABRIC_IKISUGI.getDefaultState();
        this.inanimate = true;
        this.setNoGravity(true);
    }

    @Environment(EnvType.CLIENT)
    public World getWorldClient() {
        return this.world;
    }
    public BlockState getBlockState() {
        return this.block;
    }


    public IkisugiEntity(World world, double d, double e, double f, @Nullable LivingEntity livingEntity) {
        this(YajuMod.ikisugiTnt, world);
        this.updatePosition(d, e, f);
        double g = world.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(g) * 0.02D, 0.20000000298023224D, -Math.cos(g) * 0.02D);
        this.setFuse(80);
        this.prevX = d;
        this.prevY = e;
        this.prevZ = f;
        this.causingEntity = livingEntity;
    }



    protected void initDataTracker() {
        this.dataTracker.startTracking(FUSE, 80);
    }

    protected boolean canClimb() {
        return false;
    }

    public boolean collides() {
        return !this.removed;
    }

    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98D));
        if (this.onGround) {
            this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
        }

        --this.fuseTimer;
        if (this.fuseTimer <= 0) {
            this.remove();
            if (!this.world.isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();            if (this.world.isClient) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }}

    }

    private void explode() {
        float float_1 = 4.0F;
        world.playSound((PlayerEntity)null, this.getX(),this.getY(),this.getZ(), YajuMod.NAA_TSOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);

        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
    }

    protected void writeCustomDataToTag(CompoundTag compoundTag_1) {
        compoundTag_1.putShort("Fuse", (short)this.getFuseTimer());
    }

    protected void readCustomDataFromTag(CompoundTag compoundTag_1) {
        this.setFuse(compoundTag_1.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getCausingEntity() {
        return this.causingEntity;
    }

    protected float getEyeHeight(EntityPose entityPose_1, EntityDimensions entityDimensions_1) {
        return 0.0F;
    }

    public void setFuse(int int_1) {
        this.dataTracker.set(FUSE, int_1);
        this.fuseTimer = int_1;
    }

    public void onTrackedDataSet(TrackedData<?> trackedData_1) {
        if (FUSE.equals(trackedData_1)) {
            this.fuseTimer = this.getFuse();
        }

    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    public int getFuseTimer() {
        return this.fuseTimer;
    }

    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
    static {
        FUSE = DataTracker.registerData(TntEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }
}
