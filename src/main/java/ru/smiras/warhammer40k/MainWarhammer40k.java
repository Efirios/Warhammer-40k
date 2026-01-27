/**
 * Главный класс и точка входа в приложение симулятора Warhammer 40,000 10-й редакции.
 *
 * Инициализирует боевую сцену, связывает основные компоненты архитектуры (TurnManager, CombatEngine)
 * и управляет основным игровым циклом (Game Loop).
 *
 * Основные обязанности:
 * - Инициализация данных: создание Datasheet, UnitInstance и формирование списков армий.
 * - Управление циклом: цикл выполняется до тех пор, пока живы юниты одной из сторон.
 * - Сценарная логика (Scripting): определяет поведение юнитов в фазу боя (выбор целей, проверка isAlive).
 * - Интерфейс: обеспечивает консольный вывод статуса игры и управление паузами через ввод пользователя.
 *
 * В будущем этот класс будет заменен или интегрирован в графический движок (LibGDX) как основной контроллер игры.
 */

package ru.smiras.warhammer40k;

import ru.smiras.warhammer40k.core.model.Datasheet;
import ru.smiras.warhammer40k.core.rules.CombatEngine;
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.CustodianGuard;
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.TrajannValoris;
import ru.smiras.warhammer40k.factions.worldeaters.units.Angron;
import ru.smiras.warhammer40k.game.state.UnitInstance;
import ru.smiras.warhammer40k.game.turn.TurnManager;

import java.util.List;
import java.util.Scanner;

public class MainWarhammer40k {
    public static void main(String[] args) {
        Datasheet trajannValoris = new TrajannValoris("Trajann-Valoris-1");
        Datasheet custodianGuard = new CustodianGuard("Custodian-Guard-1");
        Datasheet angron = new Angron("Angron-1");

        UnitInstance trajannValorisUnit = new UnitInstance(trajannValoris);
        UnitInstance custodianGuardUnit = new UnitInstance(custodianGuard);
        UnitInstance angronUnit = new UnitInstance(angron);

        List<UnitInstance> adeptusCutodes = List.of(trajannValorisUnit, custodianGuardUnit);
        List<UnitInstance> worldEaters = List.of(angronUnit);

        TurnManager turnManager = new TurnManager(adeptusCutodes, worldEaters);
        CombatEngine combatEngine = new CombatEngine();
        Scanner scanner = new Scanner(System.in);

        while ((trajannValorisUnit.isAlive() || custodianGuardUnit.isAlive()) && angronUnit.isAlive()) {
            System.out.println( "===== РАУНД " + turnManager.getCurrentRound() + " ======");
            System.out.println( "===== ХОДИТ " + (turnManager.isPlayer1Turn() ? "ИГРОК 1 (Adeptus Custodes)" :
                    "ИГРОК 2 (World Eaters)"));

            switch (turnManager.getCurrentPhase()) {
                case COMMAND_PHASE -> System.out.println("--- COMMAND PHASE ---");
                case MOVEMENT_PHASE -> System.out.println("--- MOVEMENT PHASE ---");
                case SHOOTING_PHASE -> System.out.println("--- SHOOTING PHASE ---");
                case CHARGE_PHASE -> System.out.println("--- CHARGE PHASE ---");

                case FIGHT_PHASE -> {
                    if (turnManager.isPlayer1Turn()) {
                        if (trajannValorisUnit.isAlive()) {
                            combatEngine.resolveAttack(trajannValorisUnit, angronUnit,
                                    trajannValorisUnit.getDatasheet().getWeapons().get(1),
                                    turnManager.getCurrentPhase());
                        }

                        if (custodianGuardUnit.isAlive() && angronUnit.isAlive()) {
                            combatEngine.resolveAttack(custodianGuardUnit, angronUnit,
                                    custodianGuardUnit.getDatasheet().getWeapons().get(1),
                                    turnManager.getCurrentPhase());
                        }
                    } else {
                        if (angronUnit.isAlive()) {
                            if (trajannValorisUnit.isAlive()) {
                                combatEngine.resolveAttack(angronUnit, trajannValorisUnit,
                                        angronUnit.getDatasheet().getWeapons().get(0),
                                        turnManager.getCurrentPhase());
                            } else if (custodianGuardUnit.isAlive()) {
                                combatEngine.resolveAttack(angronUnit, custodianGuardUnit,
                                        angronUnit.getDatasheet().getWeapons().get(1),
                                        turnManager.getCurrentPhase());
                            }
                        }
                    }

                    if ((!trajannValorisUnit.isAlive() && !custodianGuardUnit.isAlive()) || !angronUnit.isAlive()) {
                        return;
                    }
                }
            }

            System.out.println("Нажмите Enter для продолжения...");
            scanner.nextLine();

            turnManager.nextPhase();
        }
    }
}
