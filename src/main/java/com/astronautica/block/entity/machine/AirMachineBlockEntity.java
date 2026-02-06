package com.astronautica.block.entity.machine;

import com.astronautica.block.ModBlocks;
import com.astronautica.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AirMachineBlockEntity extends BlockEntity {

    public AirMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AIR_MACHINE.get(), pos, state);
    }

    private static int timer = 0;

    private int timeRemaining = 0;

    public boolean ACTIVE = false;

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void addFuel(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && timeRemaining < 72000) {
            ItemStack input = new ItemStack(player.getItemInHand(hand).getItem(), 1);
            timeRemaining += input.getBurnTime(RecipeType.SMELTING) * 2;
            level.playSound(null, worldPosition, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 2.0f, 2.0f);
            if(!input.getCraftingRemainingItem().isEmpty()) {
                player.addItem(input.getCraftingRemainingItem());
            }
            player.getItemInHand(hand).shrink(1);
            setChanged();
        }
    }

    private void pulseTurnOff(BlockPos pos, Level level) {
        //removes all space air blocks
        //air block methods should handle the rest
        BlockPos x; //X gon' give it to ya!
        for(Direction d: Direction.values()) {
            x = pos.relative(d);
            if(level.getBlockState(x).getBlock() == ModBlocks.SPACE_AIR.get()) {
                level.setBlockAndUpdate(x, Blocks.AIR.defaultBlockState());
            }
        }
    }

    public void pulseTurnOn(BlockPos pos, Level level) {
        //places all space air blocks
        BlockPos toPlace;
        for(int y = 15; y > -15; --y) {
            for(int x = 15; x > -15; --x) {
                for(int z = 15; z > -15; --z) {
                    toPlace = pos.offset(x, y, z);
                    if(level.getBlockState(toPlace).getBlock() == Blocks.AIR) {
                        level.setBlock(toPlace, ModBlocks.SPACE_AIR.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AirMachineBlockEntity entity) {
        if(entity.timeRemaining > 0) {
            --entity.timeRemaining;
            ++timer;
            if(!entity.ACTIVE) {
                entity.ACTIVE = true;
            }
        }
        if(entity.timeRemaining <= 0 && entity.ACTIVE) {
            entity.ACTIVE = false;
            entity.pulseTurnOff(pos, level);
        }
        if(timer >= 160) {
            if(entity.timeRemaining <= 2400) {
                level.playSound(null, pos, SoundEvents.BELL_BLOCK, SoundSource.BLOCKS, 5.0f, 2.0f);
            }
            else {
                level.playSound(null, pos, SoundEvents.BEACON_AMBIENT, SoundSource.BLOCKS, 2.0f, 2.0f);
            }
            entity.pulseTurnOn(pos, level);
            timer = 0;
        }
    }


    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putInt("time", timeRemaining);
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        timeRemaining = tag.getInt("time");
        super.loadAdditional(tag, provider);
    }
}