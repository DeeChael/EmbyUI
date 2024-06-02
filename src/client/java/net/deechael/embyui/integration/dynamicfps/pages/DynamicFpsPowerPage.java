package net.deechael.embyui.integration.dynamicfps.pages;

import com.google.common.collect.ImmutableList;
import dynamic_fps.impl.Constants;
import dynamic_fps.impl.DynamicFPSMod;
import dynamic_fps.impl.GraphicsState;
import dynamic_fps.impl.PowerState;
import dynamic_fps.impl.config.Config;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.deechael.embyui.integration.dynamicfps.DynamicFpsPowerStorage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DynamicFpsPowerPage extends OptionPage {

    public DynamicFpsPowerPage(PowerState powerState) {
        super(OptionIdentifier.create(new Identifier(Constants.MOD_ID, powerState.name().toLowerCase())), Text.translatable("config.dynamic_fps.category." + powerState.name().toLowerCase()), create(powerState.name().toLowerCase(), new DynamicFpsPowerStorage(DynamicFPSMod.modConfig.get(powerState))));
    }

    private static ImmutableList<OptionGroup> create(String powerState, DynamicFpsPowerStorage storage) {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(int.class, storage)
                                        .setId(new Identifier(Constants.MOD_ID, powerState + "_frame_rate_target"))
                                        .setName(Text.translatable("config.dynamic_fps.frame_rate_target"))
                                        .setTooltip(Text.translatable("config.dynamic_fps.frame_rate_target"))
                                        .setControl(option -> new SliderControl(option, 0, 61, 1, FrameRateControlValueFormatter.INSTANCE))
                                        .setBinding(Config::setFrameRateTarget,
                                                Config::frameRateTarget)
                                        .build()
                        )
                        .build()
        );

        OptionGroup.Builder volume = OptionGroup.createBuilder();
        for (SoundCategory source : SoundCategory.values()) {
            String name = source.getName();

            volume.add(
                    OptionImpl.createBuilder(int.class, storage)
                            .setId(new Identifier(Constants.MOD_ID, powerState + "_volume_" + name))
                            .setName(Text.translatable("soundCategory." + name))
                            .setTooltip(Text.translatable("soundCategory." + name))
                            .setControl(option -> new SliderControl(option, 0, 100, 1, ControlValueFormatter.percentage()))
                            .setBinding((option, value) -> option.setVolumeMultiplier(source, value / 100f),
                                    option -> (int) (option.volumeMultiplier(source) * 100))
                            .build()
            );
        }
        groups.add(volume.build());

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(GraphicsState.class, storage)
                                        .setId(new Identifier(Constants.MOD_ID, powerState + "_graphics_option"))
                                        .setName(Text.translatable("config.dynamic_fps.graphics_state"))
                                        .setTooltip(
                                                Text.translatable("config.dynamic_fps.graphics_state_minimal_tooltip")
                                        )
                                        .setControl((opt) -> new CyclingControl<>(opt, GraphicsState.class, new Text[]{
                                                Text.translatable("config.dynamic_fps.graphics_state_default"),
                                                Text.translatable("config.dynamic_fps.graphics_state_reduced"),
                                                Text.translatable("config.dynamic_fps.graphics_state_minimal")
                                        }))
                                        .setBinding(Config::setGraphicsState, Config::graphicsState)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, storage)
                                        .setId(new Identifier(Constants.MOD_ID, powerState + "_show_toasts"))
                                        .setName(Text.translatable("config.dynamic_fps.show_toasts"))
                                        .setTooltip(Text.translatable("config.dynamic_fps.show_toasts_tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding(Config::setShowToasts, Config::showToasts)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, storage)
                                        .setId(new Identifier(Constants.MOD_ID, powerState + "_invoke_garbage_collector"))
                                        .setName(Text.translatable("config.dynamic_fps.run_garbage_collector"))
                                        .setTooltip(Text.translatable("config.dynamic_fps.run_garbage_collector_tooltip"))
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
        public Text format(int value) {
            return value > 60 ? Text.translatable("options.framerateLimit.max") : Text.translatable("options.framerate", value);
        }

    }

}
