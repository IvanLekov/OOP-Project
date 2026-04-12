package bg.tu_varna.sit.f24621660.dnd.entities.hero;

public class PlayerSession {
    private static Hero currentHero = null;

    public static Hero createHero(String raceType) {
        if (currentHero != null) {
            return currentHero;
        }

        switch (raceType.toLowerCase()) {
            case "човек":
                currentHero = new Human();
                break;
            case "маг":
                currentHero = new Mage();
                break;
            case "воин":
                currentHero = new Warrior();
                break;
            default:
                throw new IllegalArgumentException("Невалидна раса!");
        }

        return currentHero;
    }

    public static Hero getHero() {
        if (currentHero == null) {
            throw new IllegalStateException("Героят все още не е създаден!");
        }
        return currentHero;
    }
}
