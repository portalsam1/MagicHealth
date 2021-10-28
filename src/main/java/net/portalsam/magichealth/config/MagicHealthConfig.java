package net.portalsam.magichealth.config;

import net.portalsam.magichealth.MagicHealth;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public class MagicHealthConfig {

    // Player variables.

    public static float minimumPlayerHealth = 20f;
    public static float maximumPlayerHealth = 40f;

    public static boolean enforcePlayerMinimumHealth = true;
    public static boolean enforcePlayerMaximumHealth = true;

    // Heart crystal variables.

    public static float increaseHealthBy = 2f;
    public static float decreaseHealthBy = 2f;

    // Drop percentages.

    public static float bossMobDropChance = 100.0f;
    public static int[] bossMobDropAmounts = { 3, 9 };

    public static float uncommonMobDropChance = 15.75f;
    public static int[] uncommonMobDropAmounts = { 1, 3 };

    public static float commonMobDropChance = 4.5f;
    public static int[] commonMobDropAmounts = { 1, 2 };

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();
    private static final FileConfiguration config = magicHealth.getConfig();

    public static void initializeConfiguration() {

        magicHealth.saveDefaultConfig();

        // Default config values.

        config.addDefault("minimumPlayerHealth", minimumPlayerHealth);
        config.addDefault("maximumPlayerHealth", maximumPlayerHealth);
        config.addDefault("enforcePlayerMinimumHealth", enforcePlayerMinimumHealth);
        config.addDefault("enforcePlayerMaximumHealth", enforcePlayerMaximumHealth);

        config.addDefault("increaseHealthBy", increaseHealthBy);
        config.addDefault("decreaseHealthBy", decreaseHealthBy);

        config.addDefault("bossMobDropChance", bossMobDropChance);
        config.addDefault("bossMobDropMinimum", bossMobDropAmounts[0]);
        config.addDefault("bossMobDropMaximum", bossMobDropAmounts[1]);

        config.addDefault("uncommonMobDropChance", uncommonMobDropChance);
        config.addDefault("uncommonMobDropMinimum", uncommonMobDropAmounts[0]);
        config.addDefault("uncommonMobDropMaximum", uncommonMobDropAmounts[1]);

        config.addDefault("commonMobDropChance", commonMobDropChance);
        config.addDefault("commonMobDropMinimum", commonMobDropAmounts[0]);
        config.addDefault("commonMobDropMaximum", commonMobDropAmounts[1]);

        config.options().copyDefaults(true);

        // Load configuration.
        loadConfiguration();

    }

    public static void loadConfiguration() {

        // Load all values from configuration.

        minimumPlayerHealth = (float)config.getDouble("minimumPlayerHealth");
        maximumPlayerHealth = (float)config.getDouble("maximumPlayerHealth");
        enforcePlayerMinimumHealth = config.getBoolean("enforcePlayerMinimumHealth");
        enforcePlayerMaximumHealth = config.getBoolean("enforcePlayerMaximumHealth");

        increaseHealthBy = (float)config.getDouble("increaseHealthBy");
        decreaseHealthBy = (float)config.getDouble("decreaseHealthBy");

        bossMobDropChance = (float)config.getDouble("bossMobDropChance");
        bossMobDropAmounts[0] = config.getInt("bossMobDropMinimum");
        bossMobDropAmounts[1] = config.getInt("bossMobDropMaximum");

        uncommonMobDropChance = (float)config.getDouble("uncommonMobDropChance");
        uncommonMobDropAmounts[0] = config.getInt("uncommonMobDropMinimum");
        uncommonMobDropAmounts[1] = config.getInt("uncommonMobDropMaximum");

        commonMobDropChance = (float)config.getDouble("commonMobDropChance");
        commonMobDropAmounts[0] = config.getInt("commonMobDropMinimum");
        commonMobDropAmounts[1] = config.getInt("commonMobDropMaximum");

        log.info(String.format("[%s] Config loaded.", magicHealth.getDescription().getName()));

    }

    /*/ Getters. /*/

    public static float getMinimumPlayerHealth() {
        return minimumPlayerHealth;
    }

    public static float getMaximumPlayerHealth() {
        return maximumPlayerHealth;
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

}