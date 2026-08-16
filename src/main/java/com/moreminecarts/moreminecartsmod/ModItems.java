package com.moreminecarts.moreminecartsmod;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.moreminecarts.moreminecartsmod.item.FastMinecartItem;

import net.minecraft.world.item.Item;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreMinecarts.MODID);
    
    public static final DeferredItem<Item> FAST_MINECART_ITEM = ITEMS.registerItem(
        "fast_minecart",
        FastMinecartItem::new,
        new Item.Properties().stacksTo(1)
    );
}
