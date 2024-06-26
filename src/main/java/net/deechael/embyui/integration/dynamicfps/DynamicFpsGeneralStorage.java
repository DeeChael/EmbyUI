package net.deechael.embyui.integration.dynamicfps;

import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.config.DynamicFPSConfig;
import org.embeddedt.embeddium.api.options.structure.OptionStorage;

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
