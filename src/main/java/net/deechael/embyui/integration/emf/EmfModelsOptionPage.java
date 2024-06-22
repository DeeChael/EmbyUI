package net.deechael.embyui.integration.emf;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.control.CyclingControl;
import org.embeddedt.embeddium.api.options.control.SliderControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import traben.entity_model_features.EMF;
import traben.entity_model_features.config.EMFConfig;
import traben.entity_texture_features.ETF;
import traben.entity_texture_features.config.ETFConfig;

import java.util.ArrayList;
import java.util.List;

public class EmfModelsOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "models"));

    public EmfModelsOptionPage() {
        super(ID, Component.translatable("options.embyui.emf.models"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "options_n_fixes"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "force_models"))
                                        .setName(Component.translatable("entity_model_features.config.force_models"))
                                        .setTooltip(Component.translatable("entity_model_features.config.force_models.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.attemptRevertingEntityModelsAlteredByAnotherMod = value,
                                                (options) -> options.attemptRevertingEntityModelsAlteredByAnotherMod)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(EMFConfig.PhysicsModCompatChoice.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "physics"))
                                        .setName(Component.translatable("entity_model_features.config.physics"))
                                        .setTooltip(Component.translatable("entity_model_features.config.physics.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, EMFConfig.PhysicsModCompatChoice.class, new Component[] {
                                                CommonComponents.OPTION_OFF,
                                                Component.translatable("entity_model_features.config.physics.1"),
                                                Component.translatable("entity_model_features.config.physics.2")
                                        }))
                                        .setBinding((options, value) -> options.attemptPhysicsModPatch_2 = value,
                                                (options) -> options.attemptPhysicsModPatch_2)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "ebe_config_modify"))
                                        .setName(Component.translatable("entity_model_features.config.ebe_config_modify"))
                                        .setTooltip(Component.translatable("entity_model_features.config.ebe_config_modify.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.allowEBEModConfigModify = value,
                                                (options) -> options.allowEBEModConfigModify)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "player"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "prevent_hand"))
                                        .setName(Component.translatable("entity_model_features.config.prevent_hand"))
                                        .setTooltip(Component.translatable("entity_model_features.config.prevent_hand.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.preventFirstPersonHandAnimating = value,
                                                (options) -> options.preventFirstPersonHandAnimating)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "only_client"))
                                        .setName(Component.translatable("entity_model_features.config.only_client"))
                                        .setTooltip(Component.translatable("entity_model_features.config.only_client.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.onlyClientPlayerModel = value,
                                                (options) -> options.onlyClientPlayerModel)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "performance"))
                        .add(
                                OptionImpl.createBuilder(ETFConfig.UpdateFrequency.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "update"))
                                        .setName(Component.translatable("entity_model_features.config.update"))
                                        .setTooltip(Component.translatable("entity_model_features.config.update.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ETFConfig.UpdateFrequency.class, new Component[] {
                                                Component.translatable("config.entity_texture_features.update_frequency.never"),
                                                Component.translatable("config.entity_texture_features.update_frequency.slow"),
                                                Component.translatable("config.entity_texture_features.update_frequency.average"),
                                                Component.translatable("config.entity_texture_features.update_frequency.fast"),
                                                Component.translatable("config.entity_texture_features.update_frequency.instant")
                                        }))
                                        .setBinding((options, value) -> options.modelUpdateFrequency = value,
                                                (options) -> options.modelUpdateFrequency)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "lod"))
                                        .setName(Component.translatable("entity_model_features.config.lod"))
                                        .setTooltip(Component.translatable("entity_model_features.config.lod.tooltip"))
                                        .setControl(option -> new SliderControl(option, 0, 65, 1, value -> value % 65 != 0 ? Component.literal(String.valueOf(value)) : CommonComponents.OPTION_OFF))
                                        .setBinding((options, value) -> options.animationLODDistance = value,
                                                (options) -> options.animationLODDistance)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "low_fps_lod"))
                                        .setName(Component.translatable("entity_model_features.config.low_fps_lod"))
                                        .setTooltip(Component.translatable("entity_model_features.config.low_fps_lod.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.retainDetailOnLowFps = value,
                                                (options) -> options.retainDetailOnLowFps)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "large_mob_lod"))
                                        .setName(Component.translatable("entity_model_features.config.large_mob_lod"))
                                        .setTooltip(Component.translatable("entity_model_features.config.large_mob_lod.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.retainDetailOnLargerMobs = value,
                                                (options) -> options.retainDetailOnLargerMobs)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "tools"))
                        .add(
                                OptionImpl.createBuilder(EMFConfig.VanillaModelRenderMode.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "vanilla_render"))
                                        .setName(Component.translatable("entity_model_features.config.vanilla_render"))
                                        .setTooltip(Component.translatable("entity_model_features.config.vanilla_render.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, EMFConfig.VanillaModelRenderMode.class, new Component[] {
                                                CommonComponents.OPTION_OFF,
                                                Component.translatable("entity_model_features.config.vanilla_render.normal"),
                                                Component.translatable("entity_model_features.config.vanilla_render.offset")
                                        }))
                                        .setBinding((options, value) -> options.vanillaModelHologramRenderMode_2 = value,
                                                (options) -> options.vanillaModelHologramRenderMode_2)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(EMFConfig.ModelPrintMode.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "print_mode"))
                                        .setName(Component.translatable("entity_model_features.config.print_mode"))
                                        .setTooltip(Component.translatable("entity_model_features.config.print_mode.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, EMFConfig.ModelPrintMode.class, new Component[] {
                                                CommonComponents.OPTION_OFF,
                                                Component.translatable("entity_model_features.config.print_mode.log"),
                                                Component.translatable("entity_model_features.config.print_mode.log_jem"),
                                                Component.translatable("entity_model_features.config.print_mode.all_log"),
                                                Component.translatable("entity_model_features.config.print_mode.all_log_jem")
                                        }))
                                        .setBinding((options, value) -> options.modelExportMode = value,
                                                (options) -> options.modelExportMode)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "debugging"))
                        .add(
                                OptionImpl.createBuilder(EMFConfig.RenderModeChoice.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "render"))
                                        .setName(Component.translatable("entity_model_features.config.render"))
                                        .setTooltip(Component.translatable("entity_model_features.config.render.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, EMFConfig.RenderModeChoice.class, new Component[] {
                                                Component.translatable("entity_model_features.config.render.normal"),
                                                Component.translatable("entity_model_features.config.render.green"),
                                                Component.translatable("entity_model_features.config.render.lines_texture"),
                                                Component.translatable("entity_model_features.config.render.lines_texture_flash"),
                                                Component.translatable("entity_model_features.config.render.lines"),
                                                Component.translatable("entity_model_features.config.render.none")
                                        }))
                                        .setBinding((options, value) -> options.renderModeChoice = value,
                                                (options) -> options.renderModeChoice)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "log_models"))
                                        .setName(Component.translatable("entity_model_features.config.log_models"))
                                        .setTooltip(Component.translatable("entity_model_features.config.log_models.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.logModelCreationData = value,
                                                (options) -> options.logModelCreationData)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EmfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(EMF.MOD_ID, "debug_right_click"))
                                        .setName(Component.translatable("entity_model_features.config.debug_right_click"))
                                        .setTooltip(Component.translatable("entity_model_features.config.debug_right_click.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.debugOnRightClick = value,
                                                (options) -> options.debugOnRightClick)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
