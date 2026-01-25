/**
 * Главный класс для запуска и тестирования прототипа игры Warhammer 40,000 10-й редакции.
 *
 * Создаёт экземпляры юнитов (Trajann Valoris, Custodian Guard, Angron) через их конструкторы,
 * передавая уникальный ID, и выводит их характеристики в консоль через toString().
 *
 * Используется для быстрого тестирования Datasheet, оружия и способностей.
 * В будущем может быть заменён на полноценный UI или тесты.
 */

package ru.smiras.warhammer40k;

import ru.smiras.warhammer40k.core.model.Datasheet;
import ru.smiras.warhammer40k.core.model.WeaponProfile;
import ru.smiras.warhammer40k.core.rules.CombatEngine;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.MortialKatah;
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

        while (trajannValorisUnit.isAlive() || angronUnit.isAlive()) {
            System.out.println( "===== РАУНД " + turnManager.getCurrentRound() + " ======");
            System.out.println( "===== ХОДИТ " + (turnManager.isPlayer1Turn() ? "ИГРОК 1 (Adeptus Custodes)" :
                    "ИГРОК 2 (World Eaters)"));

            switch (turnManager.getCurrentPhase()) {
                case COMMAND_PHASE -> System.out.println("--- COMMAND PHASE ---");
                case MOVEMENT_PHASE -> System.out.println("--- MOVEMENT PHASE ---");
                case SHOOTING_PHASE -> System.out.println("--- SHOOTING PHASE ---");
                case CHARGE_PHASE -> System.out.println("--- CHARGE PHASE ---");
                case FIGHT_PHASE ->
            }
        }
    }
}
