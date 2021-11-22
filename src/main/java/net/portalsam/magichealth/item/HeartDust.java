package net.portalsam.magichealth.item;

import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HeartDust {

    public ItemStack heartDustItem;
    public ItemMeta itemMetaClean;

    public HeartDust(ArrayList<ItemStack> itemList) {

        ItemStack item = new ItemStack(Material.RED_DYE, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(PluginLanguage.getHeartDustName());

        meta.setLore(PluginLanguage.filterItems(PluginLanguage.getHeartDustLore()));
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMetaClean = meta;

        meta.setCustomModelData(121269);
        item.setItemMeta(meta);
        heartDustItem = item;

        itemList.add(heartDustItem);

    }

    public ItemMeta getItemMetaClean() {
        return itemMetaClean;
    }

    public ItemStack getHeartDustItem() {
        return heartDustItem;
    }

}
