package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class SentinelBlade {

    public static WeaponProfile createSentinelBladeRanged() {
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

    public static WeaponProfile createSentinelBladeMelee() {
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
