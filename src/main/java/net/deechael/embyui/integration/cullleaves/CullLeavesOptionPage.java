package net.deechael.embyui.integration.cullleaves;

import com.google.common.collect.ImmutableList;
import eu.midnightdust.cullleaves.config.CullLeavesConfig;
import net.deechael.embyui.integration.etf.EtfOptionsStorage;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import traben.entity_texture_features.ETF;

import java.util.ArrayList;
import java.util.List;

public class CullLeavesOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath("cullleaves", "leaves"));

    public CullLeavesOptionPage() {
        super(ID, Component.translatable("options.embyui.cullleaves.leaves"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath("cullleaves", "general"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, CullLeavesOptionsStorage.STORAGE)
                                        .setId(ResourceLocation.fromNamespaceAndPath("cullleaves", "enabled"))
                                        .setName(Component.translatable("cullleaves.midnightconfig.enabled"))
                                        .setTooltip(Component.translatable("cullleaves.midnightconfig.enabled.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> CullLeavesConfig.enabled = value,
                                                (options) -> CullLeavesConfig.enabled)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, CullLeavesOptionsStorage.STORAGE)
                                        .setId(ResourceLocation.fromNamespaceAndPath("cullleaves", "cull_roots"))
                                        .setName(Component.translatable("cullleaves.midnightconfig.cullRoots"))
                                        .setTooltip(Component.translatable("cullleaves.midnightconfig.cullRoots.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> CullLeavesConfig.cullRoots = value,
                                                (options) -> CullLeavesConfig.cullRoots)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
