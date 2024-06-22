package net.deechael.embyui.integration.etf;

import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.control.ControlValueFormatter;
import org.embeddedt.embeddium.api.options.control.SliderControl;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import org.embeddedt.embeddium.api.options.control.CyclingControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import traben.entity_texture_features.ETF;
import traben.entity_texture_features.config.ETFConfig;

import java.util.ArrayList;
import java.util.List;

public class EtfTexturesOptionPage extends OptionPage {

    public static final OptionIdentifier<Void> ID = OptionIdentifier.create(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "textures"));

    public EtfTexturesOptionPage() {
        super(ID, Component.translatable("options.embyui.etf.textures"), create());
    }

    private static ImmutableList<OptionGroup> create() {
        final List<OptionGroup> groups = new ArrayList<>();

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "random_n_custom"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "enabled"))
                                        .setName(Component.translatable("config.entity_texture_features.enable_custom_textures.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.enable_custom_textures.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableCustomTextures = value,
                                                (options) -> options.enableCustomTextures)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ETFConfig.UpdateFrequency.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "update_frequency"))
                                        .setName(Component.translatable("config.entity_texture_features.texture_update_frequency.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.texture_update_frequency.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ETFConfig.UpdateFrequency.class, new Component[] {
                                                Component.translatable("config.entity_texture_features.update_frequency.never"),
                                                Component.translatable("config.entity_texture_features.update_frequency.slow"),
                                                Component.translatable("config.entity_texture_features.update_frequency.average"),
                                                Component.translatable("config.entity_texture_features.update_frequency.fast"),
                                                Component.translatable("config.entity_texture_features.update_frequency.instant")
                                        }))
                                        .setBinding((options, value) -> options.textureUpdateFrequency_V2 = value,
                                                (options) -> options.textureUpdateFrequency_V2)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "custom_block_entity"))
                                        .setName(Component.translatable("config.entity_texture_features.custom_block_entity.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.custom_block_entity.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableCustomBlockEntities = value,
                                                (options) -> options.enableCustomBlockEntities)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "disable_default_directory"))
                                        .setName(Component.translatable("config.entity_texture_features.disable_default_directory.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.disable_default_directory.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.disableVanillaDirectoryVariantTextures = value,
                                                (options) -> options.disableVanillaDirectoryVariantTextures)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "emissive"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "enable_emissive_textures"))
                                        .setName(Component.translatable("config.entity_texture_features.enable_emissive_textures.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.enable_emissive_textures.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableEmissiveTextures = value,
                                                (options) -> options.enableEmissiveTextures)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "emissive_block_entity"))
                                        .setName(Component.translatable("config.entity_texture_features.emissive_block_entity.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.emissive_block_entity.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableEmissiveBlockEntities = value,
                                                (options) -> options.enableEmissiveBlockEntities)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ETFConfig.EmissiveRenderModes.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "emissive_mode"))
                                        .setName(Component.translatable("config.entity_texture_features.emissive_mode.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.emissive_mode.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ETFConfig.EmissiveRenderModes.class, new Component[] {
                                                Component.translatable("config.entity_texture_features.emissive_mode.dull"),
                                                Component.translatable("config.entity_texture_features.emissive_mode.bright")
                                        }))
                                        .setBinding((options, value) -> options.emissiveRenderMode = value,
                                                (options) -> options.emissiveRenderMode)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "always_check_vanilla_emissive_suffix"))
                                        .setName(Component.translatable("config.entity_texture_features.always_check_vanilla_emissive_suffix.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.always_check_vanilla_emissive_suffix.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.alwaysCheckVanillaEmissiveSuffix = value,
                                                (options) -> options.alwaysCheckVanillaEmissiveSuffix)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "armor_enable"))
                                        .setName(Component.translatable("config.entity_texture_features.armor_enable"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.armor_enable.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableArmorAndTrims = value,
                                                (options) -> options.enableArmorAndTrims)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "enchanted_enable"))
                                        .setName(Component.translatable("config.entity_texture_features.enchanted_enable"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.enchanted_enable.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableEnchantedTextures = value,
                                                (options) -> options.enableEnchantedTextures)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "player_skins"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "player_skin_features"))
                                        .setName(Component.translatable("config.entity_texture_features.player_skin_features.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.player_skin_features.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.skinFeaturesEnabled = value,
                                                (options) -> options.skinFeaturesEnabled)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(ETFConfig.SkinTransparencyMode.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "transparent_skins_mode"))
                                        .setName(Component.translatable("config.entity_texture_features.transparent_skins.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.transparent_skins.tooltip"))
                                        .setControl((opt) -> new CyclingControl<>(opt, ETFConfig.SkinTransparencyMode.class, new Component[] {
                                                Component.translatable("config.entity_texture_features.transparent_skins.vanilla"),
                                                Component.translatable("config.entity_texture_features.transparent_skins.etf"),
                                                Component.translatable("config.entity_texture_features.transparent_skins.all")
                                        }))
                                        .setBinding((options, value) -> options.skinTransparencyMode = value,
                                                (options) -> options.skinTransparencyMode)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "transparent_skins_extra"))
                                        .setName(Component.translatable("config.entity_texture_features.transparent_skins_extra.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.transparent_skins_extra.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.skinTransparencyInExtraPixels = value,
                                                (options) -> options.skinTransparencyInExtraPixels)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "enable_enemy_team_players_skin_features"))
                                        .setName(Component.translatable("config.entity_texture_features.enable_enemy_team_players_skin_features.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.enable_enemy_team_players_skin_features.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableEnemyTeamPlayersSkinFeatures = value,
                                                (options) -> options.enableEnemyTeamPlayersSkinFeatures)
                                        .build()
                        )
                        .build()
        );

        groups.add(
                OptionGroup.createBuilder()
                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "blinking"))
                        .add(
                                OptionImpl.createBuilder(boolean.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "blinking_mob_settings"))
                                        .setName(Component.translatable("config.entity_texture_features.blinking_mob_settings.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.blinking_mob_settings.tooltip"))
                                        .setControl(TickBoxControl::new)
                                        .setBinding((options, value) -> options.enableBlinking = value,
                                                (options) -> options.enableBlinking)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "blink_frequency"))
                                        .setName(Component.translatable("config.entity_texture_features.blink_frequency.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.blink_frequency.tooltip"))
                                        .setControl(option -> new SliderControl(option, 1, 1024, 1, ControlValueFormatter.number()))
                                        .setBinding((options, value) -> options.blinkFrequency = value,
                                                (options) -> options.blinkFrequency)
                                        .build()
                        )
                        .add(
                                OptionImpl.createBuilder(int.class, EtfOptionsStorage.INSTANCE)
                                        .setId(ResourceLocation.fromNamespaceAndPath(ETF.MOD_ID, "blink_length"))
                                        .setName(Component.translatable("config.entity_texture_features.blink_length.title"))
                                        .setTooltip(Component.translatable("config.entity_texture_features.blink_length.tooltip"))
                                        .setControl(option -> new SliderControl(option, 1, 20, 1, ControlValueFormatter.number()))
                                        .setBinding((options, value) -> options.blinkLength = value,
                                                (options) -> options.blinkLength)
                                        .build()
                        )
                        .build()
        );

        return ImmutableList.copyOf(groups);
    }

}
