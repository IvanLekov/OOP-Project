package bg.tu_varna.sit.f24621660.dnd.entities.hero;

import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.base.OffensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.ResourceStat;

public class HeroFactory {

    public static Hero createHuman() {
        return new Hero(
                new ResourceStat(50),
                new PowerStat(30),
                new PowerStat(20),
                new LevelStat(1),
                new Weapon("Sword", 0.2),
                new Spell("Fire ball", 0.2)
        );
    }

    public static Hero createMage() {
        return new Hero(
                new ResourceStat(50),
                new PowerStat(10),
                new PowerStat(40),
                new LevelStat(1),
                new Weapon("Sword", 0.2),
                new Spell("Fire ball", 0.2)
        );
    }

    public static Hero createWarrior() {
        return new Hero(
                new ResourceStat(50),
                new PowerStat(40),
                new PowerStat(10),
                new LevelStat(1),
                new Weapon("Sword", 0.2),
                new Spell("Fire ball", 0.2)
        );
    }

    public static Hero recreateHero(int hp, int maxHp, int str, int mana, int levelValue,
                                    OffensiveItem weapon, OffensiveItem spell, DefensiveItem armor) {// Load Game Specific Method
        ResourceStat health = new ResourceStat(maxHp);
        health.deplete(maxHp - hp);

        PowerStat strength = new PowerStat(str);
        PowerStat manaStat = new PowerStat(mana);
        LevelStat level = new LevelStat(levelValue);

        Hero hero = new Hero(health, strength, manaStat, level, weapon, spell);

        if (armor != null) {
            hero.equipArmor(armor);
        }

        return hero;
    }
}