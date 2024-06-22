package net.deechael.embyui.integration;

import net.deechael.embyui.integration.cullleaves.CullLeavesOptionPage;
import net.deechael.embyui.integration.emf.EmfModelsOptionPage;
import net.deechael.embyui.integration.esf.EsfSoundsOptionPage;
import net.deechael.embyui.integration.etf.EtfMiscOptionPage;
import net.deechael.embyui.integration.etf.EtfTexturesOptionPage;
import net.neoforged.fml.ModList;
import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;

public class Integrations {

    public static void init() {
        OptionGUIConstructionEvent.BUS.addListener(Integrations::event);
    }

    private static void event(OptionGUIConstructionEvent event) {
        if (isModLoaded("entity_texture_features")) {
            event.addPage(new EtfTexturesOptionPage());
            if (isModLoaded("entity_model_features")) {
                event.addPage(new EmfModelsOptionPage());
            }
            if (isModLoaded("entity_sound_features")) {
                event.addPage(new EsfSoundsOptionPage());
            }
            event.addPage(new EtfMiscOptionPage());
        }
        if (isModLoaded("cullleaves")) {
            event.addPage(new CullLeavesOptionPage());
        }
    }

    private static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

}
