package net.deechael.embyui.integration.continuity;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.pepperbell.continuity.client.ContinuityClient;
import me.pepperbell.continuity.client.config.ContinuityConfig;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class ContinuityOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier(ContinuityClient.ID, ContinuityClient.ID));

    public ContinuityOptionPage() {
        super(ID, Text.literal(ContinuityClient.NAME), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, ContinuityOptionsStorage.INSTANCE)
                                        .setId(new Identifier(ContinuityClient.ID, "connected_textures"))
                                        .setName(Text.translatable("options.continuity.connected_textures"))
                                        .setTooltip(Text.translatable("options.continuity.connected_textures.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> ContinuityConfig.INSTANCE.connectedTextures.set(value),
                                                (options) -> ContinuityConfig.INSTANCE.connectedTextures.get())
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ContinuityOptionsStorage.INSTANCE)
                                        .setId(new Identifier(ContinuityClient.ID, "emissive_textures"))
                                        .setName(Text.translatable("options.continuity.emissive_textures"))
                                        .setTooltip(Text.translatable("options.continuity.emissive_textures.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> ContinuityConfig.INSTANCE.emissiveTextures.set(value),
                                                (options) -> ContinuityConfig.INSTANCE.emissiveTextures.get())
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ContinuityOptionsStorage.INSTANCE)
                                        .setId(new Identifier(ContinuityClient.ID, "custom_block_layers"))
                                        .setName(Text.translatable("options.continuity.custom_block_layers"))
                                        .setTooltip(Text.translatable("options.continuity.custom_block_layers.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> ContinuityConfig.INSTANCE.customBlockLayers.set(value),
                                                (options) -> ContinuityConfig.INSTANCE.customBlockLayers.get())
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
