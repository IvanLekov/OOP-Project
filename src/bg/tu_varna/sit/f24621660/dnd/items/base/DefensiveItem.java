package bg.tu_varna.sit.f24621660.dnd.items.base;

public abstract class DefensiveItem extends Item {
    private final double damageDecrease;

    public DefensiveItem(String name, double damageDecrease) {
        super(name);
        if (damageDecrease < 0.0 || damageDecrease > 1.0) {
            throw new IllegalArgumentException("Percent must be between 0.0 and 1.0");
        }
        this.damageDecrease = damageDecrease;
    }

    public int calculateReducedDamage(int incomingDamage) {
        double reduced = incomingDamage - (incomingDamage * getDamageDecrease());
        return (int) Math.round(reduced);
    }

    public double getDamageDecrease() {
        return damageDecrease;
    }
}