package com.astronautica.block.entity;

import com.astronautica.Astronautica;
import com.astronautica.block.ModBlocks;
import com.astronautica.block.entity.dungeon.StarGateCoreBlockEntity;
import com.astronautica.block.entity.machine.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Astronautica.MOD_ID);
    //Machine

    public static final Supplier<BlockEntityType<ForgingTableBlockEntity>> FORGING_TABLE =
            BLOCK_ENTITIES.register("forging_table", () ->
                    new BlockEntityType<>(ForgingTableBlockEntity::new, false,
                            ModBlocks.FORGING_TABLE.get()));

    public static final Supplier<BlockEntityType<AirMachineBlockEntity>> AIR_MACHINE =
            BLOCK_ENTITIES.register("air_machine", () ->
                    new BlockEntityType<>(AirMachineBlockEntity::new,
                            ModBlocks.AIR_MACHINE.get()));

    public static final Supplier<BlockEntityType<UnAlloyMachineBlockEntity>> UN_ALLOY_MACHINE =
            BLOCK_ENTITIES.register("un_alloy_machine", () ->
                    new BlockEntityType<>(UnAlloyMachineBlockEntity::new,
                            ModBlocks.ALLOY_REVERSAL_MACHINE.get()));

    public static final Supplier<BlockEntityType<PlanetDirectoryBlockEntity>> PLANET_DIRECTORY =
            BLOCK_ENTITIES.register("planet_directory", () ->
                    new BlockEntityType<>(PlanetDirectoryBlockEntity::new,
                            ModBlocks.PLANET_DIRECTORY.get()));

    public static final Supplier<BlockEntityType<WarpDriveBlockEntity>> WARP_DRIVE =
            BLOCK_ENTITIES.register("warp_drive", () ->
                    new BlockEntityType<>(WarpDriveBlockEntity::new,
                            ModBlocks.WARP_DRIVE.get()));

    //Fluid container blocks
    //I really gotta get rid of this
     /*public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BasicFluidBarrelBlockEntity>> IRON_BARREL =
            BLOCK_ENTITIES.register("basic_fluid_barrel", () -> //what a mouthful
                    BlockEntityType.Builder.of(BasicFluidBarrelBlockEntity::new,
                            ModBlocks.IRON_BARREL.get()).build(null)); */

    //StarGate Core
    public static final Supplier<BlockEntityType<StarGateCoreBlockEntity>> STARGATE_CORE =
            BLOCK_ENTITIES.register("stargate_core", () ->
                    new BlockEntityType<>(StarGateCoreBlockEntity::new,
                            ModBlocks.STARGATE_CORE.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
