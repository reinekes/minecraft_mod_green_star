package com.ineyws.greenstar.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Random;

public class GreenTorchBlock extends TorchBlock {
    
    private static final int EFFECT_RADIUS = 8; // радиус действия эффектов
    private static final int EFFECT_DURATION = 220; // длительность эффектов (11 секунд)
    
    public GreenTorchBlock(Properties properties, IParticleData particleData) {
        super(properties, particleData);
    }
    
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        // Зеленые частицы (как у портала Энда)
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        
        // Основное зеленое пламя
        world.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        
        // Зеленые искры вокруг
        for(int i = 0; i < 3; i++) {
            double offsetX = (random.nextDouble() - 0.5D) * 0.5D;
            double offsetY = random.nextDouble() * 0.5D;
            double offsetZ = (random.nextDouble() - 0.5D) * 0.5D;
            world.addParticle(ParticleTypes.HAPPY_VILLAGER, 
                d0 + offsetX, d1 + offsetY, d2 + offsetZ, 
                0.0D, 0.05D, 0.0D);
        }
        
        // Звездочки
        if(random.nextInt(3) == 0) {
            world.addParticle(ParticleTypes.END_ROD, 
                d0 + (random.nextDouble() - 0.5D) * 0.3D, 
                d1 + random.nextDouble() * 0.3D, 
                d2 + (random.nextDouble() - 0.5D) * 0.3D,
                0.0D, 0.02D, 0.0D);
        }
    }
    
    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, world, pos, oldState, isMoving);
        if (!world.isClientSide) {
            // Запускаем эффекты на сервере
            world.getBlockTicks().scheduleTick(pos, this, 20); // каждую секунду
        }
    }
    
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.tick(state, world, pos, random);
        
        // Применяем эффекты к игрокам
        applyEffectsToPlayers(world, pos);
        
        // Урон и замедление мобам
        damageAndSlowMobs(world, pos);
        
        // Отпугивание мобов (предотвращение спавна)
        preventMobSpawning(world, pos);
        
        // Плавим снег
        meltSnow(world, pos);
        
        // Планируем следующий тик
        world.getBlockTicks().scheduleTick(pos, this, 20);
    }
    
    private void applyEffectsToPlayers(World world, BlockPos pos) {
        AxisAlignedBB area = new AxisAlignedBB(pos).inflate(EFFECT_RADIUS);
        List<PlayerEntity> players = world.getEntitiesOfClass(PlayerEntity.class, area);
        
        for(PlayerEntity player : players) {
            // Скорость II
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, EFFECT_DURATION, 1, false, false));
            // Сила II
            player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, EFFECT_DURATION, 1, false, false));
            // Регенерация I
            player.addEffect(new EffectInstance(Effects.REGENERATION, EFFECT_DURATION, 0, false, false));
            // Сопротивление I
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, EFFECT_DURATION, 0, false, false));
            // Ночное зрение
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION, EFFECT_DURATION, 0, false, false));
            // Огнестойкость
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, EFFECT_DURATION, 0, false, false));
        }
    }
    
    private void damageAndSlowMobs(World world, BlockPos pos) {
        AxisAlignedBB area = new AxisAlignedBB(pos).inflate(EFFECT_RADIUS);
        List<MonsterEntity> mobs = world.getEntitiesOfClass(MonsterEntity.class, area);
        
        for(MonsterEntity mob : mobs) {
            // Урон мобам
            mob.hurt(net.minecraft.util.DamageSource.MAGIC, 2.0F);
            // Замедление IV
            mob.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 3, false, false));
            // Слабость II
            mob.addEffect(new EffectInstance(Effects.WEAKNESS, 60, 1, false, false));
        }
    }
    
    private void preventMobSpawning(World world, BlockPos pos) {
        // Эффект отпугивания реализуется через освещение (уровень 15)
        // Мобы не спавнятся при уровне света > 7
    }
    
    private void meltSnow(World world, BlockPos pos) {
        // Плавим снег в радиусе 3 блоков
        for(BlockPos checkPos : BlockPos.betweenClosed(
            pos.offset(-3, -1, -3), 
            pos.offset(3, 1, 3))) {
            
            BlockState checkState = world.getBlockState(checkPos);
            // Плавим снег и лед
            if(checkState.getMaterial() == net.minecraft.block.material.Material.ICE ||
               checkState.getMaterial() == net.minecraft.block.material.Material.TOP_SNOW) {
                world.removeBlock(checkPos, false);
            }
        }
    }
}
