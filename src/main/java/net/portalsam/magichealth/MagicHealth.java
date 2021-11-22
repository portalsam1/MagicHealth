package net.portalsam.magichealth;

import net.portalsam.magichealth.command.health.CommandMagicHealthTop;
import net.portalsam.magichealth.command.health.CommandMagicLevelUpHealth;
import net.portalsam.magichealth.command.health.CommandMagicSetHealth;
import net.portalsam.magichealth.command.health.CommandMagicSetMaxHealth;
import net.portalsam.magichealth.command.health.tabcomplete.CommandMagicLevelUpHealthTabComplete;
import net.portalsam.magichealth.command.health.tabcomplete.CommandMagicSetHealthTabComplete;
import net.portalsam.magichealth.command.health.tabcomplete.CommandMagicSetMaxHealthTabComplete;
import net.portalsam.magichealth.command.item.CommandMagicGiveItem;
import net.portalsam.magichealth.command.item.tabcomplete.CommandMagicGiveItemTabComplete;
import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.database.PluginLanguage;
import net.portalsam.magichealth.event.*;
import net.portalsam.metrics.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class MagicHealth extends JavaPlugin {

    /*/ Plugin variables. /*/

    public static final Logger LOGGER = Logger.getLogger("Minecraft");
    public static MagicHealth magicHealthInstance = null;

    public static final int METRICS_ID = 13259;
    public static Metrics metrics;

    @Override
    public void onEnable() {

        magicHealthInstance = this;
        metrics = new Metrics(this, METRICS_ID);

        MagicHealthConfig.initializeConfiguration();
        PluginLanguage.initializeLanguageConfiguration();
        PlayerHealth.initializePlayerConfiguration();

        registerEvents();
        registerCommands();

        LOGGER.info(String.format("[%s] is Enabled.", getDescription().getName()));

    }

    @Override
    public void onDisable() {

        PlayerHealth.saveConfiguration();

        LOGGER.info(String.format("[%s] is Disabled.", getDescription().getName()));

    }

    /*/ Registry. /*/

    private void registerEvents() {

        this.getServer().getPluginManager().registerEvents(new MagicEntityDeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MagicPlayerRespawnEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MagicPlayerInteractEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MagicPlayerDeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MagicPlayerJoinEvent(), this);

    }

    private void registerCommands() {

        Objects.requireNonNull(this.getCommand("magichealthgive")).setExecutor(new CommandMagicGiveItem());
        Objects.requireNonNull(this.getCommand("magichealthsetmax")).setExecutor(new CommandMagicSetMaxHealth());
        Objects.requireNonNull(this.getCommand("magichealthset")).setExecutor(new CommandMagicSetHealth());
        Objects.requireNonNull(this.getCommand("magicleveluphealth")).setExecutor(new CommandMagicLevelUpHealth());
        Objects.requireNonNull(this.getCommand("magichealthtop")).setExecutor(new CommandMagicHealthTop());

        Objects.requireNonNull(this.getCommand("magichealthgive")).setTabCompleter(new CommandMagicGiveItemTabComplete());
        Objects.requireNonNull(this.getCommand("magichealthsetmax")).setTabCompleter(new CommandMagicSetMaxHealthTabComplete());
        Objects.requireNonNull(this.getCommand("magichealthset")).setTabCompleter(new CommandMagicSetHealthTabComplete());
        Objects.requireNonNull(this.getCommand("magicleveluphealth")).setTabCompleter(new CommandMagicLevelUpHealthTabComplete());

    }

    /*/ Getters and Setters. /*/

    public static Logger getMagicHealthLogger() {
        return LOGGER;
    }

    public static MagicHealth getMagicHealthInstance() {
        return magicHealthInstance;
    }

}
