package org.zhaojason.testingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuCommand extends CommandHandler {


    public MenuCommand() {
        super(
                "menu",
                new String[]{"menus"},
                "Menu command",
                "defualt"
        );
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            Inventory inv = Bukkit.createInventory(p,9, ChatColor.translateAlternateColorCodes('&', "&7Custom menu"));
            ItemStack menu_1 = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta menu_1_meta = menu_1.getItemMeta();
            menu_1_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7This is a custom diamond sword"));
            menu_1.setItemMeta(menu_1_meta);

            inv.setItem(0,menu_1);

            p.openInventory(inv);

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
