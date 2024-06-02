package net.deechael.embyui.integration;

import dynamic_fps.impl.PowerState;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import net.deechael.embyui.integration.chunksfadein.ChunksFadeInOptionPage;
import net.deechael.embyui.integration.continuity.ContinuityOptionPage;
import net.deechael.embyui.integration.dynamicfps.pages.DynamicFpsGeneralPage;
import net.deechael.embyui.integration.dynamicfps.pages.DynamicFpsPowerPage;
import net.deechael.embyui.integration.fabricskyboxes.FabricSkyboxesOptionPage;
import net.deechael.embyui.integration.fabricskyboxesinterop.FabricSkyboxesInteropOptionPage;
import net.deechael.embyui.integration.lambdabettergrass.LambdaBetterGrassOptionPage;
import net.deechael.embyui.integration.ryoamiclights.RyoamicLightsOptionPage;
import net.deechael.embyui.integration.titletweaks.TitleTweaksOptionPage;
import net.fabricmc.loader.api.FabricLoader;
import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;

import java.util.List;

public final class Integrations {

    public static void init() {
        OptionGUIConstructionEvent.BUS.addListener(Integrations::event);
    }

    private static void event(OptionGUIConstructionEvent event) {
        List<OptionPage> pages = event.getPages();

        if (isModPresent("ryoamiclights")) {
            pages.add(new RyoamicLightsOptionPage());
        }
        if (isModPresent("continuity")) {
            pages.add(new ContinuityOptionPage());
        }
        if (isModPresent("dynamic_fps")) {
            pages.add(new DynamicFpsGeneralPage());
            pages.add(new DynamicFpsPowerPage(PowerState.HOVERED));
            pages.add(new DynamicFpsPowerPage(PowerState.UNFOCUSED));
            pages.add(new DynamicFpsPowerPage(PowerState.INVISIBLE));
            pages.add(new DynamicFpsPowerPage(PowerState.ABANDONED));
        }
        if (isModPresent("fabricskyboxes")) {
            pages.add(new FabricSkyboxesOptionPage());
            if (isModPresent("fsb-interop")) {
                pages.add(new FabricSkyboxesInteropOptionPage());
            }
        }
        if (isModPresent("titletweaks")) {
            pages.add(new TitleTweaksOptionPage());
        }
        if (isModPresent("chunksfadein")) {
            pages.add(new ChunksFadeInOptionPage());
        }
        if (isModPresent("lambdabettergrass")) {
            pages.add(new LambdaBetterGrassOptionPage());
        }
    }

    private static boolean isModPresent(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    private Integrations() {
    }

}
