package ru.smiras.warhammer40k.factions.adeptuscustodes.units;

import ru.smiras.warhammer40k.core.model.*;
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
    private static final int POINTS_VALUES = 56;

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
            GuardianSpear.createGuardianSpearRanged(),
            GuardianSpear.createGuardianSpearMelee(),
            SentinelBlade.createSentinelBladeRanged(),
            SentinelBlade.createSentinelBladeMelee(),
            Misericordia.createMisericordia()
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
                WEAPONS
        );
    }
}
