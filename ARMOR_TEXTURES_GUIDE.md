# Инструкция по текстурам брони

## Где взять текстуры

Тебе нужно создать 2 PNG файла для брони:

### 1. green_layer_1.png
- **Размер:** 64x32 пикселей
- **Используется для:** Шлем, Нагрудник, Ботинки
- **Где взять оригинал:** `.minecraft/versions/1.16.5/1.16.5.jar/assets/minecraft/textures/models/armor/diamond_layer_1.png`

### 2. green_layer_2.png
- **Размер:** 64x32 пикселей  
- **Используется для:** Поножи
- **Где взять оригинал:** `.minecraft/versions/1.16.5/1.16.5.jar/assets/minecraft/textures/models/armor/diamond_layer_2.png`

## Как создать

1. Открой `.minecraft/versions/1.16.5/1.16.5.jar` архиватором (7-Zip, WinRAR)
2. Найди `assets/minecraft/textures/models/armor/diamond_layer_1.png` и `diamond_layer_2.png`
3. Скопируй их
4. Открой в графическом редакторе (Photoshop, GIMP, Paint.NET)
5. Перекрась из голубого в зеленый цвет
6. Сохрани как:
   - `src/main/resources/assets/ineyws_green_star/textures/models/armor/green_layer_1.png`
   - `src/main/resources/assets/ineyws_green_star/textures/models/armor/green_layer_2.png`

## Быстрый способ

Можешь просто скопировать алмазные текстуры и переименовать их в `green_layer_1.png` и `green_layer_2.png` - они будут голубыми, но хотя бы не фиолетовыми! Потом перекрасишь.

## Важно!

Имена файлов должны совпадать с именем материала в коде: `green` (из `GreenArmorMaterial.GREEN`)
