package bg.tu_varna.sit.f24621660.dnd.entities.hero;

import bg.tu_varna.sit.f24621660.dnd.combat.models.AttackType;
import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Progressable;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers.Resource;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.OffensiveItem;

public class Hero extends Combatant {

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
    public int calculateDamage(AttackType attackType) {
        switch (attackType) {
            case POWER:
                int baseStr = this.getStrength().getValue();
                return (weapon != null) ? weapon.calculateAmpedDamage(baseStr) : baseStr;
            case SPELL:
                int baseMana = this.getMana().getValue();
                return (spell != null) ? spell.calculateAmpedDamage(baseMana) : baseMana;
            default:
                throw new IllegalArgumentException("Unknown attack type: " + attackType);
        }
    }

    @Override
    public void takeDamage(int amount) {
        int finalDamage = (this.armor != null) ? armor.calculateReducedDamage(amount) : amount;
        this.getHealth().deplete(finalDamage);
    }

    public void heal(int amount) {
        this.getHealth().restore(amount);
    }

    public void levelUp(int addStr, int addMana, int addHealth) {
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

    public Progressable getLevel() { return level; }
    public OffensiveItem getWeapon() { return weapon; }
    public DefensiveItem getArmor() { return armor; }
    public OffensiveItem getSpell() { return spell; }
}
