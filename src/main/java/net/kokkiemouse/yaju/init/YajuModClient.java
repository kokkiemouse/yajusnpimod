package net.kokkiemouse.yaju.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.kokkiemouse.yaju.client.render.entity.YajuTntEntityRenderer;

/**
 * Modの初期化(Client Only)
 * @author kokkiemouse
 * @version 8.1.0
 */
public class YajuModClient implements ClientModInitializer {
    /**
     * Mod(client)の初期化(レンダラーの初期化)
     */
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
