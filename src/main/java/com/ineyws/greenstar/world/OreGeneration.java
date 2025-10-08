package com.ineyws.greenstar.world;

import com.ineyws.greenstar.IneyWSGreenStar;
import com.ineyws.greenstar.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IneyWSGreenStar.MOD_ID)
public class OreGeneration {
    
    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
            generateOres(event.getGeneration());
        }
    }
    
    private static void generateOres(BiomeGenerationSettingsBuilder settings) {
        // Генерация зеленой руды
        // Параметры: размер жилы (9 блоков), количество попыток на чанк (20), 
        // высота от 0 до 64 блоков
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                ModBlocks.GREEN_ORE.get().defaultBlockState(),
                                9)) // размер жилы
                        .range(64) // максимальная высота
                        .squared() // распределение
                        .count(20)); // количество попыток генерации на чанк
    }
}
