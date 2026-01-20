package ru.smiras.warhammer40k;

import ru.smiras.warhammer40k.core.model.Datasheet;
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.CustodianGuard;
import ru.smiras.warhammer40k.factions.adeptuscustodes.units.TrajannValoris;
import ru.smiras.warhammer40k.factions.worldeaters.units.Angron;

public class MainWarhammer40k {
    public static void main(String[] args) {

        Datasheet troyanValoris = new TrajannValoris("Trajann-1");
        Datasheet сustodianGuard_1 = new CustodianGuard("CustodianGuard-1");
        Datasheet angron = new Angron("Angron-1");

        System.out.println(troyanValoris);
        System.out.println(сustodianGuard_1);
        System.out.println(angron);
    }
}
