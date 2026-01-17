package ru.smiras.warhammer40k.ArmyUnits.AdeptusCustodes;

import ru.smiras.warhammer40k.ArmyUnits.UnitProfile;

public class CustodianGuard extends UnitProfile {

    private static final String NAME = "Кустодианская стража";
    private static final int M = 6;
    private static final int T = 6;
    private static final int SV = 2;
    private static final int W = 3;
    private static final int LD = 6;
    private static final int OC = 2;
    private static final int INVULN = 4;

    public CustodianGuard (String id) {
        super(id, NAME, M, T, SV, W, LD, OC, INVULN);
    }
}
