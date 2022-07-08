package org.zhaojason.testingplugin.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zhaojason.testingplugin.Data.DataManager;
import org.zhaojason.testingplugin.Main;
import org.zhaojason.testingplugin.utils.Chat;

import javax.swing.*;
import java.util.List;


public class DataCommand extends CommandHandler {
    private Main main;

    public DataCommand(Main main) {
        super(
                "core",
                new String[]{"c"},
                "Data save",
                "admin"
        );
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if(args.length > 0)
            {
                if(args[0].equals("reload"))
                {
                    DataManager data = new DataManager(main);
                    data.fileSave();
                    player.sendMessage(Chat.color("&8[&aData&8] &7The data has been saved from config files"));
                }
            }else{
                player.sendMessage(Chat.color("&8[&aData&8] &7/data (reload)"));
            }

        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
