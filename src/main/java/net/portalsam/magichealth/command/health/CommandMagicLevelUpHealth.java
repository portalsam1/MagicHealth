package net.portalsam.magichealth.command.health;

import net.portalsam.magichealth.config.MagicHealthConfig;
import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.util.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMagicLevelUpHealth implements CommandExecutor {

    // This command mainly relies on external permissions added to the player following the permission template in plugin.yml.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1) {

            if(sender instanceof Player) {

                Player player = (Player)sender;
                float playerCurrentMaximumHealth = PlayerHealth.getPlayerMaximumHealth(player);

                // Loop through a list of numbered permissions and if a player has one with a greater value than their
                // current maximum health, update their maximum health to the one in the permission.
                boolean canLevelUpHealth = false;
                float levelUpHealthTo = MagicHealthConfig.getDefaultPlayerHealth();
                for(int i = 0; i < MagicHealthConfig.getMaximumPlayerHealth(); i++) {

                    // If player has a greater health permission.
                    if(player.hasPermission("magichealth.rankuphealth." + i)) {
                        if(i > playerCurrentMaximumHealth) {
                            canLevelUpHealth = true;
                            levelUpHealthTo = i;
                        }
                    }

                }

                if(canLevelUpHealth) {

                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + " You have levelled up your health from " + ChatColor.YELLOW + playerCurrentMaximumHealth + ChatColor.WHITE + " to " + ChatColor.YELLOW + levelUpHealthTo + ChatColor.WHITE + " !");
                    PlayerHealth.setPlayerMaximumHealth(player, levelUpHealthTo, true);

                } else {

                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " You are already at your allotted maximum health!");

                }

            } else {

                sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " Only a player can run this without arguments, please specify a player.");

            }

        } else {

            // If the sender has the permission for set health continue to check for sub commands, else refuse arguments to this command.
            if(sender.hasPermission("magichealth.sethealth")) {

                Player targetPlayer;
                boolean notify = true;

                if(Bukkit.getPlayer(args[0]) != null) {

                    targetPlayer = Bukkit.getPlayer(args[0]);

                    // Allow the sender to use an additional argument to specify if they would like to notify the player of their change in health.
                    if(args.length > 1) {

                        try {
                            notify = Boolean.parseBoolean(args[1]);
                        } catch(IllegalArgumentException ignored) { }

                    }

                    assert targetPlayer != null;
                    float playerCurrentMaximumHealth = PlayerHealth.getPlayerMaximumHealth(targetPlayer);

                    // Loop through a list of numbered permissions and if a player has one with a greater value than their
                    // current maximum health, update their maximum health to the one in the permission.
                    boolean canLevelUpHealth = false;
                    float levelUpHealthTo = MagicHealthConfig.getDefaultPlayerHealth();
                    for(int i = 0; i < MagicHealthConfig.getMaximumPlayerHealth(); i++) {

                        // If player has a greater health permission.
                        if(targetPlayer.hasPermission("magichealth.rankuphealth." + i)) {
                            if(i > playerCurrentMaximumHealth) {
                                canLevelUpHealth = true;
                                levelUpHealthTo = i;
                            }
                        }

                    }

                    if(canLevelUpHealth) {

                        if (notify) targetPlayer.sendMessage(Constants.MAGIC_HEALTH_PREFIX + " Your health has levelled up from " + ChatColor.YELLOW + playerCurrentMaximumHealth + ChatColor.WHITE + " to " + ChatColor.YELLOW + levelUpHealthTo + ChatColor.WHITE + " !");

                        PlayerHealth.setPlayerMaximumHealth(targetPlayer, levelUpHealthTo, true);

                    } else {

                        sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " This player is already at their maximum health!");

                    }

                } else {

                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " " + args[0] + " is not a valid player.");

                }

            } else {

                sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + " You do not have permissions to use arguments on this command.");

            }

        }
        return true;

    }

}