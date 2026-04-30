package bg.tu_varna.sit.f24621660.dnd.entities.monster;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.PowerStat;
import bg.tu_varna.sit.f24621660.dnd.entities.stats.models.ResourceStat;
import bg.tu_varna.sit.f24621660.dnd.items.base.DefensiveItem;
import bg.tu_varna.sit.f24621660.dnd.items.equipment.Armor;

public class Dragon extends Monster {

    private static final int BASE_STR = 25;
    private static final int BASE_MANA = 25;
    private static final int BASE_HP = 50;
    private static final int START_LEVEL = 1;
    private static final double BASE_ARMOR = 0.15;
    private static final int LEVEL_BONUS = 10;
    private static final double ARMOR_BONUS = 0.05;
    private static final double MAX_ARMOR = 0.7;

    private DefensiveItem armor;


    public Dragon(int mapLevel) {
        super(
                new ResourceStat(BASE_HP + ((mapLevel - START_LEVEL) * LEVEL_BONUS)),
                new PowerStat(BASE_STR + ((mapLevel - START_LEVEL) * LEVEL_BONUS)),
                new PowerStat(BASE_MANA + ((mapLevel - START_LEVEL) * LEVEL_BONUS))
        );

        armor = new Armor("Dragon gills", calculateArmor(mapLevel));
    }

    private double calculateArmor(int level) {
        double currentArmor = BASE_ARMOR + ((level - START_LEVEL) * ARMOR_BONUS);

        return Math.min(currentArmor, MAX_ARMOR);
    }

    @Override
    public void takeDamage(int amount) {
        if (this.armor != null) {
            this.getHealth().deplete(armor.calculateReducedDamage(amount));
        }
        this.getHealth().deplete(amount);
    }

}