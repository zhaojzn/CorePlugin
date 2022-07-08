package org.zhaojason.testingplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.zhaojason.testingplugin.utils.Chat;
import org.zhaojason.testingplugin.utils.ItemStackBuilder;

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
            ItemStackBuilder b = new ItemStackBuilder(coalGen);
            b.setName(Chat.color("&8Coal generator"));
            ItemStackBuilder.Nbt i = b.nbt().set("generator", "COALORE");
            p.getInventory().addItem(i.build());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
