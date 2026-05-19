package bg.tu_varna.sit.f24621660.dnd.entities.base.contracts;

import bg.tu_varna.sit.f24621660.dnd.combat.models.AttackType;

public interface Attacker {
    int calculateDamage(AttackType attackType);
}