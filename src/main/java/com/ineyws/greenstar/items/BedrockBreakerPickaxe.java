package com.ineyws.greenstar.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class BedrockBreakerPickaxe extends PickaxeItem {
    
    public BedrockBreakerPickaxe(IItemTier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
    
    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        if (state.getBlock() == Blocks.BEDROCK) {
            return true;
        }
        return super.canHarvestBlock(stack, state);
    }
    
    @Override
    public boolean isCorrectToolForDrops(BlockState state) {
        if (state.getBlock() == Blocks.BEDROCK) {
            return true;
        }
        return super.isCorrectToolForDrops(state);
    }
    
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.getBlock() == Blocks.BEDROCK) {
            return 30.0F;
        }
        return super.getDestroySpeed(stack, state);
    }
}
