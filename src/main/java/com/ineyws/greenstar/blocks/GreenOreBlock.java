package com.ineyws.greenstar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.Random;

public class GreenOreBlock extends Block {
    
    private static final Random RANDOM = new Random();
    
    public GreenOreBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        // Выдаем 2-6 опыта при добыче без Silk Touch
        return silktouch == 0 ? 2 + RANDOM.nextInt(5) : 0;
    }
}
