package net.portalsam.magichealth.command.health;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.Bukkit;
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

            sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetInvalidUsage()));
            return true;

        } else if(args.length < 2) {

            if(sender instanceof Player) {

                try {

                    float newHealth = Float.parseFloat(args[0]);

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetOwnHealth()).replace("{NEWHEALTH}", newHealth + ""));
                    ((Player) sender).setHealth(newHealth);

                    log.info(String.format("[%s] " + ((Player) sender).getDisplayName() + " set their health to " + newHealth, magicHealth.getDescription().getName()));

                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetIllegalNumber()).replace("{INPUT}", args[0]));
                    return true;

                }

            } else {

                sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetNonPlayer()));
                return true;

            }

        } else {

            Player targetPlayer;

            if(Bukkit.getPlayer(args[0]) != null) {

                targetPlayer = Bukkit.getPlayer(args[0]);

                try {

                    float newHealth = Float.parseFloat(args[1]);

                    assert targetPlayer != null;
                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetOtherHealth()).replace("{PLAYER}", targetPlayer.getDisplayName()).replace("{NEWHEALTH}", newHealth + ""));
                    targetPlayer.setHealth(newHealth);

                    log.info(String.format("[%s] " + ((Player) sender).getDisplayName() + " set " + targetPlayer.getDisplayName() + "'s health to " + newHealth, magicHealth.getDescription().getName()));

                    return true;

                } catch(NumberFormatException ignored) {

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetIllegalNumber()).replace("{INPUT}", args[1]));
                    return true;

                }

            } else {

                sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicSetIllegalPlayer()).replace("{INPUT}", args[0]));
                return true;

            }

        }

    }

}
