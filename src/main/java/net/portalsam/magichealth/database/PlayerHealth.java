package net.portalsam.magichealth.database;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

public class PlayerHealth {

    private static FileConfiguration playerHealthConfig;

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();

    private static final File playerHealthDatabase = new File(magicHealth.getDataFolder(), "playerHealthData.yml");

    public PlayerHealth() {

    }

    public static void initializePlayerConfiguration() {

        if(!playerHealthDatabase.exists()) {

            boolean success = playerHealthDatabase.getParentFile().mkdirs();
            if(success) log.info(String.format("[%s] Created playerHealthData.yml successfully.", magicHealth.getDescription().getName()));

            magicHealth.saveResource("playerHealthData.yml", false);

        }

        playerHealthConfig = new YamlConfiguration();

        try {
            playerHealthConfig.load(playerHealthDatabase);
        } catch(IOException | InvalidConfigurationException ignored) {
            log.severe(String.format("[%s] Unable to load playerHealthData.yml.", magicHealth.getDescription().getName()));
        }

        playerHealthConfig.addDefault("players", null);

    }

    public static void saveConfiguration() {

        try {
            playerHealthConfig.save(playerHealthDatabase);
            log.info(String.format("[%s] playerHealthData.yml has been updated.", magicHealth.getDescription().getName()));
        } catch (IOException ignored) {
            log.severe(String.format("[%s] Unable to save playerHealthData.yml", magicHealth.getDescription().getName()));
        }

    }

    /*/ Health management. /*/

    public static void setPlayerMaximumHealth(Player player, float amount, boolean saveToConfig) {

        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(amount);
        playerHealthConfig.set("players." + player.getUniqueId().toString() + ".maxHealth", getPlayerMaximumHealth(player));

        if(saveToConfig) saveConfiguration();

        checkIfHealthIsUnderMinimum(player);

    }

    public static float getPlayerMaximumHealth(Player player) {
        return (float)Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
    }

    public static float getPlayerMaximumHealthFromDatabase(Player player) {

        float value = (float)playerHealthConfig.getDouble("players." + player.getUniqueId().toString() + ".maxHealth");
        if(value != 0) return value;
        else {
            setPlayerMaximumHealth(player, MagicHealthConfig.getDefaultPlayerHealth(), true);
            return getPlayerMaximumHealth(player);
        }

    }

    public static void increasePlayerMaxHealth(Player player, float amount, boolean saveToConfig) {

        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Math.min(getPlayerMaximumHealth(player) + amount, MagicHealthConfig.getMaximumPlayerHealth()));
        playerHealthConfig.set("players." + player.getUniqueId().toString() + ".maxHealth", getPlayerMaximumHealth(player));

        if(saveToConfig) saveConfiguration();

        checkIfHealthIsUnderMinimum(player);

    }

    public static void decreasePlayerMaxHealth(Player player, float amount, boolean saveToConfig) {

        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Math.max(getPlayerMaximumHealth(player) - amount, MagicHealthConfig.getMinimumPlayerHealth()));
        playerHealthConfig.set("players." + player.getUniqueId().toString() + ".maxHealth", getPlayerMaximumHealth(player));

        if(saveToConfig) saveConfiguration();

        checkIfHealthIsUnderMinimum(player);

    }

    public static void checkIfHealthIsUnderMinimum(Player player) {

        // If the server is enforcing a minimum health set the players health to the minimum if they are under it.
        if(getPlayerMaximumHealth(player) < MagicHealthConfig.getMinimumPlayerHealth()) {
            if(MagicHealthConfig.isEnforcingPlayerMinimumHealth()) {
                setPlayerMaximumHealth(player, MagicHealthConfig.getMinimumPlayerHealth(), true);
            }
        }

    }

    /*/ Getters. /*/

    public static FileConfiguration getPlayerHealthConfig() {
        return playerHealthConfig;
    }

}