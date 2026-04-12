package bg.tu_varna.sit.f24621660.dnd.entities.hero;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.ResourceStat;
import bg.tu_varna.sit.f24621660.dnd.items.inventory.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.inventory.base.Item;
import bg.tu_varna.sit.f24621660.dnd.items.inventory.base.OffensiveItem;


public abstract class Hero {
    private LevelStat level;
    private ResourceStat health;
    private PowerStat strength;
    private PowerStat mana;
    private OffensiveItem weapon;
    private OffensiveItem spell;
    private DefensiveItem armor;

    public Hero(ResourceStat health, PowerStat strength, PowerStat mana,
                LevelStat level, OffensiveItem weapon, OffensiveItem spell) {
        this.health = health;
        this.strength = strength;
        this.mana = mana;
        this.level = level;
        this.weapon = weapon;
        this.spell = spell;
        this.armor = null;
    }

    public boolean isDead() {
        return this.health.getValue() <= 0;
    }

    public boolean isAlive() {
        return this.health.getValue() > 0;
    }

    public void levelUp(int addStrength, int addMana, int addHealth) {
//        if (addStrength + addMana + addHealth != 30) {
//            throw new IllegalArgumentException("Трябва да разпределите точно 30 точки!");
//        }

        this.level.levelUp();
        this.strength.upgrade(addStrength);
        this.mana.upgrade(addMana);
        this.health.upgrade(addHealth);
    }

    public LevelStat getLevel() {
        return level;
    }

    public ResourceStat getHealth() {
        return health;
    }

    public PowerStat getStrength() {
        return strength;
    }

    public PowerStat getMana() {
        return mana;
    }

    public Item getArmor() {
        return armor;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Item getSpell() {
        return spell;
    }

}
