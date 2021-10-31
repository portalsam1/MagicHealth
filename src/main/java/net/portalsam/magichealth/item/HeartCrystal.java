package net.portalsam.magichealth.item;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class HeartCrystal implements Listener {

    public ItemStack heartCrystalItem;
    public NamespacedKey heartCrystalKey = new NamespacedKey(MagicHealth.getMagicHealthInstance(), "heart_crystal");

    public HeartCrystal(ArrayList<ItemStack> itemList) {

        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Heart Crystal");

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.YELLOW + "A powerful magic crystalline structure.");
        itemLore.add(ChatColor.WHITE + "If used this crystal can permanently");
        itemLore.add(ChatColor.GREEN + "expand a players health" + ChatColor.WHITE + ".");

        meta.setLore(itemLore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(heartCrystalKey, PersistentDataType.BYTE, (byte) 1 );

        item.setItemMeta(meta);
        heartCrystalItem = item;

        itemList.add(heartCrystalItem);

        // If crafting is enabled for this item add a recipe for it.
        if(MagicHealthConfig.isEnablingHeartCrystalCrafting()) {

            ShapedRecipe recipe = new ShapedRecipe(heartCrystalKey, heartCrystalItem);
            RecipeChoice heartShard = new RecipeChoice.ExactChoice(MagicHealthItems.HEART_SHARD.heartShardItem);

            recipe.shape("SSS", "SDS", "SSS");
            recipe.setIngredient('S', heartShard);
            recipe.setIngredient('D', Material.DIAMOND);

            Bukkit.addRecipe(recipe);

        }

    }

    public HeartCrystal() { }

    /*/ Getters. /*/

    public ItemStack getHeartCrystalItem() {
        return heartCrystalItem;
    }

    public NamespacedKey getHeartCrystalKey() {
        return heartCrystalKey;
    }

}