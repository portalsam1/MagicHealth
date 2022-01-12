package net.portalsam.magichealth.event;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class MagicEntitySpawnEvent implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e) {

        // If spawners aren't enabled for drops and the entity did come from a spawner.
        if(!MagicHealthConfig.spawnersAreEnabled()) {
            if(e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {

                // Set a persistent tag on the entity to disable their drops later when killed.
                Entity entity = e.getEntity();
                PersistentDataContainer persistentSpawnData = entity.getPersistentDataContainer();
                persistentSpawnData.set(new NamespacedKey(MagicHealth.getMagicHealthInstance(), "disable_magicDrops"), PersistentDataType.STRING, "true");

            }
        }

    }

}
