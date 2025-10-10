package com.ineyws.greenstar.init;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.blocks.DeathMoonOreBlock;
import com.ineyws.greenstar.blocks.GlassOfTimeBlock;
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
    
    // Блок руды Death Moon - добывается только зеленой киркой
    public static final RegistryObject<Block> DEATH_MOON_ORE = BLOCKS.register("death_moon_ore",
            () -> new DeathMoonOreBlock(Block.Properties.of(Material.STONE)
                    .strength(50.0f, 1200.0f)  // прочность как у обсидиана
                    .harvestLevel(5)            // требуется зеленая кирка (уровень 5, выше незерита)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .lightLevel(state -> 5)     // светится на уровне 5
                    .sound(SoundType.STONE)));
    
    // Стекло Времени - блок энд данжа
    public static final RegistryObject<Block> GLASS_OF_TIME = BLOCKS.register("glass_of_time",
            () -> new GlassOfTimeBlock(Block.Properties.of(Material.GLASS)
                    .strength(200.0f, 200.0f)   // высокая прочность
                    .harvestLevel(4)             // требуется зеленая кирка (уровень 4+)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
                    .noOcclusion()));            // прозрачный как стекло
    
    // Блок зеленого металла (из 9 слитков)
    public static final RegistryObject<Block> GREEN_BLOCK = BLOCKS.register("green_block",
            () -> new Block(Block.Properties.of(Material.METAL)
                    .strength(5.0f, 6.0f)        // прочность как у железного блока
                    .harvestLevel(1)             // требуется каменная кирка
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));
    
    // Блок Death Moon (из 9 слитков)
    public static final RegistryObject<Block> DEATH_MOON_BLOCK = BLOCKS.register("death_moon_block",
            () -> new Block(Block.Properties.of(Material.METAL)
                    .strength(50.0f, 1200.0f)    // очень прочный
                    .harvestLevel(4)             // требуется зеленая кирка
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
