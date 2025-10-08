package com.ineyws.greenstar.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class GreenWallTorchBlock extends WallTorchBlock {
    
    private static final int EFFECT_RADIUS = 8;
    private static final int EFFECT_DURATION = 220;
    
    public GreenWallTorchBlock(Properties properties, IParticleData particleData) {
        super(properties, particleData);
    }
    
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction direction = state.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;
        Direction opposite = direction.getOpposite();
        
        world.addParticle(ParticleTypes.FLAME, 
            d0 + d4 * (double)opposite.getStepX(), 
            d1 + d3, 
            d2 + d4 * (double)opposite.getStepZ(), 
            0.0D, 0.0D, 0.0D);
        
        // Только зеленые частицы
        for(int i = 0; i < 4; i++) {
            world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                d0 + (random.nextDouble() - 0.5D) * 0.5D,
                d1 + random.nextDouble() * 0.5D,
                d2 + (random.nextDouble() - 0.5D) * 0.5D,
                0.0D, 0.05D, 0.0D);
        }
    }
    
    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);
        if (!world.isClientSide) {
            world.getBlockTicks().scheduleTick(pos, this, 20);
        }
    }
    
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.tick(state, world, pos, random);
        
        // Те же эффекты что и у обычного факела
        applyEffects(world, pos);
        world.getBlockTicks().scheduleTick(pos, this, 20);
    }
    
    private void applyEffects(World world, BlockPos pos) {
        AxisAlignedBB area = new AxisAlignedBB(pos).inflate(EFFECT_RADIUS);
        
        // Эффекты игрокам (МАКСИМУМ!)
        List<PlayerEntity> players = world.getEntitiesOfClass(PlayerEntity.class, area);
        for(PlayerEntity player : players) {
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, EFFECT_DURATION, 255, false, false));
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, EFFECT_DURATION, 255, false, false));
            player.addEffect(new EffectInstance(Effects.REGENERATION, EFFECT_DURATION, 255, false, false));
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, EFFECT_DURATION, 255, false, false));
            player.addEffect(new EffectInstance(Effects.DIG_SPEED, EFFECT_DURATION, 255, false, false));
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, EFFECT_DURATION, 255, false, false));
        }
        
        // Урон мобам
        List<MonsterEntity> mobs = world.getEntitiesOfClass(MonsterEntity.class, area);
        for(MonsterEntity mob : mobs) {
            mob.hurt(net.minecraft.util.DamageSource.MAGIC, 2.0F);
            mob.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 3, false, false));
            mob.addEffect(new EffectInstance(Effects.WEAKNESS, 60, 1, false, false));
        }
    }
}
