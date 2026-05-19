package bg.tu_varna.sit.f24621660.dnd.entities.base.contracts;

public interface Damageable {
    void takeDamage(int rawDamage);
    boolean isAlive();
}