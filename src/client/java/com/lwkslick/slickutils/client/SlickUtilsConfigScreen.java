package com.lwkslick.slickutils.client;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SlickUtilsConfigScreen {

    private static boolean placeholderToggle = false;

    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("SlickUtils"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("General"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Placeholder"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Example Toggle"))
                                        .binding(false, () -> placeholderToggle, v -> placeholderToggle = v)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .save(() -> {})
                .build()
                .generateScreen(parent);
    }
}