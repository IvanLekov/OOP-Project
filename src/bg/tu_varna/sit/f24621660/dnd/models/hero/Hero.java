package bg.tu_varna.sit.f24621660.dnd.models.hero;

import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Item;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Spell;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Weapon;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.Stat;

public abstract class Hero {
    protected Stat level;
    protected Stat health;
    protected Stat strength;
    protected Stat mana;
    protected Item armor;
    protected Item weapon;
    protected Item spell;

    public Hero() {
        this.level = new LevelStat(1);
        this.weapon = new Weapon("Basic Sword", 0.2);
        this.spell = new Spell("Fire Ball", 0.2);
        this.armor = null;
    }

    public Stat getLevel() {
        return level;
    }

    public Stat getHealth() {
        return health;
    }

    public Stat getStrength() {
        return strength;
    }

    public Stat getMana() {
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
