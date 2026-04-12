package com.astronautica.item.custom.armor;

import com.astronautica.item.ModArmorMaterials;
import com.astronautica.item.custom.tool.ModArmorItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class AquamarineResonatorItem extends ModArmorItem {
    public AquamarineResonatorItem() {
        super(ModArmorMaterials.RESONATOR, ArmorType.CHESTPLATE, new Properties()
                .fireResistant().stacksTo(1).rarity(Rarity.RARE));
    }

    private int timer = 0;
    Item slotItem;

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        if(!level.isClientSide()) {
            slotItem = ((Player) entity).getItemBySlot(EquipmentSlot.CHEST).getItem();
            if (slotItem instanceof AquamarineResonatorItem) {
                if (timer >= 599) {
                    ((Player) entity).addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4, false, false));
                    timer = 0;
                } else {
                    ++timer;
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        builder.accept(Component.literal("A full-body shield, brought to you by Byzanium technology!"));
        super.appendHoverText(stack, context, display, builder, flag);
    }
}