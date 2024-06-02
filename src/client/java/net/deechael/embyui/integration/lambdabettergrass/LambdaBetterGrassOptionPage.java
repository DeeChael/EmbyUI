package net.deechael.embyui.integration.lambdabettergrass;

import com.google.common.collect.ImmutableList;
import dev.lambdaurora.lambdabettergrass.LBGConfig;
import dev.lambdaurora.lambdabettergrass.LBGMode;
import dev.lambdaurora.lambdabettergrass.LambdaBetterGrass;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.CyclingControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.deechael.embyui.integration.ryoamiclights.RyoamicLightsOptionsStorage;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class LambdaBetterGrassOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(new Identifier(LambdaBetterGrass.NAMESPACE, "skyboxes"));

    public LambdaBetterGrassOptionPage() {
        super(ID, Text.translatable("options.embyui.lambdabettergrass.title"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .add(
                                OptionImpl.createBuilder(LBGMode.class, RyoamicLightsOptionsStorage.INSTANCE)
                                        .setId(new Identifier(LambdaBetterGrass.NAMESPACE, "mode"))
                                        .setName(Text.translatable("text.autoconfig.lambdabettergrass.option.mode"))
                                        .setTooltip(Text.translatable("text.autoconfig.lambdabettergrass.option.mode.@Tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, LBGMode.class, new Text[]{
                                                Text.translatable("options.embyui.lambdabettergrass.mode.off"),
                                                Text.translatable("options.embyui.lambdabettergrass.mode.fastest"),
                                                Text.translatable("options.embyui.lambdabettergrass.mode.fast"),
                                                Text.translatable("options.embyui.lambdabettergrass.mode.fancy")
                                        }))
                                        .setBinding((options, value) -> LBGConfig.INSTANCE.get().mode = value,
                                                (options) -> LBGConfig.INSTANCE.get().mode)
                                        .setImpact(OptionImpact.MEDIUM)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, LambdaBetterGrassOptionsStorage.INSTANCE)
                                        .setId(new Identifier(LambdaBetterGrass.NAMESPACE, "better_layer"))
                                        .setName(Text.translatable("text.autoconfig.lambdabettergrass.option.betterLayer"))
                                        .setTooltip(Text.translatable("text.autoconfig.lambdabettergrass.option.betterLayer.@Tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> LBGConfig.INSTANCE.get().betterLayer = value,
                                                (options) -> LBGConfig.INSTANCE.get().betterLayer)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
