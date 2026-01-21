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

    private static final Random RANDOM = new Random();

    public static int rollD6() {
        return RANDOM.nextInt(6) + 1;
    }

    public static int rollD3() {
        return RANDOM.nextInt(3) + 1;
    }

    public static int roll2D6() {
        return (RANDOM.nextInt(6) + 1) * 2;
    }
}
