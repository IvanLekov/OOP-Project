package bg.tu_varna.sit.f24621660.dnd.items;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;
import bg.tu_varna.sit.f24621660.dnd.utills.loaders.ItemLoader;

import java.util.List;
import java.util.Random;

public class ItemTable {
    private List<Item> items;
    private Random random;

    public ItemTable(int mapLevel, ItemLoader itemLoader) {
        this.items = itemLoader.load(mapLevel);
        this.random = new Random();

        if (this.items.isEmpty()) {
            throw new IllegalStateException("No items were loaded for level: " + mapLevel);
        }
    }

    public Item drawRandomItem() {
        int randomIndex = random.nextInt(items.size());
        return items.get(randomIndex);
    }

}
