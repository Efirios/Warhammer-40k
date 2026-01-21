/**
 * Перечисление типов оружия в правилах Warhammer 40,000 10-й редакции.
 *
 * Реализует различия между оружием: MELEE (ближний бой — range = 0), RANGED (стрельба — range > 0).
 * Влияет на правила использования (Melee — только в Engagement Range, Ranged — в Shooting phase).
 *
 * Используется в WeaponProfile для указания типа оружия и в AttackContext для определения фазы
 * и правил применения (например, Assault работает при Advance).
 */

package ru.smiras.warhammer40k.core.model;

public enum WeaponType {
    MELEE,
    RANGED
}
