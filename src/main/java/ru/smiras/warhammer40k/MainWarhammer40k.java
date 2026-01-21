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
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.MortialKatah;
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.CustodianGuard;
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.TrajannValoris;
import ru.smiras.warhammer40k.factions.worldeaters.units.Angron;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public class MainWarhammer40k {
    public static void main(String[] args) {

        Datasheet trajannValoris = new TrajannValoris("Trajann-1");
        Datasheet сustodianGuard_1 = new CustodianGuard("CustodianGuard-1");
        Datasheet angron = new Angron("Angron-1");
        UnitInstance unitInstance = new UnitInstance(trajannValoris);

        System.out.println(trajannValoris);
        System.out.println(сustodianGuard_1);
        System.out.println(angron);

        System.out.println("Способности TrajannValoris:");
        trajannValoris.getAbilities().forEach(System.out::println);
        trajannValoris.getFactionAbilities().forEach(System.out::println);

        MortialKatah.create().onPhaseStart(unitInstance, PhaseType.COMMAND_PHASE);
    }
}
