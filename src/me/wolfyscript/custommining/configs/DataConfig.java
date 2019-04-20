package me.wolfyscript.custommining.configs;

import me.wolfyscript.utilities.api.config.Config;
import me.wolfyscript.utilities.api.config.ConfigAPI;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class DataConfig extends Config {

    private HashMap<Material, List<ItemStack>> destroysMap;

    public DataConfig(ConfigAPI configAPI) {
        super(configAPI, "me/wolfyscript/custommining/configs", configAPI.getPlugin().getDataFolder().getPath()+"/data", "data");
    }

    @Override
    public void init() {
        loadDestroysMap();
    }

    public HashMap<Material, List<ItemStack>> getDestroysMap() {
        return destroysMap;
    }

    public void addItem(Material material, ItemStack itemStack){
        List<ItemStack> items = destroysMap.getOrDefault(material, new ArrayList<>());
        if(!items.contains(itemStack)){
            items.add(itemStack);
        }
        destroysMap.put(material, items);
    }

    public void removeItem(Material material, ItemStack itemStack){
        List<ItemStack> items = destroysMap.getOrDefault(material, new ArrayList<>());
        items.remove(itemStack);
        destroysMap.put(material, items);
    }

    public void loadDestroysMap() {
        HashMap<String, ItemStack> itemMap = new HashMap<>();
        HashMap<Material, List<ItemStack>> destroys = new HashMap<>();
        if(getConfig().getConfigurationSection("items") != null){
            Set<String> keys = getConfig().getConfigurationSection("items").getKeys(false);
            for(String key : keys){
                ItemStack itemStack = getItem("items."+key);
                itemMap.put(key, itemStack);
            }
            if(getConfig().getConfigurationSection("blocks") != null){
                Set<String> matsKeys = getConfig().getConfigurationSection("blocks").getKeys(false);
                for(String matKey : matsKeys){
                    Material material = Material.matchMaterial(matKey);
                    if(material != null){
                        List<ItemStack> items = new ArrayList<>();
                        List<String> itemNames = getStringList("blocks."+matKey);
                        for(String itemName : itemNames){
                            ItemStack itemStack = itemMap.getOrDefault(itemName, null);
                            if(itemStack != null){
                                items.add(itemStack);
                            }
                        }
                        destroys.put(material, items);
                    }
                }
            }
        }
        this.destroysMap = destroys;
    }

    public void saveDestroysMap(){
        set("data", null);
        set("blocks", null);
        List<ItemStack> items = new ArrayList<>();
        for(Material material : destroysMap.keySet()){
            List<String> itemNames = new ArrayList<>();
            List<ItemStack> matItems = destroysMap.get(material);
            for (ItemStack matItem : matItems) {
                if(!items.contains(matItem)){
                    items.add(matItem);
                }
                itemNames.add("item"+items.indexOf(matItem));
            }
            set(material.name().toLowerCase(Locale.ROOT), itemNames);
        }
        for (int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            String key = "item"+i;
            saveItem("items."+key, item);
        }
    }
}
