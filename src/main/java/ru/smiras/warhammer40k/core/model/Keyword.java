/**
 * Перечисление всех ключевых слов (keywords) из правил Warhammer 40,000 10-й редакции.
 *
 * Реализует систему ключевых слов: общие (INFANTRY, CHARACTER, FLY), фракционные (ADEPTUS_CUSTODES,
 * WORLD_EATERS), оружия (ASSAULT, DEVASTATING_WOUNDS, LETHAL_HITS) и юнитов (EPIC_HERO, PRIMARCH).
 *
 * Используется в Datasheet (keywords и factionKeywords), WeaponProfile (weaponKeywords),
 * Ability (проверка наличия ключевого слова для активации) и в правилах взаимодействия (например,
 * ключевые слова влияют на модификаторы, способности, стратагемы).
 */

package ru.smiras.warhammer40k.core.model;

public enum Keyword {
    // ОБЩИЕ ДЛЯ ВСЕХ
    INFANTRY,
    CHARACTER,
    EPIC_HERO,
    FLY,
    PRIMARCH,
    ASSAULT,
    PISTOL,
    DEVASTATING_WOUNDS,
    // ДЛЯ ИМПЕРИУМА
    IMPERIUM,
    ADEPTUS_CUSTODES,
    BATTLELINE,
    CUSTODIAN_GUARD,
    TRAJANN_VALORIS,
    // ДЛЯ ХАОСА
    MONSTER,
    DAEMON,
    WORLD_EATERS,
    KHORNE,
    CHAOS,
    ANGRON
}