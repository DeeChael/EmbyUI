package net.deechael.embyui.integration.cit;

import io.github.amerebagatelle.fabricskyboxes.FabricSkyBoxesClient;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;
import shcm.shsupercm.fabric.citresewn.config.CITResewnConfig;

public class CITOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new CITOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        CITResewnConfig.INSTANCE.write();
    }

}
