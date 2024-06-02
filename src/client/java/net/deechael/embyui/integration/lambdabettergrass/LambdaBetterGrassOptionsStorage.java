package net.deechael.embyui.integration.lambdabettergrass;

import dev.lambdaurora.lambdabettergrass.LBGConfig;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class LambdaBetterGrassOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new LambdaBetterGrassOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        LBGConfig.INSTANCE.save();
    }

}
