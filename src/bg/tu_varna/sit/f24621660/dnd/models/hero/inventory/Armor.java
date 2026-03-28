package bg.tu_varna.sit.f24621660.dnd.models.hero.inventory;

public class Armor extends Item {

    private final double damageDecrease;

    public Armor(String name, double damageDecrease) {
        super(name);

        if (damageDecrease < 0.0 || damageDecrease > 1.0) {
            throw new IllegalArgumentException("Percent must be between 0.0 and 1.0");
        }

        this.damageDecrease = damageDecrease;
    }

    public double getDamageDecrease() {
        return damageDecrease;
    }

    public double calculateReducedDamage(double incomingDamage) {
        return incomingDamage - (incomingDamage * this.getDamageDecrease());
    }

}