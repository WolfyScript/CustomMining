package me.wolfyscript.custommining.commands;

import me.wolfyscript.custommining.CustomMining;
import me.wolfyscript.custommining.configs.DataConfig;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class CommandCM implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length > 0){
            switch (args[0]){
                case "help":
                case "info":
                    return true;
                case "mine":
                    if(args.length > 1){
                        Material blockMaterial = Material.matchMaterial(args[1]);
                        if(blockMaterial != null){
                            ItemStack itemStack;
                            if(args.length > 2){
                                Material itemMaterial = Material.matchMaterial(args[2]);
                                if(itemMaterial != null){
                                    itemStack = new ItemStack(itemMaterial);
                                }else{
                                    //INVALID ITEM MATERIAL
                                    sender.sendMessage("You need to be a player!");
                                    return true;
                                }
                            }else if(sender instanceof Player){
                                itemStack = ((Player) sender).getInventory().getItemInMainHand();
                            }else{
                                //NEED TO BE A PLAYER!
                                sender.sendMessage("You need to be a player!");
                                return true;
                            }
                            if(!itemStack.getType().equals(Material.AIR)){
                                DataConfig dataConfig = CustomMining.getConfigHandler().getDataConfig();
                                dataConfig.addItem(blockMaterial, itemStack);
                                sender.sendMessage("Added "+blockMaterial.getKey().toString() + " to item "+itemStack);
                            }else{
                                //CAN'T ADD AIR!
                                sender.sendMessage("Can't add Air");
                            }
                        }else{
                            //INVALID BLOCK MATERIAL
                            sender.sendMessage("Invalid Block Material");
                        }

                    }


            }

        }


        return true;
    }


    @Override
    public List<String> onTabComplete(@Nonnull CommandSender commandSender,@Nonnull Command command,@Nonnull String s,@Nonnull String[] strings) {
        return null;
    }
}
