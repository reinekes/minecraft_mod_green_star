package com.ineyws.greenstar.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class GreenWallTorchBlock extends WallTorchBlock {
    
    public GreenWallTorchBlock(Properties properties, IParticleData particleData) {
        super(properties, particleData);
    }
    
    @Override
    public void animateTick(BlockState state, net.minecraft.world.World world, BlockPos pos, Random random) {
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        Direction direction1 = direction.getOpposite();
        world.addParticle(this.flameParticle, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
    }
}
