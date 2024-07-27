package dev.callmeecho.globalize.mixin;

import com.google.common.collect.ImmutableSet;
import dev.callmeecho.globalize.main.ResourcePackManagerAccessor;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(ResourcePackManager.class)
public class ResourcePackManagerMixin implements ResourcePackManagerAccessor {
    @Mutable
    @Shadow @Final public Set<ResourcePackProvider> providers;

    @Override
    public void globalize$addProvider(ResourcePackProvider provider) {
        this.providers = ImmutableSet.<ResourcePackProvider>builder().addAll(this.providers).add(provider).build();
    }
}
