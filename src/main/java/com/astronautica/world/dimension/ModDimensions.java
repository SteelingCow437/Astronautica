package com.astronautica.world.dimension;

import com.astronautica.Astronautica;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {

    public static final ResourceKey<Level> MOON =
            ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "moon"));

    public static final ResourceKey<DimensionType> MOON_TYPE = register("moon_type");

    private static ResourceKey<DimensionType> register(String register) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, register));
    }
}
