package net.portalsam.magichealth.database;

import net.portalsam.magichealth.MagicHealth;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

// Retrieve all the strings this plugin uses from a file customizable by the user.
public class PluginLanguage {

    // Message variables.

    private static String magicHealthPrefix = "[&dMagicHealth&f]";
    private static String magicHealthSeparator = ",";

    private static String heartDustName = "&dHeart Dust";
    private static String heartShardName = "&dHeart Shard";
    private static String heartCrystalName = "&Heart Crystal";
    private static String heartDrainAmuletName = "&cHeart Drain Amulet";

    private static List<String> heartDustLore = new ArrayList<>(Arrays.asList("&eA powerful mix of life essence ground", "&eInto a fine powder.&f", "&fIf four of these dust clumps are combined", "&ftogether they can form a &d{HEARTSHARD}&f."));
    private static List<String> heartShardLore = new ArrayList<>(Arrays.asList("&eA powerful broken crystalline structure.", "&fIf eight of these crystal shards are", "&fcombined together with a diamond they", "&fcan form a &d{HEARTCRYSTAL}&f."));
    private static List<String> heartCrystalLore = new ArrayList<>(Arrays.asList("&eA powerful magic crystalline structure.", "&fIf used this crystal can permanently", "&aexpand a player's health&f."));
    private static List<String> heartDrainAmuletLore = new ArrayList<>(Arrays.asList("&eA powerful dark magic stone plate.", "&fIf used this slate can permanently", "&cdecrease a players health &fif they", "&fhave extra hearts."));

    private static String heartCrystalAlreadyMax = "{PREFIX} You are already at the maximum health!";
    private static String heartCrystalDisabled = "{PREFIX} &cThis item isn't enabled on this server!";

    private static String heartDrainAmuletAlreadyMin = "{PREFIX} You don't have any spare hearts!";
    private static String heartDrainAmuletDisabled = "{PREFIX} &cThis item isn't enabled on this server!";

    private static String playerLoseAllHearts = "{PREFIX} &cAs consequence of your death you have lost all your extra hearts.";

    private static String magicGiveItemListSubcommandName = "list";
    private static String magicGiveItemList = "{PREFIX} List of available items: {ITEMLIST}";
    private static String magicGiveItemGiven = "{PREFIX}  Giving [&dx{ITEMAMOUNT}&f] {ITEMGIVEN}&f";
    private static String magicGiveItemInvalid = "{PREFIX} &cItem '{INPUT}' does not exist.";
    private static String magicGiveItemNonPlayer = "{PREFIX} &cThis command can only be used by a player.";
    private static String magicGiveItemInvalidUsage = "{PREFIX} &cInvalid usage! Use argument '{LISTARGUMENT}' for a list of items or supply a valid item name.";

    private static String magicSetMaxInvalidUsage = "{PREFIX} Not enough arguments, either specify a max health to set yourself at, or supply a player and a max health.";
    private static String magicSetMaxOwnHealth = "{PREFIX} Attempting to set your max health to '{NEWHEALTH}'";
    private static String magicSetMaxIllegalNumber = "{PREFIX} &c{INPUT} is not a valid number.";
    private static String magicSetMaxNonPlayer = "{PREFIX} &cYou must specify a player to run this command on.";
    private static String magicSetMaxOtherHealth = "{PREFIX} Attempting to set {PLAYER}'s max health to {NEWHEALTH}";
    private static String magicSetMaxIllegalPlayer = "{PREFIX} &c{INPUT} is not a valid player.";

    private static String magicSetInvalidUsage = "{PREFIX} Not enough arguments, either specify a health to set yourself to, or supply a player and a health.";
    private static String magicSetOwnHealth = "{PREFIX} Attempting to set your health to '{NEWHEALTH}'";
    private static String magicSetIllegalNumber = "{PREFIX} &c{INPUT} is not a valid number.";
    private static String magicSetNonPlayer = "{PREFIX} &cYou must specify a player to run this command on.";
    private static String magicSetOtherHealth = "{PREFIX} Attempting to set {PLAYER}'s health to {NEWHEALTH}";
    private static String magicSetIllegalPlayer = "{PREFIX} &c{INPUT} is not a valid player.";

