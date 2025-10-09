package com.ineyws.greenstar.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class DeathMoonPickaxeItem extends PickaxeItem {
    
    public DeathMoonPickaxeItem(IItemTier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
}
