/**
 * Реализация фиксированного урона (например 2, 3, 4) из правил Warhammer 40,000 10-й редакции.
 *
 * Реализует случаи, когда урон оружия или способности не бросается, а всегда одинаковый
 * (например Guardian Spear — Damage 2, Misericordia — Damage 1).
 *
 * Используется в WeaponProfile для оружия с фиксированным уроном.
 * Метод roll() всегда возвращает одно и то же значение.
 */

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
