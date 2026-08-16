package com.moreminecarts.moreminecartsmod;

import com.moreminecarts.moreminecartsmod.entity.FastMinecart;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(
            BuiltInRegistries.ENTITY_TYPE,
            MoreMinecarts.MODID
        );
    public static final DeferredHolder<EntityType<?>, EntityType<FastMinecart>> FAST_MINECART_ENTITY =
        ENTITY_TYPES.register(
            "fast_minecart", 
            key -> EntityType.Builder
                .of(FastMinecart::new, MobCategory.MISC)
                .sized(0.98F, 0.7F)
                // .clientTrackingRange(8)
                // .updateInterval(3)
                .build("fast_minecart")
        );
}
