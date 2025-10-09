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

public enum GreenArmorMaterial implements IArmorMaterial {
    // В 10 раз сильнее алмазной брони!
    // Алмазная броня: прочность 33, защита [3, 6, 8, 3], зачаровываемость 10
    GREEN(
            IneyWSGreenStar.MOD_ID + ":green",
            330,  // прочность в 10 раз больше (33 * 10)
            new int[]{30, 60, 80, 30},  // защита в 10 раз больше [3*10, 6*10, 8*10, 3*10]
            100,  // зачаровываемость в 10 раз больше (10 * 10)
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            20.0F,  // прочность (toughness) в 10 раз больше (2.0 * 10)
            0.0F,   // knockback resistance
            () -> Ingredient.of(ModItems.GREEN_INGOT.get())
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

    GreenArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue,
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
