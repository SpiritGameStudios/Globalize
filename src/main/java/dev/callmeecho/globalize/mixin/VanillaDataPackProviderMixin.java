package dev.callmeecho.globalize.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.callmeecho.globalize.main.Globalize;
import dev.callmeecho.globalize.main.ResourcePackManagerAccessor;
import net.minecraft.resource.*;
import net.minecraft.util.path.SymlinkFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.nio.file.Path;

@Mixin(VanillaDataPackProvider.class)
public class VanillaDataPackProviderMixin {
    @WrapOperation(
            method = "createManager(Lnet/minecraft/world/level/storage/LevelStorage$Session;)Lnet/minecraft/resource/ResourcePackManager;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/VanillaDataPackProvider;createManager(Ljava/nio/file/Path;Lnet/minecraft/util/path/SymlinkFinder;)Lnet/minecraft/resource/ResourcePackManager;")
    )
    private static ResourcePackManager createManager(Path dataPacksPath, SymlinkFinder symlinkFinder, Operation<ResourcePackManager> original) {
        ResourcePackManager value = original.call(dataPacksPath, symlinkFinder);
        ((ResourcePackManagerAccessor)value).globalize$addProvider(new FileResourcePackProvider(Globalize.dataPackFolder, ResourceType.SERVER_DATA, ResourcePackSource.WORLD, symlinkFinder));
        return value;
    }
}
