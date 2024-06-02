package net.deechael.embyui.integration.fabricskyboxes;

import com.google.common.collect.ImmutableList;
import io.github.amerebagatelle.fabricskyboxes.FabricSkyBoxesClient;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class FabricSkyboxesOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier(FabricSkyBoxesClient.MODID, "skyboxes"));

    public FabricSkyboxesOptionPage() {
        super(ID, Text.translatable("options.embyui.fabricskyboxes.skyboxes"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesOptionsStorage.INSTANCE)
                                        .setId(new Identifier(FabricSkyBoxesClient.MODID, "enabled"))
                                        .setName(Text.translatable("options.embyui.fabricskyboxes.enabled"))
                                        .setTooltip(Text.translatable("options.embyui.fabricskyboxes.enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FabricSkyBoxesClient.config().generalSettings.enable = value,
                                                (options) -> FabricSkyBoxesClient.config().generalSettings.enable)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesOptionsStorage.INSTANCE)
                                        .setId(new Identifier(FabricSkyBoxesClient.MODID, "debughud"))
                                        .setName(Text.translatable("options.embyui.fabricskyboxes.debughud"))
                                        .setTooltip(Text.translatable("options.embyui.fabricskyboxes.debughud"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FabricSkyBoxesClient.config().generalSettings.debugHud = value,
                                                (options) -> FabricSkyBoxesClient.config().generalSettings.debugHud)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
