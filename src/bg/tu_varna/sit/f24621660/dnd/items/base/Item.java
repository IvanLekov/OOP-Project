package bg.tu_varna.sit.f24621660.dnd.items.base;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
