/**
 * Шаблон (datasheet) персонажа Trajann Valoris — Captain-General Adeptus Custodes из кодекса
 * Adeptus Custodes 10-й редакции.
 *
 * Реализует правила юнита Trajann Valoris: характеристики (M6", T6, Sv2+/4++, W7, Ld5, OC2),
 * оружие (Eagle's Scream и Watcher's Axe), ключевые слова (EPIC_HERO, CHARACTER, INFANTRY),
 * способности (Deep Strike, Feel No Pain 5+, Martial Ka'tah и др.).
 *
 * Наследует Datasheet и передаёт свои характеристики, оружие и способности в супер-конструктор.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.units;

import ru.smiras.warhammer40k.core.model.*;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.DeepStrike;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.FeelNoPain5;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.MortialKatah;
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
            EaglesScream.create(),
            WatchersAxe.create()
    );

    private static final List<Ability> ABILITIES = List.of(
            DeepStrike.create(),
            FeelNoPain5.create()
    );

    private static final List<Ability> FACTION_ABILITIES = List.of(
            MortialKatah.create()
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
                WEAPONS,
                ABILITIES,
                FACTION_ABILITIES
        );
    }
}
