package bg.tu_varna.sit.f24621660.dnd.models.hero.inventory;

public class Weapon extends Item {

    private final double damageIncrease;

    public Weapon(String name, double damageIncrease) {
        super(name);

        if (damageIncrease < 0.0 || damageIncrease > 1.0) {
            throw new IllegalArgumentException("Percent must be between 0.0 and 1.0");
        }

        this.damageIncrease = damageIncrease;
    }

    public double getDamageIncrease() {
        return damageIncrease;
    }

    public double calculateAmpedDamage(double baseAttack) {
        return baseAttack + (baseAttack * this.getDamageIncrease());
    }
}
