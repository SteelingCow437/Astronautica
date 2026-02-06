package com.astronautica.event;

import com.astronautica.Astronautica;
import com.astronautica.client.renderer.MoonDimensionSpecialEffects;
import com.astronautica.effect.ModEffects;
import com.astronautica.item.ModItems;
import com.astronautica.item.custom.armor.SpaceSuitChestplateItem;
import com.astronautica.item.custom.armor.Z7ChestplateItem;
import com.astronautica.util.ModAttributeModifiers;
import com.astronautica.util.ModLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.event.entity.living.LivingBreatheEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ModEvents {

    public static void initModBusEvents(IEventBus eventBus) {
        eventBus.addListener(ModEvents::registerDimEffects);
    }

    private static void registerDimEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(Astronautica.MOD_ID, "moon_type"), new MoonDimensionSpecialEffects());
    }

    @EventBusSubscriber(modid = Astronautica.MOD_ID)
    public static class NeoForgeEvents {
        //space stuff
        private static int gravityTimer = 0;
        //private static LivingEntity entity;
        //private static Player player;

        @SubscribeEvent
        public static void onEntityTick(EntityTickEvent.Pre event) {
            if (!event.getEntity().level().isClientSide) {
                if(event.getEntity() instanceof LivingEntity) {
                    handleFalls((LivingEntity) event.getEntity(), event.getEntity().level().dimension());
                    if (event.getEntity().getY() >= 50000) {
                        Item item = ((LivingEntity) event.getEntity()).getItemBySlot(EquipmentSlot.CHEST).getItem();
                        if (item == ModItems.SPACESUIT_CHESTPLATE.get() || item == ModItems.Z7_CHESTPLATE.get()) {
                            ServerLevel destinationLevel = getServerLevel(event, item);
                            ((LivingEntity) event.getEntity()).addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9, 2));
                            event.getEntity().teleportTo(destinationLevel, event.getEntity().getX(), 1500, event.getEntity().getZ(), EnumSet.noneOf(RelativeMovement.class), 2.0f, 2.0f);
                        }
                    }
                    if (gravityTimer >= 20) {
                        handleGravity(((LivingEntity) event.getEntity()), event.getEntity().level().dimension());
                        gravityTimer = 0;
                    } else {
                        ++gravityTimer;
                    }
                }
            }
        }

        private static @Nullable ServerLevel getServerLevel(EntityTickEvent.Pre event, Item item) {
            MinecraftServer server = event.getEntity().getServer();
            ResourceKey<Level> selectedPlanet;
            try {
                selectedPlanet = ((SpaceSuitChestplateItem) item).getSelectedPlanet();
            } catch (Exception e) {
                selectedPlanet = ((Z7ChestplateItem) item).getSelectedPlanet();
            }
            ServerLevel destinationLevel = server.getLevel(selectedPlanet);
            return destinationLevel;
        }

        /*
        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Pre event) {
            if (!event.getEntity().level().isClientSide) {
                entity = event.getEntity();
                player = ((Player) entity);
                handleFalls(player, entity.level().dimension());
                if (playerGravityTimer >= 20) {
                    handleGravity(entity, entity.level().dimension());
                    playerGravityTimer = 0;
                } else {
                    ++playerGravityTimer;
                }
                if (player.getY() >= 50000) {
                    Item item = player.getItemBySlot(EquipmentSlot.CHEST).getItem();
                    if (item == ModItems.SPACESUIT_CHESTPLATE.get() || item == ModItems.Z7_CHESTPLATE.get()) {
                        MinecraftServer server = player.getServer();
                        ResourceKey<Level> selectedPlanet;
                        try {
                            selectedPlanet = ((SpaceSuitChestplateItem) item).getSelectedPlanet();
                        } catch (Exception e) {
                            selectedPlanet = ((Z7ChestplateItem) item).getSelectedPlanet();
                        }
                        ServerLevel destinationLevel = server.getLevel(selectedPlanet);
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9, 2));
                        player.teleportTo(destinationLevel, player.getX(), 1500, player.getZ(), EnumSet.noneOf(RelativeMovement.class), 2.0f, 2.0f);
                    }
                }
            }
        }
         */


        @SubscribeEvent
        public static void onBreath(LivingBreatheEvent event) {
            if (ModLists.NO_BREATHING_LIST.contains(event.getEntity().level().dimension())) {
                if(!checkCanBreathe(event.getEntity())) {
                    event.getEntity().setAirSupply(event.getEntity().getAirSupply() - 8);
                }
            }
        }

        private static void handleGravity(LivingEntity entity, ResourceKey<Level> planet) {
            AttributeInstance gravity = entity.getAttribute(Attributes.GRAVITY.getDelegate());
            switch (ModLists.PLANET_LIST.indexOf(planet)) {
                default -> {
                    for (int i = 0; i < ModLists.GRAVITY_CONSTANTS.size(); ++i) {
                        if (gravity.hasModifier(ModLists.GRAVITY_CONSTANTS.get(i).id())) {
                            gravity.removeModifier(ModLists.GRAVITY_CONSTANTS.get(i));
                        }
                    }
                }
                case 1 -> {
                    if (!gravity.hasModifier(ModAttributeModifiers.MOON_GRAVITY.id())) {
                        gravity.addTransientModifier(ModAttributeModifiers.MOON_GRAVITY);
                    }
                }
            }
        }

        private static void handleFalls(LivingEntity entity, ResourceKey<Level> dimension) {
            switch(ModLists.PLANET_LIST.indexOf(dimension)) {
                case 1 -> entity.resetFallDistance();
            }
        }

        private static boolean checkCanBreathe(LivingEntity entity) {
            if(entity instanceof Player) {
                return ((Player) entity).getAbilities().instabuild ||
                        ((Player) entity).getAbilities().invulnerable ||
                        entity.hasEffect(ModEffects.SPACE_BREATHING_EFFECT);
            }
            else {
                return entity.hasEffect(ModEffects.SPACE_BREATHING_EFFECT);
            }
        }
    }
}