package net.deechael.embyui.integration.esf;

import org.embeddedt.embeddium.api.options.structure.OptionStorage;
import traben.entity_model_features.EMF;
import traben.entity_sound_features.ESF;
import traben.entity_sound_features.ESFConfig;

public class EsfOptionsStorage implements OptionStorage<ESFConfig> {

    public static final EsfOptionsStorage INSTANCE = new EsfOptionsStorage();

    @Override
    public ESFConfig getData() {
        return ESF.config().getConfig();
    }

    @Override
    public void save() {
        EMF.config().saveToFile();
    }

}
