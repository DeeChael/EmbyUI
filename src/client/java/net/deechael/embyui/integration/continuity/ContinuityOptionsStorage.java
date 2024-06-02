package net.deechael.embyui.integration.continuity;

import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
import me.pepperbell.continuity.client.config.ContinuityConfig;

public class ContinuityOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new ContinuityOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        ContinuityConfig.INSTANCE.save();
    }

}
