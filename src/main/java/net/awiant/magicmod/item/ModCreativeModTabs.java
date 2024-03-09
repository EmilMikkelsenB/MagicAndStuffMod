package net.awiant.magicmod.item;

import net.awiant.magicmod.TutorialMod;
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
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATEIVE_MODE_TABS.register("tutorial_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.SAPPHIRE.get()))
            .title(Component.translatable("creativetab.tutorial_tab"))
                    .displayItems((pParameters, pOutput) ->{
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATEIVE_MODE_TABS.register(eventBus);
    }
}
