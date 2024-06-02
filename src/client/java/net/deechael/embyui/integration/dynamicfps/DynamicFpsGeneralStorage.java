package net.deechael.embyui.integration.dynamicfps;

import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.config.DynamicFPSConfig;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class DynamicFpsGeneralStorage implements OptionStorage<DynamicFPSConfig> {

    public final static DynamicFpsGeneralStorage INSTANCE = new DynamicFpsGeneralStorage();

    @Override
    public DynamicFPSConfig getData() {
        return DynamicFPSMod.modConfig;
    }

    @Override
    public void save() {
        DynamicFPSMod.modConfig.save();
    }

}
