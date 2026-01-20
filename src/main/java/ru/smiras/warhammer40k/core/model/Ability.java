package ru.smiras.warhammer40k.core.model;

import ru.smiras.warhammer40k.core.rules.*;
import ru.smiras.warhammer40k.core.util.*;
import ru.smiras.warhammer40k.game.state.*;

interface Ability {

    int UNLIMITED = Integer.MAX_VALUE;

    String getName();

    String getDescription();

    default boolean isActive(UnitInstance unit, PhaseType currentPhase) {
        return true;
    }

    default boolean requiresPlayerChoice() {
        return true;
    }

    default int getMaxUsesPerBattle() {
        return UNLIMITED;
    }

    default int getMaxUsesPerPhase() {
        return UNLIMITED;
    }

    default void modifyHitRoll(UnitInstance unit, AttackContext context) {

    }

    default void modifyWoundRoll(UnitInstance unit, AttackContext context) {

    }

    default void modifySaveRoll(UnitInstance unit, AttackContext context) {

    }

    default void modifyDamageRoll(UnitInstance unit, AttackContext context) {

    }

    default void onCriticalHit(UnitInstance unit, AttackContext context) {

    }

    default void onCriticalWound(UnitInstance unit, AttackContext context) {

    }

    default void onPhaseStart(UnitInstance unit, PhaseType phase){

    }

    default void onPhaseEnd(UnitInstance unit, PhaseType phase){

    }

    default void applyAfterHitRoll(UnitInstance unit, AttackContext context){

    }

    default void applyAfterWoundRoll(UnitInstance unit, AttackContext context) {

    }

    default void applyAfterSaveRoll(UnitInstance unit, AttackContext context){

    }

    default void applyAfterDamageAllocated(UnitInstance unit, AttackContext context){

    }

    default void applyReaction(UnitInstance unit, AttackContext context){

    }
}
