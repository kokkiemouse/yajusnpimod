package net.kokkiemouse.yaju.entities;

import javax.annotation.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kokkiemouse.yaju.init.YajuMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.packet.EntitySpawnS2CPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
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


    public IkisugiEntity(World world_1, double double_1, double double_2, double double_3, @Nullable LivingEntity livingEntity_1) {
        this(YajuMod.ikisugiTnt, world_1);
        this.setPosition(double_1, double_2, double_3);
        double double_4 = world_1.random.nextDouble() * 6.2831854820251465D;
        this.setVelocity(-Math.sin(double_4) * 0.02D, 0.20000000298023224D, -Math.cos(double_4) * 0.02D);
        this.setFuse(73);
        this.prevX = double_1;
        this.prevY = double_2;
        this.prevZ = double_3;
        this.causingEntity = livingEntity_1;
        this.setNoGravity(true);

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
            this.checkWaterState();
            this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    private void explode() {
        float float_1 = 4.0F;
        world.playSound((PlayerEntity)null, this.getX(),this.getY(),this.getZ(), YajuMod.NAA_TSOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);

        this.world.createExplosion(this, this.getX(), this.method_23323(0.0625D), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
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
