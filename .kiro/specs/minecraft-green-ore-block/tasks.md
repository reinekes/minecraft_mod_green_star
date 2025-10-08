# Implementation Plan

- [x] 1. Инициализация проекта Forge MDK




  - Скачать и настроить Forge MDK 1.16.5-36.2.39
  - Настроить build.gradle с правильными зависимостями и версиями
  - Настроить gradle.properties с именем проекта и группой
  - Выполнить `gradlew genIntellijRuns` или `gradlew genEclipseRuns` для настройки IDE
  - Проверить, что проект компилируется командой `gradlew build`

  - _Requirements: 1.1, 1.2, 1.4_



- [ ] 2. Создать базовую структуру мода
  - [ ] 2.1 Создать главный класс мода IneyWSGreenStar.java
    - Создать пакет com.ineyws.greenstar
    - Реализовать главный класс с аннотацией @Mod("ineyws_green_star")
    - Добавить константу MOD_ID


    - Настроить конструктор с регистрацией event bus
    - Добавить логирование инициализации мода
    - _Requirements: 1.1, 1.2_
  


  - [ ] 2.2 Настроить mods.toml
    - Создать файл META-INF/mods.toml
    - Указать modId, version, displayName, description, authors

    - Настроить зависимости от forge и minecraft с правильными версиями


    - _Requirements: 1.2, 1.3_
  
  - [ ] 2.3 Создать pack.mcmeta
    - Создать файл pack.mcmeta в resources
    - Указать pack_format 6 для версии 1.16.5


    - Добавить описание ресурс-пака
    - _Requirements: 1.1_

- [ ] 3. Реализовать систему регистрации блоков
  - [x] 3.1 Создать класс ModBlocks.java


    - Создать пакет com.ineyws.greenstar.init
    - Создать DeferredRegister для блоков
    - Добавить метод register() для привязки к mod event bus
    - Вызвать ModBlocks.register() в конструкторе главного класса
    - _Requirements: 5.1, 5.4_
  

  - [x] 3.2 Создать класс GreenOreBlock.java


    - Создать пакет com.ineyws.greenstar.blocks
    - Создать класс, наследующий Block
    - Реализовать конструктор, принимающий Properties
    - Переопределить метод getExpDrop() для выдачи 2-6 опыта


    - _Requirements: 2.1, 2.2, 2.5_
  
  - [x] 3.3 Зарегистрировать блок зеленой руды


    - Добавить RegistryObject<Block> GREEN_ORE в ModBlocks
    - Настроить Block.Properties: Material.STONE, strength(3.0f, 3.0f)
    - Добавить harvestLevel(1) и harvestTool(ToolType.PICKAXE)


    - Добавить requiresCorrectToolForDrops()
    - Настроить lightLevel(state -> 14) для свечения
    - Добавить sound(SoundType.STONE)
    - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7_

- [x] 4. Реализовать систему регистрации предметов

  - [x] 4.1 Создать класс ModItems.java


    - Создать DeferredRegister для предметов в пакете init
    - Добавить метод register() для привязки к mod event bus
    - Вызвать ModItems.register() в конструкторе главного класса


    - _Requirements: 5.2, 5.4_
  
  - [ ] 4.2 Зарегистрировать зеленый слиток
    - Добавить RegistryObject<Item> GREEN_INGOT в ModItems


    - Создать Item с Properties.tab(CreativeModeTab.TAB_MATERIALS)
    - _Requirements: 3.1, 3.2, 3.3, 6.2_
  


  - [ ] 4.3 Зарегистрировать BlockItem для блока руды
    - Добавить RegistryObject<BlockItem> GREEN_ORE_ITEM в ModItems
    - Создать BlockItem, связанный с ModBlocks.GREEN_ORE

    - Использовать Properties.tab(CreativeModeTab.TAB_BUILDING_BLOCKS)


    - _Requirements: 5.3, 6.1_

- [ ] 5. Создать loot table для блока руды
  - Создать директорию data/ineyws_green_star/loot_tables/blocks/

  - Создать файл green_ore.json
  - Настроить alternatives для Silk Touch (выдает блок) и обычной добычи (выдает слиток)
  - Добавить функцию apply_bonus с enchantment fortune и formula ore_drops
  - Добавить функцию explosion_decay

  - _Requirements: 4.1, 4.2, 4.3, 4.4, 4.5_



