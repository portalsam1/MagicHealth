package net.portalsam.magichealth.command.health;

import net.portalsam.magichealth.database.PlayerHealth;
import net.portalsam.magichealth.database.PlayerHealthSortOperation;
import net.portalsam.magichealth.database.PluginLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandMagicHealthTop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(PluginLanguage.getMagicHealthTopTitle());PlayerHealth.getListOfPlayersHealth(PlayerHealthSortOperation.GREATEST, 5).forEach(sender::sendMessage);
        return true;

    }

}
