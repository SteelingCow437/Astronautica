package com.astronautica.block.custom.multiblock;

import com.astronautica.block.ModBlocks;
import com.astronautica.block.custom.multiblock.slave.OrbitalTntSlaveBlock;
import com.astronautica.data.ModDataStorage;
import com.astronautica.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static com.astronautica.block.custom.multiblock.slave.OrbitalTntSlaveBlock.HAS_MASTER;

public class OrbitalTNTCoreBlock extends Block {

    public OrbitalTNTCoreBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public static final DirectionProperty FACING = DirectionProperty.create("facing");
    private BlockPos worldPosition;

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.IGNORE;
    }

    public BlockPos getPos() {
        return worldPosition;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        worldPosition = pos;
        setSlaves(level, pos, state);
    }

    public void drop(Level level) {
        ItemEntity entity = new ItemEntity(EntityType.ITEM, level);
        entity.setItem(new ItemStack(ModBlocks.ORBITAL_TNT_CORE.get(), 1));
        entity.setPos(worldPosition.getX(), worldPosition.getY(), worldPosition.getZ());
        level.addFreshEntity(entity);
        level.setBlockAndUpdate(worldPosition, Blocks.AIR.defaultBlockState());
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(level.getBlockState(neighborPos).getBlock() == Blocks.AIR) {
            level.scheduleTick(pos, this, 1);
        }
        if(level.getBlockState(neighborPos).getBlock() == ModBlocks.ORBITAL_TNT_SLAVE.get()) {
            if(!level.getBlockState(neighborPos).getValue(HAS_MASTER)) {
                sync(level, pos, state);
            }
        }
    }

    private void sync(Level level, BlockPos pos, BlockState state) {
        worldPosition = pos;
        setSlaves(level, pos, state);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        sync(level, pos, state);
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        drop(level);
    }

    private void setSlaves(Level level, BlockPos pos, BlockState state) {
        Direction d = state.getValue(OrbitalTNTCoreBlock.FACING);
        BlockPos toSet;
        for(int y = 0; y < 3; ++y) {

            for (int x = 1; x > -2; --x) {

                for (int z = -1; z < 2; ++z) {
                    switch (d) {
                        case SOUTH -> toSet = pos.offset(-x, y, -z);
                        case EAST -> toSet = pos.offset(-z, y, x);
                        case WEST -> toSet = pos.offset(z, y, -x);
                        default -> toSet = pos.offset(x, y, z);
                    }

                    if (level.getBlockState(toSet).getBlock() != ModBlocks.ORBITAL_TNT_CORE.get()) {
                        level.setBlockAndUpdate(toSet, ModBlocks.ORBITAL_TNT_SLAVE.get().defaultBlockState().setValue(HAS_MASTER, true));
                        ((OrbitalTntSlaveBlock) level.getBlockState(toSet).getBlock()).setMaster(this);
                    }
                }

            }
        }
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            if(stack.getItem() == ModItems.ORBITAL_TNT_SHELL.get() && stack.getCount() >= 1) {
                ItemStack marker = new ItemStack(ModItems.ORBITAL_MARKER.get(), 1);
                marker.set(ModDataStorage.LINKED_ORBITAL_CORE, pos);
                player.addItem(marker);
                stack.shrink(1);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.FAIL;
    }

}