- [ ] 6. Создать модели и blockstates
  - [ ] 6.1 Создать blockstate для зеленой руды
    - Создать директорию assets/ineyws_green_star/blockstates/


    - Создать файл green_ore.json с вариантом, указывающим на модель блока
    - _Requirements: 2.1_
  
  - [ ] 6.2 Создать модель блока зеленой руды
    - Создать директорию assets/ineyws_green_star/models/block/
    - Создать файл green_ore.json с parent "minecraft:block/cube_all"
    - Указать текстуру "ineyws_green_star:block/green_ore"
    - _Requirements: 2.1_
  
  - [ ] 6.3 Создать модель предмета для блока руды
    - Создать директорию assets/ineyws_green_star/models/item/
    - Создать файл green_ore.json с parent "ineyws_green_star:block/green_ore"
    - _Requirements: 2.1_
  
  - [ ] 6.4 Создать модель предмета для зеленого слитка
    - Создать файл green_ingot.json в models/item/
    - Использовать parent "minecraft:item/generated"
    - Указать текстуру "ineyws_green_star:item/green_ingot"
    - _Requirements: 3.1_

- [ ] 7. Создать placeholder текстуры
  - [ ] 7.1 Создать текстуру блока зеленой руды
    - Создать директорию assets/ineyws_green_star/textures/block/
    - Создать файл green_ore.png (16x16 пикселей)
    - Использовать зеленые оттенки с паттерном руды
    - _Requirements: 2.1_
  
  - [ ] 7.2 Создать текстуру зеленого слитка
    - Создать директорию assets/ineyws_green_star/textures/item/
    - Создать файл green_ingot.png (16x16 пикселей)
    - Использовать форму слитка с зеленым цветом
    - _Requirements: 3.1_

- [ ] 8. Добавить локализацию
  - [ ] 8.1 Создать английскую локализацию
    - Создать директорию assets/ineyws_green_star/lang/
    - Создать файл en_us.json
    - Добавить переводы для "block.ineyws_green_star.green_ore" и "item.ineyws_green_star.green_ingot"
    - _Requirements: 2.1, 3.2_
  
  - [x] 8.2 Создать русскую локализацию


    - Создать файл ru_ru.json
    - Добавить русские переводы для блока и предмета
    - _Requirements: 2.1, 3.2_

- [ ] 9. Тестирование и отладка
  - [ ] 9.1 Протестировать загрузку мода
    - Запустить игру командой `gradlew runClient`
    - Проверить, что мод появляется в списке модов
    - Проверить логи на отсутствие ошибок регистрации
    - _Requirements: 1.2, 1.3_
  
  - [ ] 9.2 Протестировать блок в игре
    - Выдать блок командой `/give @p ineyws_green_star:green_ore`
    - Разместить блок и проверить свечение (F3 для проверки уровня света)
    - Проверить, что текстура отображается корректно
    - _Requirements: 2.1, 2.2, 5.4_
  
  - [ ] 9.3 Протестировать добычу блока
    - Попробовать сломать блок рукой (не должно быть дропа)
    - Попробовать сломать деревянной киркой (не должно быть дропа)
    - Сломать каменной киркой и проверить дроп слитка
    - Проверить звук разрушения
    - _Requirements: 2.3, 2.4, 2.5, 2.6, 4.1_
  
  - [ ] 9.4 Протестировать зачарования
    - Создать кирки с Fortune I, II, III через команды
    - Проверить количество дропа (должно быть 1-2, 1-3, 1-4)
    - Создать кирку с Silk Touch
    - Проверить, что выпадает сам блок руды
    - _Requirements: 4.2, 4.3, 4.4, 4.5_
  
  - [ ] 9.5 Протестировать творческий режим
    - Переключиться в творческий режим
    - Открыть инвентарь и найти блок во вкладке Building Blocks
    - Найти слиток во вкладке Materials
    - Проверить поиск по названию
    - _Requirements: 6.1, 6.2, 6.3_
  
  - [ ] 9.6 Протестировать локализацию
    - Проверить названия на английском языке
    - Переключить язык на русский и проверить названия
    - _Requirements: 2.1, 3.2_

- [ ] 10. Финальная сборка
  - Выполнить `gradlew build` для создания JAR файла
  - Проверить, что файл создан в build/libs/
  - Протестировать JAR файл в чистой установке Minecraft с Forge
  - _Requirements: 1.4_
