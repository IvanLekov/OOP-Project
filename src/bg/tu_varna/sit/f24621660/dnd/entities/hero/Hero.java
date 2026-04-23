package bg.tu_varna.sit.f24621660.dnd.entities.hero;

import bg.tu_varna.sit.f24621660.dnd.entities.base.Combatant;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Attribute;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.traits.Progressable;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types.Resource;
import bg.tu_varna.sit.f24621660.dnd.items.base.OffensiveItem;


public abstract class Hero extends Combatant {
    private static final double RESTORE_PERCENT = 0.5;

    private final Progressable level;
    private OffensiveItem weapon;
    private OffensiveItem spell;


    protected Hero(Resource health, Attribute strength, Attribute mana, Progressable level,
                   OffensiveItem weapon, OffensiveItem spell) {

        super(health, strength, mana, null);
        this.level = level;
        this.weapon = weapon;
        this.spell = spell;
    }

    public void levelUp(int addStr, int addMana, int addHealth) {

        if (addStr + addMana + addHealth != 30) {
            throw new IllegalArgumentException("The points must be exactly 30!");
        }

        this.level.next();
        this.getStrength().upgrade(addStr);
        this.getMana().upgrade(addMana);
        this.getHealth().upgrade(addHealth);
    }

    @Override
    public int getStrengthAttackDamage() {
        int basePower = this.getStrength().getValue();
        if (this.weapon != null) {
            return this.weapon.calculateAmpedDamage(basePower);
        }
        return basePower;
    }

    @Override
    public int getSpellAttackDamage() {
        int basePower = this.getMana().getValue();
        if (this.spell != null) {
            return this.spell.calculateAmpedDamage(basePower);
        }
        return basePower;
    }

    @Override
    public void onVictory() {
        postBattle();
    }

    private void postBattle() {
        int maxHealth = getHealth().getMaxValue();
        int restoreAmount = (int) (maxHealth * RESTORE_PERCENT);
        this.getHealth().restore(restoreAmount);
    }

    public Progressable getLevel() {
        return level;
    }

    public OffensiveItem getWeapon() {
        return weapon;
    }

    public OffensiveItem getSpell() {
        return spell;
    }
}