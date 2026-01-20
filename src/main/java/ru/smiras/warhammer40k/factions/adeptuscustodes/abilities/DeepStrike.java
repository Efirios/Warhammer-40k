package ru.smiras.warhammer40k.factions.adeptuscustodes.abilities;

import ru.smiras.warhammer40k.core.model.Ability;

import java.util.Objects;

public class DeepStrike implements Ability {

    private static final DeepStrike INSTANCE = new DeepStrike();

    private DeepStrike(){

    }

    public static DeepStrike create() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Deep Strike";
    }

    @Override
    public String getDescription() {
        return "Этот юнит может быть размещён в резерве и выйти с помощью Deep Strike в фазу Reinforcement.";
    }

    @Override
    public String toString() {
        return getName() + " - " + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeepStrike deepStrike = (DeepStrike) o;
        return getName().equals(deepStrike.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName());
    }
}
