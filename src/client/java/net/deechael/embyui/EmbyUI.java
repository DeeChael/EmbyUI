package net.deechael.embyui;

import net.deechael.embyui.integration.Integrations;
import net.fabricmc.api.ClientModInitializer;

public class EmbyUI implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Integrations.init();
    }

}