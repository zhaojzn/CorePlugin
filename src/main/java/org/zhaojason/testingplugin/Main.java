package org.zhaojason.testingplugin;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.zhaojason.testingplugin.commands.DataCommand;
import org.zhaojason.testingplugin.commands.GeneratorCommand;
import org.zhaojason.testingplugin.commands.MenuCommand;
import org.zhaojason.testingplugin.commands.PickaxeCommand;
import org.zhaojason.testingplugin.data.DataManager;
import org.zhaojason.testingplugin.enums.BlockGens;
import org.zhaojason.testingplugin.events.GeneratorPlace;
import org.zhaojason.testingplugin.events.blockBreakEvent;
import org.zhaojason.testingplugin.events.MoveEvent;
import org.zhaojason.testingplugin.listeners.MenuListener;
import org.zhaojason.testingplugin.listeners.DataListeners;

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
