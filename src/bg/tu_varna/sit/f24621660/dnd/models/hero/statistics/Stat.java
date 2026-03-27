package bg.tu_varna.sit.f24621660.dnd.models.hero.statistics;

public abstract class Stat {
    protected int value;

    public Stat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increase(int amount) {
        this.value += amount;
    }
}