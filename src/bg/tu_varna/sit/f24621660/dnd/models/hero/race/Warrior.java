package bg.tu_varna.sit.f24621660.dnd.models.hero.race;

import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Item;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Spell;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Weapon;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.Stat;

public class Warrior extends Hero {
    public Warrior() {
        Stat level = new LevelStat(1);
        Stat health = new ResourceStat(50);
        Stat strength = new PowerStat(40);
        Stat mana = new PowerStat(10);
        Item armor = null;
        Item weapon = new Weapon("Basic Sword" , 20);
        Item spell = new Spell( "Fire Ball", 20);
    }
}
