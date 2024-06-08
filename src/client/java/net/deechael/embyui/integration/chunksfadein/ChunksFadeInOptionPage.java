package net.deechael.embyui.integration.chunksfadein;

import com.google.common.collect.ImmutableList;
import com.koteinik.chunksfadein.config.Config;
import com.koteinik.chunksfadein.core.Curves;
import com.koteinik.chunksfadein.core.FadeTypes;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class ChunksFadeInOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier("chunksfadein", "general"));

    public ChunksFadeInOptionPage() {
        super(ID, Text.translatable("options.embyui.chunksfadein.animations"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "enabled"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.mod_enabled"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.mod_enabled"))
                                        .setEnabled(!(MinecraftClient.getInstance().isInSingleplayer() || MinecraftClient.getInstance().getServer() != null))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.MOD_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.MOD_ENABLED_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "update_notifier"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.update_notifier_enabled"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.update_notifier_enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.UPDATE_NOTIFIER_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.UPDATE_NOTIFIER_ENABLED_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "mod_button_in_settings"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.mod_button_enabled"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.mod_button_enabled"))
                                        .setEnabled(FabricLoader.getInstance().isModLoaded("modmenu"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.SHOW_MOD_BUTTON_IN_SETTINGS_KEY, value),
                                                (options) -> Config.getBoolean(Config.SHOW_MOD_BUTTON_IN_SETTINGS_KEY))
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()

                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "fade_enabled"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.fade_enabled"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.fade_enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.FADE_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.FADE_ENABLED_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(FadeTypes.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "fade_type"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.fade_type"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.fade_type"))
                                        .setControl((opt) -> new CyclingControl<>(opt, FadeTypes.class, new Text[]{
                                                Text.translatable("options.embyui.chunksfadein.fade_type.full"),
                                                Text.translatable("options.embyui.chunksfadein.fade_type.lined")
                                        }))
                                        .setBinding((options, value) -> Config.setInteger(Config.FADE_TYPE_KEY, value.ordinal()),
                                                (options) -> FadeTypes.values()[Config.getInteger(Config.FADE_TYPE_KEY)])
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "fade_time"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.fade_time"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.fade_time"))
                                        .setControl(option -> new SliderControl(option, 0, 1000, 1, value -> Text.translatable("options.embyui.chunksfadein.time_unit", String.valueOf(value / 100d))))
                                        .setBinding((options, value) -> Config.setDouble(Config.FADE_TIME_KEY, value / 100d),
                                                (options) -> Math.min(Math.max(0, (int) Config.getDouble(Config.FADE_TIME_KEY) * 100), 1000))
                                        .build()
                        )
                        .build()
        );
        groups.add(
                OptionGroup.createBuilder()

                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "animation_enabled"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.animation_enabled"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.animation_enabled"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.ANIMATION_ENABLED_KEY, value),
                                                (options) -> Config.getBoolean(Config.ANIMATION_ENABLED_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(Curves.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "animation_curve"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.animation_curve"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.animation_curve"))
                                        .setControl((opt) -> new CyclingControl<>(opt, Curves.class, new Text[]{
                                                Text.translatable("options.embyui.chunksfadein.animation_curve.linear"),
                                                Text.translatable("options.embyui.chunksfadein.animation_curve.ease_out"),
                                                Text.translatable("options.embyui.chunksfadein.animation_curve.ease_circular"),
                                                Text.translatable("options.embyui.chunksfadein.animation_curve.bounce")
                                        }))
                                        .setBinding((options, value) -> Config.setInteger(Config.ANIMATION_CURVE_KEY, value.ordinal()),
                                                (options) -> Curves.values()[Config.getInteger(Config.ANIMATION_CURVE_KEY)])
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "animation_start"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.animation_start"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.animation_start"))
                                        .setControl(option -> new SliderControl(option, 100, 31900, 1, value -> Text.translatable("options.embyui.chunksfadein.animation_start_unit", String.valueOf(value / 100d))))
                                        .setBinding((options, value) -> Config.setDouble(Config.ANIMATION_OFFSET_KEY, value / 100d),
                                                (options) -> Math.min(Math.max(0, (int) Config.getDouble(Config.ANIMATION_OFFSET_KEY) * 100), 31900))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "animate_near_player"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.animate_near_player"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.animate_near_player"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> Config.setBoolean(Config.ANIMATE_NEAR_PLAYER_KEY, value),
                                                (options) -> Config.getBoolean(Config.ANIMATE_NEAR_PLAYER_KEY))
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, ChunksFadeInOptionsStorage.INSTANCE)
                                        .setId(new Identifier("chunksfadein", "animation_time"))
                                        .setName(Text.translatable("options.embyui.chunksfadein.animation_time"))
                                        .setTooltip(Text.translatable("options.embyui.chunksfadein.animation_time"))
                                        .setControl(option -> new SliderControl(option, 0, 1000, 1, value -> Text.translatable("options.embyui.chunksfadein.time_unit", String.valueOf(value / 100d))))
                                        .setBinding((options, value) -> Config.setDouble(Config.ANIMATION_TIME_KEY, value / 100d),
                                                (options) -> Math.min(Math.max(0, (int) Config.getDouble(Config.ANIMATION_TIME_KEY) * 100), 1000))
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
