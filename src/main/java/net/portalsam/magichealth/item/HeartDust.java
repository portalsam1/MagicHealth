package net.portalsam.magichealth.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HeartDust {

    public ItemStack heartDustItem;

    public HeartDust(ArrayList<ItemStack> itemList) {

        ItemStack item = new ItemStack(Material.RED_DYE, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Heart Dust");

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.YELLOW + "A powerful mix of life essence ground");
        itemLore.add(ChatColor.YELLOW + "into a fine powder." + ChatColor.WHITE);
        itemLore.add(ChatColor.WHITE + "If four of these dust clumps are combined");
        itemLore.add(ChatColor.WHITE + "together they can form a " + ChatColor.LIGHT_PURPLE + "Heart Shard" + ChatColor.WHITE + ".");

        meta.setLore(itemLore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        heartDustItem = item;

        itemList.add(heartDustItem);

    }

    public ItemStack getHeartDustItem() {
        return heartDustItem;
    }

}
