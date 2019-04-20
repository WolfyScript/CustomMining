package me.wolfyscript.custommining.listeners;

import me.wolfyscript.custommining.CustomMining;
import me.wolfyscript.custommining.configs.DataConfig;
import me.wolfyscript.custommining.configs.MainConfig;
import me.wolfyscript.custommining.handlers.ConfigHandler;
import me.wolfyscript.utilities.api.WolfyUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BlockListener implements Listener {

    private ArrayList<UUID> cooldowns;

    public BlockListener() {
        cooldowns = new ArrayList<>();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        ConfigHandler configHandler = CustomMining.getConfigHandler();
        long cooldown = configHandler.getMainConfig().getCooldown();
        HashMap<Material, List<ItemStack>> destroys = configHandler.getDataConfig().getDestroysMap();

        Material type = event.getBlock().getType();
        Player player = event.getPlayer();
        ItemStack holdItem = player.getInventory().getItemInMainHand();

        if (destroys.containsKey(type)) {
            if (!cooldowns.contains(player.getUniqueId())) {
                event.setDropItems(false);
                List<ItemStack> items = destroys.get(type);
                for (ItemStack item : items) {
                    if (holdItem.isSimilar(item)) {
                        UUID uuid = player.getUniqueId();
                        cooldowns.add(uuid);
                        if (configHandler.getMainConfig().isCooldown()) {
                            Bukkit.getScheduler().runTaskLaterAsynchronously(CustomMining.getInstance(), () -> cooldowns.remove(uuid), cooldown);
                        }
                        event.setDropItems(true);
                        event.setCancelled(false);
                        break;
                    }
                }
            } else {
                player.sendMessage("§cYou need to wait §4" + (cooldown / 20) + "s §cbefore breaking the next block!");
                event.setCancelled(true);
            }
        }
    }
}
