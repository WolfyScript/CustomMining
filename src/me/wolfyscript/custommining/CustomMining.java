package me.wolfyscript.custommining;

import me.wolfyscript.custommining.commands.CommandCM;
import me.wolfyscript.custommining.handlers.ConfigHandler;
import me.wolfyscript.custommining.listeners.BlockListener;
import me.wolfyscript.utilities.api.WolfyUtilities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomMining extends JavaPlugin {

    private static Plugin instance;
    private static WolfyUtilities api;
    private static ConfigHandler configHandler;


    public void onEnable() {
        instance = this;
        api = new WolfyUtilities(instance);

        api.setCONSOLE_PREFIX("&7[&cCM&7] ");
        api.setCHAT_PREFIX("&7[&cCM&7] ");

        configHandler = new ConfigHandler(api);
        configHandler.load();

        Bukkit.getPluginManager().registerEvents(new BlockListener(), instance);

        CommandCM commandCM = new CommandCM(configHandler);
        Bukkit.getPluginCommand("cm").setExecutor(commandCM);
        Bukkit.getPluginCommand("cm").setTabCompleter(commandCM);


    }

    public void onDisable() {
        configHandler.save();
    }


    public static Plugin getInstance() {
        return instance;
    }

    public static WolfyUtilities getApi() {
        return api;
    }

    public static ConfigHandler getConfigHandler() {
        return configHandler;
    }
}
