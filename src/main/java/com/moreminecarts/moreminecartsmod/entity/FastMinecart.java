package com.moreminecarts.moreminecartsmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.tags.BlockTags;

public class FastMinecart extends Minecart{
    public FastMinecart(EntityType<? extends FastMinecart> type, Level level) {
        super(type, level);
    }

    @Override
    public float getMaxCartSpeedOnRail() {
        return 1.6F;
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
                float railMaxSpeed = this.isInWater() ? 0.8F : 1.6F;
                return (double)Math.min(railMaxSpeed, this.getCurrentCartSpeedCapOnRail());
            }
        }
    }
}
