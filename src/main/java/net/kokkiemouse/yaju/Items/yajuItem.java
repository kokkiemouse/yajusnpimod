package net.kokkiemouse.yaju.Items;

import net.kokkiemouse.yaju.init.YajuMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 *デバッグ💛用
 */
public class yajuItem extends Item {

    public yajuItem(Settings group) {
        super(group);
    }

    /**
     *野獣アイテムを右クリックした時呼ばれる奴
     * @param world クリックしたワールド
     * @param playerEntity クリックしたプレーヤー
     * @param hand クリックした時持っていたアイテム
     * @return Actionの結果
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){

        playerEntity.playSound(YajuMod.IKISUGI_SOUND_EVENT, 1.0F, 1.0F);
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
        // 右クリックすると羊毛を壊した時の音がなる
    }
}
