/**
 * Способность Deep Strike (Глубокий удар / Стратегический резерв) из основных правил
 * Warhammer 40,000 10-й редакции (Core Rules).
 *
 * Реализует возможность разместить юнит в Strategic Reserves вместо развёртывания на столе,
 * а затем вывести его в Reinforcement step любой из ваших Movement phases (в пределах 9"
 * от кромки поля боя и более 9" от вражеских моделей). Юнит не может выйти в первом раунде,
 * а если не вышел до конца 3-го раунда — считается уничтоженным.
 *
 * Активируется в фазе Reinforcement (Movement phase). Обычно пассивная — проверяется в isActive
 * и может блокировать развёртывание в начале игры.
 */

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
