package dev.callmeecho.globalize.main;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Globalize implements ModInitializer {
    public static final String MODID = "globalize";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static final Path dataPackFolder = FabricLoader.getInstance().getGameDir().resolve("datapacks");

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onInitialize() {
        dataPackFolder.toFile().mkdirs();
    }
}
