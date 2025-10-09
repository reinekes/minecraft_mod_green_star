package com.ineyws.greenstar.items;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum DeathMoonArmorMaterial implements IArmorMaterial {
    // В 2 раза сильнее зеленой брони!
    // Зеленая броня: прочность 330, защита [30, 60, 80, 30], зачаровываемость 100, toughness 20.0
    DEATH_MOON(
            IneyWSGreenStar.MOD_ID + ":death_moon",
            660,  // прочность в 2 раза больше (330 * 2)
            new int[]{60, 120, 160, 60},  // защита в 2 раза больше [30*2, 60*2, 80*2, 30*2]
            200,  // зачаровываемость в 2 раза больше (100 * 2)
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            40.0F,  // прочность (toughness) в 2 раза больше (20.0 * 2)
            0.2F,   // knockback resistance (как у незерита)
            () -> Ingredient.of(ModItems.DEATH_MOON_INGOT.get())
    );

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    DeathMoonArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
                           SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.slotProtections[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
