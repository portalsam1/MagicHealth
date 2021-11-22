package net.portalsam.magichealth.item;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HeartShard {

    public ItemStack heartShardItem;
    public ItemMeta itemMetaClean;

    public HeartShard(ArrayList<ItemStack> itemList) {

        ItemStack item = new ItemStack(Material.NETHER_WART, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(PluginLanguage.getHeartShardName());

        meta.setLore(PluginLanguage.filterItems(PluginLanguage.getHeartShardLore()));
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMetaClean = meta;

        meta.setCustomModelData(121269);
        item.setItemMeta(meta);
        heartShardItem = item;

        itemList.add(heartShardItem);

        // If crafting is enabled for this item add a recipe for it.
        if(MagicHealthConfig.isEnablingHeartShardCrafting()) {

            NamespacedKey key = new NamespacedKey(MagicHealth.getMagicHealthInstance(), "heart_shard");
            RecipeChoice heartDust = new RecipeChoice.ExactChoice(MagicHealthItems.HEART_DUST.heartDustItem);

            ShapedRecipe recipe = new ShapedRecipe(key, heartShardItem);
            recipe.shape("DD", "DD");
            recipe.setIngredient('D', heartDust);

            Bukkit.addRecipe(recipe);

            // Second recipe for pre-model data compatibility.

            ItemStack heartDustItemClean = MagicHealthItems.HEART_DUST.heartDustItem;
            heartDustItemClean.setItemMeta(itemMetaClean);

            NamespacedKey keyClean = new NamespacedKey(MagicHealth.getMagicHealthInstance(), "heart_shard_clean");
            RecipeChoice heartDustClean = new RecipeChoice.ExactChoice(heartDustItemClean);

            ShapedRecipe recipeClean = new ShapedRecipe(keyClean, heartShardItem);
            recipeClean.shape("DD", "DD");
            recipeClean.setIngredient('D', heartDustClean);

            Bukkit.addRecipe(recipeClean);

        }

    }

    public ItemMeta getItemMetaClean() {
        return itemMetaClean;
    }

    public ItemStack getHeartShardItem() {
        return heartShardItem;
    }

}
