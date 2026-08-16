package com.moreminecarts.moreminecartsmod.item;

import com.moreminecarts.moreminecartsmod.ModEntities;
import com.moreminecarts.moreminecartsmod.entity.FastMinecart;

import net.minecraft.core.BlockPos;
// import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
// import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;

public class FastMinecartItem extends Item {

    public FastMinecartItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (!blockstate.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        } 
            

        ItemStack itemstack = context.getItemInHand();
        if (level instanceof ServerLevel) {
            ServerLevel serverlevel = (ServerLevel)level;

            RailShape railshape = blockstate.getBlock() instanceof BaseRailBlock 
                ? ((BaseRailBlock)blockstate.getBlock()).getRailDirection(blockstate, level, blockpos, (AbstractMinecart)null) 
                : RailShape.NORTH_SOUTH;
            
            double d0 = (double)0.0F;
            if (railshape.isAscending()) {
                d0 = (double)0.5F;
            }

            FastMinecart fast_minecart = new FastMinecart(ModEntities.FAST_MINECART_ENTITY.get(), serverlevel);
            fast_minecart.setPos(blockpos.getX() + 0.5D, blockpos.getY() + 0.0625D + d0, blockpos.getZ() + 0.5D);

            serverlevel.addFreshEntity(fast_minecart);
            serverlevel.gameEvent(GameEvent.ENTITY_PLACE, blockpos, Context.of(context.getPlayer(), serverlevel.getBlockState(blockpos.below())));
        }

        itemstack.shrink(1);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
