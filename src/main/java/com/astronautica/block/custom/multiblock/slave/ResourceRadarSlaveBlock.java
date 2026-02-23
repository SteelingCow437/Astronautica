package com.astronautica.block.custom.multiblock.slave;

import com.astronautica.block.ModBlocks;
import com.astronautica.block.custom.multiblock.ResourceRadarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ResourceRadarSlaveBlock extends TransparentBlock {

    private ResourceRadarBlock master;

    public boolean hasMaster = false;

    public static BooleanProperty HAS_MASTER = BooleanProperty.create("has_master");

    public ResourceRadarSlaveBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HAS_MASTER, true));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(HAS_MASTER, true);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HAS_MASTER);
    }

    public void setMaster(ResourceRadarBlock block) {
        master = block;
        hasMaster = true;
    }

    private BlockState getMasterState() {
        return master.defaultBlockState();
    }

    private BlockPos getMasterPos() {
        return master.getPos();
    }

    private void refreshMaster(Level level, BlockPos pos) {
        level.setBlockAndUpdate(pos, ModBlocks.RESOURCE_RADAR_SLAVE.get().defaultBlockState().setValue(HAS_MASTER, false));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(hasMaster) {
            master.useItemOn(stack, getMasterState(), level, getMasterPos(), player, hand, hitResult);
            return ItemInteractionResult.SUCCESS;
        }
        else {
            refreshMaster(level, pos);
            return ItemInteractionResult.FAIL;
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(level.getBlockState(neighborPos).getBlock() == ModBlocks.RESOURCE_RADAR_SLAVE.get()) {
            level.setBlockAndUpdate(pos, level.getBlockState(neighborPos));
        }
        if(level.getBlockState(neighborPos).getBlock() == Blocks.AIR) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!level.isClientSide()) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }
}
