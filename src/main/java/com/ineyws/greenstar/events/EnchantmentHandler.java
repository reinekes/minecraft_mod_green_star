package com.ineyws.greenstar.events;

import com.ineyws.greenstar.IneyWSGreenStar;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = IneyWSGreenStar.MOD_ID)
public class EnchantmentHandler {
    
    @SubscribeEvent
    public static void onEnchantmentLevelSet(EnchantmentLevelSetEvent event) {
        ItemStack stack = event.getItem();
        
        if (stack.getItem().getRegistryName() != null && 
            stack.getItem().getRegistryName().getNamespace().equals(IneyWSGreenStar.MOD_ID)) {
            event.setLevel(255);
        }
    }
    
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        
        if (left.getItem().getRegistryName() != null && 
            left.getItem().getRegistryName().getNamespace().equals(IneyWSGreenStar.MOD_ID)) {
            
            if (right.getItem() instanceof net.minecraft.item.EnchantedBookItem || right.isEnchanted()) {
                ItemStack output = left.copy();
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(right);
                
                for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                    int currentLevel = EnchantmentHelper.getItemEnchantmentLevel(entry.getKey(), output);
                    int newLevel = Math.min(currentLevel + entry.getValue(), 255);
                    output.enchant(entry.getKey(), newLevel);
                }
                
                event.setOutput(output);
                event.setCost(1);
            }
        }
    }
}
