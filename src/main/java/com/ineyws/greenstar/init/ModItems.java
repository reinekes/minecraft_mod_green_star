package com.ineyws.greenstar.init;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.items.*;
import net.minecraft.inventory.EquipmentSlotType;
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
    
    // Слиток Death Moon
    public static final RegistryObject<Item> DEATH_MOON_INGOT = ITEMS.register("death_moon_ingot",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    
    // BlockItem для блока зеленой руды
    public static final RegistryObject<BlockItem> GREEN_ORE_ITEM = ITEMS.register("green_ore",
            () -> new BlockItem(ModBlocks.GREEN_ORE.get(), 
                    new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    
    // BlockItem для блока руды Death Moon
    public static final RegistryObject<BlockItem> DEATH_MOON_ORE_ITEM = ITEMS.register("death_moon_ore",
            () -> new BlockItem(ModBlocks.DEATH_MOON_ORE.get(), 
                    new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
    
    // BlockItem для Стекла Времени
    public static final RegistryObject<BlockItem> GLASS_OF_TIME_ITEM = ITEMS.register("glass_of_time",
            () -> new BlockItem(ModBlocks.GLASS_OF_TIME.get(), 
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
    
    public static final RegistryObject<AxeItem> GREEN_AXE = ITEMS.register("green_axe",
            () -> new AxeItem(GreenItemTier.GREEN, 5, -3.0F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    public static final RegistryObject<ShovelItem> GREEN_SHOVEL = ITEMS.register("green_shovel",
            () -> new ShovelItem(GreenItemTier.GREEN, 1.5F, -3.0F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    public static final RegistryObject<HoeItem> GREEN_HOE = ITEMS.register("green_hoe",
            () -> new HoeItem(GreenItemTier.GREEN, -3, 0.0F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    // Супер-броня из зеленых слитков (в 10 раз сильнее алмазной!)
    public static final RegistryObject<ArmorItem> GREEN_HELMET = ITEMS.register("green_helmet",
            () -> new ArmorItem(GreenArmorMaterial.GREEN, EquipmentSlotType.HEAD,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<ArmorItem> GREEN_CHESTPLATE = ITEMS.register("green_chestplate",
            () -> new ArmorItem(GreenArmorMaterial.GREEN, EquipmentSlotType.CHEST,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<ArmorItem> GREEN_LEGGINGS = ITEMS.register("green_leggings",
            () -> new ArmorItem(GreenArmorMaterial.GREEN, EquipmentSlotType.LEGS,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<ArmorItem> GREEN_BOOTS = ITEMS.register("green_boots",
            () -> new ArmorItem(GreenArmorMaterial.GREEN, EquipmentSlotType.FEET,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    // Супер-мощные инструменты Death Moon!
    public static final RegistryObject<SwordItem> DEATH_MOON_SWORD = ITEMS.register("death_moon_sword",
            () -> new SwordItem(DeathMoonItemTier.DEATH_MOON, 250, 6.0F,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<PickaxeItem> DEATH_MOON_PICKAXE = ITEMS.register("death_moon_pickaxe",
            () -> new DeathMoonPickaxeItem(DeathMoonItemTier.DEATH_MOON, 60, -2.8F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    public static final RegistryObject<AxeItem> DEATH_MOON_AXE = ITEMS.register("death_moon_axe",
            () -> new DeathMoonAxeItem(DeathMoonItemTier.DEATH_MOON, 100, -3.0F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    public static final RegistryObject<ShovelItem> DEATH_MOON_SHOVEL = ITEMS.register("death_moon_shovel",
            () -> new DeathMoonShovelItem(DeathMoonItemTier.DEATH_MOON, 50, -3.0F, 550.0F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    public static final RegistryObject<HoeItem> DEATH_MOON_HOE = ITEMS.register("death_moon_hoe",
            () -> new HoeItem(DeathMoonItemTier.DEATH_MOON, 45, 0.0F,
                    new Item.Properties().tab(ItemGroup.TAB_TOOLS)));
    
    // Супер-мощная броня Death Moon (в 2 раза сильнее зеленой)!
    public static final RegistryObject<ArmorItem> DEATH_MOON_HELMET = ITEMS.register("death_moon_helmet",
            () -> new ArmorItem(DeathMoonArmorMaterial.DEATH_MOON, EquipmentSlotType.HEAD,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<ArmorItem> DEATH_MOON_CHESTPLATE = ITEMS.register("death_moon_chestplate",
            () -> new ArmorItem(DeathMoonArmorMaterial.DEATH_MOON, EquipmentSlotType.CHEST,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<ArmorItem> DEATH_MOON_LEGGINGS = ITEMS.register("death_moon_leggings",
            () -> new ArmorItem(DeathMoonArmorMaterial.DEATH_MOON, EquipmentSlotType.LEGS,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static final RegistryObject<ArmorItem> DEATH_MOON_BOOTS = ITEMS.register("death_moon_boots",
            () -> new ArmorItem(DeathMoonArmorMaterial.DEATH_MOON, EquipmentSlotType.FEET,
                    new Item.Properties().tab(ItemGroup.TAB_COMBAT)));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
