package bg.tu_varna.sit.f24621660.dnd.items;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.io.loaders.ItemTableLoader;

import java.util.List;
import java.util.Random;

public class ItemTable {
    private List<Item> items;
    private Random random;

    public ItemTable(List<Item> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be null or empty.");
        }
        this.items = items;
        this.random = new Random();
    }

    public Item drawRandomItem() {
        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }

}
