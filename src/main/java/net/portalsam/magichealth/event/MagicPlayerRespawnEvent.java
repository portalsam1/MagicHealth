package net.portalsam.magichealth.event;

import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class MagicPlayerRespawnEvent implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        if(MagicHealthConfig.isEnforcingPlayerMaximumHealth()) {

            if(player.hasPlayedBefore()) {

                // If the player has played before and the server is enforcing maximum health get their max health from
                // the database and set their max health to it, in practice this should be unnecessary because maximum
                // health is persistent but this adds some extra security if it's changed by anything else.
                PlayerHealth.setPlayerMaxHealth(player, PlayerHealth.getPlayerMaxHealthFromDatabase(player), true);

            } else {

                // If they haven't played before set their maximum health to the default in the config.
                PlayerHealth.setPlayerMaxHealth(player, MagicHealthConfig.getMinimumPlayerHealth(), true);

            }

        }

    }

}