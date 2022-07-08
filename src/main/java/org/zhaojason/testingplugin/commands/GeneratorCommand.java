package org.zhaojason.testingplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.zhaojason.testingplugin.enums.BlockGens;
import org.zhaojason.testingplugin.utils.Chat;
import org.zhaojason.testingplugin.utils.ItemStackBuilder;

import java.util.List;

public class GeneratorCommand extends CommandHandler {

    public GeneratorCommand() {
            super(
                    "generator",
                    new String[]{"gens", "gen"},
                    "Generators",
                    "admin"
            );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;


            p.getInventory().addItem(createGen(
                    Material.COAL_ORE,
                    Chat.color("&8Coal Generator "),
                    "COALGEN"
            ).build());
            p.getInventory().addItem(createGen(
                    Material.IRON_ORE,
                    Chat.color("&7Iron Generator "),
                    "IRONGEN"
            ).build());

        }
    }

    public ItemStackBuilder.Nbt createGen(Material material, String displayName, String nbtName){
        ItemStack gen = new ItemStack(material);
        ItemStackBuilder genB= new ItemStackBuilder(gen);
        genB.setName(displayName);
        return genB.nbt().set("generator", nbtName);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
