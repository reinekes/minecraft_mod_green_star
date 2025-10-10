package com.ineyws.greenstar.events;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IneyWSGreenStar.MOD_ID)
public class BedrockBreakHandler {
    
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        
        if (event.getState().getBlock() == Blocks.BEDROCK && 
            heldItem.getItem() == ModItems.BEDROCK_BREAKER.get()) {
            event.setNewSpeed(1.0F);
        }
    }
    
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        
        if (event.getState().getBlock() == Blocks.BEDROCK && 
            heldItem.getItem() == ModItems.BEDROCK_BREAKER.get()) {
            heldItem.hurtAndBreak(100, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
        }
    }
}
