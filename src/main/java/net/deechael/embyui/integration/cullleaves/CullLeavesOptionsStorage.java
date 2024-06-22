package net.deechael.embyui.integration.cullleaves;

import eu.midnightdust.lib.config.MidnightConfig;
import org.embeddedt.embeddium.api.options.structure.OptionStorage;

public class CullLeavesOptionsStorage implements OptionStorage<Object> {

    public final static CullLeavesOptionsStorage STORAGE = new CullLeavesOptionsStorage();

    public CullLeavesOptionsStorage() {
    }

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        MidnightConfig.write("cullleaves");
    }

}
