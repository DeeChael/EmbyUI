package net.deechael.embyui.integration.dynamicfps;

import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.config.Config;
import org.embeddedt.embeddium.api.options.structure.OptionStorage;

public class DynamicFpsPowerStorage implements OptionStorage<Config> {

    private final Config config;

    public DynamicFpsPowerStorage(Config config) {
        this.config = config;
    }

    @Override
    public Config getData() {
        return this.config;
    }

    @Override
    public void save() {
        DynamicFPSMod.modConfig.save();
    }

}
