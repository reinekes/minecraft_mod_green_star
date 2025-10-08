package com.ineyws.greenstar.init;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.items.GreenItemTier;
import net.minecraft.item.*;
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
    
    // Супер-яркий зеленый факел
    public static final RegistryObject<WallOrFloorItem> GREEN_TORCH_ITEM = ITEMS.register("green_torch",
            () -> new WallOrFloorItem(ModBlocks.GREEN_TORCH.get(), ModBlocks.GREEN_WALL_TORCH.get(),
                    new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
    
    // Супер-инструменты из зеленых слитков (в 10 раз сильнее алмазных!)
    public static final RegistryObject<SwordItem> GREEN_SWORD = ITEMS.register("green_sword",
            () -> new SwordItem(GreenItemTier.GREEN, 3, -2.4F,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<PickaxeItem> GREEN_PICKAXE = ITEMS.register("green_pickaxe",
            () -> new PickaxeItem(GreenItemTier.GREEN, 1, -2.8F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
