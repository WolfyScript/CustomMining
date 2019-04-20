package me.wolfyscript.custommining.configs;

import me.wolfyscript.utilities.api.config.Config;
import me.wolfyscript.utilities.api.config.ConfigAPI;

public class MainConfig extends Config {


    public MainConfig(ConfigAPI configAPI) {
        super(configAPI, "me/wolfyscript/custommining/configs", configAPI.getPlugin().getDataFolder().getPath(), "main_config");
    }




}