    private static String magicLevelUpHealthOwn = "{PREFIX} You have levelled up your health from &e{OLDHEALTH} &fto &e{NEWHEALTH}&f!";
    private static String magicLevelUpHealthMaximum = "{PREFIX} You are already at your allotted maximum health!";
    private static String magicLevelUpHealthNonPlayer = "{PREFIX} &cOnly a player can run this without arguments, please specify a player.";
    private static String magicLevelUpHealthOther = "{PREFIX} Levelled up {PLAYER}&f's health from &e{OLDHEALTH} &fto &e{NEWHEALTH}&f!";
    private static String magicLevelUpHealthOtherNotify = "{PREFIX} Your health has levelled up from &e{OLDHEALTH} &fto &e{NEWHEALTH}&f!";
    private static String magicLevelUpHealthOtherMaximum = "{PREFIX} This player is already at their maximum health!";
    private static String magicLevelUpHealthIllegalPlayer = "{PREFIX} &c{INPUT} is not a valid player.";
    private static String magicLevelUpHealthMissingPermission = "{PREFIX} &cYou do not have permissions to use arguments on this command.";

    // Configuration variables.

    private static FileConfiguration playerLanguageConfig;

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();

    private static final File pluginLanguageDatabase = new File(magicHealth.getDataFolder(), "messages.yml");

    public PluginLanguage() {

    }

