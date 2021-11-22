package net.portalsam.magichealth.event;

import net.portalsam.magichealth.config.MagicHealthConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MagicPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if(MagicHealthConfig.isUsingCustomModels()) {
            event.getPlayer().setResourcePack("https://portalsam.net/wp-content/uploads/2021/11/MagicHealth-1.zip");
        }
    }

}
