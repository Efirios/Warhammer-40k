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
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.TrajannValoris;
import ru.smiras.warhammer40k.factions.worldeaters.units.Angron;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public class MainWarhammer40k {
    public static void main(String[] args) {

        Datasheet trajannValoris = new TrajannValoris("Trajann-1");
        Datasheet angron = new Angron("Angron-1");

        UnitInstance trajannUnit = new UnitInstance(trajannValoris);
        UnitInstance angronUnit = new UnitInstance(angron);

        CombatEngine engine = new CombatEngine();

        // --- ТЕСТ 1: Стрельба без баффов ---
        System.out.println("\n--- ТЕСТ 1: Траян стреляет в Ангрона (Без стоек) ---");
        // Берем первое оружие Траяна (Eagle's Scream)
        WeaponProfile gun = trajannUnit.getDatasheet().getWeapons().get(0);
        engine.resolveAttack(trajannUnit, angronUnit, gun, PhaseType.SHOOTING_PHASE);

        // --- ТЕСТ 2: Активируем Martial Ka'tah (Dacatarai +1 Hit) ---
        System.out.println("\n--- Активация способности... ---");
        // Вызываем событие начала фазы (тут тебе предложат выбрать стойку - выбери 1)
        MortialKatah.create().onPhaseStart(trajannUnit, PhaseType.COMMAND_PHASE);


        // --- ТЕСТ 3: Стрельба с баффом (Должна быть БЕЗ изменений, т.к. это стрельба) ---
        System.out.println("\n--- ТЕСТ 3: Траян стреляет (Стойка Dacatarai - не должна работать на стрельбу) ---");
        engine.resolveAttack(trajannUnit, angronUnit, gun, PhaseType.SHOOTING_PHASE);


        // --- ТЕСТ 4: Ближний бой с баффом (Должен быть +1 к Hit) ---
        System.out.println("\n--- ТЕСТ 4: Траян бьет топором (Стойка Dacatarai - должна дать +1) ---");
        // Берем второе оружие (Watcher's Axe)
        WeaponProfile axe = trajannUnit.getDatasheet().getWeapons().get(1);
        engine.resolveAttack(trajannUnit, angronUnit, axe, PhaseType.FIGHT_PHASE);
    }
}
