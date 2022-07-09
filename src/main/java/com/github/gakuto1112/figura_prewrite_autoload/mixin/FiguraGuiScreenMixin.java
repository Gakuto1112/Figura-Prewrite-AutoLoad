package com.github.gakuto1112.figura_prewrite_autoload.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.blancworks.figura.gui.FiguraGuiScreen;
import com.github.gakuto1112.figura_prewrite_autoload.FiguraPrewriteAutoload;

@Mixin(FiguraGuiScreen.class)
public class FiguraGuiScreenMixin {
    @Inject(at = @At("HEAD"), method = "loadLocalAvatar(Ljava/lang/Object;)V", remap = false)
    private void loadLocalAvatar(Object stuff, CallbackInfo callbackInfo) {
        if(stuff instanceof String str) FiguraPrewriteAutoload.configManager.setLocalAvatarPath(str);
    }
}