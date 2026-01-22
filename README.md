# Warhammer 40k Simulator v. 0.0.1

Компьютерная симуляция настольной игры Warhammer 40,000 (10-я редакция) на Java.

Цель проекта — создать точную цифровую модель настольной игры, реализовав все правила Core Rules 10th Edition (фазы, последовательность атаки, ключевые слова, спецправила), с возможностью дальнейшей визуализации на LibGDX.

## Текущий статус
Проект находится в стадии активной разработки ядра правил (Core Rules Engine).
Реализована архитектура "Stateless Logic + Stateful Entities".

### Реализовано:
- **Модель данных (Model):**
    - `Datasheet`: Неизменяемые шаблоны юнитов из Кодекса (характеристики, кейворды).
    - `WeaponProfile`: Профили оружия (Melee/Ranged, S, AP, D, Keywords).
    - `UnitInstance`: Живые экземпляры юнитов на столе (учет ран, моделей, OC, Battle-shock).
- **Система правил (Core Rules):**
    - `CombatEngine`: Центральный движок, реализующий Attack Sequence (Hit Roll -> Wound Roll...).
    - `AttackContext`: Контейнер данных для передачи состояния сквозь этапы атаки.
    - Механика **Hit Roll** с учетом модификаторов, Critical Hits (Natural 6) и Auto-fails (Natural 1).
- **Способности (Abilities):**
    - Гибкая система способностей через интерфейс `Ability`.
    - Реализованы механики:
        - *Martial Ka'tah* (Adeptus Custodes) — выбор стойки в Command Phase.
        - *Deep Strike* — правило резервов.
        - *Feel No Pain 5+* — игнорирование урона.
- **Фракции:**
    - Базовая реализация **Adeptus Custodes** (Trajann Valoris, Custodian Guard).
    - Базовая реализация **World Eaters** (Angron).

## Архитектура
Проект использует строгую типизацию и разделение ответственности:
- **State:** Состояние юнитов хранится в `UnitInstance`.
- **Logic:** Правила и способности (`CombatEngine`, `Ability`) не хранят состояние (Stateless), а модифицируют переданный контекст.
- **Dice:** Утилитарный класс `DiceRoller` для симуляции D3, D6, 2D6.

## Требования
- Java 21+
- IntelliJ IDEA (рекомендуется)

## Планы (Roadmap)
- [x] Базовая структура юнитов и оружия
- [x] Движок боя: Этап попадания (Hit Roll)
- [ ] Движок боя: Этап ранения (Wound Roll)
- [ ] Движок боя: Спасброски (Save Roll) и AP
- [ ] Движок боя: Распределение урона (Allocation & Damage)
- [ ] Реализация фаз игры (Command, Movement, Shooting, Fight)
- [ ] Графический интерфейс (LibGDX) 2.5D