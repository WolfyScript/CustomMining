package me.wolfyscript.custommining.configs;

import me.wolfyscript.utilities.api.config.Config;
import me.wolfyscript.utilities.api.config.ConfigAPI;

public class MainConfig extends Config {


    public MainConfig(ConfigAPI configAPI) {
        super(configAPI, "me/wolfyscript/custommining/configs", configAPI.getPlugin().getDataFolder().getPath(), "main_config");
    }

    public long getCooldown(){
        return getLong("cooldown.value");
    }

    public boolean isCooldown(){
        return getBoolean("cooldown.enable");
    }

    public void setCooldown(long value){
        set("cooldown.value", value);
    }

    public void toggleCooldown(){
        set("cooldown.enable", !isCooldown());
    }


}
