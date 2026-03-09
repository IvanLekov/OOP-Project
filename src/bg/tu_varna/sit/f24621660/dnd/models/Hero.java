package bg.tu_varna.sit.f24621660.dnd.models;

import bg.tu_varna.sit.f24621660.dnd.models.inventory.Armor;
import bg.tu_varna.sit.f24621660.dnd.models.inventory.Spell;
import bg.tu_varna.sit.f24621660.dnd.models.inventory.Weapon;
import bg.tu_varna.sit.f24621660.dnd.models.statistics.LevelStat;
import bg.tu_varna.sit.f24621660.dnd.models.statistics.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.models.statistics.ResourceStat;

public abstract class Hero {
    LevelStat level;
    ResourceStat health;
    PowerStat strength;
    PowerStat mana;
    Armor armor;
    Weapon weapon;
    Spell spell;
}