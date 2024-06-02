package net.deechael.embyui.integration.dynamicfps.pages;

import com.google.common.collect.ImmutableList;
import dynamic_fps.impl.Constants;
import dynamic_fps.impl.config.DynamicFPSConfig;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.deechael.embyui.integration.dynamicfps.DynamicFpsGeneralStorage;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DynamicFpsGeneralPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier(Constants.MOD_ID, "general"));

    public DynamicFpsGeneralPage() {
        super(ID, Text.translatable("config.dynamic_fps.category.general"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, DynamicFpsGeneralStorage.INSTANCE)
                                        .setId(new Identifier(Constants.MOD_ID, "enabled"))
                                        .setName(Text.translatable("config.dynamic_fps.enabled"))
                                        .setTooltip(Text.translatable("config.dynamic_fps.enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(DynamicFPSConfig::setEnabled,
                                                DynamicFPSConfig::enabled)
                                        .build()
                        )
                        .build()
        );
        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(int.class, DynamicFpsGeneralStorage.INSTANCE)
                                        .setId(new Identifier(Constants.MOD_ID, "idle_timeout"))
                                        .setName(Text.translatable("config.dynamic_fps.idle_time"))
                                        .setTooltip(Text.translatable("config.dynamic_fps.idle_time_tooltip"))
                                        .setControl(option -> new SliderControl(option, 0, 30, 1, IdleControlValueFormatter.INSTANCE))
                                        .setBinding(DynamicFPSConfig::setIdleTime,
                                                DynamicFPSConfig::idleTime)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, DynamicFpsGeneralStorage.INSTANCE)
                                        .setId(new Identifier(Constants.MOD_ID, "uncap_menu_fps"))
                                        .setName(Text.translatable("config.dynamic_fps.uncap_menu_frame_rate"))
                                        .setTooltip(Text.translatable("config.dynamic_fps.uncap_menu_frame_rate_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(DynamicFPSConfig::setUncapMenuFrameRate,
                                                DynamicFPSConfig::uncapMenuFrameRate)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

    private static class IdleControlValueFormatter implements ControlValueFormatter {

        private final static IdleControlValueFormatter INSTANCE = new IdleControlValueFormatter();

        @Override
        public Text format(int value) {
            return value <= 0 ? Text.translatable("config.dynamic_fps.disabled") : Text.translatable("config.dynamic_fps.minutes", value);
        }

    }

}
