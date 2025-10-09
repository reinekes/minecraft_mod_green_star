package com.ineyws.greenstar.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class GreenTorchBlock extends TorchBlock {
    
    public GreenTorchBlock(Properties properties, IParticleData particleData) {
        super(properties, particleData);
    }
    
    @Override
    public void animateTick(BlockState state, net.minecraft.world.World world, BlockPos pos, Random random) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        world.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
