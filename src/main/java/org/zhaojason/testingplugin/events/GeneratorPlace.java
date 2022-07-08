package org.zhaojason.testingplugin.events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.zhaojason.testingplugin.data.DataManager;
import org.zhaojason.testingplugin.Main;
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
            if(nbti.getString("generator").equals("COALORE")){
                Bukkit.broadcastMessage(Chat.color("&7A coal generator has been placed at ") + e.getBlockPlaced().getLocation());
                DataManager data = new DataManager(main);
                data.setGenerator(e.getBlockPlaced().getLocation(), "COALGEN");


            }
        }

    }
}
