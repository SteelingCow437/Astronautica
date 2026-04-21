package com.astronautica.item;

import com.astronautica.Astronautica;
import com.astronautica.item.custom.armor.AquamarineResonatorItem;
import com.astronautica.item.custom.armor.SpaceSuitChestplateItem;
import com.astronautica.item.custom.armor.Z7ChestplateItem;
import com.astronautica.item.custom.space.BigKahunaItem;
import com.astronautica.item.custom.space.PlanetKeyItem;
import com.astronautica.item.custom.space.StarGateControllerItem;
import com.astronautica.item.custom.space.orbital.OrbitalMarkerItem;
import com.astronautica.item.custom.tool.HammerItem;
import com.astronautica.item.custom.tool.ModArmorItem;
import com.astronautica.item.custom.tool.ResourceScannerItem;
import com.astronautica.item.custom.tool.ShipBlueprintItem;
import com.astronautica.world.dimension.ModDimensions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Astronautica.MOD_ID);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
    //Declare all items below this line

    //begin of titanium stuff
    public static final DeferredItem<Item> HAMMER = ITEMS.register("hammer", HammerItem::new);

    public static final DeferredItem<Item> TITANIUM_INGOT = ITEMS.register( "titanium_ingot",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_ingot")))));

    public static final DeferredItem<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "raw_titanium")))));

    public static final DeferredItem<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new Item(new Item.Properties().sword(ModTiers.TITANIUM, 4, -2.4f).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_sword")))));

    public static final DeferredItem<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new Item(new Item.Properties().pickaxe(ModTiers.TITANIUM, 1, -2.8f).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_pickaxe")))));

    public static final DeferredItem<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModTiers.TITANIUM, 4, 3f, new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_axe")))));

    public static final DeferredItem<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModTiers.TITANIUM, 1.5f, -3f, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_shovel")))));

    public static final DeferredItem<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModTiers.TITANIUM, 1.5f, -3.0f, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_hoe")))));

    public static final DeferredItem<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorType.HELMET, new Item.Properties().humanoidArmor(ModArmorMaterials.TITANIUM, ArmorType.HELMET).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_helmet"))))); //TITANIUM DURABILITY: 22

    public static final DeferredItem<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorType.CHESTPLATE,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TITANIUM, ArmorType.CHESTPLATE).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_chestplate")))));

    public static final DeferredItem<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorType.LEGGINGS,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TITANIUM, ArmorType.LEGGINGS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_leggings")))));

    public static final DeferredItem<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ModArmorItem(ModArmorMaterials.TITANIUM, ArmorType.BOOTS,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TITANIUM, ArmorType.BOOTS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_boots")))));

    //end of titanium stuff

    //begin of foodstuffs
    public static final DeferredItem<Item> CANADA = ITEMS.register("canada",
            () -> new Item(new Item.Properties().food(ModFoods.CANADA).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "canada")))));

    public static final DeferredItem<Item> LEAN = ITEMS.register( "lean",
            () -> new Item(new Item.Properties().food(ModFoods.LEAN, ModConsumables.LEAN).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "lean")))));

    public static final DeferredItem<Item> BAGUETTE = ITEMS.register("baguette",
            () -> new Item(new Item.Properties().food(ModFoods.BAGUETTE).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "baguette")))));

    public static final DeferredItem<Item> ANTIDOTE = ITEMS.register("antidote",
            () -> new Item(new Item.Properties().food(ModFoods.ANTIDOTE, ModConsumables.ANTIDOTE).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "antidote")))));

    public static final DeferredItem<Item> CANNED_BREAD = ITEMS.register("canned_bread",
            () -> new Item(new Item.Properties().food(ModFoods.CANNED_BREAD, ModConsumables.CANNED_BREAD).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "canned_bread")))));
    //end of foodstuffs

    //Copper was finally added to vanilla. I like to think it was because of me :)

    //begin of turtle master stuff. DURABILITY: 45
    public static final DeferredItem<Item> AQUAMARINE = ITEMS.register("aquamarine",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "aquamarine")))));

    public static final DeferredItem<Item> TURTLE_MASTER_HELMET = ITEMS.register("turtle_master_helmet",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorType.HELMET,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TURTLE, ArmorType.HELMET).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "turtle_master_helmet")))));

    public static final DeferredItem<Item> TURTLE_MASTER_CHESTPLATE = ITEMS.register("turtle_master_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorType.CHESTPLATE,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TURTLE, ArmorType.CHESTPLATE).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "turtle_master_chestplate")))));

    public static final DeferredItem<Item> TURTLE_MASTER_LEGGINGS = ITEMS.register("turtle_master_leggings",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorType.LEGGINGS,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TURTLE, ArmorType.LEGGINGS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "turtle_master_leggings")))));

    public static final DeferredItem<Item> TURTLE_MASTER_BOOTS = ITEMS.register("turtle_master_boots",
            () -> new ModArmorItem(ModArmorMaterials.TURTLE, ArmorType.BOOTS,
                    new Item.Properties().humanoidArmor(ModArmorMaterials.TURTLE, ArmorType.BOOTS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "turtle_master_boots")))));
    //end of turtle master stuff

    //stamps
    public static final DeferredItem<Item> BLANK_STAMP = ITEMS.register("blank_stamp",
            () -> new Item(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "blank_stamp")))));

    public static final DeferredItem<Item> PLATE_STAMP = ITEMS.register("plate_stamp",
            () -> new Item(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "plate_stamp")))));

    public static final DeferredItem<Item> WIRE_STAMP = ITEMS.register("wire_stamp",
            () -> new Item(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "wire_stamp")))));

    public static final DeferredItem<Item> INGOT_STAMP = ITEMS.register("ingot_stamp",
            () -> new Item(new Item.Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "ingot_stamp")))));

    //steel stuff
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "steel_ingot")))));
    //plates
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "steel_plate")))));
    public static final DeferredItem<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_plate")))));
    public static final DeferredItem<Item> TITANIUM_PLATE = ITEMS.register("titanium_plate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_plate")))));
    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "iron_plate")))));
    public static final DeferredItem<Item> BRONZE_PLATE = ITEMS.register("bronze_plate",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "bronze_plate")))));

    //wires
    public static final DeferredItem<Item> COPPER_WIRING = ITEMS.register("copper_wiring",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_wiring")))));

    public static final DeferredItem<Item> COPPER_REDSTIDE_WIRING = ITEMS.register("copper_redstide_wiring",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_redstide_wiring")))));

    //Alloy Powders
    public static final DeferredItem<Item> IRON_POWDER = ITEMS.register("iron_powder",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "iron_powder")))));
    public static final DeferredItem<Item> CARBON_POWDER = ITEMS.register("carbon_powder",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "carbon_powder")))));
    public static final DeferredItem<Item> STEEL_BLEND = ITEMS.register("steel_blend",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "steel_blend")))));
    public static final DeferredItem<Item> COPPER_POWDER = ITEMS.register("copper_powder",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_powder")))));
    public static final DeferredItem<Item> COPPER_REDSTIDE_BLEND = ITEMS.register("copper_redstide_blend",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_redstide_blend")))));
    public static final DeferredItem<Item> COPPER_REDSTIDE_INGOT = ITEMS.register("copper_redstide_ingot",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_redstide_ingot")))));
    public static final DeferredItem<Item> TITANIUM_POWDER = ITEMS.register("titanium_powder",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titanium_powder")))));
    public static final DeferredItem<Item> BRONZE_BLEND = ITEMS.register("bronze_blend",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "bronze_blend")))));
    public static final DeferredItem<Item> TIN_POWDER = ITEMS.register("tin_powder",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "tin_powder")))));

    public static final DeferredItem<Item> MINERAL_CLUMP = ITEMS.register("mineral_clump",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "mineral_clump")))));

    public static final DeferredItem<Item> TITAN_STEEL_BLEND = ITEMS.register("titan_steel_blend",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titan_steel_blend")))));
    //tin stuff
    public static final DeferredItem<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "raw_tin")))));
    public static final DeferredItem<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "tin_ingot")))));
    //bronze
    public static final DeferredItem<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "bronze_ingot")))));
    public static final DeferredItem<Item> DEBUG_STICK = ITEMS.register("debug_stick",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "debug_stick")))));

    //misc. items
    public static final DeferredItem<Item> TIN_CAN = ITEMS.register("tin_can",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "tin_can")))));

    /*Fluid bottles GONE FOR NOW
    public static final DeferredItem<Item> LAVA_BOTTLE = ITEMS.register("lava_bottle",
            () -> new Item(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "lava_bottle"))))); */

    //Aeronautics SPACESUIT DURABILITY: 28
    public static final DeferredItem<Item> TITAN_STEEL_INGOT = ITEMS.register("titan_steel_ingot",
            () -> new Item(new Item.Properties().stacksTo(64).fireResistant().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titan_steel_ingot")))));

    public static final DeferredItem<Item> TITAN_STEEL_PLATE = ITEMS.register("titan_steel_plate",
            () -> new Item(new Item.Properties().stacksTo(64).fireResistant().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "titan_steel_plate")))));

    public static final DeferredItem<Item> SPACESUIT_HELMET = ITEMS.register("spacesuit_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorType.HELMET,
                    new Item.Properties().fireResistant().humanoidArmor(ModArmorMaterials.SPACESUIT, ArmorType.HELMET).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "spacesuit_helmet")))));

    public static final DeferredItem<Item> SPACESUIT_CHESTPLATE = ITEMS.register("spacesuit_chestplate",
            SpaceSuitChestplateItem::new);

    public static final DeferredItem<Item> SPACESUIT_LEGS = ITEMS.register("spacesuit_legs",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorType.LEGGINGS,
                    new Item.Properties().fireResistant().humanoidArmor(ModArmorMaterials.SPACESUIT, ArmorType.LEGGINGS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "spacesuit_legs")))));

    public static final DeferredItem<Item> SPACESUIT_BOOTS = ITEMS.register("spacesuit_boots",
            () -> new ModArmorItem(ModArmorMaterials.SPACESUIT, ArmorType.BOOTS,
                    new Item.Properties().fireResistant().humanoidArmor(ModArmorMaterials.SPACESUIT, ArmorType.BOOTS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "spacesuit_boots")))));

    //Z7 Suit! (Yes I know it's a Garden Warfare 2, Mass Effect, AND NASA reference, stop bitching about it!) DURABILITY: 150
    public static final DeferredItem<Item> Z7_HELMET = ITEMS.register("z7_helmet",
            () -> new ModArmorItem(ModArmorMaterials.Z7, ArmorType.HELMET,
                    new Item.Properties().fireResistant().humanoidArmor(ModArmorMaterials.Z7, ArmorType.HELMET).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "z7_helmet")))));

    public static final DeferredItem<Item> Z7_CHESTPLATE = ITEMS.register("z7_chestplate",
            Z7ChestplateItem::new);

    public static final DeferredItem<Item> Z7_LEGGINGS = ITEMS.register("z7_leggings",
            () -> new ModArmorItem(ModArmorMaterials.Z7, ArmorType.LEGGINGS,
                    new Item.Properties().fireResistant().humanoidArmor(ModArmorMaterials.Z7, ArmorType.LEGGINGS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "z7_leggings")))));

    public static final DeferredItem<Item> Z7_BOOTS = ITEMS.register("z7_boots",
            () -> new ModArmorItem(ModArmorMaterials.Z7, ArmorType.BOOTS,
                    new Item.Properties().fireResistant().humanoidArmor(ModArmorMaterials.Z7, ArmorType.BOOTS).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "z7_boots")))));

    //planet keys
    public static final DeferredItem<Item> MOON_KEY = ITEMS.register("moon_key",
            () -> new PlanetKeyItem(ModDimensions.MOON));

    //Stargate controller
    public static final DeferredItem<Item> STARGATE_CONTROLLER = ITEMS.register("stargate_controller",
            StarGateControllerItem::new);

    public static final DeferredItem<Item> SHIP_BLUEPRINT = ITEMS.register("ship_blueprint",
            ShipBlueprintItem::new);

    //The Big Kahuna
    public static final DeferredItem<Item> BIG_KAHUNA = ITEMS.register("big_kahuna",
            BigKahunaItem::new);

    public static final DeferredItem<Item> KAHUNA_CHARGE = ITEMS.register("kahuna_charge",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "kahuna_charge")))));

    public static final DeferredItem<Item> KAHUNA_SHELL = ITEMS.register("kahuna_shell",
            () -> new Item(new Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "kahuna_shell")))));


    //Orbital shells
    public static final DeferredItem<Item> ORBITAL_CASING = ITEMS.register("orbital_casing",
            () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_casing")))));

    public static final DeferredItem<Item> ORBITAL_TNT_SHELL = ITEMS.register("orbital_tnt_shell",
            () -> new Item(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_tnt_shell")))));

    public static final DeferredItem<Item> ORBITAL_FLAME_SHELL = ITEMS.register("orbital_flame_shell",
            () -> new Item(new Item.Properties().stacksTo(8).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "orbital_flame_shell")))));

    //My life for Super Earth!
    public static final DeferredItem<Item> ORBITAL_MARKER = ITEMS.register("orbital_marker",
            OrbitalMarkerItem::new);

    public static final DeferredItem<Item> RESOURCE_SCANNER = ITEMS.register("resource_scanner",
            ResourceScannerItem::new);

    //Byzanium!
    public static final DeferredItem<Item> BYZANIUM_INGOT = ITEMS.register("byzanium_ingot",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "byzanium_ingot")))));

    public static final DeferredItem<Item> AQUAMARINE_RESONATOR = ITEMS.register("aquamarine_resonator",
            AquamarineResonatorItem::new);

    public static final DeferredItem<Item> VAULT_KEY = ITEMS.register("vault_key",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "vault_key")))));

    //I HATE MICROCRAFTING
    public static final DeferredItem<Item> RED_COIL = ITEMS.register("red_coil",
            () -> new Item(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "red_coil")))));

    public static final DeferredItem<Item> TRANSFUNCTIONER = ITEMS.register("transfunctioner",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "transfunctioner")))));

    public static final DeferredItem<Item> COPPER_COIL = ITEMS.register("copper_coil",
            () -> new Item(new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, "copper_coil")))));
}