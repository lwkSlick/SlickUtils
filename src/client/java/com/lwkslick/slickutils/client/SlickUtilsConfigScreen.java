package com.lwkslick.slickutils.client;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SlickUtilsConfigScreen {

    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("SlickUtils"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("HUD"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("FPS Display"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                                false,
                                                () -> ConfigManager.config.fpsDisplayEnabled,
                                                v -> ConfigManager.config.fpsDisplayEnabled = v
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("PvP"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Toggle Sneak"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                                false,
                                                () -> ConfigManager.config.toggleSneakEnabled,
                                                v -> ConfigManager.config.toggleSneakEnabled = v
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Visual"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Fullbright"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                                false,
                                                () -> ConfigManager.config.fullbrightEnabled,
                                                v -> ConfigManager.config.fullbrightEnabled = v
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Block Outline"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enabled"))
                                        .binding(
                                                false,
                                                () -> ConfigManager.config.blockOutlineEnabled,
                                                v -> ConfigManager.config.blockOutlineEnabled = v
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Thick Outline"))
                                        .binding(
                                                false,
                                                () -> ConfigManager.config.blockOutlineThick,
                                                v -> ConfigManager.config.blockOutlineThick = v
                                        )
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .save(ConfigManager::save)
                .build()
                .generateScreen(parent);
    }
}