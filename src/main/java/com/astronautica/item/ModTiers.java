package com.astronautica.item;

import com.astronautica.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class ModTiers {
    public static final ToolMaterial TITANIUM = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 1250, 7f,
            2f, 15, ModTags.ModItemTags.REPAIRS_TITANIUM);

    public static final ToolMaterial TURTLE = new ToolMaterial(BlockTags.NEEDS_DIAMOND_TOOL, 2500, 8f,
            4f, 20, ModTags.ModItemTags.REPAIRS_TURTLE);

    public static final ToolMaterial SPACESUIT = new ToolMaterial(BlockTags.NEEDS_DIAMOND_TOOL, 1500, 7f,
            2f, 15, ModTags.ModItemTags.REPAIRS_SPACESUIT);
}
