package bg.tu_varna.sit.f24621660.dnd.models.hero.inventory;

public class Weapon extends Item {
    private double damageIncrease;

    public Weapon(String name, double damageIncrease) {
        super(name);
        this.damageIncrease = damageIncrease;
    }
}
