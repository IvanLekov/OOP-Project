package bg.tu_varna.sit.f24621660.dnd.entities.hero;

import bg.tu_varna.sit.f24621660.dnd.core.GameState;
import bg.tu_varna.sit.f24621660.dnd.core.states.State;
import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Progressable;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.OffensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;


public abstract class Hero extends Combatant {
    private static final double RESTORE_PERCENT = 0.5;
    private static final int LEVEL_UP_POINTS = 30;

    private final Progressable level;
    private OffensiveItem weapon;
    private OffensiveItem spell;
    private DefensiveItem armor;


    protected Hero(Resource health, Attribute strength, Attribute mana, Progressable level,
                   OffensiveItem weapon, OffensiveItem spell) {
        super(health, strength, mana);
        this.level = level;
        this.weapon = weapon;
        this.spell = spell;
    }

    @Override
    public int getStrengthDamage() {
        int basePower = this.getStrength().getValue();
        if (this.weapon != null) {
            return this.weapon.calculateAmpedDamage(basePower);
        }
        return basePower;
    }

    @Override
    public int getSpellDamage() {
        int basePower = this.getMana().getValue();
        if (this.spell != null) {
            return this.spell.calculateAmpedDamage(basePower);
        }
        return basePower;
    }

    @Override
    public void takeDamage(int amount) {
        int finalDamage = (this.armor != null) ? armor.calculateReducedDamage(amount) : amount;
        this.getHealth().deplete(finalDamage);
    }

    public void handleVictory() {
        int maxHealth = getHealth().getMaxValue();
        int restoreAmount = (int) (maxHealth * RESTORE_PERCENT);
        this.getHealth().restore(restoreAmount);
    }

    public void levelUp(int addStr, int addMana, int addHealth) {

        if (addStr + addMana + addHealth != LEVEL_UP_POINTS) {
            throw new IllegalArgumentException("The points must be 30");
        }

        this.level.next();
        this.getStrength().upgrade(addStr);
        this.getMana().upgrade(addMana);
        this.getHealth().upgrade(addHealth);
    }

    public void equipArmor(DefensiveItem armor) {
        this.armor = armor;
    }

    public void equipWeapon(OffensiveItem weapon) {
        this.weapon = weapon;
    }

    public void equipSpell(OffensiveItem spell) {
        this.spell = spell;
    }


    public Progressable getLevel() {
        return level;
    }

    public OffensiveItem getWeapon() {
        return weapon;
    }

    public DefensiveItem getArmor() {
        return armor;
    }

    public OffensiveItem getSpell() {
        return spell;
    }
}