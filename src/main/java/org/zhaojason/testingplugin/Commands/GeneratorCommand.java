package org.zhaojason.testingplugin.Commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.zhaojason.testingplugin.utils.Chat;
import org.zhaojason.testingplugin.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;

public class GeneratorCommand extends CommandHandler {

    public GeneratorCommand() {
        super(
                "generator",
                new String[]{"gens"},
                "Generators",
                "admin"
        );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            ItemStack coalGen = new ItemStack(Material.COAL_ORE);
            new ItemStackBuilder(coalGen).setName(Chat.color("&8Coal generator")).build();
            new ItemStackBuilder(coalGen).nbt().set("generator", "COALORE").build();
            p.getInventory().addItem(coalGen);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
