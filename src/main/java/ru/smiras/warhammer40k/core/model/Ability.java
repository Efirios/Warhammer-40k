/**
 * Интерфейс способности/специального правила юнита из правил Warhammer 40,000 10-й редакции.
 * Описывает поведение, которое может модифицировать броски, состояние юнита, фазы игры или
 * поле боя (Lethal Hits, Devastating Wounds, Feel No Pain, Martial Ka'tah, Deep Strike и т.д.).
 *
 * Реализует правила специальных способностей: активация в определённой фазе, автоматическое
 * или по выбору игрока срабатывание, модификация бросков (hit, wound, save, damage),
 * реакция на события (critical hit/wound, phase start/end), ограничение по использованию.
 *
 * Каждая способность реализует только нужные методы (остальные остаются default).
 * Способности прикрепляются к юниту через Datasheet.abilities и используются в UnitInstance.
 */

package ru.smiras.warhammer40k.core.model;

import ru.smiras.warhammer40k.core.rules.AttackContext;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public interface Ability {

    int UNLIMITED = Integer.MAX_VALUE;

    String getName();

    String getDescription();

    default boolean isActive(UnitInstance unit, PhaseType currentPhase) {
        return true;
    }

    default boolean requiresPlayerChoice() {
        return true;
    }

    default int getMaxUsesPerBattle() {
        return UNLIMITED;
    }

    default int getMaxUsesPerPhase() {
        return UNLIMITED;
    }

    // --- Броски ---

    default void modifyHitRoll(UnitInstance unit, AttackContext context) {}

    default void modifyWoundRoll(UnitInstance unit, AttackContext context) {}

    default void modifySaveRoll(UnitInstance unit, AttackContext context) {}

    default void modifyDamageRoll(UnitInstance unit, AttackContext context) {}

    // --- Криты ---

    default void onCriticalHit(UnitInstance unit, AttackContext context) {}

    default void onCriticalWound(UnitInstance unit, AttackContext context) {}

    // --- Фазы ---

    default void onPhaseStart(UnitInstance unit, PhaseType phase) {}

    default void onPhaseEnd(UnitInstance unit, PhaseType phase) {}

    // --- После этапов ---

    default void applyAfterHitRoll(UnitInstance unit, AttackContext context) {}

    default void applyAfterWoundRoll(UnitInstance unit, AttackContext context) {}

    default void applyAfterSaveRoll(UnitInstance unit, AttackContext context) {}

    default void applyAfterDamageAllocated(UnitInstance unit, AttackContext context) {}

    default void applyReaction(UnitInstance unit, AttackContext context) {}

    default void onFightSelected(UnitInstance unit, AttackContext context) {}

    default void onFightFinished(UnitInstance unit, AttackContext context) {}
}
