package net.kokkiemouse.yaju.Items;

import net.kokkiemouse.yaju.init.YajuMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
public class UnkoItem extends Item {

    public UnkoItem(Settings group) {
        super(group);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){

        playerEntity.playSound(YajuMod.UNKO_SOUND_EVENT, 1.0F, 1.0F);
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
        // 右クリックすると羊毛を壊した時の音がなる
    }
}