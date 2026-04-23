package bg.tu_varna.sit.f24621660.dnd.combat;

import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import java.util.Random;

public class CombatManager {
    private final Combatant hero;
    private final Combatant monster;
    private final Random random = new Random();

    private boolean isBattleActive;

    public CombatManager(Combatant hero, Combatant monster) {
        this.hero = hero;
        this.monster = monster;
        this.isBattleActive = true;
    }

    public void heroAttack(String type) {
        if (!isBattleActive) return;

        int damage = type.equalsIgnoreCase("power")
                ? hero.getStrengthAttackDamage()
                : hero.getSpellAttackDamage();

        monster.takeDamage(damage);
        checkCombatStatus();
    }

    public void monsterAttack() {
        if (!isBattleActive) return;

        int damage = random.nextBoolean()
                ? monster.getStrengthAttackDamage()
                : monster.getSpellAttackDamage();

        hero.takeDamage(damage);
        checkCombatStatus();
    }


    private void checkCombatStatus() {
        if (hero.isDead()) {
            isBattleActive = false;
           monster.onVictory();
        } else if (monster.isDead()) {
            isBattleActive = false;
            hero.onVictory();
        }
    }

    public boolean isBattleActive() {
        return isBattleActive;
    }

    public String getCombatStatus() {
        return String.format("Герой HP: %d | Чудовище HP: %d",
                hero.getHealth().getValue(), monster.getHealth().getValue());
    }
}