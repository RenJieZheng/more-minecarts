package com.moreminecarts.moreminecartsmod;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(MoreMinecarts.MODID)
public class MoreMinecarts {
    public static final String MODID = "moreminecarts";

    public MoreMinecarts(IEventBus modEventBus) {
        modEventBus.addListener(this::addCreative);

        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.FAST_MINECART_ITEM.get());
        }
    }
}
