package net.portalsam.magichealth.command.health;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CommandMagicSetHealth implements CommandExecutor {

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1) {

            sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " Not enough arguments, either specify a health to set yourself at, or supply a player and a health.");
            return true;

        } else if(args.length < 2) {

            if(sender instanceof Player) {

                try {

                    float newHealth = Float.parseFloat(args[0]);

                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + " Attempting to set your health to " + newHealth);
                    ((Player) sender).setHealth(newHealth);

                    log.info(String.format("[%s] " + ((Player) sender).getDisplayName() + " set their health to " + newHealth, magicHealth.getDescription().getName()));

                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " " + args[0] + " is not a valid number.");
                    return true;

                }

            } else {

                sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " You must specify a player to run this on.");
                return true;

            }

        } else {

            Player targetPlayer;

            if(Bukkit.getPlayer(args[0]) != null) {

                targetPlayer = Bukkit.getPlayer(args[0]);

                try {

                    float newHealth = Float.parseFloat(args[1]);

                    assert targetPlayer != null;
                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + " Attempting to set " + targetPlayer.getDisplayName() + "'s health to " + newHealth);
                    targetPlayer.setHealth(newHealth);

                    log.info(String.format("[%s] " + ((Player) sender).getDisplayName() + " set " + targetPlayer.getDisplayName() + "'s health to " + newHealth, magicHealth.getDescription().getName()));

                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " " + args[1] + " is not a valid number.");
                    return true;

                }

            } else {

                sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " " + args[0] + " is not a valid player.");
                return true;

            }

        }

    }

}
