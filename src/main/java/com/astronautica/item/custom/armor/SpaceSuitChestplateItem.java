package com.astronautica.item.custom.armor;

import com.astronautica.item.ModArmorMaterials;
import com.astronautica.item.custom.tool.ModArmorItem;
import com.astronautica.util.ModLists;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class SpaceSuitChestplateItem extends ModArmorItem {

    public SpaceSuitChestplateItem() {
        super(ModArmorMaterials.SPACESUIT, ArmorType.CHESTPLATE, new Properties().fireResistant()
                .durability(ArmorType.CHESTPLATE.getDurability(28)));
    }

    public ResourceKey<Level> selectedPlanet;

    public ResourceKey<Level> getSelectedPlanet() {
        if(selectedPlanet == null) {
            return ModLists.PLANET_LIST.get(0);
        }
        return selectedPlanet;
    }

    public String getPlanetNames() {
        switch(ModLists.PLANET_LIST.indexOf(selectedPlanet)) {
            case 0 -> {
                return "Overworld / Earth";
            }
            case 1 -> {
                return "The Moon";
            }
        }
        return "Yet to be selected!";
    }

    @Override
   public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag flag) {
        builder.accept(Component.literal("Destination: " + getPlanetNames()));
        super.appendHoverText(stack, context, display, builder, flag);
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }
}