package net.deechael.embyui.integration.etf;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.control.ControlValueFormatter;
import org.embeddedt.embeddium.api.options.control.CyclingControl;
import org.embeddedt.embeddium.api.options.control.SliderControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import traben.entity_texture_features.ETF;
import traben.entity_texture_features.config.ETFConfig;

import java.util.ArrayList;
import java.util.List;

public class EtfMiscOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "misc"));

    public EtfMiscOptionPage() {
        super(ID, Component.translatable("options.embyui.etf.misc"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "misc"))
                        .add(
                                OptionImpl.createBuilder(ETFConfig.IllegalPathMode.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "allow_illegal_texture_paths"))
                                        .setName(Component.translatable("config.entity_texture_features.allow_illegal_texture_paths.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.allow_illegal_texture_paths.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ETFConfig.IllegalPathMode.class, new Component[] {
                                                Component.translatable("options.off"),
                                                Component.translatable("config.entity_texture_features.illegal_path_mode.entity"),
                                                Component.translatable("config.entity_texture_features.illegal_path_mode.all")
                                        }))
                                        .setBinding((options, value) -> options.illegalPathSupportMode = value,
                                                (options) -> options.illegalPathSupportMode)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "warden"))
                                        .setName(Component.translatable("config.entity_texture_features.warden.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.warden.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableFullBodyWardenTextures = value,
                                                (options) -> options.enableFullBodyWardenTextures)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "warden"))
                                        .setName(Component.translatable("config.entity_features.hide_button"))
                                        .setTooltip(Component.translatable("config.entity_features.hide_button.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.hideConfigButton = value,
                                                (options) -> options.hideConfigButton)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
