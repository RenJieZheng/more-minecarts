package com.moreminecarts.moreminecartsmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;

public class FastMinecart extends Minecart{
    public FastMinecart(EntityType<? extends FastMinecart> type, Level level) {
        super(type, level);
    }
}
