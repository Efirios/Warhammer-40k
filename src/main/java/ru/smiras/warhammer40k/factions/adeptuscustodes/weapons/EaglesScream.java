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
