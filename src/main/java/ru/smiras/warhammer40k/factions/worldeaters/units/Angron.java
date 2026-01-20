package ru.smiras.warhammer40k.factions.worldeaters.units;

import ru.smiras.warhammer40k.core.model.*;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.DeepStrike;
import ru.smiras.warhammer40k.factions.worldeaters.weapons.*;

import java.util.List;
import java.util.Set;

public class Angron extends Datasheet{

    private static final String NAME = "Ангрон";
    private static final int M = 14;
    private static final int T = 11;
    private static final int SV = 2;
    private static final int W = 16;
    private static final int LD = 5;
    private static final int OC = 6;
    private static final boolean HAS_INVULNERABLE = true;
    private static final int INVULNERABLE_VALUE = 4;
    private static final int POINTS_VALUES = 410;

    private static final Set<Keyword> KEYWORDS = Set.of(
            Keyword.MONSTER,
            Keyword.CHARACTER,
            Keyword.FLY,
            Keyword.EPIC_HERO,
            Keyword.CHAOS,
            Keyword.KHORNE,
            Keyword.DAEMON,
            Keyword.PRIMARCH,
            Keyword.ANGRON
    );

    private static final Set<Keyword> FACTION_KEYWORDS = Set.of(
            Keyword.WORLD_EATERS
    );

    private static final List<WeaponProfile> WEAPONS = List.of(
            SamniariusAndSpinegrinder.createStrike(),
            SamniariusAndSpinegrinder.createSweep()
    );

    private static final List<Ability> ABILITIES = List.of(
            DeepStrike.create()
    );

    private static final List<Ability> FACTION_ABILITIES = List.of(
    );

    public Angron(String id) {
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
                WEAPONS,
                ABILITIES,
                FACTION_ABILITIES
        );
    }
}
