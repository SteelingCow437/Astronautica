package com.astronautica.item;

import com.astronautica.Astronautica;
import com.astronautica.util.ModTags;
import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public interface ModArmorMaterials {

    ArmorMaterial TITANIUM = new ArmorMaterial(22, makeDefense(3, 5, 7, 3, 6), 25, SoundEvents.ARMOR_EQUIP_IRON, 1.0f, 0.1f, ModTags.ModItemTags.REPAIRS_TITANIUM, ModArmorAssets.TITANIUM);

    ArmorMaterial TURTLE = new ArmorMaterial(45, makeDefense(4, 7, 9, 4, 8), 100, SoundEvents.ARMOR_EQUIP_TURTLE, 2.0f, 1.0f, ModTags.ModItemTags.REPAIRS_TURTLE, ModArmorAssets.TURTLE);

    ArmorMaterial SPACESUIT = new ArmorMaterial(22, makeDefense(4, 6, 8, 4, 7), 40, SoundEvents.ARMOR_EQUIP_IRON, 1.2f, 0.1f, ModTags.ModItemTags.REPAIRS_SPACESUIT, ModArmorAssets.SPACESUIT);

    ArmorMaterial Z7 = new ArmorMaterial(22, makeDefense(5, 8, 10, 5, 8), 50, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.5f, 0.25f, ModTags.ModItemTags.REPAIRS_Z7, ModArmorAssets.Z7);

    ArmorMaterial RESONATOR = new ArmorMaterial(22, makeDefense(0, 0, 4, 0, 0), 40, SoundEvents.ARMOR_EQUIP_NETHERITE, 0.0f, 0.0f, ModTags.ModItemTags.REPAIRS_RESONATOR, ModArmorAssets.RESONATOR);

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }

    interface ModArmorAssets {
        ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "equipment_asset"));
        ResourceKey<EquipmentAsset> TITANIUM = createId("titanium");
        ResourceKey<EquipmentAsset> SPACESUIT = createId("spacesuit");
        ResourceKey<EquipmentAsset> TURTLE = createId("turtle");
        ResourceKey<EquipmentAsset> Z7 = createId("z7");
        ResourceKey<EquipmentAsset> RESONATOR = createId("resonator");

        static ResourceKey<EquipmentAsset> createId(String name) {
            return ResourceKey.create(ROOT_ID, Identifier.withDefaultNamespace(name));
        }
    }
}
