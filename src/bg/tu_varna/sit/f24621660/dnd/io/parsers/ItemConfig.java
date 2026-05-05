package bg.tu_varna.sit.f24621660.dnd.io.parsers;

import bg.tu_varna.sit.f24621660.dnd.items.base.Item;

public class ItemConfig {
    private final Item item;
    private final int minLevel;
    private final int maxLevel;

    public ItemConfig(Item item, int minLevel, int maxLevel) {
        this.item = item;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public Item getItem() { return item; }
    public int getMinLevel() { return minLevel; }
    public int getMaxLevel() { return maxLevel; }
}