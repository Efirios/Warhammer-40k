/**
 * Реализация переменного урона через кубики (D3, D6+2, 2D6 и т.д.) из правил
 * Warhammer 40,000 10-й редакции.
 *
 * Реализует правила урона оружия и способностей, где урон не фиксированный, а бросается
 * кубиками (D3, D6, 2D6, D6+3 и т.д.). Содержит количество кубиков, тип кубика и бонус.
 *
 * Используется в WeaponProfile для урона оружия (например D3+2 для Eagle's Scream).
 * Метод roll() возвращает случайное значение урона при каждой атаке.
 */

package ru.smiras.warhammer40k.core.model;

import ru.smiras.warhammer40k.core.dice.DiceRoller;

public class DiceDamage implements DamageValue{
    private final int diceCount;  // 1 = D6, 2 = 2D6
    private final int diceType;   // 3 = D3, 6 = D6
    private final int bonus;      // +2, +3 и т.д.

    public DiceDamage(int diceCount, int diceType, int bonus) {
        if (diceCount >= 1) {
            this.diceCount = diceCount;
        } else {
            throw new IllegalArgumentException(
                    "diceCount должен быть больше 0");
        }
        this.diceType = diceType;
        this.bonus = bonus;
    }

    @Override
    public int roll() {
        return DiceRoller.roll(diceCount, diceType) + bonus;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (diceCount > 1) {
            sb.append(diceCount);
        }

        sb.append("D").append(diceType);

        if (bonus > 0) {
            sb.append("+").append(bonus);
        } else if (bonus < 0) {
            sb.append(bonus);
        }

        return sb.toString();
    }
}
