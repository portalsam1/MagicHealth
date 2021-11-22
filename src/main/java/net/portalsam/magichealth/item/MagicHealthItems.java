package net.portalsam.magichealth.item;

import net.portalsam.magichealth.MagicHealth;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MagicHealthItems {

    // Holding list for items.
    public static final ArrayList<ItemStack> MAGIC_HEALTH_ITEMS = new ArrayList<>();

    public static final HeartDust HEART_DUST = new HeartDust(MAGIC_HEALTH_ITEMS);
    public static final HeartShard HEART_SHARD = new HeartShard(MAGIC_HEALTH_ITEMS);
    public static final HeartCrystal HEART_CRYSTAL = new HeartCrystal(MAGIC_HEALTH_ITEMS);
    public static final HeartDrainAmulet HEART_DRAIN_AMULET = new HeartDrainAmulet(MAGIC_HEALTH_ITEMS);

    // Brought this back I guess...?
    static {
        MagicHealth.getMagicHealthLogger().info(String.format("[%s] Finished registering items, " + MAGIC_HEALTH_ITEMS.size() + " items registered.",
                MagicHealth.getMagicHealthInstance().getDescription().getName()));
    }

    /*/ Getters. /*/

    public static ArrayList<ItemStack> getMagicHealthItems() {
        return MAGIC_HEALTH_ITEMS;
    }

    public static HeartDust getHeartDust() {
        return HEART_DUST;
    }

    public static HeartShard getHeartShard() {
        return HEART_SHARD;
    }

    public static HeartCrystal getHeartCrystal() {
        return HEART_CRYSTAL;
    }

    public static HeartDrainAmulet getHeartDrainAmulet() {
        return HEART_DRAIN_AMULET;
    }

}
