/**
 * Профиль оружия Guardian Spear (стандартное оружие Custodian Guard) из кодекса Adeptus Custodes
 * 10-й редакции.
 *
 * Реализует два режима: Ranged (24", Assault, 2A, BS2+, S4, AP-1, D2) и Melee (5A, WS2+, S7, AP-2, D2).
 *
 * Создаётся через фабричные методы createRanged() и createMelee().
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class GuardianSpear {

    public static WeaponProfile createRanged() {
        return new WeaponProfile(
                "Guardian Spear",
                24,
                WeaponType.RANGED,
                2,
                2,
                4,
                -1,
                new FixedDamage(2),
                Set.of(Keyword.ASSAULT),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }

    public static WeaponProfile createMelee() {
        return new WeaponProfile(
                "Guardian Spear",
                0,
                WeaponType.MELEE,
                5,
                2,
                7,
                -2,
                new FixedDamage(2),
                Set.of(),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }
}
