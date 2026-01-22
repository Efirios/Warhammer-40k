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
    private int currentOC;
    private boolean isBattleShoked; // статус Battle-shocked
    private boolean isActivatedThisPhase; // активирован ли в текущей фазе

    private Map<Ability, Integer> usesThisBattle; // использований за бой по каждой способности
    private Map<Ability, Integer> usesThisPhase; // использований за фазу
    private List<Ability> activeAbilities; // активные способности (копия из datasheet)

    private KatahEffect currentMortialKatahEffect; //текущее состояние Martial Ka'tah (enum)

    public UnitInstance(Datasheet datasheet){
        this.datasheet = datasheet;
        remainingWounds = datasheet.getBaseWounds();
        remainingModels = datasheet.getBaseModelCount();

        activeAbilities = new ArrayList<>();
        activeAbilities.addAll(datasheet.getAbilities());
        activeAbilities.addAll(datasheet.getFactionAbilities());

        usesThisBattle = new HashMap<>();
        usesThisPhase = new HashMap<>();
        currentMortialKatahEffect = KatahEffect.NONE;
        isBattleShoked = false;
        isActivatedThisPhase = false;
        currentOC = recalculateOC();
    }

    public Datasheet getDatasheet() {
        return datasheet;
    }

    public int getRemainingWounds() {
        return remainingWounds;
    }

    public int getRemainingModels() {
        return remainingModels;
    }

    public int getCurrentOC() {
        return currentOC;
    }

    public boolean isBattleShoked() {
        return isBattleShoked;
    }

    public boolean isActivatedThisPhase() {
        return isActivatedThisPhase;
    }

    public Map<Ability, Integer> getUsesThisBattle() {
        return usesThisBattle;
    }

    public Map<Ability, Integer> getUsesThisPhase() {
        return usesThisPhase;
    }

    public List<Ability> getActiveAbilities() {
        return activeAbilities;
    }

    public KatahEffect getCurrentMortialKatahEffect() {
        return currentMortialKatahEffect;
    }

    public void setCurrentMortialKatahEffect(KatahEffect katahEffect) {
        this.currentMortialKatahEffect = katahEffect;
    }

    public void setIsBattleShoked(boolean volue) {
        this.isBattleShoked = volue;
        recalculateOC();
    }

    public void setIsActivatedThisPhase(boolean volue) {
        this.isActivatedThisPhase = volue;
    }

    public int recalculateOC() {
        int value = 0;
        if (remainingModels != 0 && !isBattleShoked) {
            value = remainingModels * datasheet.getBaseObjectiveControl();
        }
        return currentOC = value;
    }

    public int getObjectiveControl() {
        return currentOC; }

    public boolean isAlive() {
        if (remainingModels <= 0){
            return false;
        } else {
            return true;
        }
    }

    private void onModelDestroyed() {
        // Пока пусто — в будущем здесь будут триггеры способностей
    }

    private void onUnitDestroyed() {
        // Пока пусто — в будущем здесь будут триггеры способностей
    }

    public int receiveDamage(int damage) {
        if (damage <= 0 || remainingModels <= 0) {
            return 0;
        }

        int lostModels = 0;

        remainingWounds -= damage;

        while (remainingWounds <= 0 && remainingModels > 1) {
            lostModels++;
            remainingModels -= 1;
            remainingWounds += datasheet.getBaseWounds();
        }

        if (remainingModels == 1 && remainingWounds <= 0) {
            lostModels++;
            remainingModels = 0;
            remainingWounds = 0;
        }

        recalculateOC();

        if (lostModels > 0) {
            onModelDestroyed();
        }

        if (remainingModels <= 0) {
            onUnitDestroyed();
        }

        return lostModels;
    }
}
