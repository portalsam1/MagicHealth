package net.portalsam.magichealth.command.health;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.logging.Logger;

public class CommandMagicSetMaxHealth implements CommandExecutor {

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1) {

            sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxInvalidUsage()));
            return true;

        } else if(args.length < 2) {

            if(sender instanceof Player) {

                try {

                    float newHealth = Float.parseFloat(args[0]);

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxOwnHealth()).replace("{NEWHEALTH}", newHealth + ""));
                    PlayerHealth.setPlayerMaximumHealth((Player)sender, newHealth, true);

                    log.info(String.format("[%s] " + ((Player) sender).getDisplayName() + " set their maximum health to " + newHealth, magicHealth.getDescription().getName()));

                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxIllegalNumber()).replace("{INPUT}", args[0]));
                    return true;

                }

            } else {

                sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxNonPlayer()));
                return true;

            }

        } else {

            Player targetPlayer;

            if(Bukkit.getPlayer(args[0]) != null) {

                targetPlayer = Bukkit.getPlayer(args[0]);

                try {

                    float newHealth = Float.parseFloat(args[1]);

                    assert targetPlayer != null;
                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxOtherHealth()).replace("{PLAYER}", targetPlayer.getDisplayName()).replace("{NEWHEALTH}", newHealth + ""));
                    PlayerHealth.setPlayerMaximumHealth(targetPlayer, newHealth, true);

                    log.info(String.format("[%s] console set " + targetPlayer.getDisplayName() + "'s maximum health to " + newHealth, magicHealth.getDescription().getName()));

                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxIllegalNumber()).replace("{INPUT}", args[1]));
                    return true;

                }

            } else {

                // Deprecated for irrelevant reasons, has been deprecated since 1.8 so I assume safe to use.
                String playerUUID = Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString();

                try {

                    float newHealth = Float.parseFloat(args[1]);

                    if (PlayerHealth.getPlayerHealthConfig().contains("players." + playerUUID)) {
                        PlayerHealth.setPlayerMaximumHealth(Objects.requireNonNull(Bukkit.getOfflinePlayer(playerUUID).getPlayer()), newHealth, true);
                        sender.sendMessage(PluginLanguage.getMagicSetMaxOtherHealth().replace("{PLAYER}", Objects.requireNonNull(Bukkit.getOfflinePlayer(playerUUID).getPlayer()).getDisplayName()));
                        log.info(String.format("[%s] Set offline player " + args[0] + "'s maximum health to " + newHealth, magicHealth.getDescription().getName()));
                    } else {
                        sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxIllegalPlayer()).replace("{INPUT}", args[1]));
                    }
                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetMaxIllegalNumber()).replace("{INPUT}", args[1]));
                    return true;

                }

            }
        }

    }

}