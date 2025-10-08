package com.ineyws.greenstar.init;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.blocks.GreenOreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
