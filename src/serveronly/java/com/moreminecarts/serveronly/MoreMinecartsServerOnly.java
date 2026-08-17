package com.moreminecarts.serveronly;

import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@Mod(MoreMinecartsServerOnly.MODID)
public final class MoreMinecartsServerOnly {
    public static final String MODID = "moreminecarts";

    private static final String ENCHANTED_MINECART_TAG = "moreminecarts.enchanted";
    
    private static final float FAST_RAIL_SPEED = 1.1F;
    private static final float FAST_RAIL_SPEED_IN_WATER = 0.2F;
    private static final double VANILLA_RAIL_SPEED = 0.4D;
    private static final double VANILLA_RAIL_SPEED_IN_WATER = 0.2D;

    private static final double EXTRA_MOVE_MULTIPLIER = 1.25D;

    public MoreMinecartsServerOnly(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.addListener(this::afterEntityTick);
    }

    private void afterEntityTick(EntityTickEvent.Post event) {
        if (
            !(event.getEntity() instanceof Minecart minecart) 
            || minecart.level().isClientSide
            || !minecart.getTags().contains(ENCHANTED_MINECART_TAG)
            || !minecart.isOnRails()
        ) {
            return;
        }

        float maxSpeed = minecart.isInWater() ? FAST_RAIL_SPEED_IN_WATER : FAST_RAIL_SPEED;
        minecart.setCurrentCartSpeedCapOnRail(maxSpeed);

        Vec3 movement = minecart.getDeltaMovement();
        double horizontalSpeed = movement.horizontalDistance();
        if (horizontalSpeed < 1.0E-4D) {
            return;
        }

        double vanillaSpeed = minecart.isInWater() ? VANILLA_RAIL_SPEED_IN_WATER : VANILLA_RAIL_SPEED;
        double extraSpeed = Math.min(maxSpeed - vanillaSpeed, horizontalSpeed * EXTRA_MOVE_MULTIPLIER);
        if (extraSpeed <= 0.0D) {
            return;
        }

        Vec3 extraMovement = new Vec3(
            movement.x / horizontalSpeed * extraSpeed,
            0.0D,
            movement.z / horizontalSpeed * extraSpeed
        );
        minecart.move(MoverType.SELF, extraMovement);
    }
}
