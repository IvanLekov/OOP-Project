package bg.tu_varna.sit.f24621660.dnd.models.hero.race;

import bg.tu_varna.sit.f24621660.dnd.models.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Item;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Spell;
import bg.tu_varna.sit.f24621660.dnd.models.hero.inventory.Weapon;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.Stat;

public class Human extends Hero {
    public Human() {
        super();
        this.health = new ResourceStat(50);
        this.strength = new PowerStat(30);
        this.mana = new PowerStat(20);
    }
}