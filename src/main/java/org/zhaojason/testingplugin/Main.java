package org.zhaojason.testingplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.zhaojason.testingplugin.Commands.DataCommand;
import org.zhaojason.testingplugin.Commands.GeneratorCommand;
import org.zhaojason.testingplugin.Commands.MenuCommand;
import org.zhaojason.testingplugin.Commands.PickaxeCommand;
import org.zhaojason.testingplugin.Data.DataManager;
import org.zhaojason.testingplugin.Events.GeneratorPlace;
import org.zhaojason.testingplugin.Events.blockBreakEvent;
import org.zhaojason.testingplugin.Events.MoveEvent;
import org.zhaojason.testingplugin.Listeners.MenuListener;
import org.zhaojason.testingplugin.Listeners.DataListeners;

public final class Main extends JavaPlugin  {

    private DataManager dataManager;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        new PickaxeCommand(this);
        new MenuCommand();
        new GeneratorCommand();
        new DataCommand(this);

        Bukkit.getServer().getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new blockBreakEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DataListeners(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new GeneratorPlace(this), this);


        System.out.println("This plugin has started ..");
        for(Player p : Bukkit.getOnlinePlayers()){
            System.out.println(p.getName());
        }
        dataManager = new DataManager(this);

    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
