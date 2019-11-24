package net.kokkiemouse.yaju.client.render.entity;

import java.util.Random;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kokkiemouse.yaju.Blocks.IkisugiBlock;
import net.kokkiemouse.yaju.entities.IkisugiEntity;
import net.kokkiemouse.yaju.init.YajuMod;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
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
public class YajuTntEntityRenderer extends EntityRenderer<IkisugiEntity> {
    public YajuTntEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher);
        this.field_4673 = 0.5F;
    }

    public void render(IkisugiEntity tntEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.5D, 0.0D);
        if ((float)tntEntity.getFuseTimer() - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)tntEntity.getFuseTimer() - g + 1.0F) / 10.0F;
            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float j = 1.0F + h * 0.3F;
            matrixStack.scale(j, j, j);
        }

        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
        matrixStack.translate(-0.5D, -0.5D, 0.5D);
        TntMinecartEntityRenderer.method_23190(YajuMod.FABRIC_IKISUGI.getDefaultState(), matrixStack, vertexConsumerProvider, i, tntEntity.getFuseTimer() / 5 % 2 == 0);
        matrixStack.pop();
        super.render(tntEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(IkisugiEntity tntEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEX;
    }

}