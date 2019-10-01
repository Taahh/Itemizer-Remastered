package me.taahanis.itemizer;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Attributes {
        GENERIC_MAX_HEALTH("generic.maxHealth"),
        GENERIC_MOVEMENT_SPEED("generic.movementSpeed"),
        GENERIC_ATTACK_DAMAGE("generic.attackDamage"),
        GENERIC_ATTACK_SPEED("generic.attackSpeed"),
        GENERIC_ARMOR("generic.armor"),
        GENERIC_LUCK("generic.luck");
        String attributeModifier;

        Attributes(String aM) {
            attributeModifier = aM;
        }


    public static void setGenericMaxHealth(ItemStack item, double amount) {
        AttributeModifier modifier = new AttributeModifier(GENERIC_MAX_HEALTH.getAttributeModifier(), amount, AttributeModifier.Operation.ADD_NUMBER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
    }

    public static void setGenericMovementSpeed(ItemStack item, double amount)
    {
        AttributeModifier modifier = new AttributeModifier(GENERIC_MOVEMENT_SPEED.getAttributeModifier(), amount, AttributeModifier.Operation.ADD_NUMBER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);
    }

    public static void setGenericAttackDamage(ItemStack item, double amount)
    {
        AttributeModifier modifier = new AttributeModifier(GENERIC_ATTACK_DAMAGE.getAttributeModifier(), amount, AttributeModifier.Operation.ADD_NUMBER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
    }
    public static void setGenericAttackSpeed(ItemStack item, double amount)
    {
        AttributeModifier modifier = new AttributeModifier(GENERIC_ATTACK_SPEED.getAttributeModifier(), amount, AttributeModifier.Operation.ADD_NUMBER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
    }
    public static void setGenericLuck(ItemStack item, double amount)
    {
        AttributeModifier modifier = new AttributeModifier(GENERIC_LUCK.getAttributeModifier(), amount, AttributeModifier.Operation.ADD_NUMBER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addAttributeModifier(Attribute.GENERIC_LUCK, modifier);
    }
    public static void setGenericArmor(ItemStack item, double amount)
    {
        AttributeModifier modifier = new AttributeModifier(GENERIC_ARMOR.getAttributeModifier(), amount, AttributeModifier.Operation.ADD_NUMBER);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
    }

        public String getAttributeModifier()
        {
            return attributeModifier;
        }

}
