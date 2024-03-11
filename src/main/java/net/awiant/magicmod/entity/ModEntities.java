package net.awiant.magicmod.entity;

import net.awiant.magicmod.MagicMod;
import net.awiant.magicmod.entity.custom.MagicBolt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MagicMod.MOD_ID);

    public static final RegistryObject<EntityType<MagicBolt>> MAGIC_BOLT = ENTITY_TYPES.register("magic_bolt",
            () -> EntityType.Builder.<MagicBolt>of((entity, world) -> new MagicBolt(entity, world, BlockPos.ZERO, Vec3.ZERO), MobCategory.MISC)
                    .sized(0.6F, 1.8F) // Set the size of the entity
                    .clientTrackingRange(10) // Set the tracking range for the client
                    .build("magicmod:magic_bolt")); // Build the entity type with the specified id

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
