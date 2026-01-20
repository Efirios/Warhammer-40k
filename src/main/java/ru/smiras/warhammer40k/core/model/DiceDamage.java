package ru.smiras.warhammer40k.core.model;

import java.util.*;

public class DiceDamage implements DamageValue{

    private final Random random = new Random();

    private final int diceCount;  // 1 = D6, 2 = 2D6
    private final int diceType;   // 3 = D3, 6 = D6
    private final int bonus;      // +2, +3 и т.д.

    public DiceDamage(int diceCount, int diceType, int bonus) {
        this.diceCount = diceCount;
        this.diceType = diceType;
        this.bonus = bonus;
    }

    @Override
    public int roll() {

        int total = bonus;

        for (int i = 0; i < diceCount; i++) {
            total += random.nextInt(diceType) + 1;
        }

        return total;
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
