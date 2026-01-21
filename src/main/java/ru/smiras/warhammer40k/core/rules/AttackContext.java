package ru.smiras.warhammer40k.core.rules;

import ru.smiras.warhammer40k.core.model.WeaponProfile;
import ru.smiras.warhammer40k.core.util.PhaseType;
import ru.smiras.warhammer40k.game.state.UnitInstance;

public class AttackContext {

    private final UnitInstance attacker; // — атакующий юнит
    private final UnitInstance target; // — цель
    private final WeaponProfile weapon; // — оружие, которым атакуют
    private final PhaseType phase; // — в какой фазе происходит атака
    private int hitRoll = 0; // — результат броска на попадание (или исходный бросок)
    private int hitModifier = 0; // — текущий модификатор к попаданию (+1, -1 и т.д.)
    private boolean isCriticalHit = false; // — был ли critical hit (6+)
    private int woundRoll = 0;
    private int woundModifier = 0;
    private boolean isCriticalWound = false;
    private int saveRoll = 0;
    private int saveModifier = 0;
    private boolean ignoreSave = false; // — игнорировать спасбросок (Devastating Wounds)
    private int damageRoll = 0;
    private int damageModifier = 0;

    public AttackContext(UnitInstance attacker, UnitInstance target, WeaponProfile weapon, PhaseType phase) {
        this.attacker = attacker;
        this.target = target;
        this.weapon = weapon;
        this.phase = phase;
    }

    public UnitInstance getAttacker() {
        return attacker;
    }

    public UnitInstance getTarget() {
        return target;
    }

    public WeaponProfile getWeapon() {
        return weapon;
    }

    public PhaseType getPhase() {
        return phase;
    }

    public int getFinalHitRoll() {
        return hitRoll + hitModifier;
    }

    public int getFinalWoundRoll() {
        return woundRoll + woundModifier;
    }

    public int getFinalSaveRoll() {
        return saveRoll + saveModifier;
    }

    public int getFinalDamageRoll() {
        return damageRoll + damageModifier;
    }

    public void setHitRoll(int value) {
        hitRoll = value;
    }

    public void addHitModifier(int value) {
        hitModifier += value;
    }

    public void setCriticalHit(boolean value) {
        isCriticalHit = value;
    }

    public void setWoundRoll(int value) {
        woundRoll = value;
    }

    public void addWoundModifier(int value) {
        woundModifier += value;
    }

    public void setCriticalWound(boolean value) {
        isCriticalWound = value;
    }

    public void setSaveRoll(int value) {
        saveRoll = value;
    }

    public void addSaveModifier(int value) {
        saveModifier += value;
    }

    public void setIgnoreSave(boolean value) {
        ignoreSave = value;
    }

    public void setDamageRoll(int value) {
        damageRoll = value;
    }

    public void addDamageModifier(int value) {
        damageModifier += value;
    }

    public void setDamageModifier(int value) {
        damageModifier = value;
    }

    public void setDamage(int finalDamage) {
        damageRoll = finalDamage;
        damageModifier = 0;
    }

    public void cancelDamage() {
        setDamage(0);
    }

    public void resetModifiers() {
        hitModifier = 0;
        woundModifier = 0;
        saveModifier = 0;
        damageModifier = 0;
        isCriticalHit = false;
        isCriticalWound = false;
        ignoreSave = false;
    }
}
