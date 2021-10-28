package net.portalsam.magichealth.event;

import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.item.MagicHealthItems;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class MagicEntityDeathEvent implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if(event.getEntity().getKiller() != null) {

            LivingEntity entity = event.getEntity();

            // Generate a number to determine drops.
            float dropChance = (float)ThreadLocalRandom.current().nextDouble(0D, 100D);

            // List of boss mobs.
            if (entity instanceof EnderDragon || entity instanceof Wither) {
                if(dropChance <= MagicHealthConfig.getBossMobDropChance()) {

                    ItemStack drop = MagicHealthItems.HEART_SHARD.getHeartShardItem();
                    drop.setAmount(ThreadLocalRandom.current().nextInt(MagicHealthConfig.getBossMobDropAmounts()[0], MagicHealthConfig.getBossMobDropAmounts()[1]));

                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), drop);

                }
            }

            // List of uncommon mobs.
            if (entity instanceof ElderGuardian || entity instanceof PiglinBrute || entity instanceof Ravager || entity instanceof Shulker || entity instanceof SkeletonHorse || entity instanceof Vex || entity instanceof Vindicator || entity instanceof WitherSkeleton || entity instanceof Giant) {
                if(dropChance <= MagicHealthConfig.getUncommonMobDropChance()) {

                    ItemStack drop = MagicHealthItems.HEART_DUST.getHeartDustItem();
                    drop.setAmount(ThreadLocalRandom.current().nextInt(MagicHealthConfig.getUncommonMobDropAmounts()[0], MagicHealthConfig.getUncommonMobDropAmounts()[1]));

                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), drop);

                }
            }

            // L o n g list of common mobs.
            if(entity instanceof Strider || entity instanceof Bee || entity instanceof Enderman || entity instanceof IronGolem || entity instanceof Panda || entity instanceof Piglin || entity instanceof PolarBear || entity instanceof Spider || entity instanceof Blaze || entity instanceof Creeper || entity instanceof Endermite || entity instanceof Evoker || entity instanceof Ghast || entity instanceof Guardian || entity instanceof Hoglin || entity instanceof Silverfish || entity instanceof Skeleton || entity instanceof Slime || entity instanceof Stray || entity instanceof Witch || entity instanceof Zoglin || entity instanceof Zombie || entity instanceof Pillager) {
                if(dropChance <= MagicHealthConfig.getCommonMobDropChance()) {

                    ItemStack drop = MagicHealthItems.HEART_DUST.getHeartDustItem();
                    drop.setAmount(ThreadLocalRandom.current().nextInt(MagicHealthConfig.getCommonMobDropAmounts()[0], MagicHealthConfig.getCommonMobDropAmounts()[1]));

                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), drop);

                }
            }

        }

    }

}
