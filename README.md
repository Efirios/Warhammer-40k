# Warhammer 40,000 Simulator (Java Edition)

Цифровой симулятор настольной игры Warhammer 40,000 (10-я редакция).
Проект реализует ядро правил (Core Rules), последовательность хода и механику боя в консольном режиме с архитектурой, готовой к подключению графического интерфейса (LibGDX).

## 🚀 Текущий статус: Рабочий прототип (v0.2)

Реализован полный игровой цикл (Game Loop) и математическая модель боя. Два игрока (Adeptus Custodes и World Eaters) могут сражаться в автоматизированном режиме с логами событий.

### ✅ Реализованные механики (Core Rules)
*   **Структура хода:**
    *   Автоматический менеджер хода (`TurnManager`).
    *   Смена фаз: Command -> Movement -> Shooting -> Charge -> Fight.
    *   Roll-off (бросок кубиков) за первый ход.
    *   Счетчик раундов и определение активного игрока.
*   **Боевая система (`CombatEngine`):**
    *   Полная последовательность атаки: **Hit Roll → Wound Roll → Save Roll → Damage**.
    *   **Hit Roll:** Учет BS/WS, модификаторов, Critical Hits (Natural 6) и промахов (Natural 1).
    *   **Wound Roll:** Таблица S vs T (2+, 3+, 4+, 5+, 6+), Critical Wounds.
    *   **Save Roll:** Выбор лучшего спасброска (Armor Save + AP vs Invulnerable Save).
    *   **Damage:** Переменный урон (D3, D6+X), распределение урона по моделям.
*   **Специальные правила (Keywords & Abilities):**
    *   **Devastating Wounds:** Игнорирование спасбросков при критическом ранении.
    *   **Feel No Pain 5+:** Способность игнорировать полученный урон.
    *   **Deep Strike:** Механика резервов (база).
*   **Фракционные способности:**
    *   **Martial Ka'tah (Adeptus Custodes):** Выбор боевой стойки (+1 Hit, +1 Wound или +1 Damage) в начале фазы.

### 💂 Реализованные Юниты
*   **Adeptus Custodes:**
    *   Trajann Valoris (Watcher's Axe, Eagle's Scream).
    *   Custodian Guard (Guardian Spear, Sentinel Blade, Misericordia).
*   **World Eaters:**
    *   Angron, Daemon Primarch (Samniarius & Spinegrinder - Strike/Sweep profiles).

## 🛠 Архитектура проекта
Проект построен на принципах ООП с четким разделением ответственности:
*   **Model:** `Datasheet`, `WeaponProfile` — неизменяемые данные (Stateless).
*   **State:** `UnitInstance` — живое состояние юнита (раны, модели, эффекты).
*   **Logic:** `CombatEngine`, `TurnManager` — чистая логика правил.
*   **Interfaces:** Система `Ability` позволяет легко добавлять новые правила без изменения движка.

## 📋 Требования
*   Java 21+ (используются современные возможности switch/case и records).
*   Консоль (для текущего вывода логов боя).

## 🔮 Планы (Roadmap)
*   [x] Ядро боевой системы (Melee).
*   [x] Менеджер хода и фаз.
*   [ ] Реализация логики стрельбы (Shooting Phase).
*   [ ] Реализация движения и дистанций (Movement/Charge).
*   [ ] Подключение графической библиотеки **LibGDX** (2.5D визуализация).
*   [ ] Расширение списка юнитов и фракций.

---
*Автор: [Твое Имя/Ник]*