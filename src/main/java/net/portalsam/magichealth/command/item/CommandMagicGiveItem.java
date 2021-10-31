package net.portalsam.magichealth.command.item;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.item.MagicHealthItems;
import net.portalsam.magichealth.util.Constants;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.logging.Logger;

public class CommandMagicGiveItem implements CommandExecutor {

    private static final MagicHealth magicHealth = MagicHealth.getMagicHealthInstance();
    private static final Logger log = MagicHealth.getMagicHealthLogger();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            if(args.length > 0) {

                // Display a list of items if the player requests.
                if(args[0].equalsIgnoreCase("list")) {

                    // Add the display names of all itemStacks and replace spaces in the items name with an underscore, then print that as a list to the player.
                    StringBuilder stringBuilder = new StringBuilder();
                    for(ItemStack item : MagicHealthItems.MAGIC_HEALTH_ITEMS) {
                        stringBuilder.append(Objects.requireNonNull(item.getItemMeta()).getDisplayName().replace(" ",  "_"));
                        if(MagicHealthItems.MAGIC_HEALTH_ITEMS.get(MagicHealthItems.MAGIC_HEALTH_ITEMS.size() - 1) != item) stringBuilder.append(ChatColor.WHITE).append(", ");
                    }
                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + " List of available items: " + stringBuilder);
                    return true;

                }

                // Search for the item the player specified in MAGIC_HEALTH_ITEMS, and if it is found set itemToGive to that item.
                ItemStack itemToGive = null;
                for(ItemStack item : MagicHealthItems.MAGIC_HEALTH_ITEMS) {
                    if(ChatColor.stripColor(Objects.requireNonNull(item.getItemMeta()).getDisplayName()).equalsIgnoreCase(args[0].replace("_", " ")))
                    {
                        itemToGive = item;
                    }
                }

                // If the item is not null give it to the player, else throw an error and return.
                if(itemToGive != null) {

                    Player player = (Player) sender;

                    try {
                        if(args[1] != null) {
                            try {
                                itemToGive.setAmount(Integer.parseInt(args[1]));
                            } catch (NumberFormatException ignored) {
                                itemToGive.setAmount(1);
                            }
                        } else itemToGive.setAmount(1);
                    } catch (IndexOutOfBoundsException ignored) {
                        itemToGive.setAmount(1);
                    }

                    player.getInventory().addItem(itemToGive);
                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + " Giving " + "[" + ChatColor.LIGHT_PURPLE + "x" + itemToGive.getAmount() + ChatColor.WHITE + "]" + " of item " + itemToGive.getItemMeta().getDisplayName());
                    log.info(String.format("[%s] Giving " + "[" + ChatColor.LIGHT_PURPLE + "x" + itemToGive.getAmount() + ChatColor.WHITE + "]" + " of item " + itemToGive.getItemMeta().getDisplayName() + ChatColor.WHITE + " to player " + ((Player) sender).getDisplayName(), magicHealth.getDescription().getName()));
                    return true;

                } else {
                    sender.sendMessage(Constants.MAGIC_HEALTH_PREFIX + ChatColor.RED + "Item '" + args[0] + "' does not exist.");
                    return true;
                }

            }

        } else {

            sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            return true;

        }

        sender.sendMessage(ChatColor.RED + "Invalid usage! Use argument 'list' for a list of items or supply a valid item name.");
        return true;

    }

}
