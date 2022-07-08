package org.zhaojason.testingplugin.events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.zhaojason.testingplugin.data.DataManager;
import org.zhaojason.testingplugin.Main;
import org.zhaojason.testingplugin.enums.BlockGens;
import org.zhaojason.testingplugin.utils.Chat;

public class GeneratorPlace implements Listener {
    private final Main main;
    public GeneratorPlace(Main main){
        this.main = main;
    }
    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        ItemStack i = e.getItemInHand();
        NBTItem nbti = new NBTItem(i);
        if(nbti.hasKey("generator")){
            String generator = nbti.getString("generator");
            BlockGens genName = new BlockGens(generator);
            BlockGens.TypeGens genType = genName.getGenType();
            Location loc = e.getBlockPlaced().getLocation();
            if(genType != null){
                addGenToData(genType.getGen().toString(), loc, player, genType.getDisplayName(), e.getBlockPlaced().getType());
            }

        }

    }
    public void addGenToData(String genName, Location location, Player p, String genDisplayName, Material material){
        DataManager data = new DataManager(main);
        data.setGenerator(location, genName, material.toString());
        p.sendMessage(Chat.color("&8[&7CoreGenerators&8] ") + genDisplayName + Chat.color("&7has been placed."));
    }
}
