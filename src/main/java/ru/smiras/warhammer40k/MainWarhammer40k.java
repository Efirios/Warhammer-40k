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

public class MainWarhammer40k {
    public static void main(String[] args) {
        Datasheet trajannData = new TrajannValoris("Trajann");
        Datasheet custodianGuardData1 = new CustodianGuard("Custodian Guard");
        Datasheet angronData = new Angron("Angron");

        UnitInstance trajannUnit = new UnitInstance(trajannData);
        UnitInstance custodianGuardUnit1 = new UnitInstance(custodianGuardData1);
        UnitInstance angronUnit = new UnitInstance(angronData);

        CombatEngine engine = new CombatEngine();

        // Подготовка: Выбираем стойку перед началом замеса
        System.out.println("--- Фаза Командования: Траян выбирает стойку ---");
        MortialKatah.create().onPhaseStart(trajannUnit, PhaseType.COMMAND_PHASE);

        System.out.println("--- Фаза Командования: Custodian Guard выбирают стойку ---");
        MortialKatah.create().onPhaseStart(custodianGuardUnit1, PhaseType.COMMAND_PHASE);

        int round = 1;

        // БОЙ НАСМЕРТЬ
        while (trajannUnit.isAlive() && angronUnit.isAlive()) {
            System.out.println("\n========= РАУНД " + round + " =========");

            // --- ХОД ТРАЯНА ---
            System.out.println("\n[Траян атакует Ангрона]");
            // Оружие: Watcher's Axe (index 1)
            WeaponProfile axe = trajannUnit.getDatasheet().getWeapons().get(1);
            engine.resolveAttack(trajannUnit, angronUnit, axe, PhaseType.FIGHT_PHASE);

            if (!angronUnit.isAlive()) {
                System.out.println("\n*** АНГРОН ПОВЕРЖЕН! ПОБЕДА ИМПЕРИУМА! ***");
                break;
            }

            // --- ХОД Custodian Guard ---
            System.out.println("\n[Custodian Guard атакует Ангрона]");
            // Оружие: Watcher's Axe (index 1)
            WeaponProfile spear = custodianGuardUnit1.getDatasheet().getWeapons().get(1);
            engine.resolveAttack(custodianGuardUnit1, angronUnit, spear, PhaseType.FIGHT_PHASE);

            if (!angronUnit.isAlive()) {
                System.out.println("\n*** АНГРОН ПОВЕРЖЕН! ПОБЕДА ИМПЕРИУМА! ***");
                break;
            }

            // --- ХОД АНГРОНА ---
            System.out.println("\n[Ангрон атакует Траяна]");
            // Оружие: Samniarius Strike (index 0) - бьем профилем Strike (сильным)
            WeaponProfile strike = angronUnit.getDatasheet().getWeapons().get(0);

            engine.resolveAttack(angronUnit, trajannUnit, strike, PhaseType.FIGHT_PHASE);

            if (!trajannUnit.isAlive()) {
                System.out.println("\n*** ТРАЯН ПАЛ! ЧЕРЕПА ДЛЯ ТРОНА ЧЕРЕПОВ! ***");
                break;
            }

            round++;
        }
    }
}