    public static void initializeLanguageConfiguration() {

        if(!pluginLanguageDatabase.exists()) {

            boolean success = pluginLanguageDatabase.getParentFile().mkdirs();
            if(success) log.info(String.format("[%s] Created messages.yml successfully.", magicHealth.getDescription().getName()));

            magicHealth.saveResource("messages.yml", false);

        }

        playerLanguageConfig = new YamlConfiguration();

        try {

            playerLanguageConfig.load(pluginLanguageDatabase);

            magicHealthPrefix = playerLanguageConfig.getString("magicHealthPrefix");

            magicHealthSeparator = playerLanguageConfig.getString("magicHealthSeparator");

            heartDustName = playerLanguageConfig.getString("heartDustName");
            heartShardName = playerLanguageConfig.getString("heartShardName");
            heartCrystalName = playerLanguageConfig.getString("heartCrystalName");
            heartDrainAmuletName = playerLanguageConfig.getString("heartDrainAmuletName");

            heartDustLore = playerLanguageConfig.getStringList("heartDustLore");
            heartShardLore = playerLanguageConfig.getStringList("heartShardLore");
            heartCrystalLore = playerLanguageConfig.getStringList("heartCrystalLore");
            heartDrainAmuletLore = playerLanguageConfig.getStringList("heartDrainAmuletLore");

            heartCrystalAlreadyMax = playerLanguageConfig.getString("heartCrystalAlreadyMax");
            heartCrystalDisabled = playerLanguageConfig.getString("heartCrystalDisabled");

            heartDrainAmuletAlreadyMin = playerLanguageConfig.getString("heartDrainAmuletAlreadyMin");
            heartDrainAmuletDisabled = playerLanguageConfig.getString("heartDrainAmuletDisabled");

            playerLoseAllHearts = playerLanguageConfig.getString("playerLoseAllHearts");

            magicGiveItemListSubcommandName = playerLanguageConfig.getString("magicGiveItemListSubcommandName");
            magicGiveItemList = playerLanguageConfig.getString("magicGiveItemList");
            magicGiveItemGiven = playerLanguageConfig.getString("magicGiveItemGiven");
            magicGiveItemInvalid = playerLanguageConfig.getString("magicGiveItemInvalid");
            magicGiveItemNonPlayer = playerLanguageConfig.getString("magicGiveItemNonPlayer");
            magicGiveItemInvalidUsage = playerLanguageConfig.getString("magicGiveItemInvalidUsage");

            magicSetMaxInvalidUsage = playerLanguageConfig.getString("magicSetMaxInvalidUsage");
            magicSetMaxOwnHealth = playerLanguageConfig.getString("magicSetMaxOwnHealth");
            magicSetMaxIllegalNumber = playerLanguageConfig.getString("magicSetMaxIllegalNumber");
            magicSetMaxNonPlayer = playerLanguageConfig.getString("magicSetMaxNonPlayer");
            magicSetMaxOtherHealth = playerLanguageConfig.getString("magicSetMaxOtherHealth");
            magicSetMaxIllegalPlayer = playerLanguageConfig.getString("magicSetMaxIllegalPlayer");

            magicSetInvalidUsage = playerLanguageConfig.getString("magicSetInvalidUsage");
            magicSetOwnHealth = playerLanguageConfig.getString("magicSetOwnHealth");
            magicSetIllegalNumber = playerLanguageConfig.getString("magicSetIllegalNumber");
            magicSetNonPlayer = playerLanguageConfig.getString("magicSetNonPlayer");
            magicSetOtherHealth = playerLanguageConfig.getString("magicSetOtherHealth");
            magicSetIllegalPlayer = playerLanguageConfig.getString("magicSetIllegalPlayer");

            magicLevelUpHealthOwn = playerLanguageConfig.getString("magicLevelUpHealthOwn");
            magicLevelUpHealthMaximum = playerLanguageConfig.getString("magicLevelUpHealthMaximum");
            magicLevelUpHealthNonPlayer = playerLanguageConfig.getString("magicLevelUpHealthNonPlayer");
            magicLevelUpHealthOther = playerLanguageConfig.getString("magicLevelUpHealthOther");
            magicLevelUpHealthOtherNotify = playerLanguageConfig.getString("magicLevelUpHealthOtherNotify");
            magicLevelUpHealthOtherMaximum = playerLanguageConfig.getString("magicLevelUpHealthOtherMaximum");
            magicLevelUpHealthIllegalPlayer = playerLanguageConfig.getString("magicLevelUpHealthIllegalPlayer");
            magicLevelUpHealthMissingPermission = playerLanguageConfig.getString("magicLevelUpHealthMissingPermission");

        } catch(IOException | InvalidConfigurationException ignored) {
            log.severe(String.format("[%s] Unable to load messages.yml.", magicHealth.getDescription().getName()));
        }

    }

    public static void saveConfiguration() {

        try {

            playerLanguageConfig.save(pluginLanguageDatabase);
            log.info(String.format("[%s] messages.yml has been updated.", magicHealth.getDescription().getName()));

        } catch (IOException ignored) {

            log.severe(String.format("[%s] Unable to save messages.yml", magicHealth.getDescription().getName()));

        }

    }

    /*/ Utility. /*/

    public static String filterDefault(String text) {
        return ChatColor.translateAlternateColorCodes('&', text).replace("{PREFIX}", getMagicHealthPrefix());
    }

    public static String filterItems(String text) {
        return text.replace("{HEARTDUST}", getHeartDustName()).replace("{HEARTSHARD}", getHeartShardName()).replace("{HEARTCRYSTAL}", getHeartCrystalName()).replace("{HEARTDRAINAMULET}", getHeartDrainAmuletName());
    }

    /*/ Getters. /*/

    public static FileConfiguration getPlayerLanguageConfig() {
        return playerLanguageConfig;
    }

    public static String getMagicHealthPrefix() {
        return magicHealthPrefix;
    }

    public static String getMagicHealthSeparator() {
        return magicHealthSeparator;
    }

    public static String getHeartDustName() {
        return heartDustName;
    }

