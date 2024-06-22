package net.deechael.embyui.integration.etf;

import org.embeddedt.embeddium.api.options.structure.OptionStorage;
import traben.entity_texture_features.ETF;
import traben.entity_texture_features.config.ETFConfig;

public class EtfOptionsStorage implements OptionStorage<ETFConfig> {

    public static final EtfOptionsStorage INSTANCE = new EtfOptionsStorage();

    @Override
    public ETFConfig getData() {
        return ETF.config().getConfig();
    }

    @Override
    public void save() {
        ETF.config().saveToFile();
    }

}
