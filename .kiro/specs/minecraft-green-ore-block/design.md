# Design Document

## Overview

Этот документ описывает технический дизайн мода "ineyws_green_star" для Minecraft Forge 1.16.5. Мод добавляет новый блок зеленой руды и зеленые слитки, используя стандартную архитектуру Forge MDK (Mod Development Kit).

Мод будет использовать систему регистрации Forge через DeferredRegister, что является рекомендуемым подходом для версии 1.16.5. Все ресурсы (текстуры, модели, локализация) будут организованы согласно стандартной структуре ресурс-паков Minecraft.

## Architecture

### Структура проекта

```
ineyws_green_star/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── ineyws/
│       │           └── greenstar/
│       │               ├── IneyWSGreenStar.java (главный класс мода)
│       │               ├── init/
│       │               │   ├── ModBlocks.java (регистрация блоков)
│       │               │   └── ModItems.java (регистрация предметов)
│       │               └── blocks/
│       │                   └── GreenOreBlock.java (класс блока руды)
│       └── resources/
│           ├── META-INF/
│           │   └── mods.toml (метаданные мода)
│           ├── pack.mcmeta (метаданные ресурс-пака)
│           └── assets/
│               └── ineyws_green_star/
│                   ├── blockstates/
│                   │   └── green_ore.json
│                   ├── models/
│                   │   ├── block/
│                   │   │   └── green_ore.json
│                   │   └── item/
│                   │       ├── green_ore.json
│                   │       └── green_ingot.json
│                   ├── textures/
│                   │   ├── block/
│                   │   │   └── green_ore.png
│                   │   └── item/
│                   │       └── green_ingot.png
│                   └── lang/
│                       ├── en_us.json
│                       └── ru_ru.json
├── build.gradle (конфигурация сборки)
└── gradle.properties (свойства проекта)
```

### Технологический стек

- **Minecraft Version:** 1.16.5
- **Forge Version:** 36.2.39 (рекомендуемая стабильная версия для 1.16.5)
- **Java Version:** 8 (минимум) или 11 (рекомендуется)
- **Build System:** Gradle 7.x с ForgeGradle 5.x

## Components and Interfaces

### 1. Главный класс мода (IneyWSGreenStar.java)

**Назначение:** Точка входа в мод, инициализация и регистрация компонентов.

**Ключевые элементы:**
- Аннотация `@Mod` с ID мода
- Константа `MOD_ID = "ineyws_green_star"`
- Конструктор, принимающий `FMLJavaModLoadingContext`
- Регистрация обработчиков событий
- Логирование инициализации

**Зависимости:**
- `ModBlocks` - для регистрации блоков
- `ModItems` - для регистрации предметов
- `FMLJavaModLoadingContext` - для доступа к event bus

### 2. Регистрация блоков (ModBlocks.java)

**Назначение:** Централизованная регистрация всех блоков мода.

**Ключевые элементы:**
- `DeferredRegister<Block>` для регистрации блоков
- `RegistryObject<Block>` для блока зеленой руды
- Метод `register()` для привязки к mod event bus

**Свойства блока зеленой руды:**
```java
Block.Properties.of(Material.STONE)
    .strength(3.0f, 3.0f)  // hardness и resistance как у камня
    .harvestLevel(1)        // требуется каменная кирка
    .harvestTool(ToolType.PICKAXE)
    .requiresCorrectToolForDrops()
    .lightLevel(state -> 14)  // уровень света как у факела
    .sound(SoundType.STONE)
```

### 3. Регистрация предметов (ModItems.java)

**Назначение:** Централизованная регистрация всех предметов мода.

**Ключевые элементы:**
- `DeferredRegister<Item>` для регистрации предметов
- `RegistryObject<Item>` для зеленого слитка
- `RegistryObject<BlockItem>` для предмета-блока руды
- Метод `register()` для привязки к mod event bus

**Свойства предметов:**
- Зеленый слиток: `new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)`
- Блок руды (BlockItem): `new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)`

### 4. Класс блока руды (GreenOreBlock.java)

**Назначение:** Кастомная логика для блока зеленой руды.

**Ключевые элементы:**
- Наследуется от `Block`
- Переопределяет `getExpDrop()` для выдачи опыта при добыче (опционально)
- Конструктор принимает `Block.Properties`

**Логика дропа:**
Дроп будет настроен через loot table (JSON), а не в коде блока. Это позволит:
- Поддерживать зачарования Fortune и Silk Touch автоматически
- Легко модифицировать дроп без перекомпиляции
- Следовать best practices Minecraft modding

