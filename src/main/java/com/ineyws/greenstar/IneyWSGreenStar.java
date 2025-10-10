package com.ineyws.greenstar;

import com.ineyws.greenstar.events.BedrockBreakHandler;
import com.ineyws.greenstar.events.EnchantmentHandler;
import com.ineyws.greenstar.init.ModBlocks;
import com.ineyws.greenstar.init.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IneyWSGreenStar.MOD_ID)
public class IneyWSGreenStar {
    
    public static final String MOD_ID = "ineyws_green_star";
    private static final Logger LOGGER = LogManager.getLogger();
    
    public IneyWSGreenStar() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Регистрация блоков и предметов
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        
        // Регистрация обработчика событий
        modEventBus.addListener(this::setup);
        
        // Регистрация игровых событий на FORGE шине
        MinecraftForge.EVENT_BUS.register(new BedrockBreakHandler());
        MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
        
        LOGGER.info("Iney's Green Star mod initialized!");
    }
    
    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Iney's Green Star mod setup complete!");
    }
}
