/**
 * Профиль оружия Eagle's Scream (комби-болтер Траяна Валориса) из кодекса Adeptus Custodes
 * 10-й редакции.
 *
 * Реализует характеристики: 24", Assault, 2A, BS2+, S5, AP-2, Damage 3.
 *
 * Создаётся через фабричный метод create() и добавляется в Datasheet юнита.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class EaglesScream {

    public static WeaponProfile create() {
        return new WeaponProfile(
                "Eagle's Scream",
                24,
                WeaponType.RANGED,
                2,
                2,
                5,
                -2,
                new FixedDamage(3),
                Set.of(Keyword.ASSAULT),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }
}
