package com.ineyws.greenstar.client;

import com.ineyws.greenstar.init.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Устанавливаем cutout рендер для факелов (чтобы прозрачность работала)
        RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_WALL_TORCH.get(), RenderType.cutout());
    }
}
