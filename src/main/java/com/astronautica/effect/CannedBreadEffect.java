package com.astronautica.effect;

import com.astronautica.item.ModItems;
import com.astronautica.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CannedBreadEffect extends MobEffect {
    public CannedBreadEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        ItemStack stack = ModItems.TIN_CAN.get().getDefaultInstance();
        if (!mob.level().isClientSide()) {
            if(mob instanceof Player) {
                mob.playSound(ModSounds.CANNED_BREAD.get(), 2.0f, 2.0f);
                ((Player) mob).addItem(stack);
            }
        }
        return super.applyEffectTick(serverLevel, mob, amplification);
    }
}
