package com.ineyws.greenstar.events;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
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
            event.setNewSpeed(30.0F);
        }
    }
    
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        
        PlayerEntity player = event.player;
        ItemStack heldItem = player.getMainHandItem();
        
        if (heldItem.getItem() == ModItems.BEDROCK_BREAKER.get() && player.isUsingItem()) {
            World world = player.level;
            BlockPos lookPos = new BlockPos(player.pick(5.0D, 0.0F, false).getLocation());
            BlockState state = world.getBlockState(lookPos);
            
            if (state.getBlock() == Blocks.BEDROCK && !world.isClientSide) {
                if (player.getRandom().nextFloat() < 0.05F) {
                    world.destroyBlock(lookPos, false);
                    heldItem.hurtAndBreak(100, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                }
            }
        }
    }
}
