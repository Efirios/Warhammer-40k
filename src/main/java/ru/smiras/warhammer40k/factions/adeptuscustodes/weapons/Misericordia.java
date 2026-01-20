package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;
import java.util.*;

public class Misericordia {

    public static WeaponProfile create() {
        return new WeaponProfile(
                "Misericordia",
                0,
                WeaponType.MELEE,
                5,
                2,
                5,
                -2,
                new FixedDamage(1),
                Set.of(),
                Set.of(Keyword.ADEPTUS_CUSTODES)
        );
    }
}
