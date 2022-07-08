package org.zhaojason.testingplugin.data;


import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.zhaojason.testingplugin.Main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataManager {


    private File[] file;
    private YamlConfiguration balanceConfig;
    private YamlConfiguration blocksLocConfig;

    public DataManager(Main main) {
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdir();
        }

        file = new File[]{new File(main.getDataFolder(), "data.yml"),
                new File(main.getDataFolder(), "blocksLoc.yml")};


        for(File f : file)
        {
            if(!f.exists())
            {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        balanceConfig = YamlConfiguration.loadConfiguration(file[0]);
        blocksLocConfig = YamlConfiguration.loadConfiguration(file[1]);



    }

    public void fileSave(){
        try {
            balanceConfig.save(file[0]);
            blocksLocConfig.save(file[1]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Generator setters
    public void setGenerator(Location l, String type){
        String z = l.toString().replaceAll("\\.",",");
        blocksLocConfig.set(z + ".type", type);
        fileSave();
    }

    //Balance getters and setters.


    public void setBalance(UUID uuid, int balance){
        balanceConfig.set(uuid.toString() + ".balance", balance);
        fileSave();
    }
    public double getBalance(UUID uuid){
        return balanceConfig.getDouble(uuid.toString() + ".balance");
    }

    public boolean hasPlayed(UUID uuid){
        if(balanceConfig.get(uuid.toString()) == null){
            return false;
        }
        return true;
    }

    public void newPlayer(UUID uuid){
        balanceConfig.set(uuid.toString() + ".balance", 0);
        fileSave();
        System.out.println("New UUID added to data file :" + uuid);
    }
}
