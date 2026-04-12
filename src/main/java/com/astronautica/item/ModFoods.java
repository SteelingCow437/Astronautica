package com.astronautica.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    //Declare foodstuffs below this line!
    public static final FoodProperties LEAN = (new FoodProperties.Builder()).nutrition(8).saturationModifier(2f).build();

    public static final FoodProperties BAGUETTE = (new FoodProperties.Builder()).nutrition(15).saturationModifier(10f).build();

    public static final FoodProperties ANTIDOTE = (new FoodProperties.Builder()).nutrition(0).saturationModifier(0).build();

    public static final FoodProperties CANADA = (new FoodProperties.Builder()).nutrition(5).saturationModifier(6).build();

    public static final FoodProperties CANNED_BREAD = (new FoodProperties.Builder()).nutrition(10).saturationModifier(8f).build();
}