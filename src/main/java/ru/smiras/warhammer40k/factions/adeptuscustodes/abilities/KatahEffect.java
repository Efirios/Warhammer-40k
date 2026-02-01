/**
 * Перечисление эффектов способности Martial Ka'tah из кодекса Adeptus Custodes
 * (Warhammer 40,000 10-й редакции).
 *
 * Реализует правила Martial Ka'tah: в начале Command phase игрок выбирает один эффект,
 * который действует до начала следующей Command phase.
 *
 * Возможные эффекты:
 * - NONE — эффект не выбран или сброшен
 * - DACATARAI — +1 к броску на попадание (Hit roll) в ближнем бою
 * - KAPTARIS — +1 к броску на ранение (Wound roll) в ближнем бою
 * - CALISTUS — +1 к броску на урон (Damage roll) в ближнем бою
 *
 * Используется в классе MortialKatah для хранения текущего выбранного эффекта.
 * Позволяет безопасно и читаемо применять модификаторы в modifyHitRoll, modifyWoundRoll,
 * modifyDamageRoll.
 */

package ru.smiras.warhammer40k.factions.adeptuscustodes.abilities;

public enum KatahEffect {
    NONE,
    DACATARAI,
    RENDAX
}
