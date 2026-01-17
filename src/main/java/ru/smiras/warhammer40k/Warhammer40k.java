package ru.smiras.warhammer40k;

import ru.smiras.warhammer40k.ArmyUnits.*;
import ru.smiras.warhammer40k.ArmyUnits.AdeptusCustodes.*;

public class Warhammer40k {
    public static void main(String[] args) {

        UnitProfile troyanValoris = new TrajannValoris("Trajann-1");
        UnitProfile сustodianGuard = new CustodianGuard("CustodianGuard-1");

        System.out.println(troyanValoris);
        System.out.println(сustodianGuard);

    }
}
