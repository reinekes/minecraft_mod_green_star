package com.ineyws.greenstar.init;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.blocks.GreenOreBlock;
import com.ineyws.greenstar.blocks.GreenTorchBlock;
import com.ineyws.greenstar.blocks.GreenWallTorchBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    
    public static final DeferredRegister<Block> BLOCKS = 
            DeferredRegister.create(ForgeRegistries.BLOCKS, IneyWSGreenStar.MOD_ID);
    
    // Блок зеленой руды
    public static final RegistryObject<Block> GREEN_ORE = BLOCKS.register("green_ore",
            () -> new GreenOreBlock(Block.Properties.of(Material.STONE)
                    .strength(3.0f, 3.0f)  // hardness и resistance как у камня
                    .harvestLevel(1)        // требуется каменная кирка
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 14)  // уровень света как у факела
                    .sound(SoundType.STONE)));
    
    // Обычный зеленый факел (просто другая текстура)
    public static final RegistryObject<Block> GREEN_TORCH = BLOCKS.register("green_torch",
            () -> new GreenTorchBlock(Block.Properties.of(Material.DECORATION)
                    .noCollission()
                    .instabreak()
                    .lightLevel(state -> 14)
                    .sound(SoundType.WOOD), ParticleTypes.FLAME));
    
    // Настенный зеленый факел
    public static final RegistryObject<Block> GREEN_WALL_TORCH = BLOCKS.register("green_wall_torch",
            () -> new GreenWallTorchBlock(Block.Properties.of(Material.DECORATION)
                    .noCollission()
                    .instabreak()
                    .lightLevel(state -> 14)
                    .sound(SoundType.WOOD)
                    .lootFrom(GREEN_TORCH), ParticleTypes.FLAME));
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
