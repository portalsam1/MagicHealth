package net.portalsam.magichealth.event;

import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class MagicPlayerDeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        // If the evil setting is set in the config. :)
        if(MagicHealthConfig.isResettingPlayersHealthOnDeath()) {

            Player player = event.getEntity();

            if(PlayerHealth.getPlayerMaximumHealthFromDatabase(player) > MagicHealthConfig.getDefaultPlayerHealth()) {

                // Reset the players health.
                PlayerHealth.setPlayerMaximumHealth(player, MagicHealthConfig.getDefaultPlayerHealth(), true);

                event.getEntity().sendMessage(PluginLanguage.filterDefault(PluginLanguage.getPlayerLoseAllHearts()));

            }

        }

    }

}