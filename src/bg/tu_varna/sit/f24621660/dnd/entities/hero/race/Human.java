package bg.tu_varna.sit.f24621660.dnd.entities.hero.race;

import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.ResourceStat;

public class Human extends Hero {

    public Human() {
        super(
                new ResourceStat(50),
                new PowerStat(30),
                new PowerStat(20),
                new LevelStat(1),
                new Weapon("Basic sword", 0.20),
                new Spell("Fire ball", 0.20)
        );
    }
}