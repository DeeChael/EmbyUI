package net.deechael.embyui.integration.fabricskyboxes;

import io.github.amerebagatelle.fabricskyboxes.FabricSkyBoxesClient;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class FabricSkyboxesOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new FabricSkyboxesOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        FabricSkyBoxesClient.config().save();
    }

}
