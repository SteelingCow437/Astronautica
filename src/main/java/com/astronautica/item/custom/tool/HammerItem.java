package com.astronautica.item.custom.tool;

import com.astronautica.Astronautica;
import com.astronautica.block.ModBlocks;
import com.astronautica.item.ModItems;
import com.astronautica.util.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class HammerItem extends Item {
    public HammerItem() {
        super(new Properties()
                .stacksTo(1)
                .rarity(Rarity.COMMON)
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "hammer"))));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Block block = level.getBlockState(pos).getBlock();
        ItemStack offhand = player.getOffhandItem();
        Item item = offhand.getItem();
        if(!level.isClientSide()) {
            if(block == ModBlocks.FORGING_TABLE.get() && player.isShiftKeyDown()) {
                switch(ModLists.HAMMER_INGREDIENT_LIST.indexOf(item)) {
                    case 0 -> {player.addItem(ModItems.IRON_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 1, 2 -> {player.addItem(ModItems.CARBON_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 3 -> {player.addItem(ModItems.COPPER_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 4 -> {player.addItem(ModItems.TIN_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}
                    case 5 -> {player.addItem(ModItems.TITANIUM_POWDER.get().getDefaultInstance()); offhand.shrink(1); return InteractionResult.SUCCESS;}

                    case 6 -> {
                        player.addItem(new ItemStack(Items.COBBLESTONE, offhand.getCount()));
                        offhand.shrink(offhand.getCount());
                        return InteractionResult.SUCCESS;
                    }
                    default -> {return InteractionResult.FAIL;}
                }
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        if(flag.hasShiftDown()) {
            builder.accept(Component.literal("Use this to make wires and stamps on a Forging Table."
            + "\nAlternatively, shift-right-click on a Forging Table with an ingot in your offhand to powderize it!"));
        }
        else {
            builder.accept(Component.literal("Hold [SHIFT] for help!"));
        }
    }
}
