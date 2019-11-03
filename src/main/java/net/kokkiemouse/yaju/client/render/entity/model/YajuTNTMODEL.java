package net.kokkiemouse.yaju.client.render.entity.model;

import net.kokkiemouse.yaju.entities.IkisugiEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;

public class YajuTNTMODEL extends Model {
    private ModelPart Ita=null;

    public YajuTNTMODEL(){
        this(0, 0, 1024, 1024);
    };

    public YajuTNTMODEL(int int_1, int int_2, int int_3, int int_4) {
        super(RenderLayer::getEntityTranslucent);

        this.textureWidth = int_3;
        this.textureHeight = int_4;
        this.Ita = new ModelPart(this, int_1, int_2);
        this.Ita.addCuboid(-8.0F, 0.0F, -8.0F, 16, 16, 16);
        this.Ita.setPivot(0.0F, 0.0F, 0.0F);
    }
    public void render(float float_1, float float_2, float float_3) {
        this.Ita.yaw = float_2 * 0.017453292F;
        this.Ita.pitch = float_3 * 0.017453292F;
    }
    @Override
    public void render(MatrixStack matrixStack_1, VertexConsumer vertexConsumer_1, int int_1, int int_2, float float_1, float float_2, float float_3) {
        this.Ita.render(matrixStack_1, vertexConsumer_1, 0.0625F, int_1, int_2, (Sprite)null, float_1, float_2, float_3);
    }

    @Override
    public void accept(ModelPart modelPart) {

    }
}
