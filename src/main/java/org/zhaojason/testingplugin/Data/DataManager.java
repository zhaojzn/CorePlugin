package org.zhaojason.testingplugin.Data;


import org.bukkit.configuration.file.YamlConfiguration;
import org.zhaojason.testingplugin.Main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {


    private File file;
    private YamlConfiguration config;


    public DataManager(Main main) {
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }
        file = new File(main.getDataFolder(), "data.yml");
        if(!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);

    }
    public void setBalance(UUID uuid, int balance){
        config.set(uuid.toString() + ".balance", balance);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double getBalance(UUID uuid){
        return config.getDouble(uuid.toString() + ".balance");
    }

    public boolean hasPlayed(UUID uuid){
        if(config.get(uuid.toString()) == null){
            return false;
        }
        return true;
    }

    public void newPlayer(UUID uuid){
        config.set(uuid.toString() + ".balance", 0);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New UUID added to data file :" + uuid);
    }
}
