package org.zhaojason.testingplugin.Commands;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zhaojason.testingplugin.Main;
import org.zhaojason.testingplugin.utils.PickaxeUtils;

import java.util.List;

public class PickaxeCommand implements CommandExecutor {



    private final Main main;
    public PickaxeCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            givePickaxe(player);

        }
        return false;
    }

    public void givePickaxe(Player player){
        ItemStack i = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta i_meta = i.getItemMeta();
        i_meta.setUnbreakable(true);
        i_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Basic Pickaxe"));
        i_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        i.setItemMeta(i_meta);

        NBTItem nbti = new NBTItem(i);
        nbti.setInteger("PickaxeXP", 0);
        nbti.setInteger("PickaxeBLOCKS", 0);
        nbti.setInteger("PickaxeLEVEL", 1);
        nbti.setDouble("PickaxeXP_NEEDED", 50.0);

        PickaxeUtils p = new PickaxeUtils(main);
        List<String> lore = p.updateLore(nbti);

        ItemStack finalItem = nbti.getItem();
        ItemMeta finalItemMeta = finalItem.getItemMeta();
        finalItemMeta.setLore(lore);
        finalItem.setItemMeta(finalItemMeta);

        player.getInventory().addItem(finalItem);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have received a custom pickaxe"));
    }


}
