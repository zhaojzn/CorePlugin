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

public class PickaxeCommand extends CommandHandler {

    private Main main;

    public PickaxeCommand(Main main) {
        super(
                "pickaxe",
                new String[]{"pick"},
                "Pickaxe command",
                "defualt"
        );
        this.main = main;


    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            new PickaxeUtils(main).givePickaxe(player);

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
