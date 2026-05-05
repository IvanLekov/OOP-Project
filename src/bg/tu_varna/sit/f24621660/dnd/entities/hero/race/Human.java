package bg.tu_varna.sit.f24621660.dnd.entities.hero.race;

import bg.tu_varna.sit.f24621660.dnd.entities.hero.Hero;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Spell;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Weapon;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.ResourceStat;

public class Human extends Hero {

    private static final int BASE_HEALTH = 50;
    private static final int BASE_STRENGTH = 30;
    private static final int BASE_MANA = 20;
    private static final int START_LEVEL = 1;
    private static final double BASE_DAMAGE_INCREASE = 0.2;

    public Human() {
        super(
                new ResourceStat(BASE_HEALTH),
                new PowerStat(BASE_STRENGTH),
                new PowerStat(BASE_MANA),
                new LevelStat(START_LEVEL),
                new Weapon("Basic sword", BASE_DAMAGE_INCREASE),
                new Spell("Fire ball", BASE_DAMAGE_INCREASE)
        );
    }
}