package net.deechael.embyui.integration.cit;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;
import shcm.shsupercm.fabric.citresewn.config.CITResewnConfig;

import java.util.ArrayList;
import java.util.List;

public class CITOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier("citresewn", "textures"));

    public CITOptionPage() {
        super(ID, Text.translatable("options.embyui.cit.textures"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, CITOptionsStorage.INSTANCE)
                                        .setId(new Identifier("citresewn", "enabled"))
                                        .setName(Text.translatable("config.citresewn.enabled.title"))
                                        .setTooltip(Text.translatable("config.citresewn.enabled.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> CITResewnConfig.INSTANCE.enabled = value,
                                                (options) -> CITResewnConfig.INSTANCE.enabled)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, CITOptionsStorage.INSTANCE)
                                        .setId(new Identifier("citresewn", "mute_errors"))
                                        .setName(Text.translatable("config.citresewn.mute_errors.title"))
                                        .setTooltip(Text.translatable("config.citresewn.mute_errors.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> CITResewnConfig.INSTANCE.mute_errors = value,
                                                (options) -> CITResewnConfig.INSTANCE.mute_errors)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, CITOptionsStorage.INSTANCE)
                                        .setId(new Identifier("citresewn", "mute_warns"))
                                        .setName(Text.translatable("config.citresewn.mute_warns.title"))
                                        .setTooltip(Text.translatable("config.citresewn.mute_warns.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> CITResewnConfig.INSTANCE.mute_warns = value,
                                                (options) -> CITResewnConfig.INSTANCE.mute_warns)
                                        .build()
                        ).add(
                                OptionImpl.createBuilder(int.class, CITOptionsStorage.INSTANCE)
                                        .setId(new Identifier("citresewn", "cache"))
                                        .setName(Text.translatable("config.citresewn.cache_ms.title"))
                                        .setTooltip(Text.translatable("config.citresewn.cache_ms.tooltip"))
                                        .setControl(option -> new SliderControl(option, 0, 100, 1, CacheMsValueFormatter.INSTANCE))
                                        .setBinding((option, value) -> CITResewnConfig.INSTANCE.cache_ms = value,
                                                option -> (int) CITResewnConfig.INSTANCE.cache_ms)
                                        .build()
                        ).add(
                                OptionImpl.createBuilder(boolean.class, CITOptionsStorage.INSTANCE)
                                        .setId(new Identifier("citresewn", "broken_paths"))
                                        .setName(Text.translatable("config.citresewn.broken_paths.title"))
                                        .setTooltip(Text.translatable("config.citresewn.broken_paths.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> CITResewnConfig.INSTANCE.broken_paths = value,
                                                (options) -> CITResewnConfig.INSTANCE.broken_paths)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

    private static class CacheMsValueFormatter implements ControlValueFormatter {

        private final static CacheMsValueFormatter INSTANCE = new CacheMsValueFormatter();

        @Override
        public Text format(int ticks) {
            if (ticks <= 1) {
                return Text.translatable("config.citresewn.cache_ms.ticks." + ticks).formatted(Formatting.AQUA);
            } else {
                Formatting color = Formatting.DARK_RED;
                if (ticks <= 40) {
                    color = Formatting.RED;
                }
                if (ticks <= 20) {
                    color = Formatting.GOLD;
                }
                if (ticks <= 10) {
                    color = Formatting.DARK_GREEN;
                }
                if (ticks <= 5) {
                    color = Formatting.GREEN;
                }
                return Text.translatable("config.citresewn.cache_ms.ticks.any", ticks).formatted(color);
            }
        }

    }


}
