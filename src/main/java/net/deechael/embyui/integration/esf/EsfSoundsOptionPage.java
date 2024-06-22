package net.deechael.embyui.integration.esf;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.control.CyclingControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import traben.entity_sound_features.ESF;
import traben.entity_sound_features.ESFConfig;
import traben.entity_texture_features.ETF;

import java.util.ArrayList;
import java.util.List;

public class EsfSoundsOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "models"));

    public EsfSoundsOptionPage() {
        super(ID, Component.translatable("options.embyui.esf.sounds"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(ESF.MOD_ID, "options_n_fixes"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EsfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ESF.MOD_ID, "log_setup"))
                                        .setName(Component.translatable("entity_sound_features.config.log_setup"))
                                        .setTooltip(Component.translatable("entity_sound_features.config.log_setup.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.logSoundSetup = value,
                                                (options) -> options.logSoundSetup)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ESFConfig.AnnounceMode.class, EsfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ESF.MOD_ID, "announce"))
                                        .setName(Component.translatable("entity_sound_features.config.announce"))
                                        .setTooltip(Component.translatable("entity_sound_features.config.announce.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ESFConfig.AnnounceMode.class, new Component[] {
                                                Component.translatable("entity_sound_features.config.entity_search.exact"),
                                                Component.translatable("entity_sound_features.config.entity_search.block"),
                                                Component.translatable("entity_sound_features.config.entity_search.nearest"),
                                                Component.translatable("entity_sound_features.config.entity_search.client")
                                        }))
                                        .setBinding((options, value) -> options.announceCompatibleSounds = value,
                                                (options) -> options.announceCompatibleSounds)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ESFConfig.EntitySearchMode.class, EsfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ESF.MOD_ID, "entity_search"))
                                        .setName(Component.translatable("entity_sound_features.config.entity_search"))
                                        .setTooltip(Component.translatable("entity_sound_features.config.entity_search.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ESFConfig.EntitySearchMode.class, new Component[] {
                                                Component.translatable("entity_sound_features.config.entity_search.exact"),
                                                Component.translatable("entity_sound_features.config.entity_search.block"),
                                                Component.translatable("entity_sound_features.config.entity_search.nearest"),
                                                Component.translatable("entity_sound_features.config.entity_search.client")
                                        }))
                                        .setBinding((options, value) -> options.entitySearchMode = value,
                                                (options) -> options.entitySearchMode)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
