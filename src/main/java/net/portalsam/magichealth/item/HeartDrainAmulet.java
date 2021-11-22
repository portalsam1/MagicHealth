package net.portalsam.magichealth.item;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class HeartDrainAmulet implements Listener {

    public ItemStack heartDrainAmuletItem;
    public NamespacedKey heartDrainAmuletKey = new NamespacedKey(MagicHealth.getMagicHealthInstance(), "heart_drain_amulet");
    public ItemMeta itemMetaClean;

    public HeartDrainAmulet(ArrayList<ItemStack> itemList) {

        ItemStack item = new ItemStack(Material.FLINT, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(PluginLanguage.getHeartDrainAmuletName());

        meta.setLore(PluginLanguage.filterItems(PluginLanguage.getHeartDrainAmuletLore()));
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(heartDrainAmuletKey, PersistentDataType.BYTE, (byte) 1 );
        itemMetaClean = meta;

        meta.setCustomModelData(121269);
        item.setItemMeta(meta);
        heartDrainAmuletItem = item;

        itemList.add(heartDrainAmuletItem);

        // If crafting is enabled for this item add a recipe for it.
        if(MagicHealthConfig.isEnablingHeartDrainAmuletCrafting()) {

            ShapedRecipe recipe = new ShapedRecipe(heartDrainAmuletKey, heartDrainAmuletItem);

            recipe.shape("BGB", "GLG", "BGB");
            recipe.setIngredient('B', Material.BONE);
            recipe.setIngredient('G', Material.GUNPOWDER);
            recipe.setIngredient('L', Material.LAVA_BUCKET);

            Bukkit.addRecipe(recipe);

        }

    }

    public HeartDrainAmulet() { }

    /*/ Getters. /*/

    public ItemMeta getItemMetaClean() {
        return itemMetaClean;
    }

    public ItemStack getHeartDrainAmuletItem() {
        return heartDrainAmuletItem;
    }

    public NamespacedKey getHeartDrainAmuletKey() {
        return heartDrainAmuletKey;
    }

}