## Data Models

### Блок зеленой руды

```java
public class GreenOreBlock extends Block {
    public GreenOreBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader world, 
                          BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? 2 + RANDOM.nextInt(5) : 0; // 2-6 опыта
    }
}
```

### Регистрация блоков

```java
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = 
        DeferredRegister.create(ForgeRegistries.BLOCKS, IneyWSGreenStar.MOD_ID);
    
    public static final RegistryObject<Block> GREEN_ORE = BLOCKS.register("green_ore",
        () -> new GreenOreBlock(Block.Properties.of(Material.STONE)
            .strength(3.0f, 3.0f)
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
            .requiresCorrectToolForDrops()
            .lightLevel(state -> 14)
            .sound(SoundType.STONE)));
}
```

### Регистрация предметов

```java
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, IneyWSGreenStar.MOD_ID);
    
    public static final RegistryObject<Item> GREEN_INGOT = ITEMS.register("green_ingot",
        () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    
    public static final RegistryObject<BlockItem> GREEN_ORE_ITEM = ITEMS.register("green_ore",
        () -> new BlockItem(ModBlocks.GREEN_ORE.get(), 
            new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}
```

### Loot Table для блока руды

Файл: `data/ineyws_green_star/loot_tables/blocks/green_ore.json`

```json
{
  "type": "minecraft:block",
  "pools": [
    {
      "rolls": 1,
      "entries": [
        {
          "type": "minecraft:alternatives",
          "children": [
            {
              "type": "minecraft:item",
              "conditions": [
                {
                  "condition": "minecraft:match_tool",
                  "predicate": {
                    "enchantments": [
                      {
                        "enchantment": "minecraft:silk_touch",
                        "levels": {
                          "min": 1
                        }
                      }
                    ]
                  }
                }
              ],
              "name": "ineyws_green_star:green_ore"
            },
            {
              "type": "minecraft:item",
              "functions": [
                {
                  "function": "minecraft:apply_bonus",
                  "enchantment": "minecraft:fortune",
                  "formula": "minecraft:ore_drops"
                },
                {
                  "function": "minecraft:explosion_decay"
                }
              ],
              "name": "ineyws_green_star:green_ingot"
            }
          ]
        }
      ]
    }
  ]
}
```

### Модели и текстуры

**Blockstate (green_ore.json):**
```json
{
  "variants": {
    "": {
      "model": "ineyws_green_star:block/green_ore"
    }
  }
}
```

**Block Model (block/green_ore.json):**
```json
{
  "parent": "minecraft:block/cube_all",
  "textures": {
    "all": "ineyws_green_star:block/green_ore"
  }
}
```

**Item Model для блока (item/green_ore.json):**
```json
{
  "parent": "ineyws_green_star:block/green_ore"
}
```

**Item Model для слитка (item/green_ingot.json):**
```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "ineyws_green_star:item/green_ingot"
  }
}
```

### Локализация

**en_us.json:**
```json
{
  "block.ineyws_green_star.green_ore": "Green Ore",
  "item.ineyws_green_star.green_ingot": "Green Ingot"
}
```

**ru_ru.json:**
```json
{
  "block.ineyws_green_star.green_ore": "Зеленая руда",
  "item.ineyws_green_star.green_ingot": "Зеленый слиток"
}
```

## Error Handling

### Регистрация

**Проблема:** Ошибки при регистрации блоков/предметов могут привести к краху игры.

**Решение:**
- Использовать `DeferredRegister`, который автоматически обрабатывает timing регистрации
- Добавить логирование успешной регистрации
- Проверять, что все `RegistryObject` инициализированы перед использованием

### Отсутствующие ресурсы

**Проблема:** Отсутствие текстур или моделей приведет к "missing texture" (розово-черные кубики).

**Решение:**
- Создать placeholder текстуры на этапе разработки
- Добавить проверку наличия всех необходимых файлов в документации
- Использовать стандартные пути для ресурсов

### Несовместимость версий

**Проблема:** Мод может не работать с другими версиями Forge или Minecraft.

**Решение:**
- Указать точные версии зависимостей в `mods.toml`
- Использовать version range для совместимости с патчами: `[36.2,37)`
- Тестировать на рекомендуемой версии Forge

### Конфликты ID

**Проблема:** Другие моды могут использовать те же ID для блоков/предметов.

**Решение:**
- Использовать уникальный namespace (mod ID): `ineyws_green_star`
- Все registry names должны быть в формате: `modid:name`
- Избегать общих имен типа "ore" или "ingot" без префикса

