package org.zhaojason.testingplugin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zhaojason.testingplugin.data.DataManager;
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
            System.out.println(new DataManager(main).getBalance(player.getUniqueId()));

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
