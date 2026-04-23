package bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.traits;

public interface Depletable {
    void deplete(int amount);
    void restore(int amount);
}
