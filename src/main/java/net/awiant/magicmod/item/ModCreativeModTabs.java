package net.awiant.magicmod.item;

import net.awiant.magicmod.MagicMod;
import net.awiant.magicmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATEIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MagicMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MagicMod_tab = CREATEIVE_MODE_TABS.register("magicmod",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.MAGIC_STAFF.get()))
            .title(Component.translatable("creativetab.magicmod"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModItems.MAGIC_STAFF.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATEIVE_MODE_TABS.register(eventBus);
    }
}
