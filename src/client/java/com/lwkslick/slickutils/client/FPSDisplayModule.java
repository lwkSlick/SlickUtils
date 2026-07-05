package com.lwkslick.slickutils.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

/**
 * Ported from FireClient's FPSDisplayModule.
 * Draws current FPS as gradient-colored text at a draggable position.
 */
public class FPSDisplayModule {

    // Same two colors FireClient uses for the FPS text gradient
    private static final int COLOR_1 = 0xD3FFBF;
    private static final int COLOR_2 = 0xE8EBE6;

    public static void draw(DrawContext context, RenderTickCounter tickCounter) {
        if (!ConfigManager.config.fpsDisplayEnabled) {
            return;
        }

        var client = MinecraftClient.getInstance();
        var textRenderer = client.textRenderer;

        String msg = client.getCurrentFps() + " FPS";
        Text fpsText = gradientText(msg, COLOR_1, COLOR_2);

        int x = (int) ConfigManager.config.fpsDisplayPosX;
        int y = (int) ConfigManager.config.fpsDisplayPosY;

        context.drawText(textRenderer, fpsText, x, y, 0xFFFFFFFF, true);
    }

    /**
     * Blends color1 -> color2 across the characters of msg, same
     * approach FireClient's RooHelper.gradientText uses.
     */
    private static MutableText gradientText(String msg, int color1, int color2) {
        MutableText result = Text.literal("");

        int r1 = (color1 >> 16) & 0xFF, g1 = (color1 >> 8) & 0xFF, b1 = color1 & 0xFF;
        int r2 = (color2 >> 16) & 0xFF, g2 = (color2 >> 8) & 0xFF, b2 = color2 & 0xFF;

        for (int i = 0; i < msg.length(); i++) {
            double progress = (double) i / msg.length();

            int r = (int) (r1 + (r2 - r1) * progress);
            int g = (int) (g1 + (g2 - g1) * progress);
            int b = (int) (b1 + (b2 - b1) * progress);
            int blended = (r << 16) | (g << 8) | b;

            Style style = Style.EMPTY.withColor(blended);
            result.append(Text.literal(String.valueOf(msg.charAt(i))).setStyle(style));
        }

        return result;
    }
}