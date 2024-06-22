package net.deechael.embyui.integration.emf;

import org.embeddedt.embeddium.api.options.structure.OptionStorage;
import traben.entity_model_features.EMF;
import traben.entity_model_features.config.EMFConfig;

public class EmfOptionsStorage implements OptionStorage<EMFConfig> {

    public static final EmfOptionsStorage INSTANCE = new EmfOptionsStorage();

    @Override
    public EMFConfig getData() {
        return EMF.config().getConfig();
    }

    @Override
    public void save() {
        EMF.config().saveToFile();
    }

}
