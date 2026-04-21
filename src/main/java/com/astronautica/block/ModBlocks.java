package com.astronautica.block;

import com.astronautica.Astronautica;
import com.astronautica.block.custom.dungeon.StarGateCoreBlock;
import com.astronautica.block.custom.dungeon.StarGatePortalBlock;
import com.astronautica.block.custom.dungeon.VaultDoorBlock;
import com.astronautica.block.custom.machine.*;
import com.astronautica.block.custom.misc.SpaceAirBlock;
import com.astronautica.block.custom.multiblock.OrbitalFlameCoreBlock;
import com.astronautica.block.custom.multiblock.OrbitalTNTCoreBlock;
import com.astronautica.block.custom.multiblock.ResourceRadarBlock;
import com.astronautica.block.custom.multiblock.slave.OrbitalFlameSlaveBlock;
import com.astronautica.block.custom.multiblock.slave.OrbitalTntSlaveBlock;
import com.astronautica.block.custom.multiblock.slave.ResourceRadarSlaveBlock;
import com.astronautica.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Astronautica.MOD_ID);

    /*
    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> ret = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
        return ret;
    }
     */
    private static <T extends Block> DeferredBlock<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    //Register blocks below this line
    //The stuff above is just to declare the block class

    //IMPORTANT: DO NOT ofFullCopy any block entities, the game won't start!

    //titanium
    public static final DeferredBlock<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(9.0f)
                    .requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_block")))));

    public static final DeferredBlock<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK)
                    .strength(9f)
                    .requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "raw_titanium_block")))));

    public static final DeferredBlock<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .strength(4f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_ore")))));

    public static final DeferredBlock<Block> TITANIUM_ORE_DEEPSLATE = registerBlock("titanium_ore_deepslate",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(5f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_ore_deepslate")))));

    public static final DeferredBlock<Block> AQUAMARINE_ORE = registerBlock("aquamarine_ore",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                    .strength(4f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "aquamarine_ore")))));

    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .strength(4f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "tin_ore")))));

    public static final DeferredBlock<Block> TIN_ORE_DEEPSLATE = registerBlock("tin_ore_deepslate",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(5f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "tin_ore_deepslate")))));

    //Fluid Tanks
    /*public static final DeferredBlock<Block> IRON_BARREL = registerBlock("iron_barrel",
            () -> new IronBarrelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(5f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "iron_barrel")))));*/

    //machines
    public static final DeferredBlock<Block> FORGING_TABLE = registerBlock("forging_table",
            (properties) -> new ForgingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE)
                    .strength(4.5f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "forging_table")))));

    public static final DeferredBlock<Block> AIR_MACHINE = registerBlock("air_machine",
            (properties) -> new AirMachineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(4.5f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "air_machine")))));

    public static final DeferredBlock<Block> ALLOY_REVERSAL_MACHINE = registerBlock("un_alloy_machine",
            (properties) -> new UnAlloyMachineBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(4.5f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "un_alloy_machine")))));

    public static final DeferredBlock<Block> WARP_DRIVE = registerBlock("warp_drive",
            (properties) -> new WarpDriveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_GRATE)
                    .strength(4.5f).requiresCorrectToolForDrops().explosionResistance(999f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "warp_drive")))));

    //Space equipment stuffs
    public static final DeferredBlock<Block> PLANET_DIRECTORY = registerBlock("planet_directory",
            (properties) -> new PlanetDirectoryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARTOGRAPHY_TABLE)
                    .strength(5.0f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "planet_directory")))));


    //Moon blocks
    public static final DeferredBlock<Block> MOON_DIRT = registerBlock("moon_dirt",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                    .strength(0.5f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "moon_dirt")))));

    public static final DeferredBlock<Block> MOON_ROCK = registerBlock("moon_rock",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(1.5f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "moon_rock")))));

    public static final DeferredBlock<Block> ENRICHED_MOON_ROCK = registerBlock("enriched_moon_rock",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2.0f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "enriched_moon_rock")))));

    public static final DeferredBlock<Block> VAULT_DOOR = registerBlock("vault_door",
            (properties) -> new VaultDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK)
                    .strength(99f).noLootTable().explosionResistance(99f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "vault_door")))));

    public static final DeferredBlock<Block> VAULT_BRICK = registerBlock("vault_brick",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
                    .strength(-1.0f).explosionResistance(3600000f).noLootTable().isValidSpawn(Blocks::never).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "vault_brick")))));

    //Stargate stuff
    public static final DeferredBlock<Block> STARGATE_CORE = registerBlock("stargate_core",
            (properties) -> new StarGateCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
                    .strength(8f).requiresCorrectToolForDrops().explosionResistance(99f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "stargate_core")))));

    public static final DeferredBlock<Block> STARGATE_FRAME = registerBlock("stargate_frame",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)
                    .strength(8f).requiresCorrectToolForDrops().explosionResistance(99f).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "stargate_frame")))));

    public static final DeferredBlock<Block> STARGATE_PORTAL = registerBlockWithoutBlockItem("stargate_portal",
            () -> new StarGatePortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL)
                    .strength(99f).noLootTable().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "stargate_portal")))));

    //Rocketman!
    public static final DeferredBlock<Block> TNT_COMPRESSOR = registerBlock("tnt_compressor",
            (properties) -> new TNTCompressorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)
                    .requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "tnt_compressor")))));

    //Deco blocks
    public static final DeferredBlock<Block> STEEL_DECO_BLOCK = registerBlock("steel_deco_block",
            (properties) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(3f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "steel_deco_block")))));

    //Multiblocks
    public static final DeferredBlock<Block> ORBITAL_TNT_CORE = registerBlock("orbital_tnt_core",
            (properties) -> new OrbitalTNTCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(4f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_tnt_core")))));

    public static final DeferredBlock<Block> ORBITAL_TNT_SLAVE = registerBlock("orbital_tnt_slave",
            (properties) -> new OrbitalTntSlaveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(4f)
                    .noLootTable().noOcclusion().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_tnt_slave")))));

    public static final DeferredBlock<Block> ORBITAL_FLAME_CORE = registerBlock("orbital_flame_core",
            (properties) -> new OrbitalFlameCoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .strength(4f).requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_flame_core")))));

    public static final DeferredBlock<Block> ORBITAL_FLAME_SLAVE = registerBlock("orbital_flame_slave",
            (properties) -> new OrbitalFlameSlaveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(4f)
                    .noLootTable().noOcclusion().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_flame_slave")))));

    public static final DeferredBlock<Block> RESOURCE_RADAR = registerBlock("resource_radar",
            (properties)-> new ResourceRadarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(4f)
                    .requiresCorrectToolForDrops().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "resource_radar")))));

    public static final DeferredBlock<Block> RESOURCE_RADAR_SLAVE = registerBlock("resource_radar_slave",
            (properties) -> new ResourceRadarSlaveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(4f)
                    .noLootTable().noOcclusion().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "resource_radar_slave")))));

    //Air blocks / miscellaneous
    public static final DeferredBlock<Block> SPACE_AIR = registerBlock("space_air",
            (properties) -> new SpaceAirBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AIR).replaceable().noCollision().noLootTable().air().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "space_air")))));

}
