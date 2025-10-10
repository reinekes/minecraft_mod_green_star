package com.ineyws.greenstar.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockBreakerPickaxe extends PickaxeItem {
    
    public BedrockBreakerPickaxe(IItemTier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
    
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.getBlock() == Blocks.BEDROCK) {
            return 30.0F;
        }
        return super.getDestroySpeed(stack, state);
    }
    
    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        if (state.getBlock() == Blocks.BEDROCK) {
            return true;
        }
        return super.canHarvestBlock(stack, state);
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, PlayerEntity player) {
        World world = player.level;
        BlockState state = world.getBlockState(pos);
        
        if (state.getBlock() == Blocks.BEDROCK && !world.isClientSide) {
            world.destroyBlock(pos, false);
            itemstack.hurtAndBreak(100, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            return true;
        }
        
        return super.onBlockStartBreak(itemstack, pos, player);
    }
}
