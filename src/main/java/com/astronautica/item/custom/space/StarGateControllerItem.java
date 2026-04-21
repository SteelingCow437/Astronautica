package com.astronautica.item.custom.space;

import com.astronautica.Astronautica;
import com.astronautica.block.entity.machine.WarpDriveBlockEntity;
import com.astronautica.data.ModDataStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import java.util.function.Consumer;

public class StarGateControllerItem extends Item {
    public StarGateControllerItem() {
        super(new Properties()
                .fireResistant()
                .stacksTo(1)
                .rarity(Rarity.EPIC)
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "stargate_controller"))));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(!player.isShiftKeyDown() && !level.isClientSide()) {
            Item item = player.getItemInHand(hand).getItem();
            if(item instanceof StarGateControllerItem) {
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                player.getItemInHand(hand).set(ModDataStorage.SGC_DESTINATION, new BlockPos(0, 0, 0));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockEntity entity = level.getBlockEntity(context.getClickedPos());
        if (entity instanceof SignBlockEntity && !level.isClientSide()) {
            String messageX = ((SignBlockEntity) entity).getFrontText().getMessage(0, false).getString();
            String messageY = ((SignBlockEntity) entity).getFrontText().getMessage(1, false).getString();;
            String messageZ = ((SignBlockEntity) entity).getFrontText().getMessage(2, false).getString();
            try {
                context.getItemInHand().set(ModDataStorage.SGC_DESTINATION, new BlockPos(
                        Integer.parseInt(messageX), Integer.parseInt(messageY), Integer.parseInt(messageZ)
                ));
            } catch (Exception e) {
                context.getItemInHand().set(ModDataStorage.SGC_DESTINATION, new BlockPos(0, 0, 0));
            }
            return InteractionResult.SUCCESS;
        }
        else if(entity instanceof WarpDriveBlockEntity && !level.isClientSide() && context.getItemInHand().get(ModDataStorage.SGC_DESTINATION) != null) {
            ((WarpDriveBlockEntity) entity).setDestination(context.getItemInHand().get(ModDataStorage.SGC_DESTINATION), context.getPlayer());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        if(!flag.hasShiftDown()) {
            int x;
            int y;
            int z;
            try {
                x = stack.get(ModDataStorage.SGC_DESTINATION).getX();
                y = stack.get(ModDataStorage.SGC_DESTINATION).getY();
                z = stack.get(ModDataStorage.SGC_DESTINATION).getZ();
            } catch (Exception e) {
                x = 0;
                y = 0;
                z = 0;
            }
            builder.accept(Component.literal("Destination Coordinates: " + x + ", " + y + ", " + z).append(". Hold [SHIFT] for help!"));
        }
        else {
            builder.accept(Component.literal("Click this on a sign with the X, Y, and Z coordinates on separate lines " +
                    "(in that order) to save the destination, then click on a Warp Drive or Stargate Core to input those coordinates into the block."));
        }
    }
}
