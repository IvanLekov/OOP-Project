package bg.tu_varna.sit.f24621660.dnd.items.base;

public abstract class DefensiveItem extends Item {
    private final double damageReduction;

    public DefensiveItem(String name, double damageReduction) {
        super(name);

        if (damageReduction < 0.0 || damageReduction > 1.0) {
            throw new IllegalArgumentException("Percent must be between 0.0 and 1.0");
        }
        this.damageReduction = damageReduction;
    }

    public int calculateReducedDamage(int incomingDamage) {
        double reduced = incomingDamage - (incomingDamage * this.damageReduction);
        return (int) Math.round(reduced);
    }
}