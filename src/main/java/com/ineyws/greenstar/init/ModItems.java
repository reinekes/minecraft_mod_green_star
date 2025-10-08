package com.ineyws.greenstar.init;

import com.ineyws.greenstar.IneyWSGreenStar;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, IneyWSGreenStar.MOD_ID);
    
    // Зеленый слиток
    public static final RegistryObject<Item> GREEN_INGOT = ITEMS.register("green_ingot",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    
    // BlockItem для блока зеленой руды
    public static final RegistryObject<BlockItem> GREEN_ORE_ITEM = ITEMS.register("green_ore",
            () -> new BlockItem(ModBlocks.GREEN_ORE.get(), 
                    new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
