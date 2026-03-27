package bg.tu_varna.sit.f24621660.dnd.models.hero.race;

import bg.tu_varna.sit.f24621660.dnd.models.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;

public class Warrior extends Hero {
    public Warrior() {
        super();
        this.health = new ResourceStat(50);
        this.strength = new PowerStat(40);
        this.mana = new PowerStat(10);
    }
}