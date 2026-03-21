package bg.tu_varna.sit.f24621660.dnd.models.hero.inventory;

public class Spell extends Item {
    private double damageIncrease;

    public Spell(String name, double damageIncrease) {
        super(name);
        this.damageIncrease = damageIncrease;
    }
}
