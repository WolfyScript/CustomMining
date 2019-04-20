package me.wolfyscript.custommining.handlers;

import me.wolfyscript.custommining.configs.DataConfig;
import me.wolfyscript.custommining.configs.MainConfig;
import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.config.ConfigAPI;

public class ConfigHandler {

    private WolfyUtilities api;
    private ConfigAPI configAPI;
    private DataConfig dataConfig;
    private MainConfig mainConfig;

    public ConfigHandler(WolfyUtilities api){
        this.api = api;
        this.configAPI = api.getConfigAPI();
    }

    public void load(){
        mainConfig = new MainConfig(configAPI);
        dataConfig = new DataConfig(configAPI);
    }

    public void save(){
        dataConfig.saveDestroysMap();
        mainConfig.save();
    }

    public DataConfig getDataConfig() {
        return dataConfig;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }
}
