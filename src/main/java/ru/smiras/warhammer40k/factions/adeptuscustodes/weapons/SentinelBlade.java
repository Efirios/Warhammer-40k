/**
 * Профиль оружия Sentinel Blade (альтернативное оружие Custodian Guard) из кодекса Adeptus Custodes
 * 10-й редакции.
 *
 * Реализует два режима: Ranged (12", Assault, Pistol, 2A, BS2+, S4, AP-1, D2) и Melee (5A, WS2+, S6, AP-2, D1).
 *
 * Создаётся через фабричные методы createRanged() и createMelee().
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class SentinelBlade {

    public static WeaponProfile createRanged() {
        return new WeaponProfile(
                "Sentinel Blade",
                12,
                WeaponType.RANGED,
                2,
                2,
                4,
                -1,
                new FixedDamage(2),
                Set.of(Keyword.ASSAULT, Keyword.PISTOL),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }

    public static WeaponProfile createMelee() {
        return new WeaponProfile(
                "Sentinel Blade",
                0,
                WeaponType.MELEE,
                5,
                2,
                6,
                -2,
                new FixedDamage(1),
                Set.of(),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }
}
