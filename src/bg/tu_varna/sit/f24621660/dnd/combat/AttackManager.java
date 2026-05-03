package bg.tu_varna.sit.f24621660.dnd.combat;

import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.entities.monster.Monster;

public class AttackManager {
    private final Hero hero;
    private final Monster monster;

    public AttackManager(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
    }

    public void processHeroAttack() {
        int damage = hero.getStrengthDamage();//example
        monster.takeDamage(damage);
    }

    public void processMonsterAttack() {
        int damage = monster.getStrengthDamage();//example
        hero.takeDamage(damage);
    }
}
