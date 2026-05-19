package bg.tu_varna.sit.f24621660.dnd.entities.monster;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.ResourceStat;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;

public class MonsterFactory {

    private static final int BASE_STR = 25;
    private static final int BASE_MANA = 25;
    private static final int BASE_HP = 50;
    private static final int START_LEVEL = 1;
    private static final double BASE_ARMOR = 0.15;
    private static final int LEVEL_BONUS = 10;
    private static final double ARMOR_BONUS = 0.05;
    private static final double MAX_ARMOR = 0.7;

    public static Monster createDragon(int mapLevel) {
        int levelDiff = mapLevel - START_LEVEL;

        int hp = BASE_HP + (levelDiff * LEVEL_BONUS);
        int str = BASE_STR + (levelDiff * LEVEL_BONUS);
        int mana = BASE_MANA + (levelDiff * LEVEL_BONUS);

        double armorValue = Math.min(BASE_ARMOR + (levelDiff * ARMOR_BONUS), MAX_ARMOR);

        return new Monster(
                new ResourceStat(hp),
                new PowerStat(str),
                new PowerStat(mana),
                new Armor("Dragon scales", armorValue)
        );
    }
}