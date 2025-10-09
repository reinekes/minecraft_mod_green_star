package com.ineyws.greenstar.items;

import com.ineyws.greenstar.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum DeathMoonItemTier implements IItemTier {
    // Супер-мощные инструменты Death Moon!
    DEATH_MOON(
            50000,   // огромная прочность
            1000.0F, // скорость копания для кирки
            60.0F,   // базовый урон
            6,       // уровень добычи (максимальный)
            200,     // зачаровываемость
            () -> Ingredient.of(ModItems.DEATH_MOON_INGOT.get())
    );

    private final int uses;
    private final float speed;
    private final float damage;
    private final int level;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    DeathMoonItemTier(int uses, float speed, float damage, int level, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
