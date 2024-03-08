package net.awiant.tutorialmod.entity;

import net.awiant.tutorialmod.TutorialMod;
import net.awiant.tutorialmod.entity.custom.MagicBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModEntities {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TutorialMod.MOD_ID);
    public static final RegistryObject<EntityType<MagicBolt>> MAGIC_BOLT = ENTITY_TYPES.register("magic_bolt",
            () -> EntityType.Builder.of((EntityType.EntityFactory<MagicBolt>)MagicBolt::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).build("magic_bolt"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
