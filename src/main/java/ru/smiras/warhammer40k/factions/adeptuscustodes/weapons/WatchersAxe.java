/**
 * Профиль оружия Watcher's Axe (оружие Траяна Валориса) из кодекса Adeptus Custodes 10-й редакции.
 *
 * Реализует характеристики: Melee, 6A, WS2+, S10, AP-2, Damage 3.
 *
 * Создаётся через фабричный метод create() и добавляется в Datasheet юнита.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;

import java.util.*;

public class WatchersAxe {

    public static WeaponProfile create() {
        return new WeaponProfile(
                "Watcher's Axe",
                0,
                WeaponType.MELEE,
                6,
                2,
                10,
                -2,
                new FixedDamage(3),
                Set.of(),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }
}
