package net.kokkiemouse.yaju.Blocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kokkiemouse.yaju.init.YajuMod;
import net.kokkiemouse.yaju.entities.IkisugiEntity;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import scheduler.CancellationToken;
import scheduler.Scheduleable;
import scheduler.Scheduler;

import javax.annotation.Nullable;

/**
 *野獣先輩Blockの実装
 *本Modの目淫機能(メイン機能)
 * @author kokkiemouse
 * @version  8.1.0
 */
public class IkisugiBlock extends FacingBlock implements Scheduleable {
    /**
     *なんの変数なのか自分でも分からないw
     */
    public static final int TICKUPDATE_BOMB=810;
    /**
     *このBlockにレッドストーン信号が来てるかどうかのプロパティ
     */
    public static final BooleanProperty POWERED=BooleanProperty.of("powered");
    /**
     *動作をキャンセルするためのToken
     */
    CancellationToken cancellationToken;
    /**
     *動作をキャンセルするためのToken
     */
    CancellationToken cancellationToken2;
    /**
     *イキスギTNYのオブジェクト
     */
    IkisugiEntity tntEntity_1;
    /**
     *動作Modeを記録する変数
     */
    public static final IntProperty MODE=IntProperty.of("mode",0,1);

    /**
     *野獣Blockのコンストラクタ
     * @param block$Settings_1 設定じゃないの?
     */
    public IkisugiBlock(Settings block$Settings_1) {

        super(block$Settings_1);
        /**
         *デフォルトの方角を指定
         */
        this.setDefaultState((BlockState)this.getDefaultState().with(FACING, Direction.NORTH));
        /**
         *デフォルトのPowerを設定
         */
        this.setDefaultState((BlockState)this.getDefaultState().with(POWERED, false));
        /**
         *デフォルトのmodeを設定
         */
        this.setDefaultState((BlockState)this.getDefaultState().with(MODE, 1));
        /**
         *野獣Blockが正常に認識されているかのCheck用
         */
        System.out.println("Beast Block Call");
    }

