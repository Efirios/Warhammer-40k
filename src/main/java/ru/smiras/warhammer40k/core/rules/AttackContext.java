package ru.smiras.warhammer40k.core.rules;

import ru.smiras.warhammer40k.core.model.WeaponProfile;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public class AttackContext {

    private final UnitInstance attacker; // — атакующий юнит
    private final UnitInstance target; // — цель
    private final WeaponProfile weapon; // — оружие, которым атакуют
    private final PhaseType phase; // — в какой фазе происходит атака
    private int hitRoll; // — результат броска на попадание (или исходный бросок)
    private int hitModifier; // — текущий модификатор к попаданию (+1, -1 и т.д.)
    private boolean isCriticalHit; // — был ли critical hit (6+)
    private int woundRoll;
    private int woundModifier;
    private boolean isCriticalWound;
    private int saveRoll;
    private int saveModifier;
    private boolean ignoreSave; // — игнорировать спасбросок (Devastating Wounds)
    private int damageRoll;
    private int damageModifier;

    protected AttackContext (UnitInstance attacker, UnitInstance target, WeaponProfile weapon, PhaseType phase,
                             int hitRoll, int hitModifier, boolean isCriticalHit, int woundRoll, int woundModifier,
                             boolean isCriticalWound, int saveRoll, int saveModifier, boolean ignoreSave,int damageRoll,
                             int damageModifier) {
        this.attacker = attacker;
        this.target = target;
        this.weapon = weapon;
        this.phase = phase;
        this.hitRoll = hitRoll;
        this.hitModifier = hitModifier;
        this.isCriticalHit = isCriticalHit;
        this.woundRoll = woundRoll;
        this.woundModifier = woundModifier;
        this.isCriticalWound = isCriticalWound;
        this.saveRoll = saveRoll;
        this.saveModifier = saveModifier;
        this.ignoreSave = ignoreSave;
        this.damageRoll = damageRoll;
        this.damageModifier = damageModifier;
    }

    public void addHitModifier( int currentEffect) {

    }

    public void addWoundModifier( int currentEffect) {

    }

    public void addDamageModifier( int currentEffect) {

    }
}
