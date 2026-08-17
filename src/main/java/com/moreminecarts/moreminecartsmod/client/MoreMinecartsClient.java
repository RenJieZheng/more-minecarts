package com.moreminecarts.moreminecartsmod.client;

import com.moreminecarts.moreminecartsmod.ModEntities;
import com.moreminecarts.moreminecartsmod.MoreMinecarts;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = MoreMinecarts.MODID, value = Dist.CLIENT)
public final class MoreMinecartsClient {
    private MoreMinecartsClient() {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.FAST_MINECART_ENTITY.get(), FastMinecartRenderer::new);
    }
}
