package com.astronautica.item.custom.space;

import com.astronautica.Astronautica;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PlanetKeyItem extends Item {
    private ResourceKey<Level> destinationPlanet;

    public PlanetKeyItem(ResourceKey<Level> planet) {
        super(new Properties()
                .stacksTo(1)
                .fireResistant()
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "planet_key"))));
        destinationPlanet = planet;
    }

    public ResourceKey<Level> getDestination() {
        return destinationPlanet;
    }
}