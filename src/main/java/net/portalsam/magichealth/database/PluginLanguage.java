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
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    private static String magicSetHealthInvalidUsage = "{PREFIX} Not enough arguments, either specify a health to set yourself to, or supply a player and a health.";
    private static String magicSetHealthOwnHealth = "{PREFIX} Attempting to set your health to '{NEWHEALTH}'";
    private static String magicSetHealthIllegalNumber = "{PREFIX} &c{INPUT} is not a valid number.";
    private static String magicSetHealthNonPlayer = "{PREFIX} &cYou must specify a player to run this command on.";
    private static String magicSetHealthOtherHealth = "{PREFIX} Attempting to set {PLAYER}'s health to {NEWHEALTH}";
    private static String magicSetHealthIllegalPlayer = "{PREFIX} &c{INPUT} is not a valid player.";

    private static String magicLevelUpHealthOwn = "{PREFIX} You have levelled up your health from &e{OLDHEALTH} &fto &e{NEWHEALTH}&f!";
    private static String magicLevelUpHealthMaximum = "{PREFIX} You are already at your allotted maximum health!";
    private static String magicLevelUpHealthNonPlayer = "{PREFIX} &cOnly a player can run this without arguments, please specify a player.";
    private static String magicLevelUpHealthOther = "{PREFIX} Levelled up {PLAYER}&f's health from &e{OLDHEALTH} &fto &e{NEWHEALTH}&f!";
    private static String magicLevelUpHealthOtherNotify = "{PREFIX} Your health has levelled up from &e{OLDHEALTH} &fto &e{NEWHEALTH}&f!";
    private static String magicLevelUpHealthOtherMaximum = "{PREFIX} This player is already at their maximum health!";
    private static String magicLevelUpHealthIllegalPlayer = "{PREFIX} &c{INPUT} is not a valid player.";
    private static String magicLevelUpHealthMissingPermission = "{PREFIX} &cYou do not have permissions to use arguments on this command.";

    private static String magicHealthTopTitle = "&7--- &fPlayers with the most health. &7---";

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

        } catch(IOException | InvalidConfigurationException ignored) {
            log.severe(String.format("[%s] Unable to load messages.yml.", magicHealth.getDescription().getName()));
        }

        loadConfiguration();

    }

    private static void loadConfiguration() {

        magicHealthPrefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicHealthPrefix")));

        magicHealthSeparator = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicHealthSeparator")));

        heartDustName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartDustName")));
        heartShardName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartShardName")));
        heartCrystalName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartCrystalName")));
        heartDrainAmuletName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartDrainAmuletName")));

        heartDustLore = translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getStringList("heartDustLore")));
        heartShardLore = translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getStringList("heartShardLore")));
        heartCrystalLore = translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getStringList("heartCrystalLore")));
        heartDrainAmuletLore = translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getStringList("heartDrainAmuletLore")));

        heartCrystalAlreadyMax = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartCrystalAlreadyMax")));
        heartCrystalDisabled = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartCrystalDisabled"))) ;

        heartDrainAmuletAlreadyMin = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartDrainAmuletAlreadyMin")));
        heartDrainAmuletDisabled = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("heartDrainAmuletDisabled")));

        playerLoseAllHearts = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("playerLoseAllHearts")));

        magicGiveItemListSubcommandName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicGiveItemListSubcommandName")));
        magicGiveItemList = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicGiveItemList")));
        magicGiveItemGiven = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicGiveItemGiven")));
        magicGiveItemInvalid = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicGiveItemInvalid")));
        magicGiveItemNonPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicGiveItemNonPlayer")));
        magicGiveItemInvalidUsage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicGiveItemInvalidUsage")));

        magicSetMaxInvalidUsage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetMaxInvalidUsage")));
        magicSetMaxOwnHealth = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetMaxOwnHealth")));
        magicSetMaxIllegalNumber = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetMaxIllegalNumber")));
        magicSetMaxNonPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetMaxNonPlayer")));
        magicSetMaxOtherHealth = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetMaxOtherHealth")));
        magicSetMaxIllegalPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetMaxIllegalPlayer")));

        magicSetHealthInvalidUsage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetHealthInvalidUsage")));
        magicSetHealthOwnHealth = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetHealthOwnHealth")));
        magicSetHealthIllegalNumber = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetHealthIllegalNumber")));
        magicSetHealthNonPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetHealthNonPlayer")));
        magicSetHealthOtherHealth = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetHealthOtherHealth")));
        magicSetHealthIllegalPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicSetHealthIllegalPlayer")));

        magicLevelUpHealthOwn = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthOwn")));
        magicLevelUpHealthMaximum = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthMaximum")));
        magicLevelUpHealthNonPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthNonPlayer")));
        magicLevelUpHealthOther = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthOther")));
        magicLevelUpHealthOtherNotify = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthOtherNotify")));
        magicLevelUpHealthOtherMaximum = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthOtherMaximum")));
        magicLevelUpHealthIllegalPlayer = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthIllegalPlayer")));
        magicLevelUpHealthMissingPermission = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicLevelUpHealthMissingPermission")));

        magicHealthTopTitle = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(playerLanguageConfig.getString("magicHealthTopTitle")));

    }

    /*/ Utility. /*/

    public static String filterDefault(String text) {
        return text.replace("{PREFIX}", getMagicHealthPrefix());
    }

    public static String filterItems(String text) {
        return text.replace("{HEARTDUST}", getHeartDustName()).replace("{HEARTSHARD}", getHeartShardName()).replace("{HEARTCRYSTAL}", getHeartCrystalName()).replace("{HEARTDRAINAMULET}", getHeartDrainAmuletName());
    }

    public static List<String> filterItems(List<String> text) {
        return text.stream().map(PluginLanguage::filterItems).collect(Collectors.toList());
    }

    public static List<String> translateAlternateColorCodes(char altColorChar, List<String> textToTranslate) {
        return textToTranslate.stream().map(s -> ChatColor.translateAlternateColorCodes(altColorChar, s)).collect(Collectors.toList());
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

    public static String getMagicSetHealthInvalidUsage() {
        return magicSetHealthInvalidUsage;
    }

    public static String getMagicSetHealthOwnHealth() {
        return magicSetHealthOwnHealth;
    }

    public static String getMagicSetHealthIllegalNumber() {
        return magicSetHealthIllegalNumber;
    }

    public static String getMagicSetHealthNonPlayer() {
        return magicSetHealthNonPlayer;
    }

    public static String getMagicSetHealthOtherHealth() {
        return magicSetHealthOtherHealth;
    }

    public static String getMagicSetHealthIllegalPlayer() {
        return magicSetHealthIllegalPlayer;
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

    public static String getMagicHealthTopTitle() {
        return magicHealthTopTitle;
    }

}