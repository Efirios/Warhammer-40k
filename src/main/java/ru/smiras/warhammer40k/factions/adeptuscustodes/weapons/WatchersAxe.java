package ru.smiras.warhammer40k.factions.adeptuscustodes.weapons;

import ru.smiras.warhammer40k.core.model.*;

import java.util.*;

public class WatchersAxe {

    public static WeaponProfile createWatchersAxe() {
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
