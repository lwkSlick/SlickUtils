package com.lwkslick.slickutils.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Loads and saves SlickUtilsConfig to config/slickutils.json
 */
public class ConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("slickutils.json");

    public static SlickUtilsConfig config = new SlickUtilsConfig();

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH, StandardCharsets.UTF_8)) {
            SlickUtilsConfig loaded = GSON.fromJson(reader, SlickUtilsConfig.class);
            if (loaded != null) {
                config = loaded;
            }
        } catch (IOException e) {
            com.lwkslick.slickutils.SlickUtils.LOGGER.warn("[SlickUtils] Failed to load config, using defaults", e);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH, StandardCharsets.UTF_8)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException ignored) {
        }
    }
}