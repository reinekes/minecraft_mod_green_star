package com.ineyws.greenstar.events;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.init.ModBlocks;
import com.ineyws.greenstar.init.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = IneyWSGreenStar.MOD_ID)
public class VillagerTradesHandler {
    
    @SubscribeEvent
    public static void onVillagerTrades(VillagerTradesEvent event) {
        Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();
        
        // Добавляем сделку кузнецу-оружейнику
        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            // Добавляем сделку на уровне мастера (5)
            trades.get(5).add((trader, random) -> {
                // Создаем зачарованную кирку Death Moon
                ItemStack enchantedPickaxe = new ItemStack(ModItems.DEATH_MOON_PICKAXE.get());
                enchantedPickaxe.enchant(Enchantments.BLOCK_EFFICIENCY, 5);  // Эффективность 5
                enchantedPickaxe.enchant(Enchantments.UNBREAKING, 3);        // Прочность 3
                enchantedPickaxe.enchant(Enchantments.BLOCK_FORTUNE, 3);     // Удача 3
                
                // Цена: 16 Стекла Времени + 64 изумруда
                ItemStack glassOfTime = new ItemStack(ModBlocks.GLASS_OF_TIME.get(), 16);
                ItemStack emeralds = new ItemStack(Items.EMERALD, 64);
                
                return new MerchantOffer(glassOfTime, emeralds, enchantedPickaxe, 1, 30, 0.2F);
            });
        }
        
        // Добавляем сделку инструментальщику
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            // Добавляем сделку на уровне мастера (5)
            trades.get(5).add((trader, random) -> {
                // Создаем зачарованную кирку Death Moon
                ItemStack enchantedPickaxe = new ItemStack(ModItems.DEATH_MOON_PICKAXE.get());
                enchantedPickaxe.enchant(Enchantments.BLOCK_EFFICIENCY, 5);  // Эффективность 5
                enchantedPickaxe.enchant(Enchantments.UNBREAKING, 3);        // Прочность 3
                enchantedPickaxe.enchant(Enchantments.BLOCK_FORTUNE, 3);     // Удача 3
                
                // Цена: 16 Стекла Времени + 64 изумруда
                ItemStack glassOfTime = new ItemStack(ModBlocks.GLASS_OF_TIME.get(), 16);
                ItemStack emeralds = new ItemStack(Items.EMERALD, 64);
                
                return new MerchantOffer(glassOfTime, emeralds, enchantedPickaxe, 1, 30, 0.2F);
            });
        }
    }
}
