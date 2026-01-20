package ru.smiras.warhammer40k.factions.adeptuscustodes.abilities;

import ru.smiras.warhammer40k.core.model.Ability;

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
        return "Каждый раз, когда рана была бы нанесена модели с этой способностью, бросьте один D6: " +
                "на 5+ рана не наносится.";
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

