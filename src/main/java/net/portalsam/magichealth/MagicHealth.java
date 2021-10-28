package net.portalsam.magichealth;

import net.portalsam.magichealth.command.health.CommandMagicSetMaxHealth;
import net.portalsam.magichealth.command.health.tabcomplete.CommandMagicSetMaxHealthTabComplete;
import net.portalsam.magichealth.command.item.tabcomplete.CommandMagicGiveItemTabComplete;
import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.event.MagicEntityDeathEvent;
import net.portalsam.magichealth.event.MagicPlayerInteractEvent;
import net.portalsam.magichealth.event.MagicPlayerRespawnEvent;
import net.portalsam.magichealth.command.item.CommandMagicGiveItem;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class MagicHealth extends JavaPlugin {

    /*/ Plugin variables. /*/

    public static final Logger LOGGER = Logger.getLogger("Minecraft");
    public static MagicHealth magicHealthInstance = null;

    @Override
    public void onEnable() {

        magicHealthInstance = this;

        MagicHealthConfig.initializeConfiguration();
        PlayerHealth.initializePlayerConfig();

        registerEvents();
        registerCommands();

        LOGGER.info(String.format("[%s] is Enabled.", getDescription().getName()));

    }

    @Override
    public void onDisable() {

        saveConfig();
        PlayerHealth.saveConfig();

        LOGGER.info(String.format("[%s] is Disabled.", getDescription().getName()));

    }

    /*/ Registry. /*/

    private void registerEvents() {

        this.getServer().getPluginManager().registerEvents(new MagicEntityDeathEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MagicPlayerRespawnEvent(), this);
        this.getServer().getPluginManager().registerEvents(new MagicPlayerInteractEvent(), this);

    }

    private void registerCommands() {

        Objects.requireNonNull(this.getCommand("magichealthgive")).setExecutor(new CommandMagicGiveItem());
        Objects.requireNonNull(this.getCommand("magichealthsetmax")).setExecutor(new CommandMagicSetMaxHealth());

        Objects.requireNonNull(this.getCommand("magichealthgive")).setTabCompleter(new CommandMagicGiveItemTabComplete());
        Objects.requireNonNull(this.getCommand("magichealthsetmax")).setTabCompleter(new CommandMagicSetMaxHealthTabComplete());

    }

    /*/ Getters and Setters. /*/

    public static Logger getMagicHealthLogger() {
        return LOGGER;
    }

    public static MagicHealth getMagicHealthInstance() {
        return magicHealthInstance;
    }

}
