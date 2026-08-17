package com.moreminecarts.moreminecartsmod.entity;

import com.moreminecarts.moreminecartsmod.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FastMinecart extends Minecart{
    private static final float MAX_RAIL_SPEED = 1.1F;
    private static final float MAX_RAIL_SPEED_IN_WATER = 0.5F;

    public FastMinecart(EntityType<? extends FastMinecart> type, Level level) {
        super(type, level);
    }

    @Override
    protected Item getDropItem() {
        return ModItems.FAST_MINECART_ITEM.get();
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(ModItems.FAST_MINECART_ITEM.get());
    }

    @Override
    public float getMaxCartSpeedOnRail() {
        return MAX_RAIL_SPEED;
    }

    @Override
    public double getMaxSpeedWithRail() {
        if (!this.canUseRail()) {
            return this.getMaxSpeed();
        } else {
            BlockPos pos = this.getCurrentRailPosition();
            BlockState state = this.level().getBlockState(pos);
            if (!state.is(BlockTags.RAILS)) {
                return this.getMaxSpeed();
            } else {
                float railMaxSpeed = this.isInWater() ? MAX_RAIL_SPEED_IN_WATER : MAX_RAIL_SPEED;
                return (double)Math.min(railMaxSpeed, this.getCurrentCartSpeedCapOnRail());
            }
        }
    }
}
