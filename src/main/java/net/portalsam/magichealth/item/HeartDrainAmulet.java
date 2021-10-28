package net.portalsam.magichealth.item;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HeartDrainAmulet implements Listener {

    public ItemStack heartDrainAmuletItem;
    public NamespacedKey heartDrainAmuletKey = new NamespacedKey(MagicHealth.getMagicHealthInstance(), "heart_drain_amulet");

    public HeartDrainAmulet(ArrayList<ItemStack> itemList) {

        ItemStack item = new ItemStack(Material.FLINT, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "Heart Drain Amulet");

        List<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.YELLOW + "A powerful dark magic stone plate.");
        itemLore.add(ChatColor.WHITE + "If used this slate can permanently");
        itemLore.add(ChatColor.RED + "decrease a players health " + ChatColor.WHITE + "if they" );
        itemLore.add(ChatColor.WHITE + "have extra hearts.");

        meta.setLore(itemLore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(heartDrainAmuletKey, PersistentDataType.BYTE, (byte) 1 );

        item.setItemMeta(meta);
        heartDrainAmuletItem = item;

        itemList.add(heartDrainAmuletItem);

        ShapedRecipe recipe = new ShapedRecipe(heartDrainAmuletKey, heartDrainAmuletItem);

        recipe.shape("BGB", "GLG", "BGB");
        recipe.setIngredient('B', Material.BONE);
        recipe.setIngredient('G', Material.GUNPOWDER);
        recipe.setIngredient('L', Material.LAVA_BUCKET);

        Bukkit.addRecipe(recipe);

    }

    public HeartDrainAmulet() { }

    /*/ Getters. /*/

    public ItemStack getHeartDrainAmuletItem() {
        return heartDrainAmuletItem;
    }

    public NamespacedKey getHeartDrainAmuletKey() {
        return heartDrainAmuletKey;
    }

}
