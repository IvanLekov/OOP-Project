package bg.tu_varna.sit.f24621660.dnd.entities.monster;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.ResourceStat;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;

public class Dragon extends Monster {

    private static final int BASE_STR = 25;
    private static final int BASE_MANA = 25;
    private static final int BASE_HP = 50;
    private static final double BASE_ARMOR = 0.15;
    private static final int LEVEL_BONUS = 10;
    private static final double ARMOR_BONUS = 0.05;
    private static final double MAX_ARMOR = 0.7;



    public Dragon(int mapLevel) {
        super(
                new ResourceStat(BASE_HP + ((mapLevel - 1) * LEVEL_BONUS)),
                new PowerStat(BASE_STR + ((mapLevel - 1) * LEVEL_BONUS)),
                new PowerStat(BASE_MANA + ((mapLevel - 1) * LEVEL_BONUS)),

                new Armor("Dragon gills", calculateArmor(mapLevel))
        );
    }

    private static double calculateArmor(int level) {
        double currentArmor = BASE_ARMOR + ((level - 1) * ARMOR_BONUS);

        return Math.min(currentArmor, MAX_ARMOR);
    }
}