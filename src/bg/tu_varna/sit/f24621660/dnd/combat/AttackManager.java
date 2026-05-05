package bg.tu_varna.sit.f24621660.dnd.combat;

import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;
import java.util.Random;

public class AttackManager {
    private final Hero hero;
    private final Monster monster;
    private final Random random;

    public AttackManager(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
        this.random = new Random();
    }

    public void processHeroAttack(AttackType attackType) {
        int damage = (attackType == AttackType.POWER) ? hero.getStrengthDamage() : hero.getSpellDamage();
        monster.takeDamage(damage);
    }

    public void processMonsterAttack() {
        boolean usePower = random.nextBoolean();
        int damage = usePower ? monster.getStrengthDamage() : monster.getSpellDamage();
        hero.takeDamage(damage);
    }
}