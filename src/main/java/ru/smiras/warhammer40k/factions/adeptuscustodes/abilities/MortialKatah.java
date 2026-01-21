/**
 * Фракционная способность Adeptus Custodes — Martial Ka'tah из кодекса Adeptus Custodes
 * (10-я редакция Warhammer 40,000).
 *
 * Реализует правила Martial Ka'tah: в начале Command phase игрок выбирает один эффект
 * (Dacatarai — +1 to hit, Kaptaris — +1 to wound, Calistus — +1 to damage) для юнита
 * в ближнем бою до следующей Command phase.
 *
 * Выбор происходит в методе onPhaseStart (консольный ввод для прототипа).
 * Эффект применяется в modifyHitRoll / modifyWoundRoll / modifyDamageRoll.
 * Сбрасывается в onPhaseEnd или перед новым выбором.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.abilities;

import ru.smiras.warhammer40k.core.model.Ability;
import ru.smiras.warhammer40k.core.rules.AttackContext;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

import java.util.Objects;
import java.util.Scanner;

public class MortialKatah implements Ability {

    private static final Scanner SCANNER = new Scanner(System.in);

    private KatahEffect activeKatahEffect = KatahEffect.NONE;

    private static final MortialKatah INSTANCE = new MortialKatah();

    private MortialKatah(){

    }

    public static MortialKatah create() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Mortial Ka`tah";
    }

    @Override
    public String getDescription() {
        return "В начале Command phase выбирайте эффект: +1 to hit, +1 to wound или +1 to damage в ближнем бою до" +
                " следующей Command phase.";
    }

    @Override
    public void onPhaseStart(UnitInstance unit, PhaseType phase){

        if (!unit.isAlive() || phase != PhaseType.COMMAND_PHASE) {
            return;
        }

        while (true) {

            System.out.println("Выберите эффект Mortial Ka'tah:" +
                    "\n1 - +1 к попаданию (Dacatarai)" +
                    "\n2 - +1 к ранению (Kaptaris)" +
                    "\n3 - +1 к урону (Calistus)");

            String input = SCANNER.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= 3) {
                    if (choice == 1) {
                        activeKatahEffect = KatahEffect.DACATARAI;
                        System.out.println("Выбран эффект: Dacatarai (+1 к Hit)");
                    } else if (choice == 2) {
                        activeKatahEffect = KatahEffect.KAPTARIS;
                        System.out.println("Выбран эффект: Kaptaris (+1 к Wound)");
                    } else if (choice == 3) {
                        activeKatahEffect = KatahEffect.CALISTUS;
                        System.out.println("Выбран эффект: Calistus (+1 к Damage)");
                    }
                    break;
                } else {
                    System.out.println("Неверный выбор! Введите 1, 2 или 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод! Введите число 1, 2 или 3.");
            }
        }


    }

    @Override
    public void onPhaseEnd(UnitInstance unit, PhaseType phase) {
        if (phase == PhaseType.COMMAND_PHASE) {
            activeKatahEffect = KatahEffect.NONE;
        }
    }

    @Override
    public void modifyHitRoll(UnitInstance unit, AttackContext context) {
        if (activeKatahEffect == KatahEffect.DACATARAI) {
            context.addHitModifier(1);
        }
    }

    @Override
    public void modifyWoundRoll(UnitInstance unit, AttackContext context) {
        if (activeKatahEffect == KatahEffect.KAPTARIS) {
            context.addWoundModifier(1);
        }
    }

    @Override
    public void modifyDamageRoll(UnitInstance unit, AttackContext context) {
        if (activeKatahEffect == KatahEffect.CALISTUS) {
            context.addDamageModifier(1);
        }
    }

    public boolean isActive(UnitInstance unit, PhaseType currentPhase) {
        return unit.isAlive() && (currentPhase == PhaseType.COMMAND_PHASE || activeKatahEffect != KatahEffect.NONE);
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MortialKatah mortialKatah = (MortialKatah) o;
        return getName().equals(mortialKatah.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName());
    }
}

