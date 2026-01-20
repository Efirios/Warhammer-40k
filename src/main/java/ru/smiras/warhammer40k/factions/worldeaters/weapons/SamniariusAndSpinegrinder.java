package ru.smiras.warhammer40k.factions.worldeaters.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class SamniariusAndSpinegrinder {

    public static WeaponProfile createSamniariusAndSpinegrinderStrike() {
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

    public static WeaponProfile createSamniariusAndSpinegrinderSweep() {
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
