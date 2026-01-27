/**
 * Перечисление всех фаз хода в правилах Warhammer 40,000 10-й редакции.
 *
 * Реализует структуру игрового хода: Command Phase, Movement Phase, Psychic Phase,
 * Shooting Phase, Charge Phase, Fight Phase, Morale Phase.
 *
 * Используется в Ability (isActive, onPhaseStart/End), AttackContext (фаза атаки),
 * TurnManager/GameLoop (переключение фаз), UnitInstance (проверка состояния).
 */

package ru.smiras.warhammer40k.core.util;

public enum PhaseType {
    COMMAND_PHASE,
    MOVEMENT_PHASE,
    SHOOTING_PHASE,
    CHARGE_PHASE,
    FIGHT_PHASE
}
