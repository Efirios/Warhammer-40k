package ru.smiras.warhammer40k.core.model;

import java.util.*;

public class Datasheet {

    private final String id;
    private final String baseName;
    private final int baseMovement;
    private final int baseToughness;
    private final int baseSave;
    private final int baseWounds;
    private final int baseLeadership;
    private final int baseObjectiveControl;
    private final boolean hasInvulnerableSave;
    private final int invulnerableSaveValue;
    private final int pointsValues;

    private final Set<Keyword> keywords;
    private final Set<Keyword> factionKeywords;

    private final List<WeaponProfile> weapons;

    protected Datasheet(String id, String baseName, int baseMovement, int baseToughness, int baseSave,
            int baseWounds, int baseLeadership, int baseObjectiveControl, boolean hasInvulnerableSave,
            int invulnerableSaveValue, Set<Keyword> keywords, Set<Keyword> factionKeywords, int pointsValues,
                        List<WeaponProfile> weapons) {

        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id не может быть пустым или содержать пробелы!");
        }
        this.id = id;

        if (baseName == null || baseName.trim().isEmpty()) {
            throw new IllegalArgumentException("baseName не может быть пустым или содержать пробелы!");
        }
        this.baseName = baseName;

        if (baseMovement < 1) {
            throw new IllegalArgumentException("baseMovement не может быть меньше 1!");
        }
        this.baseMovement = baseMovement;

        if (baseToughness < 1) {
            throw new IllegalArgumentException("baseToughness не может быть меньше 1!");
        }
        this.baseToughness = baseToughness;

        if (baseSave < 2 || baseSave > 6) {
            throw new IllegalArgumentException("baseSave не может быть меньше 2 и больше 6!");
        }
        this.baseSave = baseSave;

        if (baseWounds < 1) {
            throw new IllegalArgumentException("baseWounds не может быть меньше 1!");
        }
        this.baseWounds = baseWounds;

        if (baseLeadership < 4 || baseLeadership > 9) {
            throw new IllegalArgumentException("baseLeadership не может быть меньше 4 и больше 9!");
        }
        this.baseLeadership = baseLeadership;

        if (baseObjectiveControl < 0) {
            throw new IllegalArgumentException("Objective Control не может быть меньше 0!");
        }
        this.baseObjectiveControl = baseObjectiveControl;

        this.hasInvulnerableSave = hasInvulnerableSave;

        if (hasInvulnerableSave) {
            if (invulnerableSaveValue < 2 || invulnerableSaveValue > 6) {
                throw new IllegalArgumentException(
                        "Invulnerable save должен быть от 2 до 6 (включительно)");
            }
            this.invulnerableSaveValue = invulnerableSaveValue;
        } else {
            this.invulnerableSaveValue = 7;
        }

        this.keywords = Set.copyOf(keywords);
        this.factionKeywords = Set.copyOf(factionKeywords);

        if (pointsValues < 0) {
            throw new IllegalArgumentException("Points Values не может быть меньше 0!");
        }
        this.pointsValues = pointsValues;

        this.weapons = List.copyOf(weapons);
    }

    public String getId() {
        return id;
    }

    public String getBaseName() {
        return baseName;
    }

    public int getBaseMovement() {
        return baseMovement;
    }

    public int getBaseToughness() {
        return baseToughness;
    }

    public int getBaseSave() {
        return baseSave;
    }

    public int getBaseWounds() {
        return baseWounds;
    }

    public int getBaseLeadership() {
        return baseLeadership;
    }

    public int getBaseObjectiveControl() {
        return baseObjectiveControl;
    }

    public int getInvulnerableSaveValue() {
        return invulnerableSaveValue;
    }

    public Set<Keyword> getKeywords() {
        return Set.copyOf(keywords);
    }

    public boolean hasKeyword(Keyword keyword) {
        return keywords.contains(keyword);
    }

    public Set<Keyword> getFactionKeywords() {
        return Set.copyOf(factionKeywords);
    }

    public boolean hasFactionKeyword(Keyword factionKeyword) {
        return factionKeywords.contains(factionKeyword);
    }

    public boolean hasInvulnerableSave() {
        return hasInvulnerableSave;
    }

    public int getEffectiveInvulnerableSave() {
        return hasInvulnerableSave ? invulnerableSaveValue : 7;
    }

    public int getPointsValues() {
        return pointsValues;
    }

    public List<WeaponProfile> getWeapons() {
        return weapons;
    }

    public boolean hasWeapon(WeaponProfile weapon) {
        return weapons.contains(weapon);
    }


    @Override
    public String toString() {
        return "Datasheet{" +
                "id='" + id + '\'' +
                ", name='" + baseName + '\'' +
                ", M=" + baseMovement +
                ", T=" + baseToughness +
                ", Sv=" + baseSave +
                ", W=" + baseWounds +
                ", Ld=" + baseLeadership +
                ", OC=" + baseObjectiveControl +
                ", Invul=" + (hasInvulnerableSave ? invulnerableSaveValue + "++" : "-") +
                ", KEYWORDS=" + keywords +
                ", FACTION KEYWORDS=" + factionKeywords +
                ", PTS=" + pointsValues +
                ", WEAPONS=" + weapons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Datasheet datasheet = (Datasheet) o;
        return getBaseMovement() == datasheet.getBaseMovement() &&
                getBaseToughness() == datasheet.getBaseToughness() &&
                getBaseSave() == datasheet.getBaseSave() &&
                getBaseWounds() == datasheet.getBaseWounds() &&
                getBaseLeadership() == datasheet.getBaseLeadership() &&
                getBaseObjectiveControl() == datasheet.getBaseObjectiveControl() &&
                hasInvulnerableSave == datasheet.hasInvulnerableSave() &&
                getInvulnerableSaveValue() == datasheet.getInvulnerableSaveValue() &&
                keywords.equals(datasheet.keywords) &&
                factionKeywords.equals(datasheet.factionKeywords) &&
                getPointsValues() == datasheet.getPointsValues() &&
                weapons.equals(datasheet.weapons) &&
                Objects.equals(baseName, datasheet.baseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getBaseName(),
                getBaseMovement(),
                getBaseToughness(),
                getBaseSave(),
                getBaseWounds(),
                getBaseLeadership(),
                getBaseObjectiveControl(),
                hasInvulnerableSave,
                getInvulnerableSaveValue(),
                getKeywords(),
                getFactionKeywords(),
                getPointsValues(),
                getWeapons());
    }
}

/**
 * Что логично делать следующим
 * Создать класс UnitInstance — это будет конкретный юнит в бою
 * (с текущими ранами, моделями, активными способностями и т.д.)
 * Написать простой тестовый класс, который создаёт Траяна, берёт его оружие и делает бросок урона через damageValue.roll()
 * Добавить DiceRoller в core.dice — отдельный класс для всех бросков d6, 2d6 и т.д.
 */