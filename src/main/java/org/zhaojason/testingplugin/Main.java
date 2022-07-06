package org.zhaojason.testingplugin;

import jdk.internal.org.jline.reader.ConfigurationPath;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.zhaojason.testingplugin.Commands.MenuCommand;
import org.zhaojason.testingplugin.Commands.PickaxeCommand;
import org.zhaojason.testingplugin.Events.BlockBreakListener;
import org.zhaojason.testingplugin.Events.MoveEvent;
import org.zhaojason.testingplugin.Listeners.MenuListener;

import java.lang.management.PlatformLoggingMXBean;

public final class Main extends JavaPlugin  {


    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("pickaxe").setExecutor(new PickaxeCommand(this));
        getCommand("menu").setExecutor(new MenuCommand());

        Bukkit.getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(), this);

        System.out.println("This plugin has started ..");
        for(Player p : Bukkit.getOnlinePlayers()){
            System.out.println(p.getName());
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
