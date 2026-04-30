package bg.tu_varna.sit.f24621660.dnd.items.base;

public class OffensiveItem extends Item {
    private final double damageIncrease;

    public OffensiveItem(String name, double damageIncrease) {
        super(name);
        if (damageIncrease < 0.0 || damageIncrease > 1.0) {
            throw new IllegalArgumentException("Percent must be between 0.0 and 1.0");
        }
        this.damageIncrease = damageIncrease;
    }

    public int calculateAmpedDamage(int baseStat) {
        double amped = baseStat + (baseStat * this.damageIncrease);
        return (int) Math.round(amped);
    }
}
