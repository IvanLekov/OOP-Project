package bg.tu_varna.sit.f24621660.dnd.entities.stats.models;

public class LevelUpSession {
    public static final int MAX_POINTS = 30;

    private int pendingStrength = 0;
    private int pendingMana = 0;
    private int pendingHealth = 0;

    public void addStrength(int points) { this.pendingStrength += points; }
    public void addMana(int points) { this.pendingMana += points; }
    public void addHealth(int points) { this.pendingHealth += points; }

    public int getPendingStrength() { return pendingStrength; }
    public int getPendingMana() { return pendingMana; }
    public int getPendingHealth() { return pendingHealth; }

    public int getTotalAllocated() {
        return pendingStrength + pendingMana + pendingHealth;
    }

    public int getRemainingPoints() {
        return MAX_POINTS - getTotalAllocated();
    }
}