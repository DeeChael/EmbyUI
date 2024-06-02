package net.deechael.embyui.integration.titletweaks;

import com.google.common.collect.ImmutableList;
import dev.microcontrollers.titletweaks.config.TitleTweaksConfig;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class TitleTweaksOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier("titletweaks", "title"));

    public TitleTweaksOptionPage() {
        super(ID, Text.translatable("options.embyui.titletweaks.title"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "disable_titles"))
                                        .setName(Text.translatable("options.embyui.titletweaks.disable_titles"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.disable_titles.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> TitleTweaksConfig.CONFIG.instance().disableTitles = value,
                                                (options) -> TitleTweaksConfig.CONFIG.instance().disableTitles)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "title_scale"))
                                        .setName(Text.translatable("options.embyui.titletweaks.title_scale"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.title_scale.tooltip"))
                                        .setControl(option -> new SliderControl(option, 0, 100, 1, ControlValueFormatter.percentage()))
                                        .setBinding((option, value) -> TitleTweaksConfig.CONFIG.instance().titleScale = value,
                                                option -> (int) TitleTweaksConfig.CONFIG.instance().titleScale)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "automatically_scale_titles"))
                                        .setName(Text.translatable("options.embyui.titletweaks.automatically_scale_titles"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.automatically_scale_titles.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> TitleTweaksConfig.CONFIG.instance().autoTitleScale = value,
                                                (options) -> TitleTweaksConfig.CONFIG.instance().autoTitleScale)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "title_vertical_position"))
                                        .setName(Text.translatable("options.embyui.titletweaks.title_vertical_position"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.title_vertical_position.tooltip"))
                                        .setControl(option -> new SliderControl(option, -100, 100, 1, ControlValueFormatter.number()))
                                        .setBinding((option, value) -> TitleTweaksConfig.CONFIG.instance().titlePositionOffset = value,
                                                option -> (int) TitleTweaksConfig.CONFIG.instance().titlePositionOffset)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "subtitle_vertical_position"))
                                        .setName(Text.translatable("options.embyui.titletweaks.subtitle_vertical_position"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.subtitle_vertical_position.tooltip"))
                                        .setControl(option -> new SliderControl(option, -100, 100, 1, ControlValueFormatter.number()))
                                        .setBinding((option, value) -> TitleTweaksConfig.CONFIG.instance().subtitlePositionOffset = value,
                                                option -> (int) TitleTweaksConfig.CONFIG.instance().subtitlePositionOffset)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "title_opacity"))
                                        .setName(Text.translatable("options.embyui.titletweaks.title_opacity"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.title_opacity.tooltip"))
                                        .setControl(option -> new SliderControl(option, 0, 100, 1, ControlValueFormatter.percentage()))
                                        .setBinding((option, value) -> TitleTweaksConfig.CONFIG.instance().titleOpacity = value,
                                                option -> (int) TitleTweaksConfig.CONFIG.instance().titleOpacity)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "remove_text_shadow"))
                                        .setName(Text.translatable("options.embyui.titletweaks.remove_text_shadow"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.remove_text_shadow.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> TitleTweaksConfig.CONFIG.instance().removeTextShadow = value,
                                                (options) -> TitleTweaksConfig.CONFIG.instance().removeTextShadow)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, TitleTweaksOptionsStorage.INSTANCE)
                                        .setId(new Identifier("titletweaks", "clear_on_disconnect"))
                                        .setName(Text.translatable("options.embyui.titletweaks.clear_on_disconnect"))
                                        .setTooltip(Text.translatable("options.embyui.titletweaks.clear_on_disconnect.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> TitleTweaksConfig.CONFIG.instance().clearOnDisconnect = value,
                                                (options) -> TitleTweaksConfig.CONFIG.instance().clearOnDisconnect)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
