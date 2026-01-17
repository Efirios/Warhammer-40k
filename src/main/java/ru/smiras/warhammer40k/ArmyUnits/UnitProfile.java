package ru.smiras.warhammer40k.ArmyUnits;

import java.util.Objects;

public class UnitProfile {

    private final String id;
    private final String baseName;
    private final int baseMovement;
    private final int baseToughness;
    private final int baseSave;
    private final int baseWounds;
    private final int baseLeadership;
    private final int baseObjectiveControl;
    private final int invulnerableSave;

    private int currentWounds;
    private int currentObjectiveControl;

    protected UnitProfile(String id, String baseName, int baseMovement, int baseToughness, int baseSave
            ,  int baseWounds, int baseLeadership, int baseObjectiveControl, int invulnerableSave) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Количество id не может быть пустым!");
        }

        this.id = id;

        if (baseName == null || baseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Количество baseName не может быть пустым!");
        }

        this.baseName = baseName;

        if (baseMovement < 1) {
            throw new IllegalArgumentException("Количество baseMovement не может быть меньше 1!");
        }

        this.baseMovement = baseMovement;

        if (baseToughness < 1) {
            throw new IllegalArgumentException("Количество baseToughness не может быть меньше 1!");
        }

        this.baseToughness = baseToughness;

        if (baseSave < 2 || baseSave > 6) {
            throw new IllegalArgumentException("Количество baseSave не может быть меньше 2 и больше 6!");
        }

        this.baseSave = baseSave;

        if (baseWounds < 1) {
            throw new IllegalArgumentException("Количество baseWounds не может быть меньше 1!");
        }

        this.baseWounds = baseWounds;
        this.currentWounds = baseWounds;

        if (baseLeadership < 4 || baseLeadership > 9) {
            throw new IllegalArgumentException("Количество baseLeadership не может быть меньше 4 и больше 9!");
        }

        this.baseLeadership = baseLeadership;

        if (baseObjectiveControl < 0) {
            throw new IllegalArgumentException("Количество Objective Control не может быть меньше 0!");
        }

        this.baseObjectiveControl = baseObjectiveControl;
        this.currentObjectiveControl = baseObjectiveControl;

        if (invulnerableSave < 2 && invulnerableSave != 7) {
            throw new IllegalArgumentException("invulnerableSave должен быть 2–6 или 7 (нет инвуля)!");
        }

        this.invulnerableSave = invulnerableSave;
    }

    protected String getId() {
        return id;
    }

    protected String getBaseName() {
        return baseName;
    }

    protected int getBaseMovement() {
        return baseMovement;
    }

    protected int getBaseToughness() {
        return baseToughness;
    }

    protected int getBaseSave() {
        return baseSave;
    }

    protected int getBaseWounds() {
        return baseWounds;
    }

    protected int getBaseLeadership() {
        return baseLeadership;
    }

    protected int getBaseObjectiveControl() {
        return baseObjectiveControl;
    }

    protected int getInvulnerableSave() {
        return invulnerableSave;
    }

    protected int getCurrentWounds() {
        return currentWounds;
    }

    protected int getCurrentObjectiveControl() {
        return currentObjectiveControl;
    }

    protected void setCurrentObjectiveControl(int currentObjectiveControl) {
        this.currentObjectiveControl = currentObjectiveControl;
    }


    @Override
    public String toString() {
        return "Unit{" +
                "ID='" + id + '\'' +
                ", baseName='" + baseName + '\'' +
                ", baseMovement=" + baseMovement +
                ", baseToughness=" + baseToughness +
                ", baseSave=" + baseSave +
                ", baseWounds=" + baseWounds +
                ", baseLeadership=" + baseLeadership +
                ", baseObjectiveControl=" + baseObjectiveControl +
                ", invulnerableSave=" + invulnerableSave +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnitProfile unitProfile = (UnitProfile) o;
        return getBaseMovement() == unitProfile.getBaseMovement() && getBaseToughness() == unitProfile.getBaseToughness()
                && getBaseSave() == unitProfile.getBaseSave() && getBaseWounds() == unitProfile.getBaseWounds()
                && getBaseLeadership() == unitProfile.getBaseLeadership()
                && getBaseObjectiveControl() == unitProfile.getBaseObjectiveControl()
                && getInvulnerableSave() == unitProfile.getInvulnerableSave()
                && Objects.equals(getBaseName(), unitProfile.getBaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaseName(), getBaseMovement(), getBaseToughness(), getBaseSave(), getBaseWounds()
                , getBaseLeadership(), getBaseObjectiveControl(), getInvulnerableSave());
    }

    public void applyDamage(int dmg) {
        if (dmg < 0) {
            throw new IllegalArgumentException("Урон не может быть отрицательным!");
        }
        currentWounds = Math.max(0, currentWounds - dmg);
    }

    public boolean isDestroyed() {
        return currentWounds <= 0;
    }
}
