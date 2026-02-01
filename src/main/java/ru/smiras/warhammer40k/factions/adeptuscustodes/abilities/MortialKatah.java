package ru.smiras.warhammer40k.factions.adeptuscustodes.abilities;

import ru.smiras.warhammer40k.core.model.Ability;
import ru.smiras.warhammer40k.core.model.WeaponType;
import ru.smiras.warhammer40k.core.rules.AttackContext;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

import java.util.Objects;
import java.util.Scanner;

public class MortialKatah implements Ability {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final MortialKatah INSTANCE = new MortialKatah();

    private MortialKatah() {}

    public static MortialKatah create() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Martial Ka'tah";
    }

    @Override
    public String getDescription() {
        return "Каждый раз, когда юнит выбирается для боя, выберите одну из стоек: " +
                "Dacatarai (Sustained Hits 1 в ближнем бою) или Rendax (Lethal Hits в ближнем бою). " +
                "Эффект действует, пока юнит выполняет свои атаки.";
    }

    public void selectStanceForFight(UnitInstance unit) {
        if (!unit.isAlive()) {
            return;
        }

        while (true) {
            System.out.println("Выберите стойку Martial Ka'tah для юнита " +
                    unit.getDatasheet().getBaseName() + ":" +
                    "\n1 - Dacatarai (Sustained Hits 1 (доп. попадания при крите), ближний бой)" +
                    "\n2 - Rendax (Lethal Hits (авто‑ранение при крите), ближний бой)");

            String input = SCANNER.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);

                if (choice == 1) {
                    unit.setCurrentMortialKatahEffect(KatahEffect.DACATARAI);
                    System.out.println("Выбрана стойка: Dacatarai (Sustained Hits 1 (доп. попадания при крите)).");
                    break;
                } else if (choice == 2) {
                    unit.setCurrentMortialKatahEffect(KatahEffect.RENDAX);
                    System.out.println("Выбрана стойка: Rendax (Lethal Hits (авто‑ранение при крите)).");
                    break;
                } else {
                    System.out.println("Неверный выбор! Введите 1 или 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Введите число 1 или 2.");
            }
        }
    }

    @Override
    public void onFightSelected(UnitInstance unit, AttackContext context) {
        if (!unit.isAlive()) return;
        if (context.getPhase() != PhaseType.FIGHT_PHASE) return;
        if (context.getWeapon().getType() != WeaponType.MELEE) return;

        // Каждый раз, когда юнит выбирается для боя, выбираем стойку
        selectStanceForFight(unit);
    }

    @Override
    public void onFightFinished(UnitInstance unit, AttackContext context) {
        unit.setCurrentMortialKatahEffect(KatahEffect.NONE);
    }

    @Override
    public void modifyHitRoll(UnitInstance unit, AttackContext context) {
        if (unit.getCurrentMortialKatahEffect() == KatahEffect.DACATARAI &&
                context.getWeapon().getType() == WeaponType.MELEE &&
                context.isCriticalHit()) {
            context.addExtraHits(1);
        }

        if (unit.getCurrentMortialKatahEffect() == KatahEffect.RENDAX &&
                context.getWeapon().getType() == WeaponType.MELEE &&
                context.isCriticalHit()) {
            context.setAutoWound(true);
        }
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MortialKatah that = (MortialKatah) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}