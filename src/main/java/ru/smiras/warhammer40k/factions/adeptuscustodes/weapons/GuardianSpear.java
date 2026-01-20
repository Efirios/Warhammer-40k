package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class GuardianSpear {

    public static WeaponProfile createGuardianSpearRanged() {
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

    public static WeaponProfile createGuardianSpearMelee() {
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
