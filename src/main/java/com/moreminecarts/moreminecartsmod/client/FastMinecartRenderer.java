package com.moreminecarts.moreminecartsmod.client;

import com.moreminecarts.moreminecartsmod.MoreMinecarts;
import com.moreminecarts.moreminecartsmod.entity.FastMinecart;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.resources.ResourceLocation;

public class FastMinecartRenderer extends MinecartRenderer<FastMinecart> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MoreMinecarts.MODID, "textures/entity/fast_minecart.png");

    public FastMinecartRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.MINECART);
    }

    @Override
    public ResourceLocation getTextureLocation(FastMinecart entity) {
        return TEXTURE;
    }
}
