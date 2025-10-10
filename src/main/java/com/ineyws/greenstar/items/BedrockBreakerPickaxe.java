package com.ineyws.greenstar.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class BedrockBreakerPickaxe extends PickaxeItem {
    
    public BedrockBreakerPickaxe(IItemTier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
}
