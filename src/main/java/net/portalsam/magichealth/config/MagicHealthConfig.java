package net.portalsam.magichealth.config;

import net.portalsam.magichealth.MagicHealth;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MagicHealthConfig {

    // Player variables.

    private static float minimumPlayerHealth = 20f;
    private static float maximumPlayerHealth = 40f;

    private static float defaultPlayerHealth = 20f;

    private static boolean enforcePlayerMinimumHealth = true;
    private static boolean enforcePlayerMaximumHealth = true;

    private static boolean useCustomModels = true;

    // Heart crystal variables.

    private static float increaseHealthBy = 2f;
    private static float decreaseHealthBy = 2f;

    // Drop percentages.

    private static boolean enableMobDrops = true;
    private static boolean enableSpawnerDrops = true;
    private static boolean maxHealthPlayersLootHeartDust = true;

    private static float bossMobDropChance = 100.0f;
    private static final int[] bossMobDropAmounts = { 3, 9 };

    private static float uncommonMobDropChance = 15.75f;
    private static final int[] uncommonMobDropAmounts = { 1, 3 };

    private static float commonMobDropChance = 4.5f;
    private static final int[] commonMobDropAmounts = { 1, 2 };

    // Hardcore / strip players hearts on death.

    private static boolean resetPlayersHealthOnDeath = false;

    // Enable crafting variables.

    private static boolean enableHeartShardCrafting = true;
    private static boolean enableHeartCrystalCrafting = true;
    private static boolean enableHeartDrainAmuletCrafting = true;

    // Enable item variables.

    private static boolean enableHeartCrystal = true;
    private static boolean enableHeartDrainAmulet = true;

    // Mob lists.

    private static List<String> commonMobList = Arrays.asList("STRIDER", "BEE", "ENDERMAN", "IRON_GOLEM", "PANDA",
            "PIGLIN", "POLAR_BEAR", "SPIDER", "BLAZE", "CREEPER", "ENDERMITE", "EVOKER", "GHAST", "GUARDIAN", "HOGLIN",
            "SILVERFISH", "SKELETON", "SLIME", "STRAY", "WITCH", "ZOGLIN", "ZOMBIE", "PILLAGER");

    private static List<String> uncommonMobList = Arrays.asList("ELDER_GUARDIAN", "PIGLIN_BRUTE", "RAVAGER", "SHULKER",
            "SKELETON_HORSE", "VEX", "VINDICATOR", "WITHER_SKELETON", "GIANT");

    private static List<String> bossMobList = Arrays.asList("ENDER_DRAGON", "WITHER");

    // Plugin variables.

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();
    private static FileConfiguration config = null;

    public static void initializeConfiguration() {

        magicHealth.saveDefaultConfig();

        config = magicHealth.getConfig();

        // Load configuration.
        loadConfiguration();

    }

    public static void loadConfiguration() {

        // Load all values from configuration.

        minimumPlayerHealth = (float)config.getDouble("minimumPlayerHealth");
        maximumPlayerHealth = (float)config.getDouble("maximumPlayerHealth");
        defaultPlayerHealth = (float)config.getDouble("defaultPlayerHealth");
        enforcePlayerMinimumHealth = config.getBoolean("enforcePlayerMinimumHealth");
        enforcePlayerMaximumHealth = config.getBoolean("enforcePlayerMaximumHealth");
        useCustomModels = config.getBoolean("useCustomModels");

        increaseHealthBy = (float)config.getDouble("increaseHealthBy");
        decreaseHealthBy = (float)config.getDouble("decreaseHealthBy");
        maxHealthPlayersLootHeartDust = config.getBoolean("maxHealthPlayersLootHeartDust");

        enableMobDrops = config.getBoolean("enableMobDrops");
        enableSpawnerDrops = config.getBoolean("enableSpawnerDrops");

        bossMobDropChance = (float)config.getDouble("bossMobDropChance");
        bossMobDropAmounts[0] = config.getInt("bossMobDropMinimum");
        bossMobDropAmounts[1] = config.getInt("bossMobDropMaximum");

        uncommonMobDropChance = (float)config.getDouble("uncommonMobDropChance");
        uncommonMobDropAmounts[0] = config.getInt("uncommonMobDropMinimum");
        uncommonMobDropAmounts[1] = config.getInt("uncommonMobDropMaximum");

        commonMobDropChance = (float)config.getDouble("commonMobDropChance");
        commonMobDropAmounts[0] = config.getInt("commonMobDropMinimum");
        commonMobDropAmounts[1] = config.getInt("commonMobDropMaximum");

        resetPlayersHealthOnDeath = config.getBoolean("resetPlayersHealthOnDeath");

        enableHeartShardCrafting = config.getBoolean("enableHeartShardCrafting");
        enableHeartCrystalCrafting = config.getBoolean("enableHeartCrystalCrafting");
        enableHeartDrainAmuletCrafting = config.getBoolean("enableHeartDrainAmuletCrafting");

        enableHeartCrystal = config.getBoolean("enableHeartCrystal");
        enableHeartDrainAmulet = config.getBoolean("enableHeartDrainAmulet");

        commonMobList = config.getStringList("commonMobList");
        uncommonMobList = config.getStringList("uncommonMobList");
        bossMobList = config.getStringList("bossMobList");

        log.info(String.format("[%s] Internal variables updated from configuration.", magicHealth.getDescription().getName()));

    }

    /*/ Getters. /*/

    public static float getMinimumPlayerHealth() {
        return minimumPlayerHealth;
    }

    public static float getMaximumPlayerHealth() {
        return maximumPlayerHealth;
    }

    public static boolean isUsingCustomModels() {
        return useCustomModels;
    }

    public static float getBossMobDropChance() {
        return bossMobDropChance;
    }

    public static int[] getBossMobDropAmounts() {
        return bossMobDropAmounts;
    }

    public static float getUncommonMobDropChance() {
        return uncommonMobDropChance;
    }

    public static int[] getUncommonMobDropAmounts() {
        return uncommonMobDropAmounts;
    }

    public static float getCommonMobDropChance() {
        return commonMobDropChance;
    }

    public static int[] getCommonMobDropAmounts() {
        return commonMobDropAmounts;
    }

    public static boolean isEnforcingPlayerMinimumHealth() {
        return enforcePlayerMinimumHealth;
    }

    public static boolean isEnforcingPlayerMaximumHealth() {
        return enforcePlayerMaximumHealth;
    }

    public static float getIncreaseHealthBy() {
        return increaseHealthBy;
    }

    public static float getDecreaseHealthBy() {
        return decreaseHealthBy;
    }

    public static boolean doMaxHealthPlayersLootHeartDust() {
        return maxHealthPlayersLootHeartDust;
    }

    public static boolean isResettingPlayersHealthOnDeath() {
        return resetPlayersHealthOnDeath;
    }

    public static boolean isEnablingHeartShardCrafting() {
        return enableHeartShardCrafting;
    }

    public static boolean isEnablingHeartCrystalCrafting() {
        return enableHeartCrystalCrafting;
    }

    public static boolean isEnablingHeartDrainAmuletCrafting() {
        return enableHeartDrainAmuletCrafting;
    }

    public static boolean isEnablingHeartCrystal() {
        return enableHeartCrystal;
    }

    public static boolean isEnablingHeartDrainAmulet() {
        return enableHeartDrainAmulet;
    }

    public static List<String> getCommonMobList() {
        return commonMobList;
    }

    public static List<String> getUncommonMobList() {
        return uncommonMobList;
    }

    public static List<String> getBossMobList() {
        return bossMobList;
    }

    public static boolean mobDropsAreEnabled() {
        return enableMobDrops;
    }

    public static boolean spawnersAreEnabled() {
        return enableSpawnerDrops;
    }

    public static float getDefaultPlayerHealth() {
        return defaultPlayerHealth;
    }

}