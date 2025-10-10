package com.ineyws.greenstar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GlassOfTimeBlock extends Block {
    
    public GlassOfTimeBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        // Можно добавить частицы для эффекта времени
        super.animateTick(state, world, pos, random);
    }
}
