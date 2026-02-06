package com.astronautica.block.custom.misc;

import com.astronautica.block.ModBlocks;
import com.astronautica.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class SpaceAirBlock extends Block {
    public SpaceAirBlock(Properties properties) {
        super(properties);
    }


    //Random random = new Random(867530912);

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(entity instanceof LivingEntity) {
            if(!((LivingEntity) entity).hasEffect(ModEffects.SPACE_BREATHING_EFFECT)) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT.getDelegate(), 200, 0, false, false, true));
            }
        }
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!level.isClientSide()) {
            BlockPos nP;
            Block n;
            for(Direction d: Direction.values()) {
                nP = pos.relative(d);
                n = level.getBlockState(nP).getBlock();
                if(n == Blocks.AIR || n == Blocks.CAVE_AIR || n == Blocks.VOID_AIR) {
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                }
            }
        }
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if(!level.isClientSide()) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public boolean isPossibleToRespawnInThis(BlockState state) {
        return true;
    }
}
