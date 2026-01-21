/**
 * Профиль оружия Samniarius and Spinegrinder (оружие Ангрона) из кодекса World Eaters
 * 10-й редакции.
 *
 * Реализует два режима: Strike (8A, WS2+, S14, AP-3, Damage D6+2, Devastating Wounds) и Sweep
 * (16A, WS2+, S7, AP-2, Damage 2, Devastating Wounds).
 *
 * Создаётся через фабричные методы createStrike() и createSweep().
 */

package ru.smiras.warhammer40k.factions.worldeaters.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class SamniariusAndSpinegrinder {

    public static WeaponProfile createStrike() {
        return new WeaponProfile(
                "Samniarius And Spinegrinder-Strike",
                0,
                WeaponType.MELEE,
                8,
                2,
                14,
                -3,
                new DiceDamage(1, 6, 2),
                Set.of(Keyword.DEVASTATING_WOUNDS),
                Set.of(Keyword.WORLD_EATERS)
        );
    }

    public static WeaponProfile createSweep() {
        return new WeaponProfile(
                "Samniarius And Spinegrinder-Sweep",
                0,
                WeaponType.MELEE,
                16,
                2,
                7,
                -2,
                new FixedDamage(2),
                Set.of(Keyword.DEVASTATING_WOUNDS),
                Set.of(Keyword.WORLD_EATERS)
        );
    }
}
