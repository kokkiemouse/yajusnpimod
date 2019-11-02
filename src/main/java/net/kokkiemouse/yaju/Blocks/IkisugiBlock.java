package net.kokkiemouse.yaju.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IkisugiBlock extends Block{

    public IkisugiBlock(Settings block$Settings_1) {

        super(block$Settings_1);
        System.out.println("Ikisugi Block Call");
    }

    public ActionResult onUse(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        if (!world_1.isClient) {
            System.out.println("YajuBlock Click Client!");
            //playerEntity_1.playSound(SoundEvents.ENTITY_ZOMBIE_AMBIENT,1.0F,1.0F);
            return ActionResult.SUCCESS;
        } else {
            System.out.println("YajuBlock Click Server!");
            playerEntity_1.playSound(SoundEvents.ENTITY_ZOMBIE_AMBIENT,1.0F,1.0F);
            return ActionResult.SUCCESS;
        }

    }

}

