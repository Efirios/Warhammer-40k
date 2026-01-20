package ru.smiras.warhammer40k.factions.adeptuscustodes.units;

import ru.smiras.warhammer40k.core.model.*;
import ru.smiras.warhammer40k.factions.adeptuscustodes.weapons.EaglesScream;
import ru.smiras.warhammer40k.factions.adeptuscustodes.weapons.WatchersAxe;

import java.util.*;

public class TrajannValoris extends Datasheet {

    private static final String NAME = "Траян Валорис";
    private static final int M = 6;
    private static final int T = 6;
    private static final int SV = 2;
    private static final int W = 7;
    private static final int LD = 5;
    private static final int OC = 2;
    private static final boolean HAS_INVULNERABLE = true;
    private static final int INVULNERABLE_VALUE = 4;
    private static final int POINTS_VALUES = 180;

    private static final Set<Keyword> KEYWORDS = Set.of(
            Keyword.INFANTRY,
            Keyword.CHARACTER,
            Keyword.EPIC_HERO,
            Keyword.IMPERIUM,
            Keyword.TRAJANN_VALORIS
    );

    private static final Set<Keyword> FACTION_KEYWORDS = Set.of(
            Keyword.ADEPTUS_CUSTODES
    );

    private static final List<WeaponProfile> WEAPONS = List.of(
            EaglesScream.createEaglesScream(),
            WatchersAxe.createWatchersAxe()
    );

    public TrajannValoris(String id) {
        super(
                id,
                NAME,
                M,
                T,
                SV,
                W,
                LD,
                OC,
                HAS_INVULNERABLE,
                INVULNERABLE_VALUE,
                KEYWORDS,
                FACTION_KEYWORDS,
                POINTS_VALUES,
                WEAPONS
        );
    }
}
