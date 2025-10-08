# Iney's Green Star Mod

Мод для Minecraft 1.16.5 (Forge), добавляющий зеленую руду и зеленые слитки.

## Возможности

- **Зеленая руда** - новый блок руды, который светится (уровень света 14)
- **Зеленые слитки** - новый материал, получаемый из зеленой руды
- Поддержка зачарований Fortune и Silk Touch
- Локализация на русском и английском языках

## Установка

1. Установите Minecraft Forge 1.16.5 (версия 36.2.39 или выше)
2. Скопируйте файл `ineyws_green_star-1.0.0.jar` в папку `mods` вашего Minecraft
3. Запустите игру

## Разработка

### Требования

- Java 8 или выше
- Gradle (включен wrapper)

### Сборка

```bash
gradlew build
```

JAR файл будет создан в `build/libs/`

### Запуск в режиме разработки

```bash
gradlew runClient
```

## Текстуры

⚠️ **Важно**: В данный момент используются placeholder текстуры (пустые файлы).

Чтобы добавить свои текстуры:

1. Создайте текстуру 16x16 пикселей для блока руды
2. Сохраните как `src/main/resources/assets/ineyws_green_star/textures/block/green_ore.png`
3. Создайте текстуру 16x16 пикселей для слитка
4. Сохраните как `src/main/resources/assets/ineyws_green_star/textures/item/green_ingot.png`
5. Пересоберите мод командой `gradlew build`

## Использование в игре

### Получение зеленой руды

- Добывайте блок зеленой руды каменной киркой или лучше
- Блок светится, что облегчает его поиск в темноте

### Получение зеленых слитков

- Добудьте зеленую руду обычной киркой - получите 1 зеленый слиток
- С зачарованием Fortune I - получите 1-2 слитка
- С зачарованием Fortune II - получите 1-3 слитка
- С зачарованием Fortune III - получите 1-4 слитка
- С зачарованием Silk Touch - получите сам блок руды

### Команды для тестирования

```
/give @p ineyws_green_star:green_ore
/give @p ineyws_green_star:green_ingot
```

## Структура проекта

```
src/main/
├── java/com/ineyws/greenstar/
│   ├── IneyWSGreenStar.java          # Главный класс мода
│   ├── blocks/
│   │   └── GreenOreBlock.java        # Класс блока руды
│   └── init/
│       ├── ModBlocks.java            # Регистрация блоков
│       └── ModItems.java             # Регистрация предметов
└── resources/
    ├── META-INF/
    │   └── mods.toml                 # Метаданные мода
    ├── pack.mcmeta                   # Метаданные ресурс-пака
    ├── assets/ineyws_green_star/
    │   ├── blockstates/              # Состояния блоков
    │   ├── models/                   # Модели блоков и предметов
    │   ├── textures/                 # Текстуры
    │   └── lang/                     # Локализация
    └── data/ineyws_green_star/
        └── loot_tables/              # Таблицы дропа
```

## Версия

- **Версия мода**: 1.0.0
- **Minecraft**: 1.16.5
- **Forge**: 36.2.39+

## Автор

ineyws

## Лицензия

All Rights Reserved
