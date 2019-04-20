package me.wolfyscript.custommining.handlers;

import me.wolfyscript.custommining.configs.DataConfig;
import me.wolfyscript.utilities.api.WolfyUtilities;
import me.wolfyscript.utilities.api.config.ConfigAPI;

public class ConfigHandler {

    private WolfyUtilities api;
    private ConfigAPI configAPI;
    private DataConfig dataConfig;

    public ConfigHandler(WolfyUtilities api){
        this.api = api;
        this.configAPI = api.getConfigAPI();
    }

    public void load(){
        dataConfig = new DataConfig(configAPI);
    }

    public void save(){
        dataConfig.saveDestroysMap();
    }

    public DataConfig getDataConfig() {
        return dataConfig;
    }
}
