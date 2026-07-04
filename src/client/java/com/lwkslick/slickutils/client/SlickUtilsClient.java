package com.lwkslick.slickutils.client;

import net.fabricmc.api.ClientModInitializer;

public class SlickUtilsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		UpdateChecker.checkAsync();
	}
}