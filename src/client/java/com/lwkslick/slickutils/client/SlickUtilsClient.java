package com.lwkslick.slickutils.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SlickUtilsClient implements ClientModInitializer {

	public static KeyBinding openConfigKey;

	@Override
	public void onInitializeClient() {
		UpdateChecker.checkAsync();

		openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.slickutils.open_config",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				"category.slickutils.general"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openConfigKey.wasPressed()) {
				if (client.currentScreen == null) {
					client.setScreen(SlickUtilsConfigScreen.create(null));
				}
			}
		});
	}
}