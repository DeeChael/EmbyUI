package net.deechael.embyui.integration.chunksfadein;

import com.koteinik.chunksfadein.config.Config;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

public class ChunksFadeInOptionsStorage implements OptionStorage<Object> {

    public static final OptionStorage<?> INSTANCE = new ChunksFadeInOptionsStorage();

    @Override
    public Object getData() {
        return new Object();
    }

    @Override
    public void save() {
        Config.save();
    }

}
