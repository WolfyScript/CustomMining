package me.wolfyscript.custommining.listeners;

import me.wolfyscript.custommining.CustomMining;
import me.wolfyscript.custommining.configs.DataConfig;
import me.wolfyscript.custommining.handlers.ConfigHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        ConfigHandler configHandler = CustomMining.getConfigHandler();
        DataConfig dataConfig = configHandler.getDataConfig();
        HashMap<Material, List<ItemStack>> destroys = dataConfig.getDestroysMap();

        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack holdItem = player.getInventory().getItemInMainHand();

        if(destroys.containsKey(block.getType())){
            event.setDropItems(false);
            List<ItemStack> items = destroys.get(block.getType());
            for (ItemStack item : items) {
                if(holdItem.isSimilar(item)){
                    player.sendMessage("Broke");
                    event.setDropItems(true);
                    event.setCancelled(false);
                    return;
                }
            }

        }
    }

    @EventHandler
    public void onDamage(BlockDamageEvent event){



    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){

        }
    }


}
