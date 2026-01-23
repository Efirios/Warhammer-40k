/**
 * Шаблон (datasheet) отряда Custodian Guard из кодекса Adeptus Custodes 10-й редакции.
 *
 * Реализует правила юнита Custodian Guard: характеристики (M6", T6, Sv2+/4++, W3 на модель),
 * оружие (Guardian Spear и/или Sentinel Blade + Misericordia), ключевые слова (BATTLELINE, INFANTRY),
 * способности (Deep Strike, Martial Ka'tah и др.).
 *
 * Поддерживает несколько вариантов оружия (ranged + melee для каждого типа). Наследует Datasheet.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.units;

import ru.smiras.warhammer40k.core.model.*;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.DeepStrike;
import ru.smiras.warhammer40k.factions.adeptuscustodes.abilities.MortialKatah;
import ru.smiras.warhammer40k.factions.adeptuscustodes.weapons.*;

import java.util.List;
import java.util.Set;

public class CustodianGuard extends Datasheet {

    private static final String NAME = "Кустодианская стража";
    private static final int M = 6;
    private static final int T = 6;
    private static final int SV = 2;
    private static final int W = 3;
    private static final int LD = 6;
    private static final int OC = 2;
    private static final boolean HAS_INVULNERABLE = true;
    private static final int INVULNERABLE_VALUE = 4;
    private static final int POINTS_VALUES = 280;
    private static final int MODEL_COUNT = 5;

    private static final Set<Keyword> KEYWORDS = Set.of(
            Keyword.INFANTRY,
            Keyword.BATTLELINE,
            Keyword.IMPERIUM,
            Keyword.CUSTODIAN_GUARD
    );

    private static final Set<Keyword> FACTION_KEYWORDS = Set.of(
            Keyword.ADEPTUS_CUSTODES
    );

    private static final List<WeaponProfile> WEAPONS = List.of(
            GuardianSpear.createRanged(),
            GuardianSpear.createMelee(),
            SentinelBlade.createRanged(),
            SentinelBlade.createMelee(),
            Misericordia.create()
    );

    private static final List<Ability> ABILITIES = List.of(
            DeepStrike.create()
    );

    private static final List<Ability> FACTION_ABILITIES = List.of(
            MortialKatah.create()
    );

    public CustodianGuard(String id) {
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
                MODEL_COUNT,
                WEAPONS,
                ABILITIES,
                FACTION_ABILITIES
        );
    }
}
