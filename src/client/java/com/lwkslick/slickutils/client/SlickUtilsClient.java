package com.lwkslick.slickutils.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

public class SlickUtilsClient implements ClientModInitializer {

	public static final KeyBinding.Category CATEGORY =
			KeyBinding.Category.create(Identifier.of("slickutils", "general"));
	public static KeyBinding openConfigKey;

	@Override
	public void onInitializeClient() {
		UpdateChecker.checkAsync();
		ConfigManager.load();
		ToggleSneakHandler.register();

		openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.slickutils.open_config",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_SHIFT,
				CATEGORY
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openConfigKey.wasPressed()) {
				if (client.currentScreen == null) {
					client.setScreen(SlickUtilsConfigScreen.create(null));
				}
			}
		});

		HudRenderCallback.EVENT.register((context, tickCounter) -> {
			FPSDisplayModule.draw(context, tickCounter);
		});
	}
}