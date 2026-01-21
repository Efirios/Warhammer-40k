/**
 * Способность Feel No Pain 5+ из правил Warhammer 40,000 10-й редакции.
 * Часто встречается у Adeptus Custodes, некоторых фракций Chaos, Tyranids и Necrons.
 *
 * Реализует правило: каждый раз, когда рана была бы нанесена модели (после всех предыдущих
 * этапов), бросьте D6: на 5+ рана не наносится. Работает против mortal wounds и ран,
 * не прошедших обычный спасбросок (armour/invul).
 *
 * Срабатывает в applyAfterSaveRoll: бросок D6 → если 5+ → context.cancelDamage().
 * Игнорирует модификаторы к спасброску.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.abilities;

import ru.smiras.warhammer40k.core.dice.DiceRoller;
import ru.smiras.warhammer40k.core.model.Ability;
import ru.smiras.warhammer40k.core.rules.AttackContext;
import ru.smiras.warhammer40k.game.state.UnitInstance;

import java.util.Objects;

public class FeelNoPain5 implements Ability {

    private static final FeelNoPain5 INSTANCE = new FeelNoPain5();

    private FeelNoPain5(){

    }

    public static FeelNoPain5 create() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Feel No Pain 5+";
    }

    @Override
    public String getDescription() {
        return "Каждый раз, когда рана была бы нанесена модели, бросьте D6: на 5+ рана не наносится" +
                " (игнорирует модификаторы к спасброску).";
    }

    @Override
    public void applyAfterSaveRoll(UnitInstance unit, AttackContext context) {
        int roll = DiceRoller.rollD6();
        if (roll >= 5) {
            context.cancelDamage();
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
        FeelNoPain5 feelNoPain5 = (FeelNoPain5) o;
        return getName().equals(feelNoPain5.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName());
    }
}

