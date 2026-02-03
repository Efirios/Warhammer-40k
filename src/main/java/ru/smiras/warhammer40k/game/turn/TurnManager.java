/**
 * Менеджер игрового цикла и структуры хода (Game Loop & Turn Structure) для Warhammer 40,000 10-й редакции.
 *
 * Этот класс выступает "Дирижером" игры. Он управляет глобальным состоянием матча:
 * 1. Отслеживает текущий раунд, активного игрока и текущую фазу.
 * 2. Реализует логику переключения фаз (Command -> Movement -> Shooting -> Charge -> Fight).
 * 3. Автоматически запускает триггеры способностей (onPhaseStart/onPhaseEnd) для всех юнитов
 *    в момент смены фаз (например, выбор стоек Martial Ka'tah в Command Phase).
 *
 * Используется в основном цикле приложения для продвижения игры вперед через метод nextPhase().
 */

package ru.smiras.warhammer40k.game.turn;

import ru.smiras.warhammer40k.core.dice.DiceRoller;
import ru.smiras.warhammer40k.core.model.Ability;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

import java.util.List;

public class TurnManager {
    private int currentRound;
    private PhaseType currentPhase;
    private boolean isPlayer1Turn;
    private List<UnitInstance> armyPlayer1;
    private List<UnitInstance> armyPlayer2;

    public TurnManager(List<UnitInstance> armyPlayer1, List<UnitInstance> armyPlayer2) {
        this.armyPlayer1 = armyPlayer1;
        this.armyPlayer2 = armyPlayer2;
        currentRound = 1;
        currentPhase = PhaseType.COMMAND_PHASE;
        isPlayer1Turn = determineFirstPlayer();
        triggerPhaseStart();
    }

    public void nextPhase() {
        triggerPhaseEnd();

        switch (currentPhase) {
            case COMMAND_PHASE -> currentPhase = PhaseType.MOVEMENT_PHASE;
            case MOVEMENT_PHASE -> currentPhase = PhaseType.SHOOTING_PHASE;
            case SHOOTING_PHASE -> currentPhase = PhaseType.CHARGE_PHASE;
            case CHARGE_PHASE -> currentPhase = PhaseType.FIGHT_PHASE;
            case FIGHT_PHASE -> endTurnLogic();
        }

        triggerPhaseStart();
    }

    private void endTurnLogic() {
        currentPhase = PhaseType.COMMAND_PHASE;

        if (isPlayer1Turn) {
            isPlayer1Turn = false;
        } else {
            isPlayer1Turn = true;
            currentRound += 1;
        }
    }

    private void triggerPhaseStart() {
        for (UnitInstance unit  : armyPlayer1) {
            for (Ability ability : unit.getActiveAbilities()) {
                ability.onPhaseStart(unit, currentPhase);
            }
        }

        for (UnitInstance unitInstance  : armyPlayer2) {
            for (Ability ability : unitInstance.getActiveAbilities()) {
                ability.onPhaseStart(unitInstance, currentPhase);
            }
        }
    }

    private void triggerPhaseEnd() {
        for (UnitInstance unit  : armyPlayer1) {
            for (Ability ability : unit.getActiveAbilities()) {
                ability.onPhaseEnd(unit, currentPhase);
            }
        }

        for (UnitInstance unit  : armyPlayer2) {
            for (Ability ability : unit.getActiveAbilities()) {
                ability.onPhaseEnd(unit, currentPhase);
            }
        }
    }

    private boolean determineFirstPlayer() {
        System.out.println("\n=== ОПРЕДЕЛЕНИЕ ПЕРВОГО ХОДА (Roll-off) ===");

        while (true) {
            int roll1 = DiceRoller.roll(1, 6);
            int roll2 = DiceRoller.roll(1,6);

            System.out.print("Игрок 1 бросает: " + roll1 + " | Игрок 2 бросает: " + roll2);

            if (roll1 > roll2) {
                System.out.println(" -> Победил Игрок 1!");
                return true;
            } else if (roll2 > roll1) {
                System.out.println(" -> Победил Игрок 2!");
                return false;
            } else {
                System.out.println(" -> Ничья! Переброс...");
            }
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public PhaseType getCurrentPhase() {
        return currentPhase;
    }

    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    public boolean getPlayer() {
        return isPlayer1Turn;
    }
}
