package net.portalsam.magichealth.command.health.tabcomplete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandMagicLevelUpHealthTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> onlinePlayers = new ArrayList<>();

        for(Player player : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(player.getName());
        }

        // Only show tab complete arguments to the sender if they have the permission to use them.
        switch (args.length) {
            case 1:
                if(sender.hasPermission("magichealth.sethealth")) return onlinePlayers;
                else return null;
            case 2:
                if(sender.hasPermission("magichealth.sethealth")) return new ArrayList<>(Arrays.asList("true", "false"));
                else return null;
            default: return null;
        }

    }

}
