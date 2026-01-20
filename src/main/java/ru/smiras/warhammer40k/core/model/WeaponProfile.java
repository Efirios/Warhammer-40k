package ru.smiras.warhammer40k.core.model;

import java.util.*;

public class WeaponProfile {

    private final String name;
    private final int range;
    private final WeaponType type;
    private final int attacks;
    private final int bsWs;
    private final int strength;
    private final int ap;
    private final DamageValue damageValue;
    private final int pointsValue = 0;

    private final Set<Keyword> weaponKeywords;
    private final Set<Keyword> weaponFactionKeywords;

    public WeaponProfile(String name, int range, WeaponType type, int attacks, int bsWs, int strength, int ap,
                         DamageValue damageValue, Set<Keyword> weaponKeywords, Set<Keyword> weaponFactionKeywords){

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name не может быть пустым или содержать пробелы!");
        }
        this.name = name;

        if (range < 0) {
            throw new IllegalArgumentException("range не может быть меньше 0!");
        }
        this.range = range;

        if (type == WeaponType.MELEE && range != 0) {
            throw new IllegalArgumentException("WeaponType.MELEE не может быть больше 0!");
        }

        if (type == WeaponType.RANGED && range < 1) {
            throw new IllegalArgumentException("WeaponType.RANGED не может быть меньше 1!");
        }
        this.type = type;

        if (attacks < 1) {
            throw new IllegalArgumentException("attacks не может быть меньше 1!");
        }
        this.attacks = attacks;

        if (bsWs < 2 || bsWs > 6) {
            throw new IllegalArgumentException("bsWs должно быть в диапазоне 2-6!");
        }
        this.bsWs = bsWs;

        if (strength < 1) {
            throw new IllegalArgumentException("strength не может быть меньше 1!");
        }
        this.strength = strength;

        if (ap > 0) {
            throw new IllegalArgumentException("ap должно быть меньше 0!");
        }
        this.ap = ap;
        this.damageValue = damageValue;
        this.weaponKeywords = Set.copyOf(weaponKeywords);
        this.weaponFactionKeywords = Set.copyOf(weaponFactionKeywords);
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public WeaponType getType() {
        return type;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getBsWs() {
        return bsWs;
    }

    public int getStrength() {
        return strength;
    }

    public int getAp() {
        return ap;
    }

    public DamageValue getDamageValue() {
        return damageValue;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public Set<Keyword> getWeaponKeywords() {
        return Set.copyOf(weaponKeywords);
    }

    public Set<Keyword> getWeaponFactionKeywords() {
        return Set.copyOf(weaponFactionKeywords);
    }

    public boolean hasWeaponKeyword(Keyword k) {
        return weaponKeywords.contains(k);
    }

    public boolean hasWeaponFactionKeywords(Keyword k) {
        return weaponFactionKeywords.contains(k);
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + name + '\'' +
                ", RANGE=" + range +
                ", TYPE=" + type +
                ", A=" + attacks +
                ", BS/WS=" + bsWs + "+" +
                ", S=" + strength +
                ", AP=" + ap +
                ", D=" + damageValue +
                ", KEYWORDS=" + weaponKeywords +
                ", FACTION KEYWORDS=" + weaponFactionKeywords +
                ", PTS=" + pointsValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WeaponProfile weaponProfile = (WeaponProfile) o;
        return getRange() == weaponProfile.getRange() &&
                getType() == weaponProfile.getType() &&
                getAttacks() == weaponProfile.getAttacks() &&
                getBsWs() == weaponProfile.getBsWs() &&
                getStrength() == weaponProfile.getStrength() &&
                getAp() == weaponProfile.getAp() &&
                damageValue.equals(weaponProfile.damageValue) &&
                weaponKeywords.equals(weaponProfile.weaponKeywords) &&
                weaponFactionKeywords.equals(weaponProfile.weaponFactionKeywords) &&
                getPointsValue() == weaponProfile.getPointsValue() &&
                Objects.equals(name, weaponProfile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName(),
                getRange(),
                getType(),
                getAttacks(),
                getBsWs(),
                getStrength(),
                getAp(),
                getDamageValue(),
                getWeaponKeywords(),
                getWeaponFactionKeywords(),
                getPointsValue());
    }
}
