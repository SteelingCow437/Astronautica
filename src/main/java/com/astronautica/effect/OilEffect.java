package com.astronautica.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class OilEffect extends MobEffect {
    public OilEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (!mob.level().isClientSide()) {
            double x = mob.getX();
            double y = mob.getY();
            double z = mob.getZ();
            if(mob.isInWaterOrRain()) {
                mob.addDeltaMovement(new Vec3(mob.getDeltaMovement().x, mob.getDeltaMovement().y + 0.05, mob.getDeltaMovement().z));
            }
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }
}


