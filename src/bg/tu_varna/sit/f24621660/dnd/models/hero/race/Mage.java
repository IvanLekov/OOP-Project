package bg.tu_varna.sit.f24621660.dnd.models.hero.race;

import bg.tu_varna.sit.f24621660.dnd.models.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.hero.statistics.ResourceStat;

public class Mage extends Hero {
    public Mage() {
        super();
        this.health = new ResourceStat(50);
        this.strength = new PowerStat(10);
        this.mana = new PowerStat(40);
    }
}
