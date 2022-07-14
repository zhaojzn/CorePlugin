package org.zhaojason.testingplugin.utils;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.zhaojason.testingplugin.Main;

import java.util.ArrayList;
import java.util.List;

public class PickaxeUtils {

    private final Main main;
    public PickaxeUtils(Main main) {
        this.main = main;
    }

    public List<String> updateLore(NBTItem nbti) {
        List<String> lore = new ArrayList<>();
        for (String s : main.getConfig().getStringList("pickaxe.lore")) {
            s = s.replace("%blocks%", Integer.toString(nbti.getInteger("PickaxeBLOCKS")));
            s = s.replace("%xp%", Integer.toString(nbti.getInteger("PickaxeXP")));
            s = s.replace("%level%", Integer.toString(nbti.getInteger("PickaxeLEVEL")));
            double percent = ((double) nbti.getInteger("PickaxeXP")/nbti.getDouble("PickaxeXP_NEEDED")) * 100;
            s = s.replace("%percentage%", Double.toString(percent));
            s = s.replace("%xp_needed%", Double.toString(nbti.getDouble("PickaxeXP_NEEDED")));
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return lore;
    }
    public boolean isUpgradeable(NBTItem nbti){
        return nbti.getInteger("PickaxeXP") >= nbti.getDouble("PickaxeXP_NEEDED");
    }

    public NBTItem levelUpPickaxe(NBTItem nbti){
        nbti.setInteger("PickaxeXP", nbti.getInteger("PickaxeXP") - nbti.getInteger("PickaxeXP_NEEDED"));
        nbti.setDouble("PickaxeXP_NEEDED",nbti.getDouble("PickaxeXP_NEEDED") * 1.25);
        nbti.setInteger("PickaxeLEVEL", nbti.getInteger("PickaxeLEVEL")+1);
        return nbti;
    }

    public void givePickaxe(Player player){
        ItemStack i = new ItemStack(Material.WOODEN_PICKAXE);
        ItemMeta i_meta = i.getItemMeta();
        i_meta.setUnbreakable(true);
        i_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7Basic Pickaxe"));
        i_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        i_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(i_meta);

        NBTItem nbti = new NBTItem(i);
        nbti.setInteger("PickaxeXP", 0);
        nbti.setInteger("PickaxeBLOCKS", 0);
        nbti.setInteger("PickaxeLEVEL", 1);
        nbti.setDouble("PickaxeXP_NEEDED", 50.0);

        PickaxeUtils p = new PickaxeUtils(main);
        List<String> lore = p.updateLore(nbti);

        ItemStack finalItem = nbti.getItem();
        ItemMeta finalItemMeta = finalItem.getItemMeta();
        finalItemMeta.setLore(lore);
        finalItem.setItemMeta(finalItemMeta);

        player.getInventory().addItem(finalItem);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have received a custom pickaxe"));
    }
}
