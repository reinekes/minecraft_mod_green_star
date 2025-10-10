package com.ineyws.greenstar.events;

import com.ineyws.greenstar.init.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BedrockBreakHandler {
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        
        if (event.getState().getBlock() == Blocks.BEDROCK && 
            heldItem.getItem() == ModItems.BEDROCK_BREAKER.get()) {
            LOGGER.info("BreakSpeed event: Setting speed for bedrock");
            // Делаем бедрок ломаемым
            event.setNewSpeed(0.5F);
        }
    }
    
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock() == Blocks.BEDROCK) {
            PlayerEntity player = event.getPlayer();
            ItemStack heldItem = player.getMainHandItem();
            
            LOGGER.info("BlockBreakEvent: Bedrock! Item: " + heldItem.getItem().getRegistryName());
            
            if (heldItem.getItem() == ModItems.BEDROCK_BREAKER.get()) {
                LOGGER.info("Bedrock Breaker confirmed! Allowing break.");
                // НЕ отменяем событие - разрешаем ломание
                heldItem.hurtAndBreak(100, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
            } else {
                LOGGER.info("Not Bedrock Breaker, canceling event.");
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        PlayerEntity player = event.getPlayer();
        ItemStack heldItem = player.getMainHandItem();
        
        // Альтернативный способ - ПКМ по бедроку с Shift
        if (world.getBlockState(pos).getBlock() == Blocks.BEDROCK && 
            heldItem.getItem() == ModItems.BEDROCK_BREAKER.get() &&
            player.isShiftKeyDown()) {
            
            LOGGER.info("Right click on bedrock with Shift + Bedrock Breaker");
            
            if (!world.isClientSide) {
                LOGGER.info("Removing bedrock at " + pos);
                world.destroyBlock(pos, false);
                heldItem.hurtAndBreak(100, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                event.setCanceled(true);
                event.setCancellationResult(net.minecraft.util.ActionResultType.SUCCESS);
            }
        }
    }
}
