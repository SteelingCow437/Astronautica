package com.astronautica.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class AntidoteEffect extends MobEffect {

    public AntidoteEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (!mob.level().isClientSide()) {
            mob.removeAllEffects();
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }
}
