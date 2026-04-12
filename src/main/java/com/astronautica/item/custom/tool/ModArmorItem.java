package com.astronautica.item.custom.tool;

import com.astronautica.effect.ModEffects;
import com.astronautica.util.ModLists;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.Nullable;


public class ModArmorItem extends Item {

    ArmorMaterial mat;
    ArmorType typ;

    public ModArmorItem(ArmorMaterial material, ArmorType type, Item.Properties settings) {
        super(settings);
        mat = material;
        typ = type;
    }

    private int timer = 0;

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        if(entity instanceof Player player && !level.isClientSide() && hasFullSuitOfArmorOn(player)) {
            if(timer >= 90) {
                evaluateArmorEffects(player);
                timer = 0;
            }
            else {
                ++timer;
            }
        }
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        return true;
    }

    private void evaluateArmorEffects(Player player) {
        for(ArmorMaterial material : ModLists.ARMOR_MATERIAL_INDEX) {
            if(hasSameSetOfArmorOn(material, player)) {
                addEffectToPlayer(player, material);
            }
        }
    }

    private void addEffectToPlayer(Player player, ArmorMaterial material) {
        switch(ModLists.ARMOR_MATERIAL_INDEX.indexOf(material)) {
            case 1 -> {
                if(player.isInWaterOrRain()) {
                    turtleMasterArmorInWater(player);
                }
                else {
                    turtleMasterArmorOnLand(player);
                }
            }
            case 2 -> spaceSuit(player);
            case 3 -> evaluateZ7Effect(player);
        }
    }

    protected boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }

    protected boolean hasSameSetOfArmorOn(ArmorMaterial material, Player player) {
        for(EquipmentSlot slot : EquipmentSlotGroup.ARMOR) {
            if(!slot.isArmor()) {
                return false;
            }
        }

        Item boots = player.getItemBySlot(EquipmentSlot.FEET).getItem();
        Item leggings = player.getItemBySlot(EquipmentSlot.LEGS).getItem();
        Item chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
        Item helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();


        for(ArmorMaterial m : ModLists.ARMOR_MATERIAL_INDEX) {
            if(boots.getDefaultInstance().is(m.repairIngredient())
                    && leggings.getDefaultInstance().is(m.repairIngredient())
                    && chestplate.getDefaultInstance().is(m.repairIngredient())
                    && helmet.getDefaultInstance().is(m.repairIngredient())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    //Lists of armor effects!

    private void spaceSuit(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT, 240, 0, false, false));
    }

    private void turtleMasterArmorInWater(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 110, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 110, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 110, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 110, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 110, 1, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.HASTE, 110, 1, false, false));
    }

    private void turtleMasterArmorOnLand(Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 2, false, false));
    }

    private void evaluateZ7Effect(Player player) {
        player.addEffect(new MobEffectInstance(ModEffects.SPACE_BREATHING_EFFECT, 240, 0, false, false));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 110, 1, false, false));
        if(player.isInWaterOrRain()) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 110, 0, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 110, 0, false, false));
        }
        if(player.isOnFire()) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 110, 0, false, false));
        }
    }
}