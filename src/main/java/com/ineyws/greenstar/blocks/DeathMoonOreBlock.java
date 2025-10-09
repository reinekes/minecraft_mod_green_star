package com.ineyws.greenstar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DeathMoonOreBlock extends Block {
    
    public DeathMoonOreBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        // Можно добавить частицы для эффекта свечения
        super.animateTick(state, world, pos, random);
    }
}
