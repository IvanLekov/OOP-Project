package bg.tu_varna.sit.f24621660.dnd.combat.logic;

import bg.tu_varna.sit.f24621660.dnd.combat.models.AttackType;
import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;
import java.util.Random;

public class AttackManager {
    private final Hero hero;
    private final Monster monster;
    private final Random random;

    public AttackManager(Hero hero, Monster monster, Random random) {
        this.hero = hero;
        this.monster = monster;
        this.random = random;
    }

    public void processHeroAttack(AttackType attackType) {
        int damage = hero.calculateDamage(attackType);
        monster.takeDamage(damage);
    }

    public void processMonsterAttack() {
        AttackType[] availableAttacks = AttackType.values();
        AttackType randomAttack = availableAttacks[random.nextInt(availableAttacks.length)];

        int damage = monster.calculateDamage(randomAttack);
        hero.takeDamage(damage);
    }
}