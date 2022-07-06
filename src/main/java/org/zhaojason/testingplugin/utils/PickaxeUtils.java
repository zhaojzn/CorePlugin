package org.zhaojason.testingplugin.utils;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
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
            System.out.println(nbti.getInteger("PickaxeXP") + "-" + nbti.getDouble("PickaxeXP_NEEDED"));
            double percent = ((double) nbti.getInteger("PickaxeXP")/nbti.getDouble("PickaxeXP_NEEDED")) * 100;
            System.out.println(percent);
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
}
