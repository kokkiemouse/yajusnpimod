package net.kokkiemouse.yaju;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.kokkiemouse.yaju.client.render.entity.YajuTntEntityRenderer;
import net.kokkiemouse.yaju.entities.IkisugiEntity;
import net.minecraft.util.Identifier;

public class YajuModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.INSTANCE.register(
                YajuMod.ikisugiTnt,
                ((manager,context)-> new YajuTntEntityRenderer(manager) /*{
                    @Override
                    public Identifier getTexture(IkisugiEntity var1) {
                        return new Identifier("yajumod:textures/block/aa.png");
                    }
                }*/)
        );
    }
}
