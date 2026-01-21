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
