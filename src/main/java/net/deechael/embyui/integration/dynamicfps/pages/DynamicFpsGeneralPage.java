package net.deechael.embyui.integration.dynamicfps.pages;

import com.google.common.collect.ImmutableList;
import dynamic_fps.impl.Constants;
import dynamic_fps.impl.config.DynamicFPSConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import org.embeddedt.embeddium.api.options.control.ControlValueFormatter;
import org.embeddedt.embeddium.api.options.control.SliderControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import net.deechael.embyui.integration.dynamicfps.DynamicFpsGeneralStorage;

import java.util.ArrayList;
import java.util.List;

public class DynamicFpsGeneralPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "general"));

    public DynamicFpsGeneralPage() {
        super(ID, Component.translatable("config.dynamic_fps.category.general"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "enabled_section"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, DynamicFpsGeneralStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "enabled"))
                                        .setName(Component.translatable("config.dynamic_fps.enabled"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(DynamicFPSConfig::setEnabled,
                                                DynamicFPSConfig::enabled)
                                        .build()
                        )
                        .build()
        );
        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "misc"))
                        .add(
                                OptionImpl.createBuilder(int.class, DynamicFpsGeneralStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "idle_timeout"))
                                        .setName(Component.translatable("config.dynamic_fps.idle_time"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.idle_time_tooltip"))
                                        .setControl(option -> new SliderControl(option, 0, 30, 1, IdleControlValueFormatter.INSTANCE))
                                        .setBinding(DynamicFPSConfig::setIdleTime,
                                                DynamicFPSConfig::idleTime)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, DynamicFpsGeneralStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "uncap_menu_fps"))
                                        .setName(Component.translatable("config.dynamic_fps.uncap_menu_frame_rate"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.uncap_menu_frame_rate_tooltip"))
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
        public Component format(int value) {
            return value <= 0 ? Component.translatable("config.dynamic_fps.disabled") : Component.translatable("config.dynamic_fps.minutes", value);
        }

    }

}