## Testing Strategy

### Ручное тестирование

**Тест 1: Загрузка мода**
- Запустить Minecraft с модом
- Проверить, что мод появляется в списке модов
- Проверить логи на отсутствие ошибок

**Тест 2: Блок в игре**
- Выдать блок командой: `/give @p ineyws_green_star:green_ore`
- Разместить блок в мире
- Проверить, что блок светится (уровень света 14)
- Проверить текстуру блока

**Тест 3: Добыча блока**
- Попробовать сломать блок рукой - не должно быть дропа
- Сломать деревянной киркой - не должно быть дропа
- Сломать каменной киркой - должен выпасть 1 зеленый слиток
- Проверить звук разрушения

**Тест 4: Зачарования**
- Создать кирку с Fortune I, II, III
- Проверить количество дропа (1-2, 1-3, 1-4 соответственно)
- Создать кирку с Silk Touch
- Проверить, что выпадает сам блок руды

**Тест 5: Творческий режим**
- Открыть инвентарь в творческом режиме
- Найти блок руды во вкладке "Building Blocks"
- Найти слиток во вкладке "Materials"
- Проверить поиск по названию

**Тест 6: Локализация**
- Переключить язык на английский - проверить названия
- Переключить язык на русский - проверить названия

### Тестирование сборки

**Тест 1: Gradle build**
```bash
gradlew build
```
- Проверить, что сборка завершается без ошибок
- Проверить наличие JAR файла в `build/libs/`

**Тест 2: Запуск из IDE**
```bash
gradlew runClient
```
- Проверить, что игра запускается
- Проверить, что мод загружается

### Чек-лист перед релизом

- [ ] Все текстуры созданы и корректно отображаются
- [ ] Все модели JSON валидны и работают
- [ ] Локализация на английском и русском языках
- [ ] Блок корректно добывается нужными инструментами
- [ ] Дроп работает с Fortune и Silk Touch
- [ ] Блок светится с правильным уровнем света
- [ ] Предметы доступны в творческом режиме
- [ ] Нет ошибок в логах при загрузке
- [ ] JAR файл собирается без ошибок
- [ ] mods.toml содержит корректную информацию

## Build Configuration

### build.gradle (ключевые настройки)

```gradle
buildscript {
    repositories {
        maven { url = 'https://maven.minecraftforge.net' }
        mavenCentral()
    }
    dependencies {
        classpath group: 'net.minecraftforge.gradle', name: 'ForgeGradle', version: '5.1.+', changing: true
    }
}

apply plugin: 'net.minecraftforge.gradle'
apply plugin: 'java'

version = '1.0.0'
group = 'com.ineyws.greenstar'
archivesBaseName = 'ineyws_green_star'

java.toolchain.languageVersion = JavaLanguageVersion.of(8)

minecraft {
    mappings channel: 'official', version: '1.16.5'
    
    runs {
        client {
            workingDirectory project.file('run')
            property 'forge.logging.console.level', 'debug'
            mods {
                ineyws_green_star {
                    source sourceSets.main
                }
            }
        }
    }
}

dependencies {
    minecraft 'net.minecraftforge:forge:1.16.5-36.2.39'
}
```

### mods.toml

```toml
modLoader="javafml"
loaderVersion="[36,)"
license="All Rights Reserved"

[[mods]]
modId="ineyws_green_star"
version="1.0.0"
displayName="Iney's Green Star"
description='''
Adds green ore and green ingots to Minecraft.
'''
authors="ineyws"

[[dependencies.ineyws_green_star]]
    modId="forge"
    mandatory=true
    versionRange="[36.2,37)"
    ordering="NONE"
    side="BOTH"

[[dependencies.ineyws_green_star]]
    modId="minecraft"
    mandatory=true
    versionRange="[1.16.5]"
    ordering="NONE"
    side="BOTH"
```

## Future Extensibility

Дизайн мода позволяет легко добавлять новые функции:

1. **Новые блоки:** Добавить регистрацию в `ModBlocks.java`
2. **Новые предметы:** Добавить регистрацию в `ModItems.java`
3. **Крафт рецепты:** Создать JSON файлы в `data/ineyws_green_star/recipes/`
4. **Генерация руды в мире:** Добавить конфигурацию генерации мира
5. **Инструменты и броня:** Создать новые классы предметов с материалом из зеленых слитков

Архитектура с `DeferredRegister` и разделением на модули делает расширение мода простым и безопасным.
