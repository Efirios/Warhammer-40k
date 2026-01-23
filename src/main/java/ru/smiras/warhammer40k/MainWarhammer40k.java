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
        Datasheet trajannData = new TrajannValoris("Trajann");
        Datasheet angronData = new Angron("Angron");

        UnitInstance trajannUnit = new UnitInstance(trajannData);
        UnitInstance angronUnit = new UnitInstance(angronData);

        CombatEngine engine = new CombatEngine();

        // Оружие: Watcher's Axe (S10)
        WeaponProfile axe = trajannUnit.getDatasheet().getWeapons().get(1);
        System.out.println("Оружие:" + axe);

        System.out.println("Сценарий: Траян (S10) бьет Ангрона (T11).");
        System.out.println("Нужно 5+ для пробития.\n");

        // --- ТЕСТ 1: Без стоек ---
        System.out.println("--- ТЕСТ 1: Обычные атаки ---");
        engine.resolveAttack(trajannUnit, angronUnit, axe, PhaseType.FIGHT_PHASE);

        // --- ТЕСТ 2: Включаем Kaptaris (+1 to Wound) ---
        System.out.println("\n--- Активация Kaptaris (+1 to Wound)... ---");
        // Выбери в консоли цифру 2
        MortialKatah.create().onPhaseStart(trajannUnit, PhaseType.COMMAND_PHASE);

        System.out.println("\n--- ТЕСТ 2: Атаки с +1 на ранение (теперь пробиваем на 4+) ---");
        engine.resolveAttack(trajannUnit, angronUnit, axe, PhaseType.FIGHT_PHASE);
    }
}
