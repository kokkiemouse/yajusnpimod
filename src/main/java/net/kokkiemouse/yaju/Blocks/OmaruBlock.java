package net.kokkiemouse.yaju.Blocks;

import net.kokkiemouse.yaju.YajuMod;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OmaruBlock extends Block {

    public OmaruBlock(Settings block$Settings_1) {
        super(block$Settings_1);
    }
    @Override
    public void onSteppedOn(World world, BlockPos blockpos, Entity entity){
        //System.out.println(entity.getName().toString());
        if(entity.isInSneakingPose()){
            System.out.println("Sneeek!!" + blockpos.toString());

            this.excretion(world,blockpos);
        }
    }
    public void excretion(World world, BlockPos blockpos)
    {
        if (!world.isClient) {
            ItemEntity entityitem = new ItemEntity(world, blockpos.getX(),blockpos.getY(),blockpos.getZ(), YajuMod.UNKO_ITEM.getStackForRender());
            float f3 = 0.05F;
            world.spawnEntity(entityitem);

            world.playSound(null, blockpos, YajuMod.UNKO_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        }


    }
}
