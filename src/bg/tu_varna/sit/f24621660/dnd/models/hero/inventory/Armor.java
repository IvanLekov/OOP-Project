package bg.tu_varna.sit.f24621660.dnd.models.hero.inventory;

public class Armor extends Item {
    private double damageDecrease;

    public Armor(String name, double damageDecrease) {
        super(name);
        this.damageDecrease = damageDecrease;
    }
}
