package org.zhaojason.testingplugin.Events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zhaojason.testingplugin.utils.Chat;

public class GeneratorPlace implements Listener {
    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        ItemStack i = e.getItemInHand();
        NBTItem nbti = new NBTItem(i);
        if(nbti.hasKey("generator")){
            if(nbti.getString("generator").equals("COALORE")){
                Bukkit.broadcastMessage(Chat.color("&7A coal generator has been placed at ") + e.getBlockPlaced().getLocation());
            }
        }

    }
}