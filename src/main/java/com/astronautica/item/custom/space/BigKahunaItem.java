package com.astronautica.item.custom.space;

import com.astronautica.Astronautica;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class BigKahunaItem extends Item {
    public BigKahunaItem() {
        super(new Properties()
                .fireResistant()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
                .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "big_kahuna"))));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        if (player.getY() >= 300) {
            player.setDeltaMovement(player.getDeltaMovement().x * 120, player.getDeltaMovement().y * 15000, player.getDeltaMovement().z * 120);
            player.getItemInHand(usedHand).shrink(1);
        } else {
            if (!level.isClientSide()) {
                player.sendSystemMessage(Component.literal("You must be at or above Y: 300 to use this!"));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.literal("Use at Y: 300 or above!"));
    }
}
