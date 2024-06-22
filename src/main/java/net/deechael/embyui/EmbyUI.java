package net.deechael.embyui;

import net.deechael.embyui.integration.Integrations;
import net.neoforged.api.distmarker.Dist;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = EmbyUIConstants.MOD_ID, dist = Dist.CLIENT)
public class EmbyUI {

    public EmbyUI(IEventBus modEventBus) {
        Integrations.init();
    }

}
