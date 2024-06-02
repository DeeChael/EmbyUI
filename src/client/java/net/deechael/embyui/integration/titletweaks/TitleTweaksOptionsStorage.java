package net.deechael.embyui.integration.titletweaks;

import dev.microcontrollers.titletweaks.config.TitleTweaksConfig;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class TitleTweaksOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new TitleTweaksOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        TitleTweaksConfig.CONFIG.save();
    }

}
