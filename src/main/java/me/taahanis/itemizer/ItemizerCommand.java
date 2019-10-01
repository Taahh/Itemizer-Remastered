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

        // naming done, title done, lores done, clearing all done, heads done,

        if (args.length <= 0)
        {
            sender.sendMessage("§c===§6[§bItemizer§6] §aCommands§c===");
            sender.sendMessage("   §b/itemizer name <name> - §cSets the name of your item");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer title <title> - §cSet the title of your book (Must be signed)");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer author <author> - §cSets the title of your book (Must be signed)");
            sender.sendMessage("");
            sender.sendMessage("   §b/itemizer lore help - §cProvides the options for the lore subcommand.");
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
            String name = StringUtils.join(args, " ", 1, args.length);

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
            String title = StringUtils.join(args, " ", 1, args.length);

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


            BookMeta meta = (BookMeta) itemMeta;
            assert meta != null;
            meta.setTitle(ChatColor.translateAlternateColorCodes('&', title));


            item.setItemMeta(meta);

            player.sendMessage("§6[§bItemizer§6] §aYour book has been titled as '" + ChatColor.translateAlternateColorCodes('&', title) + "'");
            return true;
        }

        if (args[0].equalsIgnoreCase("author"))
        {


            if (item.getType() != Material.WRITTEN_BOOK)
            {
                player.sendMessage("§6[§bItemizer§6] §aYou are not holding a book in your hand. \nIf you are holding a book, it must be written.");
                return true;
            }
            if (args.length < 2)// /argslength0 author1 name2 /args args0 args1 args2etcetc
            {
                player.sendMessage("§6[§bItemizer§6] §aYou did not specify an author.");
                return true;
            }
            String authorName = StringUtils.join(args, " ", 1, args.length);

            BookMeta meta = (BookMeta) itemMeta;
            assert meta != null;
            meta.setAuthor(ChatColor.translateAlternateColorCodes('&', authorName));
            item.setItemMeta(meta);
            player.sendMessage("§6[§bItemizer§6] §aYour book's author has been set to '" + ChatColor.translateAlternateColorCodes('&', authorName) + "'");
            return true;
        }

        if (args[0].equalsIgnoreCase("lore"))
        {
            if (args.length <= 1)
            {
                player.sendMessage("§6[§bItemizer§6] §aYou did not a subcommand: add, set, remove, clear, help");
                return true;
            }

            if (args[1].equalsIgnoreCase("help"))
            {
                player.sendMessage("§c===§6[§bItemizer§6] §aLore Commands§c===");
                player.sendMessage("");
                player.sendMessage("§b/itemizer lore add <line> - §cAdds a line to your lore.");
                player.sendMessage("");
                player.sendMessage("§b/itemizer lore set <lore> - §cStarts off your item's list of lores.");
                player.sendMessage("");
                player.sendMessage("§b/itemizer lore clear - §cClears your lore(s).");
                player.sendMessage("");
                player.sendMessage("§b/itemizer lore remove <line> - §cClears a line of your lore.");
                return true;
            }
            if (args[1].equalsIgnoreCase("set"))
            {
                String lore = StringUtils.join(args, " ", 2, args.length);

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

            if (args[1].equalsIgnoreCase("add"))
            {
                String lore = StringUtils.join(args, " ", 2, args.length);

                assert itemMeta != null;

                List<String> lores;

                if (itemMeta.hasLore())
                {
                    lores = itemMeta.getLore();
                } else {
                    player.sendMessage("§6[§bItemizer§6] §aYou currently do not have any lores, to add one, please use /itemizer lore set <lore>, and then proceed.");
                    return true;
                }
                if (args.length < 3)
                {
                    player.sendMessage("§6[§bItemizer§6] §aYou did not specify a lore to add.");
                    return true;
                }
                assert lores != null;
                lores.add(ChatColor.translateAlternateColorCodes('&', lore));
                itemMeta.setLore(lores);
                item.setItemMeta(itemMeta);
                player.sendMessage("§6[§bItemizer§6] §aYou have added the following lore to your item: '" + ChatColor.translateAlternateColorCodes('&', lore) + "'");
                return true;
            }
            if (args[1].equalsIgnoreCase("remove"))
            {
                assert itemMeta != null;

                List<String> lores;

                if (itemMeta.hasLore())
                {
                    lores = itemMeta.getLore();
                } else {
                    player.sendMessage("§6[§bItemizer§6] §aYou currently do not have any lores, to add one, please use /itemizer lore set <lore>, and then proceed.");
                    return true;
                }

                if (args.length < 3) //itemizer0 lore1 remove2 clear3
                {
                    player.sendMessage("§6[§bItemizer§6] §aYou did not specify a line to remove.");
                    return true;
                }
                int lineNumber = Integer.parseInt(args[2]);
                try {
                    assert lores != null;
                    lores.remove(lineNumber);
                } catch (Exception e)
                {
                    player.sendMessage("§6[§bItemizer§6] §aPlease remember that every lore's line starts with the number 0, not 1. Example:");
                    player.sendMessage("§c1st Lore - §bLine 0");
                    player.sendMessage("§c2nd Lore - §bLine 1");
                    return true;
                }
                itemMeta.setLore(lores);
                item.setItemMeta(itemMeta);
                player.sendMessage("§6[§bItemizer§6] §aYou have successfully removed that line.");
                return true;
            }
            if (args[1].equalsIgnoreCase("clear"))
            {
                assert itemMeta != null;
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
            //noinspection deprecation
            playerHead.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
            item.setItemMeta(playerHead);
            player.sendMessage("§6[§bItemizer§6] §aSuccessfully set player skull owner to: '§c" + playerName + "§a'");
            return true;
        }
        return false;
    }
}
