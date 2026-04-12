package bg.tu_varna.sit.f24621660.dnd.entities.hero;

import bg.tu_varna.sit.f24621660.dnd.items.inventory.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.inventory.Weapon;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.ResourceStat;

public class Mage extends Hero {

     Mage() {
        super(
                new ResourceStat(50),
                new PowerStat(10),
                new PowerStat(40),
                new LevelStat(1),
                new Weapon("Обикновен меч", 0.20),
                new Spell("Огнена топка", 0.20)
        );
    }
}
