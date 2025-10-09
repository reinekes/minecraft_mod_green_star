package com.ineyws.greenstar.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class DeathMoonShovelItem extends ShovelItem {
    
    private final float customSpeed;
    
    public DeathMoonShovelItem(IItemTier tier, float attackDamage, float attackSpeed, float customSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        this.customSpeed = customSpeed;
    }
}
