/**
 * Движок боевой системы (Combat Engine) для правил Warhammer 40,000 10-й редакции.
 *
 * Отвечает за проведение полного цикла атаки: от броска на попадание (Hit Roll)
 * до нанесения урона. Объединяет правила (Core Rules), данные юнитов (UnitInstance)
 * и модификаторы способностей (Ability) в единый процесс.
 *
 * Реализует последовательность атаки (Attack Sequence):
 * 1. Hit Roll (Попадание) — сравнение броска с BS/WS, учет модификаторов и Critical Hits.
 * 2. Wound Roll (Ранение) — сравнение Силы (S) и Стойкости (T), определение Target Number.
 * 3. Save Roll (Спасбросок) — выбор лучшего спасброска (Sv/Invul), учет AP.
 * 4. Damage Allocation (Урон) — применение урона, Feel No Pain и удаление моделей.
 *
 * Класс является "оркестратором": он создает AttackContext и прогоняет его через
 * все этапы, вызывая соответствующие методы проверки и модификации.
 */

package ru.smiras.warhammer40k.core.rules;

import ru.smiras.warhammer40k.core.dice.DiceRoller;
import ru.smiras.warhammer40k.core.model.Ability;
import ru.smiras.warhammer40k.core.model.WeaponProfile;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public class CombatEngine {

    public void resolveAttack(UnitInstance attacker, UnitInstance target, WeaponProfile weapon, PhaseType phase) {


        for (int i = 0; i < weapon.getAttacks(); i++) {
            AttackContext context = new AttackContext(attacker, target, weapon, phase);

            boolean isHit = resolveHitRoll(context);

            if (isHit) {
                System.out.println("Попадание! (Кубик: " + context.getHitRoll() +
                        " -> Итого: " + context.getFinalHitRoll() + ")");
            } else {
                System.out.println("Промах. (Кубик: " + context.getHitRoll() +
                        " -> Итого: " + context.getFinalHitRoll() + ")");
            }
        }
    }

    private boolean resolveHitRoll(AttackContext context) {
        int diceRoll = DiceRoller.rollD6();

        context.setHitRoll(diceRoll);

        if (diceRoll == 6) {
            context.setCriticalHit(true);
        }

        for (Ability ability : context.getAttacker().getActiveAbilities()) {
            ability.modifyHitRoll(context.getAttacker(), context);
        }

        if (diceRoll == 1) {
            return false;
        }

        if (diceRoll == 6) {
            return true;
        } else if (context.getFinalHitRoll() >= context.getWeapon().getBsWs()) {
            return true;
        }

        return false;
    }

    private boolean resolveWoundRoll(AttackContext context) {

    }
}
