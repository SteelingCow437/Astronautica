package com.astronautica.item;

import com.astronautica.effect.ModEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {

    public static final Consumable DEFAULT_FOOD = defaultFood().build();
    public static final Consumable DEFAULT_DRINK = defaultDrink().build();
    public static final Consumable LEAN;
    public static final Consumable ANTIDOTE;
    public static final Consumable CANNED_BREAD;

    public ModConsumables() {
    }

    public static Consumable.Builder defaultFood() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }

    public static Consumable.Builder defaultDrink() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
    }

    static {
        LEAN = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.STRENGTH, 2400, 1), new MobEffectInstance(MobEffects.SPEED, 2400, 1)))).build();
        ANTIDOTE = defaultDrink().onConsume(ClearAllStatusEffectsConsumeEffect.INSTANCE).build();
        CANNED_BREAD = defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.CANNED_BREAD_EFFECT, 1, 1), 1.0F)).build();
    }
}
