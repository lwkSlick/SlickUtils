package com.lwkslick.slickutils.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

/**
 * Ported from FireClient's ToggleToggleSneakModule.
 * Pressing the keybind (while the module is enabled) flips vanilla's
 * "Toggle Sneak" option and shows a colored action bar message.
 */
public class ToggleSneakHandler {

    public static KeyBinding useToggleSneakKey;

    private static final int OFF_COLOR_1 = 0xF72121;
    private static final int OFF_COLOR_2 = 0xB01212;
    private static final int ON_COLOR_1 = 0x2FD827;
    private static final int ON_COLOR_2 = 0x1C9E15;

    public static void register() {
        useToggleSneakKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.slickutils.use_toggle_sneak",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                SlickUtilsClient.CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (useToggleSneakKey.wasPressed()) {
                useKey();
            }
        });
    }

    private static void useKey() {
        if (!ConfigManager.config.toggleSneakEnabled) {
            return;
        }

        var client = MinecraftClient.getInstance();
        if (client.player == null) {
            return;
        }

        boolean toggled = !client.options.getSneakToggled().getValue();
        client.options.getSneakToggled().setValue(toggled);

        MutableText message = toggled
                ? gradientText("Sneak Toggled: On", ON_COLOR_1, ON_COLOR_2)
                : gradientText("Sneak Toggled: Off", OFF_COLOR_1, OFF_COLOR_2);

        client.player.sendMessage(message, true);
    }

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