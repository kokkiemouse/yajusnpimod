package net.kokkiemouse.yaju.client.render.entity;

import java.util.Random;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kokkiemouse.yaju.Blocks.IkisugiBlock;
import net.kokkiemouse.yaju.entities.IkisugiEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LayeredVertexConsumerStorage;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class YajuTntEntityRenderer extends EntityRenderer {
    public YajuTntEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
        System.out.println("TNtRender Load 1");
        this.field_4673 = 0.5F;
    }
/*
    public void render(IkisugiEntity entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, LayeredVertexConsumerStorage layeredVertexConsumerStorage11) {
        matrices.push();
        matrices.translate(0.0D, 0.5D, 0.0D);
        if ((float) entity.getFuseTimer() - tickDelta + 1.0F < 10.0F) {
            float float12 = 1.0F - ((float) entity.getFuseTimer() - tickDelta + 1.0F) / 10.0F;
            float12 = MathHelper.clamp(float12, 0.0F, 1.0F);
            float12 *= float12;
            float12 *= float12;
            float float13 = 1.0F + float12 * 0.3F;
            matrices.scale(float13, float13, float13);
        }

        int integer12 = entity.getLightmapCoordinates();
        matrices.multiply(Vector3f.POSITIVE_Y.getRotationQuaternion(-90.0F));
        matrices.translate(-0.5D, -0.5D, 0.5D);
        TntMinecartEntityRenderer.method_23190(Blocks.TNT.getDefaultState(), matrices, layeredVertexConsumerStorage11, integer12, entity.getFuseTimer() / 5 % 2 == 0);
        matrices.pop();
        super.render(entity, x, y, z, yaw, tickDelta, matrices, layeredVertexConsumerStorage11);
    }
*/

public void render(IkisugiEntity entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, LayeredVertexConsumerStorage layeredVertexConsumerStorage11) {
    System.out.println("TNtRender Load 2");

    BlockState blockState12 = entity.getBlockState();
    if (blockState12.getRenderType() == BlockRenderType.MODEL) {
        World world13 = entity.getWorldClient();
        if (blockState12 != world13.getBlockState(new BlockPos(entity)) && blockState12.getRenderType() != BlockRenderType.INVISIBLE) {
            matrices.push();
            BlockPos blockPos14 = new BlockPos(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
            matrices.translate(-0.5D, 0.0D, -0.5D);
            BlockRenderManager blockRenderManager15 = MinecraftClient.getInstance().getBlockRenderManager();
            blockRenderManager15.getModelRenderer().tesselate(world13, blockRenderManager15.getModel(blockState12), blockState12, blockPos14, matrices, layeredVertexConsumerStorage11.getBuffer(RenderLayers.getBlockLayer(blockState12)), false, new Random(), blockState12.getRenderingSeed(entity.getFallingBlockPos()), OverlayTexture.DEFAULT_UV);
            matrices.pop();
            super.render(entity, x, y, z, yaw, tickDelta, matrices, layeredVertexConsumerStorage11);
        }
    }
}
    public Identifier getTexture(Entity class_1541_1) {
        System.out.println("TNtRender Load getTexture");

        return SpriteAtlasTexture.BLOCK_ATLAS_TEX;
    }

}