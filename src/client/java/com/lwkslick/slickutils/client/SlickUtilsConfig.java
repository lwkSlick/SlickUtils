package com.lwkslick.slickutils.client;

/**
 * Holds all of SlickUtils' persistent settings.
 * One boolean per module toggle, plus position/scale for HUD elements
 * that can be moved around with the "Arrange Items" screen.
 */
public class SlickUtilsConfig {

    // --- Module enabled toggles ---
    public boolean fpsDisplayEnabled = false;
    public boolean toggleSneakEnabled = false;
    public boolean fullbrightEnabled = false;
    public boolean blockOutlineEnabled = false;
    public boolean nametagsEnabled = false;
    public boolean scrollClickEnabled = false;
    public boolean armorDisplayEnabled = false;

    // --- HUD element position/scale (draggable via Arrange Items) ---
    // FPS Display
    public double fpsDisplayPosX = 4;
    public double fpsDisplayPosY = 4;
    public double fpsDisplayScale = 1.0;

    // Armor Display
    public double armorDisplayPosX = 4;
    public double armorDisplayPosY = 20;
    public double armorDisplayScale = 1.0;
}