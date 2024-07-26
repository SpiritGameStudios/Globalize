package dev.callmeecho.globalize.mixin;

import dev.callmeecho.globalize.main.Globalize;
import net.minecraft.resource.*;
import net.minecraft.util.path.SymlinkFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.nio.file.Path;

@Mixin(VanillaDataPackProvider.class)
public class VanillaDataPackProviderMixin {
    @Redirect(method = "createManager(Lnet/minecraft/world/level/storage/LevelStorage$Session;)Lnet/minecraft/resource/ResourcePackManager;", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/VanillaDataPackProvider;createManager(Ljava/nio/file/Path;Lnet/minecraft/util/path/SymlinkFinder;)Lnet/minecraft/resource/ResourcePackManager;"))
    private static ResourcePackManager createManager(Path dataPacksPath, SymlinkFinder symlinkFinder) {
        return new ResourcePackManager(
                new VanillaDataPackProvider(symlinkFinder),
                new FileResourcePackProvider(dataPacksPath, ResourceType.SERVER_DATA, ResourcePackSource.WORLD, symlinkFinder),
                new FileResourcePackProvider(Globalize.dataPackFolder, ResourceType.SERVER_DATA, ResourcePackSource.WORLD, symlinkFinder)
        );
    }
}
