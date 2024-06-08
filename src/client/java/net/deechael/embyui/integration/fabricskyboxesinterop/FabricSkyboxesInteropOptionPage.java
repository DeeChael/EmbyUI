package net.deechael.embyui.integration.fabricskyboxesinterop;

import com.google.common.collect.ImmutableList;
import io.github.amerebagatelle.fabricskyboxes.FabricSkyBoxesClient;
import me.flashyreese.mods.fabricskyboxes_interop.client.config.FSBInteropConfig;
import me.flashyreese.mods.fabricskyboxes_interop.client.config.FSBInteropMode;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class FabricSkyboxesInteropOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier(FabricSkyBoxesClient.MODID, "interop"));

    public FabricSkyboxesInteropOptionPage() {
        super(ID, Text.translatable("options.embyui.fabricskyboxes.interop"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesInteropOptionsStorage.INSTANCE)
                                        .setId(new Identifier("fsb-interop", "interoperability"))
                                        .setName(Text.translatable("options.fsb-interop.interoperability"))
                                        .setTooltip(Text.translatable("options.fsb-interop.interoperability.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FSBInteropConfig.INSTANCE.interoperability = value,
                                                (options) -> FSBInteropConfig.INSTANCE.interoperability)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(FSBInteropMode.class, FabricSkyboxesInteropOptionsStorage.INSTANCE)
                                        .setId(new Identifier("fsb-interop", "interop_mode"))
                                        .setName(Text.translatable("options.fsb-interop.mode"))
                                        .setTooltip(Text.translatable("options.fsb-interop.mode.tooltip"))
                                        .setControl(opt -> new CyclingControl<>(opt, FSBInteropMode.class, new Text[]{
                                                Text.translatable("options.fsb-interop.mode.conversion"),
                                                Text.translatable("options.fsb-interop.mode.native")
                                        }))
                                        .setBinding((options, value) -> FSBInteropConfig.INSTANCE.mode = value,
                                                (options) -> FSBInteropConfig.INSTANCE.mode)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesInteropOptionsStorage.INSTANCE)
                                        .setId(new Identifier("fsb-interop", "prefer_fsb_native"))
                                        .setName(Text.translatable("options.fsb-interop.prefer_fsb_native"))
                                        .setTooltip(Text.translatable("options.fsb-interop.prefer_fsb_native.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FSBInteropConfig.INSTANCE.preferFSBNative = value,
                                                (options) -> FSBInteropConfig.INSTANCE.preferFSBNative)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesInteropOptionsStorage.INSTANCE)
                                        .setId(new Identifier("fsb-interop", "debug_mode"))
                                        .setName(Text.translatable("options.fsb-interop.debug_mode"))
                                        .setTooltip(Text.translatable("options.fsb-interop.debug_mode.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FSBInteropConfig.INSTANCE.debugMode = value,
                                                (options) -> FSBInteropConfig.INSTANCE.debugMode)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesInteropOptionsStorage.INSTANCE)
                                        .setId(new Identifier("fsb-interop", "process_optifine"))
                                        .setName(Text.translatable("options.fsb-interop.process_optifine"))
                                        .setTooltip(Text.translatable("options.fsb-interop.process_optifine.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FSBInteropConfig.INSTANCE.processOptiFine = value,
                                                (options) -> FSBInteropConfig.INSTANCE.processOptiFine)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, FabricSkyboxesInteropOptionsStorage.INSTANCE)
                                        .setId(new Identifier("fsb-interop", "process_mcpatcher"))
                                        .setName(Text.translatable("options.fsb-interop.process_mcpatcher"))
                                        .setTooltip(Text.translatable("options.fsb-interop.process_mcpatcher.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> FSBInteropConfig.INSTANCE.processMCPatcher = value,
                                                (options) -> FSBInteropConfig.INSTANCE.processMCPatcher)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
