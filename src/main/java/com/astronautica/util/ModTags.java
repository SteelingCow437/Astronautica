package com.astronautica.util;

import com.astronautica.Astronautica;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class ModBlockTags {
        public static TagKey<Block> tag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, name));
        }

        public static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, name));
        }
        //block tags here
        public static final TagKey<Block> MOON_STONE_REPLACEABLES = tag("moon_stone_replaceables");

    }
    public static class ModItemTags {
        public static TagKey<Item> tag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, name));
        }

        public static TagKey<Item> neoForgeTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(Astronautica.MOD_ID, name));
        }
        //item tags here
        public static final TagKey<Item> REPAIRS_TITANIUM = tag("repairs_titanium");
        public static final TagKey<Item> REPAIRS_TURTLE = tag("repairs_turtle");
        public static final TagKey<Item> REPAIRS_SPACESUIT = tag("repairs_spacesuit");
        public static final TagKey<Item> REPAIRS_Z7 = tag("repairs_z7");
        public static final TagKey<Item> REPAIRS_RESONATOR = tag("repairs_resonator");

    }
}
