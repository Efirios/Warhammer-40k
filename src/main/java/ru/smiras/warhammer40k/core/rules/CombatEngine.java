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
 * Класс обрабатывает дополнительные попадания (Sustained Hits / Dacatarai);
 * Поддерживает авто‑ранение (Lethal Hits / Rendax) через autoWound;
 * Перед началом и после окончания атак юнита вызывает onFightSelected / onFightFinished у способностей
 * (для таких вещей как Martial Ka’tah).
 *
 * Класс является "оркестратором": он создает AttackContext и прогоняет его через
 * все этапы, вызывая соответствующие методы проверки и модификации.
 */

package ru.smiras.warhammer40k.core.rules;

import ru.smiras.warhammer40k.core.dice.DiceRoller;
import ru.smiras.warhammer40k.core.model.Ability;
import ru.smiras.warhammer40k.core.model.Keyword;
import ru.smiras.warhammer40k.core.model.WeaponProfile;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public class CombatEngine {

    public void resolveAttack(UnitInstance attacker, UnitInstance target, WeaponProfile weapon, PhaseType phase) {
        System.out.println("\n--- Атака: " + weapon.getName() + " ---");

        int totalAttacks = weapon.getAttacks() * attacker.getRemainingModels();

        AttackContext fightContext = new AttackContext(attacker, target, weapon, phase);

        // Сообщаем способностям атакующего, что он выбран для боя
        for (Ability ability : attacker.getActiveAbilities()) {
            ability.onFightSelected(attacker, fightContext);
        }

        for (int i = 0; i < totalAttacks; i++) {
            if (!target.isAlive()) {
                System.out.println("   [Цель уничтожена. Остальные атаки отменены]");
                break;
            }

            AttackContext context = new AttackContext(attacker, target, weapon, phase);

            boolean isHit = resolveHitRoll(context);

            if (isHit) {
                System.out.print("Попадание (Кубик: " + context.getHitRoll() +
                        " -> Итого: " + context.getFinalHitRoll() + ") ");

                processHit(context);

                if (context.getExtraHits() > 0) {
                    for (int j = 0; j < context.getExtraHits(); j++) {
                        if (!target.isAlive()) {
                            System.out.println("   [Цель уничтожена. Остальные атаки отменены]");
                            break;
                        }

                        System.out.print("Дополнительное попадание (Sustained Hits)");

                        AttackContext newContext = new AttackContext(attacker, target, weapon, phase);

                        processHit(newContext);
                    }
                }

            } else {
                System.out.println("Промах (Кубик: " + context.getHitRoll() + ")");
            }
        }

        // Сообщаем способностям, что юнит закончил свои атаки
        for (Ability ability : attacker.getActiveAbilities()) {
            ability.onFightFinished(attacker, fightContext);
        }
    }

    private boolean resolveHitRoll(AttackContext context) {
        int diceRoll = DiceRoller.roll(1, 6);

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
        if (context.getAutoWound()) {
            return true;
        } else {
            int strength = context.getWeapon().getStrength();
            int toughness = context.getTarget().getDatasheet().getBaseToughness();
            int diceRoll = DiceRoller.roll(1, 6);
            int targetNumber = 0;

            if (strength >= toughness * 2){
                targetNumber = 2;
            } else if (strength > toughness) {
                targetNumber = 3;
            } else if (strength == toughness) {
                targetNumber = 4;
            } else if (strength * 2 <= toughness) {
                targetNumber = 6;
            } else if (strength < toughness) {
                targetNumber = 5;
            }

            context.setWoundRoll(diceRoll);

            if (diceRoll == 6) {
                context.setCriticalWound(true);
            }

            for (Ability ability : context.getAttacker().getActiveAbilities()) {
                ability.modifyWoundRoll(context.getAttacker(), context);
            }

            if (diceRoll == 1) {
                return false;
            }

            if (diceRoll == 6) {
                return true;
            } else if (context.getFinalWoundRoll() >= targetNumber){
                return true;
            }
        }

        return false;
    }

    private boolean resolveSaveRoll(AttackContext context) {
        if (context.getIgnoreSave()) {
            return false;
        }

        int save = context.getTarget().getDatasheet().getBaseSave() - context.getWeapon().getAp();
        int bestSave = Math.min(save, context.getTarget().getDatasheet().getInvulnerableSaveValue());
        int diceRoll = DiceRoller.roll(1, 6);

        context.setSaveRoll(diceRoll);

        for (Ability ability : context.getTarget().getActiveAbilities()) {
            ability.modifySaveRoll(context.getTarget(), context);
        }

        if (diceRoll == 1) {
            return false;
        } else if (context.getFinalSaveRoll() >= bestSave) {
            return true;
        }

        return false;
    }

    private int resolveDamage(AttackContext context) {
        context.setDamageRoll(context.getWeapon().getDamageValue().roll());

        for (Ability ability : context.getAttacker().getActiveAbilities()){
            ability.modifyDamageRoll(context.getAttacker(), context);
        }

        for (Ability ability : context.getTarget().getActiveAbilities()){
            ability.modifyDamageRoll(context.getTarget(), context);
        }

        for (Ability ability : context.getTarget().getActiveAbilities()){
            ability.applyAfterSaveRoll(context.getTarget(), context);
        }

        int damage = context.getFinalDamageRoll();

        int lostModels = context.getTarget().receiveDamage(damage);

        return lostModels;
    }

    private void processHit(AttackContext context) {
        boolean isWounded = resolveWoundRoll(context);

        if (isWounded) {

            if (context.getAutoWound()) {
                System.out.print("Авто‑ранение (Lethal Hits)");
            } else {
                System.out.print("-> Ранение (Бросок: " + context.getWoundRoll() +
                        " -> Итого: " + context.getFinalWoundRoll() + ") ");
            }

            boolean isDevastating = context.isCriticalWound() &&
                    context.getWeapon().getWeaponKeywords().contains(Keyword.DEVASTATING_WOUNDS);

            int lostModels = 0;
            boolean damageWasDealt = false;

            if (isDevastating) {
                System.out.println("-> DEVASTATING WOUNDS! (Спасброски игнорируются)");
                context.setIgnoreSave(true);
            }

            boolean isSaved = resolveSaveRoll(context);

            if (isSaved) {
                System.out.println("-> ОТБИТО (Бросок: " + context.getSaveRoll() +
                        " -> Итого: " + context.getFinalSaveRoll() + ")");
            } else {
                System.out.println("-> Провал сейва (Бросок: " + context.getSaveRoll() +
                        " -> Итого: " + context.getFinalSaveRoll() + ")");
                lostModels = resolveDamage(context);
                damageWasDealt = true;
            }

            if (damageWasDealt) {
                if (lostModels > 0) {
                    System.out.println("      !!! МОДЕЛЬ УНИЧТОЖЕНА !!!");
                }

                System.out.println("      [Цель: " + context.getTarget().getRemainingModels() +
                        " моделей, " + context.getTarget().getRemainingWounds() + " Wounds]");
            }

        } else {
            System.out.println("-> Не пробил (Бросок: " + context.getWoundRoll() + ")");
        }
    }
}
