package net.portalsam.magichealth.event;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.item.MagicHealthItems;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class MagicEntityDeathEvent implements Listener {

    public static List<EntityType> commonMobList = new ArrayList<>();
    public static List<EntityType> uncommonMobList = new ArrayList<>();
    public static List<EntityType> bossMobList = new ArrayList<>();

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();

    static {

        for(String entity : MagicHealthConfig.getCommonMobList()) {

            try {

                Class<? extends Entity> entityClass = EntityType.valueOf(entity.toUpperCase()).getEntityClass();
                assert entityClass != null;

                if(LivingEntity.class.isAssignableFrom(entityClass)) {
                    commonMobList.add(EntityType.valueOf(entity.toUpperCase()));
                } else {
                    throw new IllegalArgumentException();
                }

            } catch(IllegalArgumentException ignored) {

                log.info(String.format("[%s] Entity '" + entity + "' was not recognized in config.yml, did you type the EntityType correctly, or is it not a LivingEntity?", magicHealth.getDescription().getName()));

            }

        }

        for(String entity : MagicHealthConfig.getUncommonMobList()) {

            try {

                Class<? extends Entity> entityClass = EntityType.valueOf(entity.toUpperCase()).getEntityClass();
                assert entityClass != null;

                if(LivingEntity.class.isAssignableFrom(entityClass)) {
                    uncommonMobList.add(EntityType.valueOf(entity.toUpperCase()));
                } else {
                    throw new IllegalArgumentException();
                }

            } catch(IllegalArgumentException ignored) {

                log.info(String.format("[%s] Entity '" + entity + "' was not recognized in config.yml, did you type the EntityType correctly, or is it not a LivingEntity?", magicHealth.getDescription().getName()));

            }

        }

        for(String entity : MagicHealthConfig.getBossMobList()) {

            try {

                Class<? extends Entity> entityClass = EntityType.valueOf(entity.toUpperCase()).getEntityClass();
                assert entityClass != null;

                if(LivingEntity.class.isAssignableFrom(entityClass)) {
                    bossMobList.add(EntityType.valueOf(entity.toUpperCase()));
                } else {
                    throw new IllegalArgumentException();
                }

            } catch(IllegalArgumentException ignored) {

                log.info(String.format("[%s] Entity '" + entity + "' was not recognized in config.yml, did you type the EntityType correctly, or is it not a LivingEntity?", magicHealth.getDescription().getName()));

            }

        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if(MagicHealthConfig.mobDropsAreEnabled()) {
            if(event.getEntity().getKiller() != null) {

                LivingEntity entity = event.getEntity();

                // Generate a number to determine drops.
                float dropChance = (float)ThreadLocalRandom.current().nextDouble(0D, 100D);

                // List of boss mobs.

                for(EntityType entityType : bossMobList) {

                    if(entityType == entity.getType()) {

                        if(dropChance <= MagicHealthConfig.getBossMobDropChance()) {

                            ItemStack drop = MagicHealthItems.HEART_SHARD.getHeartShardItem();
                            drop.setAmount(ThreadLocalRandom.current().nextInt(MagicHealthConfig.getBossMobDropAmounts()[0], MagicHealthConfig.getBossMobDropAmounts()[1]));

                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), drop);

                        }
                    }
                }

                // List of uncommon mobs.

                for(EntityType entityType : uncommonMobList) {

                    if(entityType == entity.getType()) {

                        if(dropChance <= MagicHealthConfig.getUncommonMobDropChance()) {

                            ItemStack drop = MagicHealthItems.HEART_DUST.getHeartDustItem();
                            drop.setAmount(ThreadLocalRandom.current().nextInt(MagicHealthConfig.getUncommonMobDropAmounts()[0], MagicHealthConfig.getUncommonMobDropAmounts()[1]));

                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), drop);

                        }
                    }
                }

                // List of common mobs.

                for(EntityType entityType : commonMobList) {

                    if(entityType == entity.getType()) {

                        if(dropChance <= MagicHealthConfig.getCommonMobDropChance()) {

                            ItemStack drop = MagicHealthItems.HEART_DUST.getHeartDustItem();
                            drop.setAmount(ThreadLocalRandom.current().nextInt(MagicHealthConfig.getCommonMobDropAmounts()[0], MagicHealthConfig.getCommonMobDropAmounts()[1]));

                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), drop);

                        }
                    }
                }
            }
        }

    }
}