/**
 * Утилитарный класс для бросков кубиков (D3, D6, 2D6 и т.д.) в правилах Warhammer 40,000 10-й редакции.
 *
 * Реализует механику случайных бросков, которые используются везде: попадание, ранение,
 * спасбросок, урон (D3, D6+2), Feel No Pain 5+, стратагемы, способности и т.д.
 *
 * Методы возвращают случайные значения в нужном диапазоне. Используется в AttackContext,
 * DamageValue (DiceDamage), Ability (например FeelNoPain5) и других местах, где нужен рандом.
 */

package ru.smiras.warhammer40k.core.dice;

import java.util.Random;

public class DiceRoller {
    static final Random RANDOM = new Random();

    public static int roll(int diceCount, int diceType) {
        if (diceCount < 1) {
            throw new IllegalArgumentException(
                    "diceCount должен быть больше 0");
        }

        if (diceType != 3 && diceType != 6) {
            throw new IllegalArgumentException(
                    "diceType должен быть 3 или 6");
        }

        int summ = 0;
        for (int i = 0; i < diceCount; i++) {
            int random = RANDOM.nextInt(diceType) + 1;
            summ += random;
        }

        return summ;
    }
}
