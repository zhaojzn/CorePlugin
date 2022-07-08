package org.zhaojason.testingplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class CommandHandler extends BukkitCommand {

    public CommandHandler(String command, String[] aliases, String description, String permission) {
        super(command);
        this.setAliases(Arrays.asList(aliases));
        this.setDescription(description);
        this.setPermission(permission);
        this.setPermissionMessage(ChatColor.RED + "No permission.");

        try{
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(command, this);
        } catch(NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }

    }

    // random shit
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        execute(sender, args);
        return false;
    }
    //simplified
    public abstract void execute(CommandSender sender, String[] args);
    //random shit
    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return onTabComplete(sender,args);
    }
    //simplified
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}
