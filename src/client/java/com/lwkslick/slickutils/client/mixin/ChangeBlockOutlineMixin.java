package com.lwkslick.slickutils.client.mixin;

import com.lwkslick.slickutils.client.ConfigManager;
import net.minecraft.client.render.*;
import net.minecraft.client.render.state.WorldRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class ChangeBlockOutlineMixin {

    @Unique
    private VertexConsumerProvider.Immediate consumer;

    @Inject(method = "renderTargetBlockOutline", at = @At("HEAD"))
    private void getConsumer(VertexConsumerProvider.Immediate immediate, MatrixStack matrices, boolean renderBlockOutline, WorldRenderState renderStates, CallbackInfo ci) {
        consumer = immediate;
    }

    @ModifyVariable(method = "drawBlockOutline", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int changeColor(int original) {
        if (!ConfigManager.config.blockOutlineEnabled) {
            return original;
        }

        return ConfigManager.config.blockOutlineColor;
    }

    @ModifyVariable(method = "drawBlockOutline", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private VertexConsumer changeColor(VertexConsumer original) {
        if (!ConfigManager.config.blockOutlineEnabled) {
            return original;
        }

        var layer = ConfigManager.config.blockOutlineThick ? RenderLayers.secondaryBlockOutline() : RenderLayers.lines();
        return consumer.getBuffer(layer);
    }
}