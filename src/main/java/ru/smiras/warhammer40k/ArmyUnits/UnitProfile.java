package ru.smiras.warhammer40k.ArmyUnits;

import java.util.Objects;

public class UnitProfile {

    private String name;
    private int movement;
    private int toughness;
    private int save;
    private int wounds;
    private int leadership;
    private int objectiveControl;

    protected UnitProfile(String name, int movement, int toughness, int save, int wounds, int leadership
            , int objectiveControl) {
        this.name = name;
        this.movement = movement;
        this.toughness = toughness;
        this.save = save;
        this.wounds = wounds;
        this.leadership = leadership;
        this.objectiveControl = objectiveControl;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected int getMovement() {
        return movement;
    }

    protected void setMovement(int movement) {

        if (movement < 1) {
            throw new IllegalArgumentException("Количество Movement не может быть меньше 1!");
        }

        this.movement = movement;
    }

    protected int getToughness() {
        return toughness;
    }

    protected void setToughness(int toughness) {

        if (toughness < 1) {
            throw new IllegalArgumentException("Количество Toughness не может быть меньше 1!");
        }

        this.toughness = toughness;
    }

    protected int getSave() {
        return save;
    }

    protected void setSave(int save) {

        if (save < 2 || save > 6) {
            throw new IllegalArgumentException("Количество Save не может быть меньше 2 и больше 6!");
        }

        this.save = save;
    }

    protected int getWounds() {
        return wounds;
    }

    protected void setWounds(int wounds) {

        if (wounds < 1) {
            throw new IllegalArgumentException("Количество Wounds не может быть меньше 1!");
        }

        this.wounds = wounds;
    }

    protected int getLeadership() {
        return leadership;
    }

    protected void setLeadership(int leadership) {

        if (leadership < 5 || leadership > 6) {
            throw new IllegalArgumentException("Количество Leadership не может быть меньше 5 и больше 6!");
        }

        this.leadership = leadership;
    }

    protected int getObjectiveControl() {
        return objectiveControl;
    }

    protected void setObjectiveControl(int objectiveControl) {

        if (objectiveControl < 0) {
            throw new IllegalArgumentException("Количество Objective Control не может быть меньше 0!");
        }

        this.objectiveControl = objectiveControl;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", movement=" + movement +
                ", toughness=" + toughness +
                ", save=" + save +
                ", leadership=" + leadership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnitProfile unitProfile = (UnitProfile) o;
        return getMovement() == unitProfile.getMovement() && getToughness() == unitProfile.getToughness()
                && getSave() == unitProfile.getSave() && getLeadership() == unitProfile.getLeadership()
                && Objects.equals(getName(), unitProfile.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMovement(), getToughness(), getSave(), getLeadership());
    }
}
