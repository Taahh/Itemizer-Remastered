package me.taahanis.itemizer;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemizerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        if (args.length <= 0)
        {
            sender.sendMessage("§c===§6[§bItemizer§6] §aCommands§c===");
            sender.sendMessage("   §b/itemizer name <name> - §cSets the name of your item");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer title <title> - §cSet the title of your book (Must be signed)");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer lore <set | clear> [lore] - §cSet or clear the lore of your item.");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer clearall - §cClears all item meta of your held item.");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer head <player name> - §cChange a player head into a player's head");
            return true;
        }
        if (!(sender instanceof Player))
        {
            sender.sendMessage("§6[§bItemizer§6] §aYou must be a player to run this command.");
            return true;
        }

        Player player = (Player) sender;

        if (player.getInventory().getItemInMainHand().getType() == Material.AIR)
        {
            player.sendMessage("§6[§bItemizer§6] §aYou're not holding an item.");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (args[0].equalsIgnoreCase("name"))
        {
            String name = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");

            if (args.length < 2)// /argslength0 argslength1 argslength2
            {
                player.sendMessage("§6[§bItemizer§6] §aYou did not specify a name.");
                return true;
            }
            assert itemMeta != null;
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

            item.setItemMeta(itemMeta);

            player.sendMessage("§6[§bItemizer§6] §aYour item name has been set to '" + ChatColor.translateAlternateColorCodes('&', name) + "'");
            return true;
        }

        if (args[0].equalsIgnoreCase("title"))
        {
            String title = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");

            if (args.length < 2)// /argslength0 argslength1 argslength2
            {
                player.sendMessage("§6[§bItemizer§6] §aYou did not specify a title.");
                return true;
            }

            if (item.getType() != Material.WRITTEN_BOOK)
            {
                player.sendMessage("§6[§bItemizer§6] §aYou are not holding a book in your hand. \nIf you are holding a book, it must be written.");
                return true;
            }


            BookMeta meta = (BookMeta) item.getItemMeta();
            meta.setTitle(ChatColor.translateAlternateColorCodes('&', title));


            item.setItemMeta(meta);

            player.sendMessage("§6[§bItemizer§6] §aYour book has been titled as '" + ChatColor.translateAlternateColorCodes('&', title) + "'");
            return true;
        }

        if (args[0].equalsIgnoreCase("lore"))
        {
            if (args.length <= 1)
            {
                player.sendMessage("§6[§bItemizer§6] §aYou did not a subcommand: add, clear");
                return true;
            }

            if (args[1].equalsIgnoreCase("set"))
            {
                String lore = StringUtils.join(ArrayUtils.subarray(args, 2, args.length), " ");

                if (args.length < 3)// /itemizer lore set lore
                {
                    player.sendMessage("§6[§bItemizer§6] §aYou did not specify a lore.");
                    return true;
                }

                List<String> lores = new ArrayList<String>();
                lores.add(ChatColor.translateAlternateColorCodes('&', lore));
                assert itemMeta != null;
                itemMeta.setLore(lores);
                item.setItemMeta(itemMeta);
                player.sendMessage("§6[§bItemizer§6] §aYour item's lore has been set to '" + ChatColor.translateAlternateColorCodes('&', lore) + "'");
                return true;
            }
            if (args[1].equalsIgnoreCase("clear"))
            {
                itemMeta.setLore(null);
                item.setItemMeta(itemMeta);
                player.sendMessage("§6[§bItemizer§6] §aYour item's lore has been cleared");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("clearall"))
        {
            item.setItemMeta(null);
            player.sendMessage("§6[§bItemizer§6] §aSuccessfully cleared all ItemMeta Data for your item.");
            return true;
        }

        if (args[0].equalsIgnoreCase("head"))
        {

            if (item.getType() != Material.PLAYER_HEAD)
            {
                player.sendMessage("§6[§bItemizer§6] §aYou are not currently holding a player's head.");
                return true;
            }
             //command args0 args1 args2
                                         //command head name
                                         //length0 length1 length2
            if (args.length < 2)
            {
                player.sendMessage("§6[§bItemizer§6] §aYou are not specifying a player name.");
                return true;
            }

            String playerName = args[1];

            SkullMeta playerHead = (SkullMeta) item.getItemMeta();

            assert playerHead != null;
            if (playerHead.hasOwner())
            {
                playerHead.setOwningPlayer(null);
            }
            playerHead.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
            item.setItemMeta(playerHead);
            return true;
        }
        return false;
    }
}