    /**
     *Clientだけw
     *設置された時の向きの設定?
     * @param blockState_1 ブロック状態
     * @param blockState_2 ブロック状態
     * @param direction_1 ブロックの方角
     * @return true or false
     */
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        return blockState_2.getBlock() == this ? true : super.isSideInvisible(blockState_1, blockState_2, direction_1);
    }

    /**
     *プロパティを追加してあげている。
     * @param stateManager$Builder_1 ブロック状態
     */
    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> stateManager$Builder_1) {
        stateManager$Builder_1.add(POWERED,MODE,FACING);
    }

    /**
     *ブロックが更新されたら
     *レッドストーン入力を検知するため。
     * @param blockState_1 ブロック状態
     * @param world_1 検知されたワールド
     * @param blockPos_1 検知されたブロックの座標
     * @param block_1 検知されたブロック
     * @param blockPos_2 検知されたブロック座標
     * @param boolean_1 なんだっけ?
     */
    public void neighborUpdate(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_1) {
        boolean boolean_2 = world_1.isReceivingRedstonePower(blockPos_1);
        if (boolean_2 != (Boolean)blockState_1.get(POWERED)) {
            if (boolean_2) {
                soundply(world_1,blockPos_1,(int)blockState_1.get(MODE));
            }

            world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(POWERED, boolean_2), 3);
        }

    }

    /**
     *サウンド再生用
     * @param world_1 サウンドを鳴らすワールド
     * @param blockPos_1 サウンドを鳴らす座標
     * @param modeaniki サウンドを鳴らすブロックのMode
     */
    private void soundply(World world_1,BlockPos blockPos_1,int modeaniki){
        switch(modeaniki){
            case 0:
                world_1 .playSound(null,blockPos_1,YajuMod.IKISUGI_SOUND_EVENT, SoundCategory.BLOCKS,1f,1f);
                return;
            case 1:
                world_1.playSound(null,blockPos_1,YajuMod.NAA_TSOUND_EVENT,SoundCategory.BLOCKS,1f,1f);
        }
        //world_1 .playSound(null,blockPos_1,YajuMod.IKISUGINAA_SOUND_EVENT, SoundCategory.BLOCKS,1f,1f);

    }

    /**
     *ブロックを右クリックすると呼ばれる。
     * @param blockState_1 ブロックの状態
     * @param world_1 ブロックを右クリックしたワールド
     * @param blockPos_1 ブロック座標
     * @param playerEntity_1 右クリックしたプレーヤー
     * @param hand_1 右クリックした手
     * @param blockHitResult_1 しらん
     * @return 成功したか否か
     */
    public ActionResult onUse(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {

        ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
        Item item_1 = itemStack_1.getItem();
        if(item_1==YajuMod.FABRIC_IKISUGI_ITEM) return ActionResult.PASS;
        if (item_1 != Items.FLINT_AND_STEEL && item_1 != Items.FIRE_CHARGE) {

            if (!playerEntity_1.abilities.allowModifyWorld) {
            return ActionResult.PASS;}else {
                if(world_1.isClient){
                    //System.out.println("YajuBlock Click Client");
                }else {
                    //System.out.println("YajuBlock Click Server");
                }
                //if(cancellationToken != null) cancellationToken.cancel(world_1);
                if ((int) blockState_1.get(MODE) == 0) {
                    world_1.setBlockState(blockPos_1, (BlockState) blockState_1.cycle(MODE), 3);
                    world_1.playSound(null, blockPos_1, YajuMod.NAA_TSOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
                    return ActionResult.SUCCESS;
                } else {
                    world_1.setBlockState(blockPos_1, (BlockState) blockState_1.cycle(MODE), 3);

                    world_1.playSound(null, blockPos_1, YajuMod.IKISUGI_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);

                }
                //playerEntity_1.playSound(YajuMod.IKISUGI_SOUND_EVENT,1.0F,1.0F);
                return ActionResult.SUCCESS;
            }

            }else{
            //if(playerEntity_1==null) return ActionResult.PASS;
                world_1.playSound(playerEntity_1,blockPos_1,YajuMod.IKISUGINAAA_SOUND_EVENT,SoundCategory.BLOCKS,1f,1f);
            /*CompoundTag scheduleData = new CompoundTag();
            scheduleData.putUuid("player", playerEntity_1.getUuid());
            cancellationToken = Scheduler.Builder(this, world_1)
                        .scheduleId(TICKUPDATE_BOMB)
                        .pos(blockPos_1)
                        .additionalData(scheduleData)
                        .schedule(75);

*/
            CompoundTag scheduleData = new CompoundTag();
            primeTnt(world_1, blockPos_1, playerEntity_1);
            scheduleData.putUuidNew("player", playerEntity_1.getUuid());
            //world_1.setBlockState(blockPos_1, Blocks.AIR.getDefaultState(), 11);

            cancellationToken=Scheduler.Builder(this, world_1)
                    .scheduleId(114)
                    .pos(blockPos_1)
                    .additionalData(scheduleData)
                    .repeat(5,13);
            cancellationToken2=Scheduler.Builder(this, world_1)
                    .scheduleId(TICKUPDATE_BOMB)
                    .pos(blockPos_1)
                    .additionalData(scheduleData)
                    .schedule(73);


        }
        return ActionResult.SUCCESS;

    }

    /**
     *かいてーん!
     * @param blockState_1 ブロックの状態
     * @param blockRotation_1 知りませんw
     * @return ブロックの状態
     */
    @Override
    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return (BlockState)blockState_1.with(FACING, blockRotation_1.rotate((Direction)blockState_1.get(FACING)));
    }

    /**
     *ミラー??
     * @param blockState_1 ブロックの状態
     * @param blockMirror_1 尻ませーんw
     * @return ブロックの状態
     */
    @Override
    public BlockState mirror(BlockState blockState_1, BlockMirror blockMirror_1) {
        return blockState_1.rotate(blockMirror_1.getRotation((Direction)blockState_1.get(FACING)));
    }

    /**
     *ブロックが壊れた時に呼ばれる
     * @param beforeState 以前のブロックの状態
     * @param world ワールド
     * @param pos 座標?
     * @param afterState その後のブロックの状態?
     * @param bool しらん
     */
    @Override
    public void onBlockRemoved(BlockState beforeState, World world, BlockPos pos, BlockState afterState, boolean bool) {
        // The scheduled action won't occur!
        if(cancellationToken != null) cancellationToken.cancel(world);
        if(cancellationToken2 != null) cancellationToken2.cancel(world);

    }

    /**
     *しらん
     * @param itemPlacementContext_1 なんだこれはたまげたなぁ
     * @return ブロックの状態
     */
    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return (BlockState)this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerFacing());
    }

    /**
     *スケジュールの終わり
     * @param world ワールド
     * @param blockPos ブロックの座標
     * @param scheduleId スケジュールID
     * @param additionalData 追加Data
     */
    @Override
    public void onScheduleEnd(World world, BlockPos blockPos, int scheduleId, CompoundTag additionalData) {
        switch(scheduleId) {
            case TICKUPDATE_BOMB:
                PlayerEntity player = world.getPlayerByUuid(additionalData.getUuidNew("player"));
                world.playSound(player, blockPos, YajuMod.NAA_TSOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
                System.out.println("X amount of ticks have passed!");
                //world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 11);
                world.playSound((PlayerEntity)null, blockPos.getX(),blockPos.getY(),blockPos.getZ(), YajuMod.NAA_TSOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
                //world.playSound(player, blockPos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
                //tntEntity_1.teleport(blockPos.getX(),blockPos.getY(),blockPos.getZ());
                //world.createExplosion(null,blockPos.getX() + 0.5D,blockPos.getY() + 0.5D,blockPos.getZ() + 0.5D,4.0F, Explosion.DestructionType.BREAK);

                break;
            case 114:
                BlockState blockState=world.getBlockState(blockPos);
                if(blockState.getBlock() != this) break;
                world.setBlockState(blockPos, (BlockState) blockState.cycle(MODE), 3);
                break;
        }
    }

    /**
     *着火すると呼ばれます。(Entity無指定の場合)
     * @param world_1　ワールド
     * @param blockPos_1　ブロック座標
     */
    public void primeTnt(World world_1, BlockPos blockPos_1) {
        primeTnt(world_1, blockPos_1, (LivingEntity)null);
    }

    /**
     *着火するよ呼ばれます。
     * @param world_1 ワールド
     * @param blockPos_1　ブロック座標
     * @param livingEntity_1 着火したEntity(いなければNull)
     */
    private void primeTnt(World world_1, BlockPos blockPos_1, @Nullable LivingEntity livingEntity_1) {
        if (!world_1.isClient) {
            tntEntity_1 = new IkisugiEntity(world_1, (double)((float)blockPos_1.getX() + 0.5F), (double)blockPos_1.getY(), (double)((float)blockPos_1.getZ() + 0.5F), livingEntity_1);
            world_1.spawnEntity(tntEntity_1);
            world_1.playSound((PlayerEntity)null, tntEntity_1.getX(), tntEntity_1.getY(), tntEntity_1.getZ(), YajuMod.IKISUGINAAA_SOUND_EVENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    /**
     *誘爆したとき
     * @param world_1 ワールド
     * @param blockPos_1 ブロック座標
     * @param explosion_1 爆発?
     */
    @Override
    public void onDestroyedByExplosion(World world_1, BlockPos blockPos_1, Explosion explosion_1) {
        if (!world_1.isClient) {
            IkisugiEntity tntEntity_1 = new IkisugiEntity(world_1, (double)((float)blockPos_1.getX() + 0.5F), (double)blockPos_1.getY(), (double)((float)blockPos_1.getZ() + 0.5F), explosion_1.getCausingEntity());
            tntEntity_1.setFuse((short)(world_1.random.nextInt(tntEntity_1.getFuseTimer() / 4) + tntEntity_1.getFuseTimer() / 8));
            world_1.spawnEntity(tntEntity_1);
        }
    }
}

