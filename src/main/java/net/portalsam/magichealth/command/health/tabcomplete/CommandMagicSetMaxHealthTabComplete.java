package net.portalsam.magichealth.command.health.tabcomplete;

import net.portalsam.magichealth.config.MagicHealthConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandMagicSetMaxHealthTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> onlinePlayers = new ArrayList<>();

        for(Player player : Bukkit.getOnlinePlayers()) {
            onlinePlayers.add(player.getName());
        }

        switch (args.length) {
            case 1: return onlinePlayers;
            case 2: return new ArrayList<>(Arrays.asList(MagicHealthConfig.getMinimumPlayerHealth() + "", MagicHealthConfig.getDefaultPlayerHealth() + "", MagicHealthConfig.getMaximumPlayerHealth() + ""));
            default: return null;
        }

    }

}
