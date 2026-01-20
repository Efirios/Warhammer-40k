package ru.smiras.warhammer40k.core.model;

public class FixedDamage implements DamageValue {

    private final int value;

    public FixedDamage(int value) {
        this.value = value;
    }

    @Override
    public int roll() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
