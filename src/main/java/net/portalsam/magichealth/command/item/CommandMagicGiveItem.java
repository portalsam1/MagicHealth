package net.portalsam.magichealth.command.item;

import net.portalsam.magichealth.MagicHealth;
import net.portalsam.magichealth.database.PluginLanguage;
import net.portalsam.magichealth.item.MagicHealthItems;
import org.bukkit.Bukkit;
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

            if(args.length > 0) {

                // Display a list of items if the player requests.
                if(args[0].equalsIgnoreCase(PluginLanguage.getMagicGiveItemListSubcommandName())) {

                    // Add the display names of all itemStacks and replace spaces in the items name with an underscore, then print that as a list to the player.
                    StringBuilder stringBuilder = new StringBuilder();
                    for(ItemStack item : MagicHealthItems.MAGIC_HEALTH_ITEMS) {
                        stringBuilder.append(Objects.requireNonNull(item.getItemMeta()).getDisplayName().replace(" ",  "_"));
                        if(MagicHealthItems.MAGIC_HEALTH_ITEMS.get(MagicHealthItems.MAGIC_HEALTH_ITEMS.size() - 1) != item) stringBuilder.append(ChatColor.WHITE).append(PluginLanguage.getMagicHealthSeparator()).append(" ");
                    }
                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicGiveItemList()).replace("{ITEMLIST}", stringBuilder));
                    return true;

                }

                if(Bukkit.getPlayer(args[0]) != null) {

                    Player targetPlayer = Bukkit.getPlayer(args[0]);

                    ItemStack itemToGive = null;

                    try {
                        // Search for the item the player specified in MAGIC_HEALTH_ITEMS, and if it is found set itemToGive to that item.
                        for(ItemStack item : MagicHealthItems.MAGIC_HEALTH_ITEMS) {
                            if(ChatColor.stripColor(Objects.requireNonNull(item.getItemMeta()).getDisplayName()).equalsIgnoreCase(args[1].replace("_", " ")))
                            {
                                itemToGive = item;
                            }
                        }
                    } catch(IndexOutOfBoundsException ignored) {
                        sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicGiveItemInvalidUsage()).replace("{LISTARGUMENT}", PluginLanguage.getMagicGiveItemListSubcommandName()));
                        return true;
                    }

                    // If the item is not null give it to the player, else throw an error and return.
                    if(itemToGive != null) {

                        try {
                            if(args[2] != null) {
                                try {
                                    itemToGive.setAmount(Integer.parseInt(args[2]));
                                } catch (NumberFormatException ignored) {
                                    itemToGive.setAmount(1);
                                }
                            } else itemToGive.setAmount(1);
                        } catch (IndexOutOfBoundsException ignored) {
                            itemToGive.setAmount(1);
                        }

                        assert targetPlayer != null;
                        targetPlayer.getInventory().addItem(itemToGive);
                        sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicGiveItemGiven()).replace("{ITEMAMOUNT}", itemToGive.getAmount() + "").replace("{ITEMGIVEN}", itemToGive.getItemMeta().getDisplayName()).replace("{PLAYER}", targetPlayer.getDisplayName()));
                        log.info(String.format("[%s] Giving " + "[" + ChatColor.LIGHT_PURPLE + "x" + itemToGive.getAmount() + ChatColor.WHITE + "]" + " of item " + itemToGive.getItemMeta().getDisplayName() + ChatColor.WHITE + " to player " + targetPlayer.getDisplayName(), magicHealth.getDescription().getName()));

                    } else {
                        sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicGiveItemInvalid()).replace("{INPUT}", args[0]));
                    }
                    return true;


                } else {

                    sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicGiveItemNonPlayer()));
                    return true;

                }

            }

        sender.sendMessage(PluginLanguage.filterDefault(PluginLanguage.getMagicGiveItemInvalidUsage()).replace("{LISTARGUMENT}", PluginLanguage.getMagicGiveItemListSubcommandName()));
        return true;

    }

}
