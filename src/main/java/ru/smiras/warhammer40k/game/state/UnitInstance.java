/**
 * Конкретный экземпляр юнита в бою (на столе) в правилах Warhammer 40,000 10-й редакции.
 *
 * Реализует текущее состояние юнита во время игры: сколько ран осталось, сколько моделей живо,
 * какие способности активны, прикреплён ли лидер, статус (Battle-shocked, Activated и т.д.),
 * позиция на поле (в будущем) и другие изменяемые характеристики.
 *
 * В отличие от Datasheet (неизменяемый шаблон юнита из кодекса), UnitInstance — это
 * "живое" состояние конкретного юнита в одной партии: он теряет раны, модели, может стать
 * Battle-shocked, активировать способности и т.д.
 *
 * Основные обязанности:
 * - Хранит ссылку на Datasheet для базовых характеристик
 * - Отслеживает текущее здоровье (remainingWounds, remainingModels)
 * - Управляет активными способностями (из Datasheet + динамические эффекты)
 * - Реагирует на урон, способности и фазы игры
 * - Используется в AttackContext (как attacker/target), в игровом цикле (TurnManager/GameLoop)
 *   и в расчёте Objective Control, Morale и т.д.
 *
 * При создании: копирует способности из Datasheet, инициализирует здоровье.
 * В будущем может добавлять поля: позиция, статус Activated, прикреплённый лидер, использованные способности.
 */

package ru.smiras.warhammer40k.game.state;

import ru.smiras.warhammer40k.core.model.*;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.KatahEffect;

import java.util.*;

public class UnitInstance {

    private final Datasheet datasheet;
    private int remainingWounds; // текущее значение W
    private int remainingModels; // оставшиеся модели (для отрядов)
    private boolean isBattleShoked; // статус Battle-shocked
    private boolean isActivatedThisPhase; // активирован ли в текущей фазе

    private Map<Ability, Integer> usesThisBattle; // использований за бой по каждой способности
    private Map<Ability, Integer> usesThisPhase; // использований за фазу
    private List<Ability> activeAbilities; // активные способности (копия из datasheet)

    private KatahEffect currentMortialKatahEffect; //текущее состояние Martial Ka'tah (enum)

    public UnitInstance(Datasheet datasheet){
        this.datasheet = datasheet;
    }

    public boolean isAlive() {
        if (datasheet.getBaseWounds() < 1){
            return false;
        } else {
            return true;
        }
    }


}
