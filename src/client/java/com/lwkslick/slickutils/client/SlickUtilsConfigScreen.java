package com.lwkslick.slickutils.client;

import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.client.gui.screen.Screen;

public class SlickUtilsConfigScreen {
    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(net.minecraft.text.Text.literal("SlickUtils"))
                .save(() -> {})
                .build()
                .generateScreen(parent);
    }
}