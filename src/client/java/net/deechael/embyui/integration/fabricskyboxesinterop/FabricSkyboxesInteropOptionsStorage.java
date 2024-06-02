package net.deechael.embyui.integration.fabricskyboxesinterop;

import me.flashyreese.mods.fabricskyboxes_interop.client.config.FSBInteropConfig;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class FabricSkyboxesInteropOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new FabricSkyboxesInteropOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        FSBInteropConfig.INSTANCE.writeChanges();
    }

}
