package com.astronautica.world.dimension;

import com.astronautica.Astronautica;
import com.astronautica.world.biome.ModBiomes;
import com.astronautica.world.noise.ModNoiseSettings;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

import java.util.Optional;

public class ModDimensions {

    private static ResourceKey<DimensionType> register(String register) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, register));
    }

    //moon
    public static final ResourceKey<Level> MOON =
            ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "moon"));

    public static final ResourceKey<LevelStem> MOON_STEM =
            ResourceKey.create(Registries.LEVEL_STEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "moon"));

    public static final ResourceKey<DimensionType> MOON_TYPE = register("moon_type");



    //bootstrapping types

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var clocks = context.lookup(Registries.WORLD_CLOCK);
        var blocks = context.lookup(Registries.BLOCK);

        context.register(MOON_TYPE, new DimensionType(
                false,
                true,
                false,
                false,
                1.0,
                0,
                256,
                256,
                blocks.getOrThrow(BlockTags.INFINIBURN_OVERWORLD).key(),
                1.0f,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, 0)
                        .set(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(0f))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0)
                        .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(0, 0, 0, 0))
                        .build(),
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        //This is for dimensions with single biomes
        NoiseBasedChunkGenerator singleBiomeGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomes.getOrThrow(ModBiomes.MOON_BIOME)),
                noiseGenSettings.getOrThrow(ModNoiseSettings.MOON_NOISE));

        /*
        Use this for dimensions with multiple biomes!
        NoiseBasedChunkGenerator multiBiomeGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0f, 0f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.FOREST)),
                                Pair.of(Climate.parameters(0f, 0.1f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.BIRCH_FOREST)),
                                Pair.of(Climate.parameters(0.1f, 0.1f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.CHERRY_GROVE)),
                                Pair.of(Climate.parameters(0.1f, 0.25f, 0f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.BEACH)),
                                Pair.of(Climate.parameters(0.1f, 0.3f, -0.05f, 0f, 0f, 0f, 0f), biomes.getOrThrow(Biomes.DEEP_LUKEWARM_OCEAN))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));
         */

        context.register(MOON_STEM, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.MOON_TYPE), singleBiomeGenerator));
    }
}
