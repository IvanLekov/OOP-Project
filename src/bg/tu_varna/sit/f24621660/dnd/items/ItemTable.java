package bg.tu_varna.sit.f24621660.dnd.items;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;

import java.util.List;
import java.util.Random;

public class ItemTable {
    private final List<Item> items;
    private final Random random;

    public ItemTable(List<Item> items, Random random) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Item list cannot be null or empty.");
        }
        this.items = List.copyOf(items);
        this.random = random;
    }

    public Item drawRandomItem() {
        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }
}