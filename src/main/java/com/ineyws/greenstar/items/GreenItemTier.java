package com.ineyws.greenstar.items;

import com.ineyws.greenstar.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public enum GreenItemTier implements IItemTier {
    // В 10 раз сильнее алмазов!
    // Алмазы: прочность 1561, скорость 8.0, урон 3.0, уровень 3, зачаровываемость 10
    GREEN(
            15610,  // прочность в 10 раз больше (1561 * 10)
            80.0F,  // скорость добычи в 10 раз больше (8.0 * 10)
            30.0F,  // урон в 10 раз больше (3.0 * 10)
            4,      // уровень добычи (выше алмазов)
            100,    // зачаровываемость в 10 раз больше (10 * 10)
            () -> Ingredient.of(ModItems.GREEN_INGOT.get())
    );

    private final int uses;
    private final float speed;
    private final float damage;
    private final int level;
    private final int enchantmentValue;
    private final LazyValue<Ingredient> repairIngredient;

    GreenItemTier(int uses, float speed, float damage, int level, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
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
