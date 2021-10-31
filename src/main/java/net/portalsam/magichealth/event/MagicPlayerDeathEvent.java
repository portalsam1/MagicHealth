package net.portalsam.magichealth.event;

import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
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

            // Reset the players health.
            PlayerHealth.setPlayerMaxHealth(player, MagicHealthConfig.getMinimumPlayerHealth(), true);

        }

    }

}