    public static String getHeartShardName() {
        return heartShardName;
    }

    public static String getHeartCrystalName() {
        return heartCrystalName;
    }

    public static String getHeartDrainAmuletName() {
        return heartDrainAmuletName;
    }

    public static List<String> getHeartDustLore() {
        return heartDustLore;
    }

    public static List<String> getHeartShardLore() {
        return heartShardLore;
    }

    public static List<String> getHeartCrystalLore() {
        return heartCrystalLore;
    }

    public static List<String> getHeartDrainAmuletLore() {
        return heartDrainAmuletLore;
    }

    public static String getHeartCrystalAlreadyMax() {
        return heartCrystalAlreadyMax;
    }

    public static String getHeartCrystalDisabled() {
        return heartCrystalDisabled;
    }

    public static String getHeartDrainAmuletAlreadyMin() {
        return heartDrainAmuletAlreadyMin;
    }

    public static String getHeartDrainAmuletDisabled() {
        return heartDrainAmuletDisabled;
    }

    public static String getPlayerLoseAllHearts() {
        return playerLoseAllHearts;
    }

    public static String getMagicGiveItemListSubcommandName() {
        return magicGiveItemListSubcommandName;
    }

    public static String getMagicGiveItemList() {
        return magicGiveItemList;
    }

    public static String getMagicGiveItemGiven() {
        return magicGiveItemGiven;
    }

    public static String getMagicGiveItemInvalid() {
        return magicGiveItemInvalid;
    }

    public static String getMagicGiveItemNonPlayer() {
        return magicGiveItemNonPlayer;
    }

    public static String getMagicGiveItemInvalidUsage() {
        return magicGiveItemInvalidUsage;
    }

    public static String getMagicSetMaxInvalidUsage() {
        return magicSetMaxInvalidUsage;
    }

    public static String getMagicSetMaxOwnHealth() {
        return magicSetMaxOwnHealth;
    }

    public static String getMagicSetMaxIllegalNumber() {
        return magicSetMaxIllegalNumber;
    }

    public static String getMagicSetMaxNonPlayer() {
        return magicSetMaxNonPlayer;
    }

    public static String getMagicSetMaxOtherHealth() {
        return magicSetMaxOtherHealth;
    }

    public static String getMagicSetMaxIllegalPlayer() {
        return magicSetMaxIllegalPlayer;
    }

    public static String getMagicSetInvalidUsage() {
        return magicSetInvalidUsage;
    }

    public static String getMagicSetOwnHealth() {
        return magicSetOwnHealth;
    }

    public static String getMagicSetIllegalNumber() {
        return magicSetIllegalNumber;
    }

    public static String getMagicSetNonPlayer() {
        return magicSetNonPlayer;
    }

    public static String getMagicSetOtherHealth() {
        return magicSetOtherHealth;
    }

    public static String getMagicSetIllegalPlayer() {
        return magicSetIllegalPlayer;
    }

    public static String getMagicLevelUpHealthOwn() {
        return magicLevelUpHealthOwn;
    }

    public static String getMagicLevelUpHealthMaximum() {
        return magicLevelUpHealthMaximum;
    }

    public static String getMagicLevelUpHealthNonPlayer() {
        return magicLevelUpHealthNonPlayer;
    }

    public static String getMagicLevelUpHealthOther() {
        return magicLevelUpHealthOther;
    }

    public static String getMagicLevelUpHealthOtherNotify() {
        return magicLevelUpHealthOtherNotify;
    }

    public static String getMagicLevelUpHealthOtherMaximum() {
        return magicLevelUpHealthOtherMaximum;
    }

    public static String getMagicLevelUpHealthIllegalPlayer() {
        return magicLevelUpHealthIllegalPlayer;
    }

    public static String getMagicLevelUpHealthMissingPermission() {
        return magicLevelUpHealthMissingPermission;
    }

}