package net.deechael.embyui.integration.dynamicfps.pages;

import com.google.common.collect.ImmutableList;
import dynamic_fps.impl.Constants;
import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.GraphicsState;
import dynamic_fps.impl.PowerState;
import dynamic_fps.impl.config.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import org.embeddedt.embeddium.api.options.control.ControlValueFormatter;
import org.embeddedt.embeddium.api.options.control.CyclingControl;
import org.embeddedt.embeddium.api.options.control.SliderControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import net.deechael.embyui.integration.dynamicfps.DynamicFpsPowerStorage;

import java.util.ArrayList;
import java.util.List;

public class DynamicFpsPowerPage extends OptionPage {

    public DynamicFpsPowerPage(PowerState powerState) {
        super(OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState.name().toLowerCase())), Component.translatable("config.dynamic_fps.category." + powerState.name().toLowerCase()), create(powerState.name().toLowerCase(), new DynamicFpsPowerStorage(DynamicFPSMod.modConfig.get(powerState))));
    }

    private static ImmutableList<OptionGroup> create(String powerState, DynamicFpsPowerStorage storage) {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_fps"))
                        .add(
                                OptionImpl.createBuilder(int.class, storage)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_frame_rate_target"))
                                        .setName(Component.translatable("config.dynamic_fps.frame_rate_target"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.frame_rate_target"))
                                        .setControl(option -> new SliderControl(option, 0, 61, 1, FrameRateControlValueFormatter.INSTANCE))
                                        .setBinding(Config::setFrameRateTarget,
                                                Config::frameRateTarget)
                                        .build()
                        )
                        .build()
        );

        OptionGroup.Builder volume = OptionGroup.createBuilder();
        volume.setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_sound"));
        for (SoundSource source : SoundSource.values()) {
            String name = source.getName();

            volume.add(
                    OptionImpl.createBuilder(int.class, storage)
                            .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_volume_" + name))
                            .setName(Component.translatable("soundCategory." + name))
                            .setTooltip(Component.translatable("soundCategory." + name))
                            .setControl(option -> new SliderControl(option, 0, 100, 1, ControlValueFormatter.percentage()))
                            .setBinding((option, value) -> option.setVolumeMultiplier(source, value / 100f),
                                    option -> (int) (option.volumeMultiplier(source) * 100))
                            .build()
            );
        }
        groups.add(volume.build());

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_options"))
                        .add(
                                OptionImpl.createBuilder(GraphicsState.class, storage)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_graphics_option"))
                                        .setName(Component.translatable("config.dynamic_fps.graphics_state"))
                                        .setTooltip(
                                                Component.translatable("config.dynamic_fps.graphics_state_minimal_tooltip")
                                        )
                                        .setControl((opt) -> new CyclingControl<>(opt, GraphicsState.class, new Component[]{
                                                Component.translatable("config.dynamic_fps.graphics_state_default"),
                                                Component.translatable("config.dynamic_fps.graphics_state_reduced"),
                                                Component.translatable("config.dynamic_fps.graphics_state_minimal")
                                        }))
                                        .setBinding(Config::setGraphicsState, Config::graphicsState)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, storage)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_show_toasts"))
                                        .setName(Component.translatable("config.dynamic_fps.show_toasts"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.show_toasts_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(Config::setShowToasts, Config::showToasts)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, storage)
                                        .setId(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, powerState + "_invoke_garbage_collector"))
                                        .setName(Component.translatable("config.dynamic_fps.run_garbage_collector"))
                                        .setTooltip(Component.translatable("config.dynamic_fps.run_garbage_collector_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(Config::setRunGarbageCollector, Config::runGarbageCollector)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

    private static class FrameRateControlValueFormatter implements ControlValueFormatter {

        private final static FrameRateControlValueFormatter INSTANCE = new FrameRateControlValueFormatter();

        @Override
        public Component format(int value) {
            return value > 60 ? Component.translatable("options.framerateLimit.max") : Component.translatable("options.framerate", value);
        }

    }

}
